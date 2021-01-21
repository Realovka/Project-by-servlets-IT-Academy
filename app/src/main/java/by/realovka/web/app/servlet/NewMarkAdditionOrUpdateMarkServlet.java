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
@WebServlet(urlPatterns = "/addOrUpdateMark")
public class NewMarkAdditionOrUpdateMarkServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User auth = (User) req.getSession().getAttribute("userAuth");
        String studentId = req.getParameter("studentId");
        String themeName = req.getParameter("themeName");
        Integer mark = null;
        try {
            mark = Integer.parseInt(req.getParameter("mark"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("massageFormatOfMarkIsWrong", "Format of mark is wrong!");
            req.getRequestDispatcher("mainTrainer.jsp").forward(req, resp);
            return;
        }
        if (mark > 100 || mark < 0) {
            req.getSession().setAttribute("massageFormatOfMarkIsWrong", "You can't enter a mark greater than 100 or less than 0");
            req.getRequestDispatcher("mainTrainer.jsp").forward(req, resp);
            return;
        }
        auth = userService.addOrUpdateMarkToStudent(auth, studentId, themeName, mark);
        log.info("Auth trainer and his students after insert(update) mark to theme = {}", auth);
        req.getSession().setAttribute("userAuth", auth);
        req.getRequestDispatcher("mainTrainer.jsp").forward(req, resp);
    }
}
