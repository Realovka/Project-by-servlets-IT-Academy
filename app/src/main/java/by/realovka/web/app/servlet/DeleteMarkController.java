package by.realovka.web.app.servlet;

import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.service.service.UserService;
import by.realovka.web.service.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping(path = "/deleteMark")
public class DeleteMarkController {

    private final UserService userService;

    @GetMapping(path = "/{themeId}")
    public ModelAndView deleteMark (@PathVariable("themeId") Long themeId, HttpSession session, ModelAndView modelAndView) throws ServletException, IOException {
        Trainer trainer = (Trainer) session.getAttribute("userAuth");
        trainer = userService.addOrUpdateOrDeleteMark(trainer, themeId, 0);
        List<Student> students = trainer.getGroup().getStudents();
        log.info("Trainer and his students after delete mark = {}", themeId);
        modelAndView.addObject("listStudents", students);
        modelAndView.setViewName("mainTrainer");
        return modelAndView;
    }
}
