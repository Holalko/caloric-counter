package cz.vsb.jakhol.caloriccounter.models;

import java.util.List;

public class Food {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String name;
    private NutritionValuePer100g nutritionValuePer100g;

    private String barcodeNumber;

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }

    public Food(String name, NutritionValuePer100g nutritionValuePer100g) {
        this.name = name;
        this.nutritionValuePer100g = nutritionValuePer100g;
    }

    public Food(){

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
