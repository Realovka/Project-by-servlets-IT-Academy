package by.realovka.web.app.servlet;

import by.realovka.web.dao.dto.TrainerDTO;
import by.realovka.web.service.service.TrainerService;
import by.realovka.web.service.service.TrainerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addSalary")
public class AdditionSalaryToTrainerServlet extends HttpServlet {

    private final TrainerService trainerService = TrainerServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trainerId = (String) req.getSession().getAttribute("trainerId");
        String salary = req.getParameter("salary");
        trainerService.addNewSalaryToTrainer(trainerId, salary);
        List<TrainerDTO> trainerDTO = trainerService.getAllTrainers();
        req.getServletContext().setAttribute("listTrainers", trainerDTO);
        req.getRequestDispatcher("/mainAdmin.jsp").forward(req, resp);
    }
}
