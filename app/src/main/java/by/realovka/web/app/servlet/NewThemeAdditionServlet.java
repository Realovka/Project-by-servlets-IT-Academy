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
import java.io.IOException;

@Slf4j
@WebServlet (urlPatterns = "/addTheme")
public class NewThemeAdditionServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String themeName = req.getParameter("themeName");
        req.getSession().setAttribute("theme",themeName);
        User trainer = (User) req.getSession().getAttribute("userAuth");
        User trainerAndHisStudentsAfterAddTheme = userService.getTrainerAndHisStudentsAfterAddTheme(trainer, themeName);
        log.info("Trainer after addition theme {}", trainerAndHisStudentsAfterAddTheme);
        req.getRequestDispatcher("mainTrainer.jsp").forward(req, resp);
    }
}
