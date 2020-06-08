package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;

public class DatabaseConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        try
        {
            String userName = "root";
            String password = "admin";
            String connectionUrl = "jdbc:mysql://localhost:3306/Servlets?verifyServerCertificate=false&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            return DriverManager.getConnection(connectionUrl, userName, password);
        }
        catch (SQLException e){
           System.out.println("Не конектится" + e);
        }
        catch (MissingResourceException e){
            System.out.println("Не конектится" + e);
        }
        return  null;
    }
}

