package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MealOrder {
    private MealCount[] meals;
    public MealOrder() { }
    public MealOrder(@JsonProperty("meals")MealCount[] meals) {
        this.meals = meals;
    }
    public MealCount[] getMeals() {
        return meals;
    }
    public void setmeals(MealCount[] meals) {
        this.meals = meals;
    }
}
