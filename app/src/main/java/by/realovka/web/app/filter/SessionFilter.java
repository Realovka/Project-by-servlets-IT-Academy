package by.realovka.web.app.filter;

import by.realovka.web.dao.dto.TrainerDto;
import by.realovka.web.dao.model.Admin;
import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//
@WebFilter(filterName = "sessionFilter")
public class SessionFilter extends UtilFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        if (session.isNew()) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            Object auth = req.getSession().getAttribute("userAuth");
            if (auth instanceof Admin) {
                request.getRequestDispatcher("/mainAdmin.jsp").forward(request, response);
            } else {
                if (auth instanceof Student) {
                    request.getRequestDispatcher("/mainStudent.jsp").forward(request, response);
                } else {
                    if (auth instanceof TrainerDto) {
                        request.getRequestDispatcher("/mainTrainer.jsp").forward(request, response);
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }
}
