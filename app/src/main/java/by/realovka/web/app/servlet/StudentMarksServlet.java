package by.realovka.web.app.servlet;

import by.realovka.web.dao.model.Student;
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

@WebServlet(urlPatterns = "/marks")
public class StudentMarksServlet extends HttpServlet {
    private UserRepository userRepository;

    private UserService userService;

    Logger log = LoggerFactory.getLogger(TrainerAddStudentServlet.class);

    @Override
    public void init() throws ServletException {
        userRepository = UserRepositoryImpl.getInstance();
        userService = UserServiceImpl.getInstance(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student user = (Student) req.getSession().getAttribute("userAuth");
        Student student = userService.getStudentByLogin(user.getLogin());
        log.info("student = {}", student);
        req.getSession().setAttribute("studentTheme", student.getThemes());
        req.getRequestDispatcher("/mainStudent.jsp").forward(req, resp);
    }
}
