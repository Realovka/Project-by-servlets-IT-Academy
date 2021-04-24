package by.realovka.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/choiceTrainer")
public class TrainerSalaryController {

    @GetMapping(path = "/{trainerId}")
    public ModelAndView choiceTrainer(@PathVariable("trainerId") Long trainerId, ModelAndView modelAndView) {
        modelAndView.addObject("trainerId", trainerId);
        modelAndView.setViewName("addSalary");
        return modelAndView;
    }
}
