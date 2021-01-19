package by.realovka.web.app.servlet;

import by.realovka.web.dao.dto.UserDTO;
import by.realovka.web.dao.model.*;
import by.realovka.web.service.UserService;
import by.realovka.web.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/reg")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("nameRegistration");
        int age = Integer.parseInt(req.getParameter("ageRegistration"));
        String login = req.getParameter("loginRegistration");
        String password = req.getParameter("passwordRegistration");
        String role = req.getParameter("role");
        UserDTO userDTO = new UserDTO(userName, age, login, password, Role.valueOf(role));
        log.info("User = {}", userDTO);
        if (!userService.saveUser(userDTO)) {
            req.setAttribute("massageAboutFailRegistration", "Such user already exists!");
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}