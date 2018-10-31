package cz.vsb.jakhol.caloriccounter.models;

import java.util.List;

public class CaloricCount {

    private CaloricCount(){}

    public static Macronutrients countTotalNutrients(User user) {
        double res = 88 + (13.4 * user.getWeight()) + (4.8 * user.getHeightInCm()) - (5.7 * user.getAge());
        switch (user.getGoalState()) {
            case GAIN:
                return countTotalMacrosGain(user, (int) (res * 1.55 + 300));
            case LOOSE:
                return countTotalMacrosLoose(user, (int) (res * 1.55 - 300));
            case SUSTAIN:
                return countTotalMacrosSustain(user, (int) (res * 1.55));
            default:
                return countTotalMacrosSustain(user, 2500);
        }
    }

    private static Macronutrients countTotalMacrosGain(User user, int totalCalories) {
        Macronutrients macronutrients = new Macronutrients();
        int proteins = (int) (user.getWeight() * 1.8);
        macronutrients.setProteins(proteins);

        int fats = (int) (totalCalories * 0.25) / NutritionValuePer100g.KCAL_PER_FAT;
        macronutrients.setFats(fats);

        int carbs = (totalCalories - ((proteins * NutritionValuePer100g.KCAL_PER_PROTEIN) + (fats * NutritionValuePer100g.KCAL_PER_FAT))) / NutritionValuePer100g.KCAL_PER_CARBOHYDRATE;
        macronutrients.setCarbohydrates(carbs);
        macronutrients.setCalories(totalCalories);
        macronutrients.setFiber(25);
        return macronutrients;
    }

    private static Macronutrients countTotalMacrosLoose(User user, int totalCalories) {
        Macronutrients macronutrients = new Macronutrients();
        int proteins = (int) (user.getWeight() * 2.8);
        macronutrients.setProteins(proteins);

        int fats = (int) (totalCalories * 0.15) / NutritionValuePer100g.KCAL_PER_FAT;
        macronutrients.setFats(fats);

        int carbs = (totalCalories - ((proteins * NutritionValuePer100g.KCAL_PER_PROTEIN) + (fats * NutritionValuePer100g.KCAL_PER_FAT))) / NutritionValuePer100g.KCAL_PER_CARBOHYDRATE;
        macronutrients.setCarbohydrates(carbs);
        macronutrients.setCalories(totalCalories);
        macronutrients.setFiber(25);
        return macronutrients;
    }

    private static Macronutrients countTotalMacrosSustain(User user, int totalCalories) {
        Macronutrients macronutrients = new Macronutrients();
        int proteins = (int) (user.getWeight() * 2.3);
        macronutrients.setProteins(proteins);

        int fats = (int) (totalCalories * 0.20) / NutritionValuePer100g.KCAL_PER_FAT;
        macronutrients.setFats(fats);

        int carbs = (totalCalories - ((proteins * NutritionValuePer100g.KCAL_PER_PROTEIN) + (fats * NutritionValuePer100g.KCAL_PER_FAT))) / NutritionValuePer100g.KCAL_PER_CARBOHYDRATE;
        macronutrients.setCarbohydrates(carbs);
        macronutrients.setCalories(totalCalories);
        macronutrients.setFiber(25);
        return macronutrients;
    }

    public static Macronutrients countNutrientsFromFood(List<EatedFood> foodList) {
        Macronutrients macronutrients = new Macronutrients();
        double fiber = 0;
        double proteins = 0;
        double carbs = 0;
        double fats = 0;
        double calories = 0;
        for (EatedFood food : foodList) {
            calories += food.getWeight() * (food.getNutritionValuePer100g().getTotalCalories() / 100.);
            fiber += food.getWeight() * (food.getNutritionValuePer100g().getFiber() / 100.);
            proteins += food.getWeight() * (food.getNutritionValuePer100g().getProteins() / 100.);
            carbs += food.getWeight() * (food.getNutritionValuePer100g().getCarbohydrates() / 100.);
            fats += food.getWeight() * (food.getNutritionValuePer100g().getFats() / 100.);
        }
        macronutrients.setFiber((int)fiber);
        macronutrients.setProteins((int)proteins);
        macronutrients.setCarbohydrates((int)carbs);
        macronutrients.setFats((int)fats);
        macronutrients.setCalories((int)calories);
        return macronutrients;

    }
}
