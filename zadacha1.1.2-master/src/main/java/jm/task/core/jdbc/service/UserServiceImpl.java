package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDaoHibernateImpl userDaoHibernateser;

    public UserServiceImpl() {
        userDaoHibernateser = new UserDaoHibernateImpl();
    }

    public void createUsersTable() {
        userDaoHibernateser.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoHibernateser.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernateser.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoHibernateser.removeUserById(id);

    }

    public List<User> getAllUsers() {
        return userDaoHibernateser.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoHibernateser.cleanUsersTable();
    }
}
