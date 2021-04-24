package by.realovka.web.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/choiceTrainerGetAverageSalary")
public class MonthsNumberController {

    @GetMapping(path = "/{trainerId}")
    public ModelAndView addMonthsForAverageSalary(@PathVariable("trainerId") Long trainerId, ModelAndView modelAndView) {
        modelAndView.addObject("trainerId", trainerId);
        modelAndView.setViewName("monthsNumber");
        return modelAndView;
    }
}
