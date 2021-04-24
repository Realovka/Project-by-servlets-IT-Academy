package by.realovka.web.app.controller;

import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(path = "/addGroup")
public class AdditionGroupToTrainerController {

    private final UserService userService;

    @GetMapping
    public ModelAndView addGroupToTrainer(HttpServletRequest req, ModelAndView modelAndView) {
        Trainer auth = (Trainer) req.getSession().getAttribute("userAuth");
        auth = userService.createGroupByTrainer(auth);
        log.info("Trainer after addition group {}", auth);
        List<Student> students = auth.getGroup().getStudents();
        modelAndView.addObject("listStudents", students);
        modelAndView.setViewName("mainTrainer");
        return modelAndView;
    }
}
