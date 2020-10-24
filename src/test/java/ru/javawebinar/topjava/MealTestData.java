package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static int USER_ID = UserTestData.USER_ID;
    public static int ADMIN_ID = UserTestData.ADMIN_ID;

    public static Meal meal1 = new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static Meal meal2 = new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static Meal meal3 = new Meal(3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static Meal meal4 = new Meal(4, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static Meal meal5 = new Meal(5, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    public static Meal meal6 = new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    public static Meal meal7 = new Meal(7, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);

    public static Meal meal11 = new Meal(11, LocalDateTime.of(2020, Month.FEBRUARY, 1, 8, 0), "Завтрак", 410);
    public static Meal meal12 = new Meal(12, LocalDateTime.of(2020, Month.FEBRUARY, 1, 14, 0), "Обед", 1000);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static Meal getUpdatedMeal(){
        Meal updated = new Meal(meal1);
        updated.setCalories(1000);
        updated.setDescription("Измененный завтрак");

        return updated;
    }

    public static Meal getNewMeal(){
        return new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 20, 0), "Ужин", 410);
    }

    public static Meal getNewMealWithDublicatedDateTime(){
        return new  Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
    }
}
