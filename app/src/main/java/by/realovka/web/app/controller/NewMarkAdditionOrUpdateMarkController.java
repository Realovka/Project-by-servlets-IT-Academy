package by.realovka.web.app.controller;

import by.realovka.web.dao.dto.StudentDto;
import by.realovka.web.dao.dto.TrainerDto;
import by.realovka.web.service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping(path = "/addOrUpdateMark")
public class NewMarkAdditionOrUpdateMarkController {

    private final UserService userService;

    @PostMapping(path = "/{themeId}")
    public ModelAndView addOrUpdateMark(@PathVariable("themeId") Long themeId, @ModelAttribute("mark") String mark, HttpServletRequest req,
                                        ModelAndView modelAndView) {
        TrainerDto auth = (TrainerDto) req.getSession().getAttribute("userAuth");
        List<StudentDto> trainerStudents = auth.getGroup().getStudents();
        modelAndView.addObject("listStudents", trainerStudents);
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
        TrainerDto authUser = userService.addOrUpdateOrDeleteMark(auth, themeId, markParse);
        req.getSession().setAttribute("userAuth", authUser);
        trainerStudents = authUser.getGroup().getStudents();
        modelAndView.addObject("listStudents", trainerStudents);
        modelAndView.setViewName("mainTrainer");
        return modelAndView;
    }
}
