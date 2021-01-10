package by.realovka.web.app.filter;

import by.realovka.web.dao.dao.UserDao;
import by.realovka.web.dao.model.Role;
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
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userService = UserServiceImpl.getInstance(userDao);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String login = req.getParameter("loginAuthorization");
        String password = req.getParameter("passwordAuthorization");
        if (userService.identificationUserByLoginAndPassword(login, password).getUserName() != null) {
            User auth = userService.identificationUserByLoginAndPassword(login, password);
            log.info("UserFromCollection = {}", auth);
            HttpSession session = req.getSession();
            session.setAttribute("userAuth", auth);
            if (auth.getRole().equals(Role.ADMIN)) {
                req.getRequestDispatcher("/mainAdmin.jsp").forward(request, response);
            } else {
                if (auth.getRole().equals(Role.TRAINER)) {
                    req.getRequestDispatcher("/mainTrainer.jsp").forward(request, response);
                } else {
                    if (auth.getRole().equals(Role.STUDENT)) {
                        req.getRequestDispatcher("/mainStudent.jsp").forward(request, response);
                    }
                }
            }
        } else {
            request.setAttribute("MassageAboutFailIdentification", "Identification is fail!");
            req.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }
}