package by.realovka.web.app.controller;

import by.realovka.web.dao.dto.UserDto;
import by.realovka.web.dao.model.Role;
import by.realovka.web.service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@AllArgsConstructor
@RequestMapping(path = "/reg")
public class RegistrationController {

    private final UserService userService;

    @PostMapping
    public ModelAndView registrationUser (@ModelAttribute("nameRegistration") String userName, @ModelAttribute("ageRegistration") int age,
                                          @ModelAttribute("loginRegistration") String login, @ModelAttribute("passwordRegistration") String password,
                                          @ModelAttribute("role") String role,
                                          ModelAndView modelAndView) {
        UserDto user = UserDto.builder()
                .name(userName)
                .age(age)
                .login(login)
                .password(password)
                .role(Role.valueOf(role))
                .build();
        log.info("User = {}", user);
        if (!userService.saveUser(user)) {
            modelAndView.addObject("massageAboutFailRegistration", "Such user already exists!");
            modelAndView.setViewName("registration");
        } else {
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

}