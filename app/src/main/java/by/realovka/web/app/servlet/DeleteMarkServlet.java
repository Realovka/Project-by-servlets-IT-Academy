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
@WebServlet(urlPatterns = "/deleteMark")
public class DeleteMarkServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        String themeName = req.getParameter("themeName");
        User auth = (User) req.getSession().getAttribute("userAuth");
        userService.deleteMark(auth, studentId, themeName);
        log.info("Trainer and his students after delete mark = {}", auth);
        req.getSession().setAttribute("userAuth", auth);
        req.getRequestDispatcher("mainTrainer.jsp").forward(req,resp);
    }
}
