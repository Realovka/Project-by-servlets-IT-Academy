package by.realovka.web.app.filter;

import by.realovka.web.dao.model.Role;
import by.realovka.web.service.UserService;
import by.realovka.web.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebFilter(value = "/auth", filterName = "authFilter")
public class AuthFilter extends UtilFilter {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String login = req.getParameter("loginAuthorization");
        String password = req.getParameter("passwordAuthorization");
        userService.identificationUserByLoginAndPassword(login, password).ifPresentOrElse(auth -> {
                    log.info("User from DB = {}", auth);
                    HttpSession session = req.getSession();
                    session.setAttribute("userAuth", auth);
                    try {
                        if (auth.getRole().equals(Role.ADMIN)) {
                            request.getRequestDispatcher("/mainAdmin.jsp").forward(request, response);
                        } else {
                            if (auth.getRole().equals(Role.TRAINER)) {
                                req.getRequestDispatcher("/trainerAndHisStudents").forward(request, response);
                            } else {
                                if (auth.getRole().equals(Role.STUDENT)) {
                                   req.getRequestDispatcher("/studentThemesAndMarks").forward(request, response);
                                }
                            }
                        }
                    } catch (ServletException | IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        req.setAttribute("authorizationFail", "Login or password is wrong!");
                        req.getRequestDispatcher("/index.jsp").forward(request, response);
                    } catch (ServletException | IOException e) {
                        e.printStackTrace();
                    }
                });

    }

}