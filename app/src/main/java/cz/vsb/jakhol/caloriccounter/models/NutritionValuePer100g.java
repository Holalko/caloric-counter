package cz.vsb.jakhol.caloriccounter.models;


public class NutritionValuePer100g {

    private static final Integer KCAL_PER_CARBOHYDRATE = 4;
    private static final Integer KCAL_PER_PROTEIN = 4;
    private static final Integer KCAL_PER_FAT = 9;

    private Integer carbohydrates;
    private Integer proteins;
    private Integer fats;

    private Integer fiber;

    private Integer totalCalories;


    public NutritionValuePer100g(Integer carbohydrates, Integer proteins, Integer fats, Integer fiber) {
        this.carbohydrates = carbohydrates;
        this.proteins = proteins;
        this.fats = fats;
        this.fiber = fiber;

        this.totalCalories = countTotalCalories();
    }

    private Integer countTotalCalories() {
        int totalCal;
        totalCal = (this.carbohydrates * KCAL_PER_CARBOHYDRATE);
        totalCal += (this.proteins * KCAL_PER_PROTEIN);
        totalCal += (this.fats * KCAL_PER_FAT);
        return totalCal;
    }

    public Integer getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(Integer totalCalories) {
        this.totalCalories = totalCalories;
    }

    public Integer getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Integer carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Integer getProteins() {
        return proteins;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    public Integer getFats() {
        return fats;
    }

    public void setFats(Integer fats) {
        this.fats = fats;
    }

    public Integer getFiber() {
        return fiber;
    }

    public void setFiber(Integer fiber) {
        this.fiber = fiber;
    }


}
