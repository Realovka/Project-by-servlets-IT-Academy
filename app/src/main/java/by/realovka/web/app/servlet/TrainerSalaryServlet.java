package by.realovka.web.app.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/choiceTrainer")
public class TrainerSalaryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trainerId = req.getParameter("trainerId");
        req.getSession().setAttribute("trainerId", trainerId);
        req.getRequestDispatcher("addSalary.jsp").forward(req, resp);
    }
}
