package by.realovka.web.app.config;

//import by.realovka.web.app.interceptor.AuthInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan({"by.realovka.web.app", "by.realovka.web.dao", "by.realovka.web.service"})
@EnableWebMvc
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "by.realovka.web.dao.repository")
public class ApplicationConfig {

    @Value("${driver}")
    private String driver;
    @Value("${url}")
    private String url;
    @Value("${username}")
    private String user;
    @Value("${password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driver);
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(user);
        driverManagerDataSource.setPassword(password);
        return driverManagerDataSource;
    }

//    @Bean
//    public JdbcTemplate jdbcTemplate() {
//        return new JdbcTemplate(dataSource());
//    }


    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(@Autowired ApplicationContext applicationContext) {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//        localSessionFactoryBean.setDataSource(dataSource());
//        localSessionFactoryBean.setPackagesToScan("by.realovka.web.dao.model");
//        localSessionFactoryBean.setHibernateProperties(hibProps());
//        return localSessionFactoryBean;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
//        hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
//        return hibernateTransactionManager;
//    }
//
//    @Bean
//    public Properties hibProps() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "none");
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        properties.setProperty("hibernate.show_sql", "true");
//        return properties;
//    }

}
