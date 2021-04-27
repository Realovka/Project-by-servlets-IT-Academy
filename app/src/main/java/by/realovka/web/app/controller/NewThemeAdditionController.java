//package by.realovka.web.app.controller;
//
//import by.realovka.web.dao.dto.StudentDto;
//import by.realovka.web.dao.dto.TrainerDto;
//import by.realovka.web.dao.model.Student;
//import by.realovka.web.dao.model.Trainer;
//import by.realovka.web.service.service.UserService;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//@Slf4j
//@AllArgsConstructor
//@Controller
//@RequestMapping(path = "/addTheme")
//public class NewThemeAdditionController {
//
//    private final UserService userService;
//
//    @GetMapping
//    public ModelAndView getPageForAdditionNewTheme(ModelAndView modelAndView) {
//        modelAndView.setViewName("addTheme");
//        return modelAndView;
//    }
//
//    @PostMapping
//    public ModelAndView addNewTheme(ModelAndView modelAndView, HttpServletRequest req) {
//        String themeName = req.getParameter("themeName");
//        TrainerDto auth = (TrainerDto) req.getSession().getAttribute("userAuth");
//        auth = userService.getTrainerAndHisStudentsAfterAddTheme(auth, themeName);
//        List<StudentDto> students = auth.getGroup().getStudents();
//        modelAndView.addObject("listStudents", students);
//        log.info("Trainer after addition theme {}", auth);
//        modelAndView.setViewName("mainTrainer");
//        return modelAndView;
//    }
//}
