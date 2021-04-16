package by.realovka.web.app.servlet;

import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.service.service.UserService;
import by.realovka.web.service.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping(path = "/addOrUpdateMark")
public class NewMarkAdditionOrUpdateMarkController {

    private final UserService userService;

    @PostMapping(path = "/{themeId}")
    public ModelAndView addOrUpdateMark(@PathVariable("themeId") Long themeId, @ModelAttribute("mark") String mark, HttpSession session,
                                        ModelAndView modelAndView) {
        Trainer auth = (Trainer) session.getAttribute("userAuth");
        Trainer trainer = userService.getStudentsWithTrainerThemes(auth);
        modelAndView.addObject("listStudents", trainer.getGroup().getStudents());
        Integer markParse = 0;
        try {
            markParse = Integer.parseInt(mark);
        } catch (NumberFormatException e) {
            modelAndView.addObject("massageFormatOfMarkIsWrong", "Format of mark is wrong!");
            modelAndView.setViewName("mainTrainer");
            return modelAndView;
        }
        if (markParse > 100 || markParse < 0) {
            modelAndView.addObject("massageFormatOfMarkIsWrong", "You can't enter a mark greater than 100 or less than 0");
            modelAndView.setViewName("mainTrainer");
            return modelAndView;
        }
        Trainer authUser = userService.addOrUpdateOrDeleteMark(auth, themeId, markParse);
        List<Student> trainerStudents = authUser.getGroup().getStudents();
        modelAndView.addObject("listStudents", trainerStudents);
        modelAndView.setViewName("mainTrainer");
        return modelAndView;
    }
}
