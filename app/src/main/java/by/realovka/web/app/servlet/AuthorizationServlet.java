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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth")
public class AuthorizationServlet extends HttpServlet {

    private UserService userService;

    private UserRepository userRepository;

    private final static Logger log = LoggerFactory.getLogger(AuthorizationServlet.class);


    @Override
    public void init() throws ServletException {
        userRepository = UserRepositoryImpl.getInstance();
        userService = UserServiceImpl.getInstance(userRepository);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("loginAuthorization");
        String password = req.getParameter("passwordAuthorization");
        User user = userService.getUserByLoginAndPassword(login, password);
        log.info("UserFromCollection = {}", user);
        HttpSession session = req.getSession();
        session.setAttribute("userAuth", user);
        if(user.getClass().equals(Student.class)){
            req.getRequestDispatcher("/mainStudent.jsp").forward(req, resp);
        } else if(user.getClass().equals(Trainer.class)){
            req.getRequestDispatcher("/mainTrainer.jsp").forward(req,resp);
        } else  if(user.getClass().equals(Admin.class)){
            req.getRequestDispatcher("/mainAdmin.jsp").forward(req,resp);
        }
    }
}
