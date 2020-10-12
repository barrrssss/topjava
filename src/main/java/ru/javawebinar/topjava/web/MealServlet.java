package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private static final String LIST_MEALS = "meals.jsp";
    private static final String EDIT_PAGE = "saveMeal.jsp";

    private MealService mealService;

    public MealServlet() {
        this.mealService = new MealServiceImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";

        String action = req.getParameter("action");

        if (action == null){
            List<MealTo> mealTos = mealService.getAllMealTos();
            req.setAttribute("mealTos", mealTos);
            forward = LIST_MEALS;
        } else if (action.equalsIgnoreCase("delete")) {
            Long id = Long.parseLong(req.getParameter("mealId"));
            mealService.deleteMeal(id);

            log.debug("delete action, meadId = " + id);

            resp.sendRedirect("meals");
            return;
        } else {
            forward = EDIT_PAGE;
            Meal meal = action.equalsIgnoreCase("create") ?
                    new Meal(null, LocalDateTime.now().withNano(0), "", 1000) :
                    mealService.getMealById(Long.parseLong(req.getParameter("mealId")));

            log.debug("send to editor meal : " + meal);

            req.setAttribute("meal", meal);
        }
        log.debug("forward : " + forward + " parameters : " + parametersFormatter(req.getParameterMap()));
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("mealId");
        Long parsedId = id == null || id.length() == 0 ? null : Long.parseLong(id);
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        Meal meal = new Meal(parsedId, localDateTime, description, calories);
        log.debug("Meal to save : " + meal);

        mealService.save(meal);

        log.debug("redirect : " + "meals" + " parameters : " + parametersFormatter(req.getParameterMap()));
        resp.sendRedirect("meals");
    }

    private static String parametersFormatter(Map<String, String[]> map){
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String[]> entry : map.entrySet()){
            stringBuilder.append(entry.getKey()).append("[");

            for (String param : entry.getValue()){
                stringBuilder.append(param).append(", ");
            }

            stringBuilder.append("] ");
        }

        return stringBuilder.toString();
    }
}
