package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.MealTestData.*;

import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService mealService;

    @Test
    public void get() {
        Meal meal = mealService.get(meal1.getId(), USER_ID);
        assertMatch(meal, meal1);
    }

    @Test
    public void getAlien(){
        assertThrows(NotFoundException.class, () -> mealService.get(meal11.getId(), USER_ID));
    }

    @Test
    public void delete() {
        mealService.delete(meal1.getId(), USER_ID);
        assertThrows(NotFoundException.class, () -> mealService.get(meal1.getId(), USER_ID));
    }

    @Test
    public void deleteAlien(){
        assertThrows(NotFoundException.class, () -> mealService.delete(meal11.getId(), USER_ID));
    }

    @Test
    public void deleteNotFound(){
        assertThrows(NotFoundException.class, () -> mealService.delete(100, USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> meals = mealService.getBetweenInclusive(LocalDate.of(2020, 1, 30),
                LocalDate.of(2020, 1, 30), USER_ID);

        assertMatch(meals, meal3, meal2, meal1);
    }

    @Test
    public void getAll() {
        List<Meal> meals = mealService.getAll(USER_ID);

        assertMatch(meals, meal7, meal6, meal5, meal4, meal3, meal2, meal1);
    }

    @Test
    public void update() {
        Meal updated = getUpdatedMeal();
        mealService.update(updated, USER_ID);

        Meal actual = mealService.get(updated.getId(), USER_ID);
        assertMatch(actual, updated);
    }

    @Test
    public void updateAlien() {
        Meal updated = getUpdatedMeal();
        assertThrows(NotFoundException.class, () -> mealService.update(updated, ADMIN_ID));
    }

    @Test
    public void updateDuplicateDateTime(){
        Meal example = meal3;
        Meal original = meal4;
        original.setDateTime(example.getDateTime());

        assertThrows(DataAccessException.class, () -> mealService.update(original, USER_ID));
    }

    @Test
    public void create() {
        Meal newMeal = getNewMeal();
        Meal returned = mealService.create(newMeal, USER_ID);

        assertThat(returned).usingRecursiveComparison().ignoringFields("id").isEqualTo(newMeal);
    }

    @Test
    public void createDuplicateDateTime(){
        Meal meal = getNewMealWithDublicatedDateTime();
        assertThrows(DataAccessException.class, () -> mealService.create(meal, USER_ID));
    }
}