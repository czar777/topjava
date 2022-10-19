package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");
        request.getRequestDispatcher("/index.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doPost");
        int authUserId = Integer.parseInt(request.getParameter("hero"));
        SecurityUtil.authUserId = authUserId;

        response.sendRedirect("/topjava/meals");
    }
}
