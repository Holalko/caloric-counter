package cz.vsb.jakhol.caloriccounter.stores;

import cz.vsb.jakhol.caloriccounter.models.Food;
import cz.vsb.jakhol.caloriccounter.models.NutritionValuePer100g;

import java.util.ArrayList;
import java.util.List;

public class FoodStore {

    public static List<Food> foodList = getFoodListFromDb();

    // TODO implement returning from DBG
    private static List<Food> getFoodListFromDb() {
        return  new ArrayList<Food>() {{
            add(new Food("Tvaroh", new NutritionValuePer100g(20, 25, 30, 3)));
            add(new Food("Cottage", new NutritionValuePer100g(20, 25, 30, 3)));
        }};
    }

}
