package by.realovka.web.app.controller;

import by.realovka.web.dao.dto.TrainerDto;
import by.realovka.web.service.service.TrainerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/addTrainer")
public class AdditionNewTrainerForAdminController {

    private final TrainerService trainerService;

    @PostMapping
    public ModelAndView addNewTrainerForAdmin (ModelAndView modelAndView, HttpServletRequest req) {
        String trainerName = req.getParameter("trainerName");
        boolean flag = trainerService.addTrainer(trainerName);
        if (!flag) {
            modelAndView.addObject("messageTrainerExists", "Such trainer already exists");
            modelAndView.setViewName("addTrainer");
            return modelAndView;
        }
        List<TrainerDto> trainerDTO = trainerService.getAllTrainers();
        modelAndView.addObject("listTrainers", trainerDTO);
        modelAndView.setViewName("listAllTrainers");
        return modelAndView;
    }
}
