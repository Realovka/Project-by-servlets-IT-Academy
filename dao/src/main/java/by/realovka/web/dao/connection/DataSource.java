package by.realovka.web.dao.connection;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionToDB {
    private static volatile ConnectionToDB instance;

    private String url;
    private String user;
    private String password;
    private String driverClassName;

    @SneakyThrows
    private ConnectionToDB() {
        Properties property = new Properties();
        property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        url = property.getProperty("url");
        user = property.getProperty("user");
        password = property.getProperty("password");
        driverClassName = property.getProperty("driver.class.name");
    }

    public static ConnectionToDB getInstance() {
        if (instance == null) {
            synchronized (ConnectionToDB.class) {
                if (instance == null) {
                    instance = new ConnectionToDB();
                }
            }
        }
        return instance;
    }


    @SneakyThrows
    public Connection getConnection() {
        Class.forName(driverClassName);
        return DriverManager.getConnection(url, user, password);
    }
}
