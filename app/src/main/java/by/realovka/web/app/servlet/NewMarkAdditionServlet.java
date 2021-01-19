//package by.realovka.web.app.servlet;
//
//import by.realovka.web.dao.model.Student;
//import by.realovka.web.dao.model.Trainer;
//import by.realovka.web.dao.repository.UserRepository;
//import by.realovka.web.dao.repository.UserRepositoryImpl;
//import by.realovka.web.service.UserService;
//import by.realovka.web.service.UserServiceImpl;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(urlPatterns = "/addMark")
//public class NewMarkAdditionServlet extends HttpServlet {
//
//    private UserService userService;
//
//    private UserRepository userRepository;
//
//    private final static Logger log = LoggerFactory.getLogger(AllStudentsServlet.class);
//
//
//    @Override
//    public void init() throws ServletException {
//        userRepository = UserRepositoryImpl.getInstance();
//        userService = UserServiceImpl.getInstance(userRepository);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Trainer trainer = (Trainer) req.getSession().getAttribute("userAuth");
//        String studentLogin = req.getParameter("student");
//        String theme = req.getParameter("theme");
//        Integer mark = null;
//        try {
//            mark = Integer.parseInt(req.getParameter("mark"));
//        } catch (NumberFormatException e) {
//            req.getServletContext().setAttribute("massageFormatOfMarkIsWrong", "Format of mark is wrong!");
//            req.getRequestDispatcher("mainTrainer.jsp").forward(req, resp);
//            return;
//        }
//        List<Student> students = userService.addOrUpdateMarkToStudent(trainer, studentLogin, theme, mark);
//        log.info("Students = {}", students);
//        req.getSession().setAttribute("listStudentsForTrainer", students);
//        req.getRequestDispatcher("mainTrainer.jsp").forward(req, resp);
//    }
//}
