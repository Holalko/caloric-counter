package cz.vsb.jakhol.caloriccounter.models;

import java.util.List;

public class Food {

    private String name;
    private NutritionValuePer100g nutritionValuePer100g;


    private List<Weights> weights;

    public Food(String name, NutritionValuePer100g nutritionValuePer100g, List<Weights> weights) {
        this.name = name;
        this.nutritionValuePer100g = nutritionValuePer100g;
        this.weights = weights;
    }

    public List<Weights> getWeights() {
        return weights;
    }

    public void setWeights(List<Weights> weights) {
        this.weights = weights;
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
