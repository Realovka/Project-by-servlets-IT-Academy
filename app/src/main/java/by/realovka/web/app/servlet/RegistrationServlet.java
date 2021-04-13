package by.realovka.web.app.servlet;

import by.realovka.web.dao.dto.UserDTO;
import by.realovka.web.dao.model.Role;
import by.realovka.web.service.service.UserService;
import by.realovka.web.service.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequestMapping(path = "/reg")
public class RegistrationServlet {

    private final UserService userService;

    @Autowired
    public RegistrationServlet(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("nameRegistration");
        int age = Integer.parseInt(req.getParameter("ageRegistration"));
        String login = req.getParameter("loginRegistration");
        String password = req.getParameter("passwordRegistration");
        String role = req.getParameter("role");
        UserDTO userDTO = UserDTO.builder()
                .userName(userName)
                .age(age)
                .login(login)
                .password(password)
                .role(Role.valueOf(role))
                .build();
        log.info("User = {}", userDTO);
        if (!userService.saveUser(userDTO)) {
            req.setAttribute("massageAboutFailRegistration", "Such user already exists!");
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}