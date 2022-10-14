package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.DAO.MealDAO;
import ru.javawebinar.topjava.DAO.MealDAOImp;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private MealDAO mealDAO = new MealDAOImp();

    private static String INSERT_OR_EDIT = "/insertOrEdit.jsp";

    private static String LIST_MEAL = "/meals.jsp";

    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("MealServlet doGet");
        String forward = "";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            log.info("action delete");
            int id = Integer.parseInt(request.getParameter("id"));
            mealDAO.deleteMeal(id);
            forward = LIST_MEAL;
            request.setAttribute("mealTos", MealsUtil.filteredByStreams(mealDAO.getAllMeal(), LocalTime.MIN, LocalTime.MAX, 2000));
        } else if (action.equalsIgnoreCase("edit")) {
            log.info("action edit");
            forward = INSERT_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));
            Meal meal = mealDAO.getMeal(id);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("insert")) {
            log.info("action insert");
            forward = INSERT_OR_EDIT;
        } else if (action.equalsIgnoreCase("meals")) {
            log.info("redirect meals");
            forward = LIST_MEAL;
            request.setAttribute("mealTos", MealsUtil.filteredByStreams(mealDAO.getAllMeal(), LocalTime.MIN, LocalTime.MAX, 2000));
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("MealServlet doPost");
        request.setCharacterEncoding("utf-8");

        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));

        if (request.getParameter("id").isEmpty()) {
            Meal meal = new Meal(dateTime, description, calories);
            mealDAO.createMeal(meal);
        } else {
            int id = Integer.parseInt(request.getParameter("id")) - 1;
            Meal meal = new Meal(id, dateTime, description, calories);
            mealDAO.updateMeal(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
        request.setAttribute("mealTos", MealsUtil.filteredByStreams(mealDAO.getAllMeal(), LocalTime.MIN, LocalTime.MAX, 2000));
        view.forward(request, response);
    }
}
