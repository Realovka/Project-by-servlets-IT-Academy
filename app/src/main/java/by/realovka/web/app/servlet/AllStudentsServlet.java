package by.realovka.web.app.servlet;

import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.service.service.UserService;
import by.realovka.web.service.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/allStudents")
public class AllStudentsServlet {

    private final UserService userService;

    @Autowired
    public AllStudentsServlet(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Trainer auth = (Trainer) req.getSession().getAttribute("userAuth");
        List<Student> students = userService.getAllStudents(auth);
        req.getSession().setAttribute("students", students);
        req.getRequestDispatcher("/listAllStudents.jsp").forward(req, resp);
    }
}
