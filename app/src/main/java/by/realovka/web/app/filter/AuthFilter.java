package by.realovka.web.app.filter;

import by.realovka.web.dao.model.Admin;
import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;
import by.realovka.web.dao.repository.UserRepository;
import by.realovka.web.dao.repository.UserRepositoryImpl;
import by.realovka.web.service.UserService;
import by.realovka.web.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = "/auth", filterName = "authFilter")
public class AuthFilter extends UtilFilter {

    private UserService userService;

    private UserRepository userRepository;

    private final static Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userRepository = UserRepositoryImpl.getInstance();
        userService = UserServiceImpl.getInstance(userRepository);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String login = req.getParameter("loginAuthorization");
        String password = req.getParameter("passwordAuthorization");
        User user = userService.getUserByLoginAndPassword(login, password);
        log.info("UserFromCollection = {}", user);
        HttpSession session = req.getSession();
        session.setAttribute("userAuth", user);
        if (user instanceof Admin) {
            req.getRequestDispatcher("/mainAdmin.jsp").forward(request, response);
        } else {
            if (user instanceof Student) {
                req.getRequestDispatcher("/mainStudent.jsp").forward(request, response);
            } else {
                if (user instanceof Trainer) {
                    req.getRequestDispatcher("/mainTrainer.jsp").forward(request, response);
                } else {
                    req.getRequestDispatcher("/index.jsp").forward(request, response);
                    request.setAttribute("authIsInvalid", "Authentication is invalid!"); //TODO
                }
            }
        }
        chain.doFilter(request, response);
    }
}