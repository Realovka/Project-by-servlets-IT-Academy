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
import java.math.BigDecimal;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/calculationAverageSalary")
public class AverageSalaryController {

    private final TrainerService trainerService;

    @PostMapping(path = "/{trainerId}")
    public ModelAndView getAverageSalary(@PathVariable("trainerId") Long trainerId,
                                         HttpServletRequest req, ModelAndView modelAndView) {
        Integer finishMonthsNumberParse;
        String finishMonthsNumber = req.getParameter("finishMonthsNumber");
        try {
           finishMonthsNumberParse = Integer.parseInt(finishMonthsNumber);
        } catch (NumberFormatException e) {
            modelAndView.addObject("wrongEnterNumberMonths", "Format of months is wrong");
            modelAndView.setViewName("monthsNumber");
            return modelAndView;
        }
        TrainerDto trainerDTO = trainerService.getAverageSalary(trainerId, finishMonthsNumberParse);
        if(trainerDTO.getAverageSalary().compareTo(new BigDecimal(0)) == 0) {
            modelAndView.addObject("tooManyMonths", "This trainer has not worked so many months!");
            modelAndView.setViewName("monthsNumber");
            return modelAndView;
        }
        modelAndView.addObject("trainerWithHisAverageSalary", trainerDTO);
        modelAndView.setViewName("answerAverageSalary");
        return modelAndView;
    }
}
