package by.realovka.web.app.servlet;

import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.service.service.UserService;
import by.realovka.web.service.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
@RequestMapping(path = "/deleteMark")
public class DeleteMarkServlet  {

    private final UserService userService;

    @Autowired
    public DeleteMarkServlet(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String themeId = req.getParameter("themeId");
        Trainer trainer = (Trainer) req.getSession().getAttribute("userAuth");
        trainer = userService.addOrUpdateOrDeleteMark(trainer, themeId, 0);
        List<Student> students = trainer.getGroup().getStudents();
        log.info("Trainer and his students after delete mark = {}", themeId);
        req.getSession().setAttribute("userAuth", trainer);
        req.getSession().setAttribute("listStudents", students);
        req.getRequestDispatcher("mainTrainer.jsp").forward(req, resp);
    }
}
