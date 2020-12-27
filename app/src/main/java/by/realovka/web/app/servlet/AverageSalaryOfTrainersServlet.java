package by.realovka.web.app.servlet;

import by.realovka.web.dao.dto.TrainerDTO;
import by.realovka.web.dao.repository.TrainerRepository;
import by.realovka.web.dao.repository.TrainerRepositoryImpl;
import by.realovka.web.service.TrainerService;
import by.realovka.web.service.TrainerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (urlPatterns = "/averageSalary")
public class AverageSalaryOfTrainersServlet extends HttpServlet {

    private TrainerService trainerService;

    private TrainerRepository trainerRepository;

    private static final Logger log = LoggerFactory.getLogger(AverageSalaryOfTrainersServlet.class);

    @Override
    public void init() throws ServletException {
        trainerRepository = TrainerRepositoryImpl.getInstance();
        trainerService = TrainerServiceImpl.getInstance(trainerRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String trainerName = req.getParameter("trainerName");
       Integer months = null;
       try {
           months = Integer.parseInt(req.getParameter("numberOfMonths"));
       } catch (NumberFormatException e){
           ServletContext servletContext = req.getServletContext();
           servletContext.setAttribute("message", "Parameters are wrong!");
           req.getRequestDispatcher("/averageSalary.jsp").forward(req, resp);
           return;
       }
       TrainerDTO trainerDTO = trainerService.getAverageSalary(trainerName, months);
       req.getSession().setAttribute("trainerDTO", trainerDTO);
       req.getRequestDispatcher("/averageSalary.jsp").forward(req, resp);
    }
}
