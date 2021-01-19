package by.realovka.web.app.servlet;

import by.realovka.web.dao.model.User;
import by.realovka.web.service.UserService;
import by.realovka.web.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/allStudents")
public class AllStudentsServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User auth = (User) req.getSession().getAttribute("userAuth");
        List<User> students = userService.getAllStudents();
        req.getSession().setAttribute("students", students);
        List<User> trainerStudents = auth.getStudents();
        req.getSession().setAttribute("listStudentsOfTrainer", trainerStudents);
        req.getRequestDispatcher("/listAllStudents.jsp").forward(req, resp);
    }
}
