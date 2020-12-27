package by.realovka.web.app.servlet;

import by.realovka.web.dao.model.Student;
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
import java.util.List;

@WebServlet (urlPatterns = "/addTheme")
public class NewThemeAdditionServlet extends HttpServlet {

    private UserService userService;

    private UserRepository userRepository;

    private final static Logger log = LoggerFactory.getLogger(AllStudentsServlet.class);


    @Override
    public void init() throws ServletException {
        userRepository = UserRepositoryImpl.getInstance();
        userService = UserServiceImpl.getInstance(userRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String themeName = req.getParameter("themeName");
        req.getSession().setAttribute("theme",themeName);
        Trainer trainer = (Trainer) req.getSession().getAttribute("userAuth");
        List<Student> students = userService.addThemeToStudents(trainer, themeName);
        log.info("Students = {}", students);
        req.getSession().setAttribute("listStudentsForTrainer", students);
        req.getRequestDispatcher("mainTrainer.jsp").forward(req, resp);
    }
}
