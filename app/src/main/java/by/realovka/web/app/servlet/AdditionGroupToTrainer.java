package by.realovka.web.app.servlet;

import by.realovka.web.dao.dao.UserDao;
import by.realovka.web.dao.model.User;
import by.realovka.web.service.UserService;
import by.realovka.web.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/addGroup")
public class AdditionGroupToTrainer extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User auth = (User) req.getSession().getAttribute("userAuth");
        auth = userService.createGroupByTrainer(auth);
        req.getSession().setAttribute("userAuth", auth);
        req.getRequestDispatcher("mainTrainer.jsp").forward(req, resp);
    }
}
