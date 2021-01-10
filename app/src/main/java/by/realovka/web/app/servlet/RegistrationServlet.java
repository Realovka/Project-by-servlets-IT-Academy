package by.realovka.web.app.servlet;

import by.realovka.web.dao.connection.ConnectionToDB;
import by.realovka.web.dao.dao.UserDao;
import by.realovka.web.dao.dto.UserDTO;
import by.realovka.web.dao.model.*;
import by.realovka.web.dao.repository.UserRepository;
import by.realovka.web.dao.repository.UserRepositoryImpl;
import by.realovka.web.service.UserService;
import by.realovka.web.service.UserServiceImpl;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;

@WebServlet(urlPatterns = "/reg")
public class RegistrationServlet extends HttpServlet {

    private UserService userService;
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(RegistrationServlet.class);


    @Override
    public void init() throws ServletException {
        userService = UserServiceImpl.getInstance(userDao);
    }


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
            req.setAttribute("MassageAboutFailRegistration", "Such user already exists!");
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}