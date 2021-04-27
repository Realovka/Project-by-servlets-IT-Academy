package by.realovka.web.app.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan({"by.realovka.web.app", "by.realovka.web.dao", "by.realovka.web.service"})
@EnableWebMvc
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "by.realovka.web.dao.repository", entityManagerFactoryRef = "factory", transactionManagerRef = "jpaTransactionManager")
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

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }


    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(@Autowired ApplicationContext applicationContext) {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean factory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPersistenceUnitName("jpa-unit");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setPackagesToScan("by.realovka.web.model");
        emf.setJpaProperties(jpaProperties());
        return emf;
    }

    @Bean
    public Properties jpaProperties() {
        Properties props = new Properties();
        props.setProperty("connection.pool_size", "20");
        props.setProperty("dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        props.setProperty("show_sql", "true");
        props.setProperty("current_session_context_class", "thread");
        props.setProperty("hbm2ddl.auto", "none");
        props.setProperty("hibernate.dbcp.initialSize", "5");
        props.setProperty("hibernate.dbcp.maxTotal", "20");
        props.setProperty("hibernate.dbcp.maxIdle", "10");
        props.setProperty("hibernate.dbcp.minIdle", "5");
        props.setProperty("hibernate.dbcp.maxWaitMillis", "-1");
        props.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        return props;
    }

    @Bean
    public JpaTransactionManager jpaTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(factory().getObject());
        return jpaTransactionManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate() {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(jpaTransactionManager());
        return transactionTemplate;
    }
}
