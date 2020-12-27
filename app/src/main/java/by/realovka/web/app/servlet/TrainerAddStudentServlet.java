package by.realovka.web.app.servlet;

import by.realovka.web.dao.model.Trainer;
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

@WebServlet(urlPatterns = "/addStudent")
public class TrainerAddStudentServlet extends HttpServlet {

    private UserRepository userRepository;

    private UserService userService;

    private static final  Logger log = LoggerFactory.getLogger(TrainerAddStudentServlet.class);

    @Override
    public void init() throws ServletException {
        userRepository = UserRepositoryImpl.getInstance();
        userService = UserServiceImpl.getInstance(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentName = req.getParameter("student");
        Trainer trainer = (Trainer) req.getSession().getAttribute("userAuth");
        Trainer trainerAndHisStudents = userService.addStudentToTrainer(trainer, studentName);
        log.info("trainerAndHisStudents = {}", trainerAndHisStudents);
        req.getSession().setAttribute("listStudentsForTrainer", trainerAndHisStudents.getStudents());
        resp.sendRedirect("/listAllStudents.jsp");
    }
}
