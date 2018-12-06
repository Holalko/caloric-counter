package cz.vsb.jakhol.caloriccounter.models;

public class EatedFood extends Food {

    private int weight;

    public EatedFood(String name, NutritionValuePer100g nutritionValuePer100g) {
        super(name, nutritionValuePer100g);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public EatedFood(String name, NutritionValuePer100g nutritionValuePer100g, int weight) {

        super(name, nutritionValuePer100g);
        this.weight = weight;
    }
}
