package by.realovka.web.app.interceptor;

import by.realovka.web.dao.model.User;
import by.realovka.web.service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String login = req.getParameter("loginAuthentication");
        String password = req.getParameter("passwordAuthentication");
        User user = userService.identificationUserByLoginAndPassword(login, password);
        if (!user.equals(new User())) {
            req.getSession().setAttribute("userAuth", user);
        } else {
            req.getServletContext().setAttribute("authenticationFail", "Login or password is wrong!");
            resp.sendRedirect("index.jsp");
            return false;
        }
        return true;
    }
}
