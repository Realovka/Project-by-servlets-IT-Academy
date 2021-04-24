package by.realovka.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/getAdminMainPage")
public class AdminMainPageController {

    @GetMapping
    public ModelAndView getAdminMainPage(ModelAndView modelAndView) {
        modelAndView.setViewName("mainAdmin");
        return modelAndView;
    }
}
