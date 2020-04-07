package company.system.DAO.Entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class DatabaseConnection {
    public static Connection getConnection(){
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
            String sqlConnection = resourceBundle.getString("database.url");
            String user = resourceBundle.getString("database.user");
            String password = resourceBundle.getString("database.password");

            Properties properties = new Properties();
            properties.put("user", user);
            properties.put("password", password);

            return DriverManager.getConnection(sqlConnection, properties);

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (MissingResourceException e){
            e.printStackTrace();
        }
        return  null;
    }
}
