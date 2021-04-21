package by.realovka.web.app.controller;

import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/trainerAndHisStudents")
public class TrainerMainPageController {

    private final UserService userService;

    @GetMapping
    public ModelAndView getMainTrainerPage(ModelAndView modelAndView, HttpSession session) {
        Trainer auth = (Trainer) session.getAttribute("userAuth");
        Trainer trainer = userService.getById(auth.getId());
        if (trainer.getGroup() != null) {
            List<Student> students = trainer.getGroup().getStudents();
            modelAndView.addObject("listStudents", students);
        }
        modelAndView.setViewName("mainTrainer");
        return modelAndView;
    }
}
