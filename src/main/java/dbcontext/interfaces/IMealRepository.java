package dbcontext.interfaces;

import dbcontext.models.Meal;

import java.util.ArrayList;

public interface IMealRepository {
    ArrayList<Meal> getMeals();
    Meal getMeal(int id);
}
