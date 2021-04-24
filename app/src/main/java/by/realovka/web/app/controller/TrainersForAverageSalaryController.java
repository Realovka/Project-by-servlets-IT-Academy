package by.realovka.web.app.controller;

import by.realovka.web.dao.dto.TrainerDto;
import by.realovka.web.service.service.TrainerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/allTrainersForAverageSalary")
public class TrainersForAverageSalaryController {

    private final TrainerService trainerService;

    @GetMapping
    public ModelAndView getAllTrainerForAverageSalary(ModelAndView modelAndView) {
        List<TrainerDto> trainerDTO = trainerService.getAllTrainers();
        modelAndView.addObject("listTrainers", trainerDTO);
        modelAndView.setViewName("allTrainers");
        return modelAndView;
    }
}
