package by.realovka.web.app.filter;

import by.realovka.web.dao.model.Admin;
import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;
import by.realovka.web.service.service.UserService;
import by.realovka.web.service.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(value = "/auth", filterName = "authFilter")
public class AuthFilter extends UtilFilter {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String login = req.getParameter("loginAuthorization");
        String password = req.getParameter("passwordAuthorization");
        User auth = userService.identificationUserByLoginAndPassword(login, password);
        req.getSession().setAttribute("userAuth", auth);
        log.info("User from DB = {}", auth);
        if (!auth.equals(new User())) {
            forwardToSomeMainPage(auth, req, res);
        } else {
            try {
                req.setAttribute("authorizationFail", "Login or password is wrong!");
                req.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void forwardToSomeMainPage(User auth, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (auth instanceof Admin) {
                request.getRequestDispatcher("/adminAndTrainers").forward(request, response);
            } else {
                if (auth instanceof Trainer) {
                    request.getRequestDispatcher("/trainerAndHisStudents").forward(request, response);
                } else {
                    if (auth instanceof Student) {
                        request.getRequestDispatcher("mainStudent.jsp").forward(request, response);
                    }
                }
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}