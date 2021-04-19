//package by.realovka.web.app.filter;
//
//
//import by.realovka.web.dao.model.Admin;
//import by.realovka.web.dao.model.Student;
//import by.realovka.web.dao.model.Trainer;
//import by.realovka.web.dao.model.User;
//import by.realovka.web.service.service.UserService;
//import by.realovka.web.service.service.UserServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Slf4j
////@WebFilter(urlPatterns = "/*", filterName = "authFilter", dispatcherTypes = {DispatcherType.REQUEST})
////@Component
//public class AuthFilter extends HttpFilter{
//
//    @Autowired
//    private UserService userService;
//
////    @Autowired
////    private ApplicationContext applicationContext;
//
////    public AuthFilter(UserService userService, ApplicationContext applicationContext) {
////        this.userService = userService;
//////        this.applicationContext = applicationContext;
////    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
////        String[] arr = applicationContext.getBeanDefinitionNames();
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//        String login = req.getParameter("loginAuthorization");
//        String password = req.getParameter("passwordAuthorization");
//        User auth = userService.identificationUserByLoginAndPassword(login, password);
//        if (!auth.equals(new User())) {
//            HttpSession session = req.getSession();
//            session.setAttribute("userAuth", auth);
//            forwardToSomeMainPage(auth, req, res);
//            chain.doFilter(request, response);
//        } else {
//            try {
//                req.setAttribute("authorizationFail", "Login or password is wrong!");
//                req.getRequestDispatcher("/index.jsp").forward(request, response);
//            } catch (ServletException | IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    private void forwardToSomeMainPage(User auth, HttpServletRequest request, HttpServletResponse response) {
//        try {
//            if (auth instanceof Admin) {
//                request.getRequestDispatcher("/adminAndTrainers").forward(request, response);
//            } else {
//                if (auth instanceof Trainer) {
//                    request.getRequestDispatcher("/trainerAndHisStudents").forward(request, response);
//                } else {
//                    if (auth instanceof Student) {
//                        request.getRequestDispatcher("mainStudent.jsp").forward(request, response);
//                    }
//                }
//            }
//        } catch (ServletException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
