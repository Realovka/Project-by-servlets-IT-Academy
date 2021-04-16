//package by.realovka.web.app.interceptor;
//
//import by.realovka.web.dao.model.Admin;
//import by.realovka.web.dao.model.Student;
//import by.realovka.web.dao.model.Trainer;
//import by.realovka.web.dao.model.User;
//import by.realovka.web.service.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Component
//public class AuthInterceptor extends HandlerInterceptorAdapter {
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String[] arr = applicationContext.getBeanDefinitionNames();
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//        String login = req.getParameter("loginAuthorization");
//        String password = req.getParameter("passwordAuthorization");
//        User auth = userService.identificationUserByLoginAndPassword(login, password);
//        if (!auth.equals(new User())) {
//            HttpSession session = req.getSession();
//            session.setAttribute("userAuth", auth);
//            forwardToSomeMainPage(auth, req, res);
//        } else {
//            try {
//                req.setAttribute("authorizationFail", "Login or password is wrong!");
//                req.getRequestDispatcher("/index.jsp").forward(request, response);
//            } catch (ServletException | IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return true;
//    }
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
