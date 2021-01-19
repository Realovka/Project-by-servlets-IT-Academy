//package by.realovka.web.app.servlet;
//
//import by.realovka.web.dao.model.Trainer;
//import by.realovka.web.dao.repository.TrainerRepository;
//import by.realovka.web.dao.repository.TrainerRepositoryImpl;
//import by.realovka.web.service.TrainerService;
//import by.realovka.web.service.TrainerServiceImpl;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//
//@WebServlet(urlPatterns = "/addTrainer")
//public class EnterNewTrainerForAdminServlet extends HttpServlet {
//
//    private TrainerService trainerService;
//
//    private TrainerRepository trainerRepository;
//
//    private final static Logger log = LoggerFactory.getLogger(EnterNewTrainerForAdminServlet.class);
//
//
//    @Override
//    public void init() throws ServletException {
//       trainerRepository = TrainerRepositoryImpl.getInstance();
//       trainerService = TrainerServiceImpl.getInstance(trainerRepository);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String trainerName = req.getParameter("trainerName");
//        int trainerAge = 0;
//        Double salary = null;
//        try {
//            salary = Double.parseDouble(req.getParameter("salary"));
//            trainerAge = Integer.parseInt(req.getParameter("trainerAge"));
//        } catch (NumberFormatException e){
//            ServletContext servletContext = req.getServletContext();
//            servletContext.setAttribute("message", "Parameters are wrong!");
//            req.getRequestDispatcher("/enterTrainer.jsp").forward(req, resp);
//            return;
//        }
//        if(trainerService.existTrainer(trainerName)){
//            trainerService.addSalaryByOneMonth(trainerName, salary);
//        } else {
//            List<Double> salaries = new ArrayList<>();
//            salaries.add(salary);
//            Trainer trainer = new Trainer(trainerName, trainerAge, salaries);
//            log.info("Trainer = {}", trainer);
//            trainerService.saveTrainer(trainer);
//        }
//        Map<String, Trainer> trainers = trainerService.getAllTrainers();
//        req.getServletContext().setAttribute("trainers", trainers);
//        req.getRequestDispatcher("/enterTrainer.jsp").forward(req,resp);
//    }
//}
