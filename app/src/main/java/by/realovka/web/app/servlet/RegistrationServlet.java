package by.realovka.web.app.servlet;

import by.realovka.web.dao.model.Admin;
import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;
import by.realovka.web.dao.repository.UserRepository;
import by.realovka.web.dao.repository.UserRepositoryImpl;
import by.realovka.web.service.UserService;
import by.realovka.web.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/reg")
public class RegistrationServlet extends HttpServlet {

    private UserService userService;

    private UserRepository userRepository;

    private final static Logger log = LoggerFactory.getLogger(RegistrationServlet.class);


    @Override
    public void init() throws ServletException {
        userRepository = UserRepositoryImpl.getInstance();
        userService = UserServiceImpl.getInstance(userRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("nameRegistration");
        int age = Integer.parseInt(req.getParameter("ageRegistration"));
        String login = req.getParameter("loginRegistration");
        String password = req.getParameter("passwordRegistration");
        String role = req.getParameter("role");
        User user = createUser(role);
        setFieldsToUser(user, userName, age, login, password);
        log.info("User = {}", user);
        userService.saveUser(user);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private User createUser(String role) {
        User user = null;
        switch (role) {
            case "Admin":
                user = new Admin();
                break;
            case "Trainer":
                user = new Trainer();
                break;
            case "Student":
                user = new Student();
        }
        return user;
    }

    private User setFieldsToUser(User user, String userName, int age, String login, String password) {
        user.setUserName(userName);
        user.setAge(age);
        user.setLogin(login);
        user.setPassword(password);
        return user;
    }

}