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
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/addSalary")
public class AdditionSalaryToTrainerController {

    private final TrainerService trainerService;

    @PostMapping(path = "/{trainerId}")
    public ModelAndView addSalaryToTrainer(@PathVariable("trainerId") Long trainerId,
                                           HttpServletRequest req, ModelAndView modelAndView) {
        String salary = req.getParameter("salary");
        trainerService.addNewSalaryToTrainer(trainerId, salary);
        List<TrainerDto> trainerDTO = trainerService.getAllTrainers();
        modelAndView.addObject("listTrainers", trainerDTO);
        modelAndView.setViewName("mainAdmin");
        return modelAndView;
    }
}
