package by.realovka.web.app.config;
//
//import by.realovka.web.app.filter.AuthFilter;
//import by.realovka.web.app.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebApplicationConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
//    @Autowired
//    private ApplicationContext applicationContext;
//    private UserService userService =  applicationContext.getBean(UserService.class);

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ApplicationConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

//    @Override
//    protected Filter[] getServletFilters() {
//        return new Filter[]{new AuthFilter()};
//    }

}
