package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    List<Meal> getAllMeals();

    void deleteMeal(Long id);

    Meal getMealById(Long id);

    void save(Meal meal);
}
