package by.realovka.web.app.config;

import by.realovka.web.app.filter.AuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class ControllerInterceptorConfig implements WebMvcConfigurer {

    private final AuthFilter filter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(filter);
    }
}
