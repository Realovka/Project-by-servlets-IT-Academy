package by.realovka.web.app.filter;

import by.realovka.web.dao.model.Admin;
import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "sessionFilter")
public class SessionFilter extends UtilFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (session.isNew()) {
            req.getRequestDispatcher("home").include(request, response);
        } else {
            User auth = (User) session.getAttribute("userAuth");
            if (auth instanceof Admin) {
                request.getRequestDispatcher("/getAdminMainPage").forward(request, response);
            } else {
                if (auth instanceof Student) {
                    request.getRequestDispatcher("/getMainStudentPage").forward(request, response);
                } else {
                    if (auth instanceof Trainer) {
                        request.getRequestDispatcher("/trainerAndHisStudents").forward(request, response);
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }
}
