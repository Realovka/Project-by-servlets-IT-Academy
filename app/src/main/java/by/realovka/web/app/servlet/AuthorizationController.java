package by.realovka.web.app.servlet;

import by.realovka.web.dao.model.Admin;
import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;
import by.realovka.web.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(path = "/auth")
public class AuthorizationController {

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping("/auth")
    public ModelAndView registration(HttpServletRequest req, HttpServletResponse resp, ModelAndView modelAndView) throws Exception {
        String[] arr = applicationContext.getBeanDefinitionNames();
        String login = req.getParameter("loginAuthorization");
        String password = req.getParameter("passwordAuthorization");
        User auth = userService.identificationUserByLoginAndPassword(login, password);
        if (!auth.equals(new User())) {
            modelAndView.addObject("userAuth", auth);
            modelAndView.addObject("userName", auth.getUserName());
            forwardToSomeMainPage(auth, modelAndView);
        } else {
            modelAndView.addObject("authorizationFail", "Login or password is wrong!");
            modelAndView.setViewName("/index");
        }
        return modelAndView;
    }

    private void forwardToSomeMainPage(User auth, ModelAndView modelAndView) {
        if (auth instanceof Admin) {
            modelAndView.setViewName("/mainAdmin");
        } else {
            if (auth instanceof Trainer) {
                modelAndView.setViewName("/mainTrainer");
            } else {
                if (auth instanceof Student) {
                    modelAndView.setViewName("/mainStudent");
                }
            }
        }
    }
}
