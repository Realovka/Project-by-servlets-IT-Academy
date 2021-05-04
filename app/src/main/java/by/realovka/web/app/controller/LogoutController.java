package by.realovka.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/logout")
public class LogoutController {

    @GetMapping
    public ModelAndView logout(HttpSession session, ModelAndView modelAndView) {
//        session.invalidate();
//        modelAndView.setViewName("index.html");
        return modelAndView;
    }
}
