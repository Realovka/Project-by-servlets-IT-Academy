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
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
//@Slf4j
//@AllArgsConstructor
//@Controller
//@RequestMapping(path = "/deleteMark")
//public class DeleteMarkController {
//
//    private final UserService userService;
//
//    @GetMapping(path = "/{themeId}")
//    public ModelAndView deleteMark (@PathVariable("themeId") Long themeId, HttpSession session, ModelAndView modelAndView) {
//        TrainerDto trainer = (TrainerDto) session.getAttribute("userAuth");
//        trainer = userService.addOrUpdateOrDeleteMark(trainer, themeId, 0);
//        List<StudentDto> students = trainer.getGroup().getStudents();
//        log.info("Trainer and his students after delete mark = {}", themeId);
//        modelAndView.addObject("listStudents", students);
//        modelAndView.setViewName("mainTrainer");
//        return modelAndView;
//    }
//}
