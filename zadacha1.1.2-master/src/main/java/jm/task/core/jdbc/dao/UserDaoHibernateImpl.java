package jm.task.core.jdbc.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnect;

public class UserDaoHibernateImpl implements UserDao {
    private Connection connection;
    private Statement statement;

    public UserDaoHibernateImpl() {
        try {
            connection = Util.connectDatabase();
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE users " +
                "(Id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "Name VARCHAR(20) NOT NULL, " +
                "lastName VARCHAR(20) NOT NULL, " +
                "Age INTEGER NOT NULL )";
        try {
            statement.executeUpdate(sqlCommand);
        } catch (MySQLSyntaxErrorException ignored) {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            statement.executeUpdate("DROP TABLE users");
        } catch (MySQLSyntaxErrorException ignored) {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        EntityManager em = getConnect();
        em.getTransaction().begin();
        User user = new User(name, lastName, age);
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        EntityManager em = getConnect();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager em = getConnect();
        em.getTransaction().begin();
        TypedQuery<User> query = em.createQuery("SELECT b FROM User b", User.class);
        List<User> users = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        EntityManager em = getConnect();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM User ").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
