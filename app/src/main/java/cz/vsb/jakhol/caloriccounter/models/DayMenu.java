package cz.vsb.jakhol.caloriccounter.models;


import java.util.ArrayList;
import java.util.List;

public class DayMenu {

    private User user;
    private List<EatedFood> foodList;

    private Macronutrients total;
    private Macronutrients current;

    public DayMenu() {
        this.user = User.getUser();
        foodList = new ArrayList<>();
        this.countTotalNutrients();
    }

    private void countTotalNutrients() {
        total = CaloricCount.countTotalNutrients(user);
    }

    public List<EatedFood> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<EatedFood> foodList) {
        this.foodList = foodList;
    }

    public void addFood(Food food, int weight){
        EatedFood eatedFood = new EatedFood(food.getName(), food.getNutritionValuePer100g());
        eatedFood.setWeight(weight);
        this.foodList.add(eatedFood);

        current = CaloricCount.countNutrientsFromFood(foodList);
    }

    public Macronutrients getTotal() {
        return total;
    }

    public void setTotal(Macronutrients total) {
        this.total = total;
    }

    public Macronutrients getCurrent() {
        return current;
    }

    public void setCurrent(Macronutrients current) {
        this.current = current;
    }




}
