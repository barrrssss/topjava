package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MealDaoConcurrentHashMap implements MealDao {

    private final Map<Long, Meal> meals = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    {
        save(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        save(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        save(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        save(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        save(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        save(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        save(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));

    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public void deleteMeal(Long id) {
        meals.remove(id);
    }

    @Override
    public Meal getMealById(Long id) {
        return meals.get(id);
    }

    @Override
    public void save(Meal meal) {
        Meal newMeal = meal.isNew() ?
                new Meal(counter.incrementAndGet(), meal.getDateTime(), meal.getDescription(), meal.getCalories()) :
                meal;

        meals.put(newMeal.getId(), newMeal);
    }
}
