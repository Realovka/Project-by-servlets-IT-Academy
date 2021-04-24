package by.realovka.web.app.controller;

import by.realovka.web.dao.dto.TrainerDto;
import by.realovka.web.service.service.TrainerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/calculationAverageSalary")
public class AverageSalaryController {

    private final TrainerService trainerService;

    @PostMapping(path = "/{trainerId}")
    public ModelAndView getAverageSalary(@PathVariable("trainerId") Long trainerId,
                                         HttpServletRequest req, ModelAndView modelAndView) {
        String finishMonthsNumber = req.getParameter("finishMonthsNumber");
        TrainerDto trainerDTO = trainerService.getAverageSalary(trainerId, finishMonthsNumber);
        modelAndView.addObject("trainerWithHisAverageSalary", trainerDTO);
        modelAndView.setViewName("answerAverageSalary");
        return modelAndView;
    }
}
