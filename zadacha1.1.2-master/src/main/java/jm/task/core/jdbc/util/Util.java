package jm.task.core.jdbc.util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("users");

    public static Connection connectDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=Europe/Moscow&useSSL=false",
                "root", "7373221");
    }
    public static EntityManager getConnect(){
        return emf.createEntityManager();
    }

}


