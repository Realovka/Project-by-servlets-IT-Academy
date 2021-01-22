package by.realovka.web.app.servlet;

import by.realovka.web.dao.model.User;
import by.realovka.web.service.UserService;
import by.realovka.web.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/trainerAndHisStudents")
public class TrainerMainServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User auth = (User) session.getAttribute("userAuth");
        auth = userService.getUserWithHisStudents(auth);
        session.setAttribute("userAuth", auth);
        log.info("Auth trainer = {}", auth);
        req.getRequestDispatcher("/mainTrainer.jsp").forward(req, resp);
    }
}
