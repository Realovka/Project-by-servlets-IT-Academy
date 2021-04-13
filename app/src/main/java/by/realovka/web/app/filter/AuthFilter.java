package by.realovka.web.app.filter;

import by.realovka.web.dao.model.Admin;
import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;
import by.realovka.web.service.service.UserService;
import by.realovka.web.service.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
@Component
@AllArgsConstructor
public class AuthFilter extends HandlerInterceptorAdapter {

    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String login = req.getParameter("loginAuthorization");
        String password = req.getParameter("passwordAuthorization");
        User auth = userService.identificationUserByLoginAndPassword(login, password);
        if (!auth.equals(new User())) {
            HttpSession session = req.getSession();
            session.setAttribute("userAuth", auth);
            forwardToSomeMainPage(auth, req, resp);
        } else {
            try {
                req.setAttribute("authorizationFail", "Login or password is wrong!");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
        return true;
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