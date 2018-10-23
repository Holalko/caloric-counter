package cz.vsb.jakhol.caloriccounter.stores;

import cz.vsb.jakhol.caloriccounter.models.Food;
import cz.vsb.jakhol.caloriccounter.models.NutritionValuePer100g;

import java.util.ArrayList;
import java.util.List;


public class FoodStore {

    private FoodStore() {
    }

    private static List<Food> foodList;

    // TODO implement returning from DB
    public static List<Food> getFoodList() {
        if (foodList == null) {
            foodList = new ArrayList<Food>() {{
                add(new Food("Tvaroh", new NutritionValuePer100g(20, 25, 30, 3)));
                add(new Food("Cottage", new NutritionValuePer100g(20, 25, 30, 3)));
            }};
        }
        return foodList;
    }


    // TODO
    public static List<Food> getFoodListByName(String name) {
        return new ArrayList<Food>() {{
            add(new Food("Tvaroh", new NutritionValuePer100g(20, 25, 30, 3)));
            add(new Food("Cottage", new NutritionValuePer100g(20, 25, 30, 3)));
        }};
    }


}
