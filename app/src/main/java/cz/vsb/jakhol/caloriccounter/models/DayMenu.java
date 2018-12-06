package cz.vsb.jakhol.caloriccounter.models;


import java.util.ArrayList;
import java.util.List;

public class DayMenu {


    private Integer id;
    private String date;

    private User user;
    private List<EatedFood> foodList;


    private Macronutrients total;
    private Macronutrients current;

    public DayMenu(User user) {
        this.total = new Macronutrients();
        this.current = new Macronutrients();
        foodList = new ArrayList<>();
        this.user = user;
        this.countTotalNutrients();
    }

    public void countTotalNutrients() {
        total = CaloricCount.countTotalNutrients(user);
    }

    public List<EatedFood> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<EatedFood> foodList) {
        this.foodList = foodList;
        current = CaloricCount.countNutrientsFromFood(foodList);

    }

    public void addFood(Food food, int weight) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
