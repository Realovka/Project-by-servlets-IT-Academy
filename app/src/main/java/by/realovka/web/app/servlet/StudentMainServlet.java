package by.realovka.web.app.servlet;//package by.realovka.web.app.servlet;
//
//import by.realovka.web.dao.model.User;
//import by.realovka.web.service.service.UserService;
//import by.realovka.web.service.service.UserServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//@Slf4j
//@WebServlet(urlPatterns = "/studentThemesAndMarks")
//public class StudentMainServlet extends HttpServlet {
//
//    private final UserService userService = UserServiceImpl.getInstance();
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User auth = (User) req.getSession().getAttribute("userAuth");
//        auth = userService.findAllThemesAndMarkOfStudent(auth);
//        req.getSession().setAttribute("userAuth", auth);
//        log.info("Auth student = {}", auth);
//        req.getRequestDispatcher("/mainStudent.jsp").forward(req, resp);
//    }
//}
