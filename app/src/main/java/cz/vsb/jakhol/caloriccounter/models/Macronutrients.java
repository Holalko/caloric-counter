package cz.vsb.jakhol.caloriccounter.models;

public class Macronutrients {

    private int calories;
    private int proteins;
    private int carbohydrates;
    private int fats;
    private int fiber;

    public Macronutrients(){
        this.calories = 0;
        this.proteins = 0;
        this.carbohydrates = 0;
        this.fats = 0;
        this.fiber = 0;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getFiber() {
        return fiber;
    }

    public void setFiber(int fiber) {
        this.fiber = fiber;
    }

}
