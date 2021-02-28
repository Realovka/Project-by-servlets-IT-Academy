package by.realovka.web.app.servlet;

import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.service.service.UserService;
import by.realovka.web.service.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet(urlPatterns = "/addStudent")
public class TrainerAddStudentServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long studentId = Long.parseLong(req.getParameter("studentId"));
        Trainer auth = (Trainer) req.getSession().getAttribute("userAuth");
        auth = userService.addStudentToGroup(auth, studentId);
        List<Student> students = auth.getGroup().getStudents();
        req.getSession().setAttribute("listStudents", students);
        log.info("trainerAndHisStudents = {}", auth);
        req.getRequestDispatcher("/listAllStudents.jsp").forward(req, resp);

    }
}
