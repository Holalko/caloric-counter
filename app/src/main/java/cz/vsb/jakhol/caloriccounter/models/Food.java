package cz.vsb.jakhol.caloriccounter.models;

import java.util.List;

public class Food {

    private String name;
    private NutritionValuePer100g nutritionValuePer100g;



    public Food(String name, NutritionValuePer100g nutritionValuePer100g) {
        this.name = name;
        this.nutritionValuePer100g = nutritionValuePer100g;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NutritionValuePer100g getNutritionValuePer100g() {
        return nutritionValuePer100g;
    }

    public void setNutritionValuePer100g(NutritionValuePer100g nutritionValuePer100g) {
        this.nutritionValuePer100g = nutritionValuePer100g;
    }
}
