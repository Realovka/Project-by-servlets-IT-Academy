package by.realovka.web.app.servlet;

import by.realovka.web.dao.dto.TrainerDTO;
import by.realovka.web.service.service.TrainerService;
import by.realovka.web.service.service.TrainerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/addTrainer")
public class AdditionNewTrainerForAdminServlet {

    private final TrainerService trainerService;

    @Autowired
    public AdditionNewTrainerForAdminServlet(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trainerName = req.getParameter("trainerName");
        trainerService.addTrainer(trainerName);
        List<TrainerDTO> trainerDTO = trainerService.getAllTrainers();
        req.getServletContext().setAttribute("listTrainers", trainerDTO);
        req.getRequestDispatcher("/listAllTrainers.jsp").forward(req, resp);
    }
}
