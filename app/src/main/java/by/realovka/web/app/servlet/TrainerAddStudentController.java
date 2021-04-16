package by.realovka.web.app.servlet;

import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping(path = "/addStudent")
public class TrainerAddStudentController {

    private final UserService userService;

    @GetMapping
    public ModelAndView getAllStudent(HttpSession session, ModelAndView modelAndView) {
        Trainer auth = (Trainer) session.getAttribute("userAuth");
        List<Student> students = userService.getAllStudentsWithoutTrainerStudents(auth);
        modelAndView.addObject("students", students);
        modelAndView.setViewName("listAllStudents");
        return modelAndView;
    }

    @GetMapping(path = "/{studentId}")
    public ModelAndView addStudentToTrainer(@PathVariable("studentId") Long studentId, HttpSession session, ModelAndView modelAndView) {
        Trainer auth = (Trainer) session.getAttribute("userAuth");
        auth = userService.addStudentToGroup(auth, studentId);
        List<Student> students = auth.getGroup().getStudents();
        modelAndView.addObject("students", students);
        log.info("trainerAndHisStudents = {}", auth);
        modelAndView.setViewName("listAllStudents");
        return modelAndView;
    }
}
