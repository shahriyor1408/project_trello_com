package uz.meta.config;

import uz.meta.property.Property;
import uz.meta.property.Property;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConfigurer {
    private static Connection connection;

    private DbConfigurer() {
    }


    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(
                        Property.getProperty("jdbc.database.url"),
                        Property.getProperty("jdbc.database.username"),
                        Property.getProperty("jdbc.database.password")
                );
//                connection.setAutoCommit(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }



}
