package by.realovka.web.dao.connection;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DataSource {
    private static volatile DataSource instance;

    private String url;
    private String user;
    private String password;
    private String driverClassName;

    @SneakyThrows
    private DataSource() {
        Properties property = new Properties();
        property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        url = property.getProperty("url");
        user = property.getProperty("user");
        password = property.getProperty("password");
        driverClassName = property.getProperty("driver.class.name");
    }

    public static DataSource getInstance() {
        if (instance == null) {
            synchronized (DataSource.class) {
                if (instance == null) {
                    instance = new DataSource();
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
