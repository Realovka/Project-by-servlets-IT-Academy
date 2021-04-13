package by.realovka.web.app.servlet;

import by.realovka.web.dao.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Controller
@RequestMapping(path = "/studentThemesAndMarks")
public class StudentMainServlet {

    @PostMapping
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student auth = (Student) req.getSession().getAttribute("userAuth");
        req.getSession().setAttribute("userAuth", auth);
        log.info("Auth student = {}", auth);
        req.getRequestDispatcher("/mainStudent.jsp").forward(req, resp);
    }
}
