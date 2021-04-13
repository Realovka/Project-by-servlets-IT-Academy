package by.realovka.web.app.servlet;

import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.service.service.UserService;
import by.realovka.web.service.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(path = "/addTheme")
public class NewThemeAdditionServlet  {

    private final UserService userService;

    @Autowired
    public NewThemeAdditionServlet(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Trainer auth = (Trainer) req.getSession().getAttribute("userAuth");
        String themeName = req.getParameter("themeName");
        req.getSession().setAttribute("theme", themeName);
        auth = userService.getTrainerAndHisStudentsAfterAddTheme(auth, themeName);
        List<Student> students = auth.getGroup().getStudents();
        req.getSession().setAttribute("listStudents", students);
        log.info("Trainer after addition theme {}", auth);
        req.getRequestDispatcher("mainTrainer.jsp").forward(req, resp);
    }
}
