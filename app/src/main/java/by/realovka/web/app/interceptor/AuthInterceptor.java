//package by.realovka.web.app.interceptor;
//
//import by.realovka.web.dao.dto.UserDto;
//import by.realovka.web.dao.model.Admin;
//import by.realovka.web.dao.model.Student;
//import by.realovka.web.dao.model.Trainer;
//import by.realovka.web.dao.model.User;
//import by.realovka.web.service.service.UserService;
//import lombok.AllArgsConstructor;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Optional;
//
//@Component
//@AllArgsConstructor
//public class AuthInterceptor extends HandlerInterceptorAdapter {
//
//    private UserService userService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
//        String login = req.getParameter("loginAuthorization");
//        String password = req.getParameter("passwordAuthorization");
//        User user = userService.identificationUserByLoginAndPassword(login, password);
//        if (!user.equals(new User())) {
//            req.getSession().setAttribute("userAuth", user);
//        } else {
//            req.getServletContext().setAttribute("authorizationFail", "Login or password is wrong!");
//            resp.sendRedirect("index.html");
//            return false;
//        }
//        return true;
//    }
//}
