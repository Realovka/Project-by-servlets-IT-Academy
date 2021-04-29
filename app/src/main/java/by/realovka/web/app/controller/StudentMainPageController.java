package by.realovka.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/getMainStudentPage")
public class StudentMainPageController {

    @GetMapping
    public ModelAndView getStudentMainPage(ModelAndView modelAndView) {
        modelAndView.setViewName("mainStudent");
        return modelAndView;
    }
}
