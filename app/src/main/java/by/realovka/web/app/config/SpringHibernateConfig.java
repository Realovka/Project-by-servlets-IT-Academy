package by.realovka.web.app.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@AllArgsConstructor
@Import(SpringJdbcTemplate.class)
public class SpringHibernateConfig {

    private final DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean factory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPersistenceUnitName("jpa-unit");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setPackagesToScan("by.realovka.web.dao.model");
        emf.setJpaProperties(jpaProperties());
        return emf;
    }

    @Bean
    public Properties jpaProperties() {
        Properties props = new Properties();
        props.setProperty("connection.pool_size", "20");
        props.setProperty("dialect", "org.hibernate.dialect.PostgreSQLDialect");
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

}
