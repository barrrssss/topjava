package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoConcurrentHashMap;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;


public class MealServiceImpl implements MealService {
    private static final int MAX_CALORIES_PER_DAY = 2000;

    private MealDao mealDao;

    public MealServiceImpl() {
        this.mealDao = new MealDaoConcurrentHashMap();
    }

    @Override
    public List<MealTo> getAllMealTos() {
        List<Meal> meals = mealDao.getAllMeals();
        List<MealTo> mealTos = MealsUtil.transformToMealTo(meals, MAX_CALORIES_PER_DAY);

        return mealTos;
    }

    @Override
    public void deleteMeal(Long id) {
        mealDao.deleteMeal(id);
    }

    @Override
    public Meal getMealById(Long id) {
        return mealDao.getMealById(id);
    }

    @Override
    public void save(Meal meal) {
        mealDao.save(meal);
    }
}
