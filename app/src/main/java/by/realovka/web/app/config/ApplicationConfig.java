package by.realovka.web.app.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;


@Configuration
@ComponentScan({"by.realovka.web.app", "by.realovka.web.dao", "by.realovka.web.service"})
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = "by.realovka.web.dao.repository",
        entityManagerFactoryRef = "factory",
        transactionManagerRef = "jpaTransactionManager")
public class ApplicationConfig {


    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(@Autowired ApplicationContext applicationContext) {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/");
        internalResourceViewResolver.setSuffix(".html");
        return internalResourceViewResolver;
    }
}