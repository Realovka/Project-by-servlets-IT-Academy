package by.realovka.web.app.servlet;

import by.realovka.web.dao.dao.UserDao;
import by.realovka.web.dao.model.User;
import by.realovka.web.dao.repository.UserRepository;
import by.realovka.web.dao.repository.UserRepositoryImpl;
import by.realovka.web.service.UserService;
import by.realovka.web.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/addStudent")
public class TrainerAddStudentServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long studentId = Long.parseLong(req.getParameter("student"));
        User auth = (User) req.getSession().getAttribute("userAuth");
        User trainerAndHisStudents = userService.addStudentToGroup(auth, studentId);
        log.info("trainerAndHisStudents = {}", trainerAndHisStudents);
        req.getSession().setAttribute("listStudentsOfTrainer", trainerAndHisStudents.getStudents());
        resp.sendRedirect("/listAllStudents.jsp");
    }
}
