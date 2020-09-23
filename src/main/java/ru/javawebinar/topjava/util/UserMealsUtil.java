package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, List<UserMeal>> map = new HashMap<>();

        for (UserMeal userMeal : meals){
            LocalDate localDate = userMeal.getDateTime().toLocalDate();
            List<UserMeal> list = map.get(localDate);

            if (list != null){
                list.add(userMeal);
            } else {
                List<UserMeal> newList = new ArrayList<>();
                newList.add(userMeal);
                map.put(localDate, newList);
            }
        }

        List<UserMealWithExcess> userMealWithExcessList = new ArrayList<>();

        for (Map.Entry<LocalDate, List<UserMeal>> entry : map.entrySet()){
            List<UserMeal> list = entry.getValue();

            int caloriesQuantity = 0;
            for (UserMeal userMeal : list){
                caloriesQuantity += userMeal.getCalories();
            }

            boolean excess = caloriesQuantity > caloriesPerDay;

            for (UserMeal userMeal : list){
                if (TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime)){
                    UserMealWithExcess userMealWithExcess = new UserMealWithExcess(userMeal, excess);
                    userMealWithExcessList.add(userMealWithExcess);
                }
            }
        }


        return userMealWithExcessList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }



}
