package by.realovka.web.app.controller;

import by.realovka.web.dao.dto.TrainerDto;
import by.realovka.web.service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(path = "/addGroup")
public class AdditionGroupToTrainerController {

    private final UserService userService;

    @GetMapping
    public ModelAndView addGroupToTrainer(HttpServletRequest req, ModelAndView modelAndView) {
        TrainerDto auth = (TrainerDto) req.getSession().getAttribute("userAuth");
        auth = userService.createGroupByTrainer(auth);
        req.getSession().setAttribute("userAuth", auth);
        log.info("Trainer after addition group {}", auth);
        modelAndView.setViewName("mainTrainer");
        return modelAndView;
    }
}
