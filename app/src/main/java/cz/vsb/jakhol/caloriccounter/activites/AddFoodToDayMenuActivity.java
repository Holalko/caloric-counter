package cz.vsb.jakhol.caloriccounter.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cz.vsb.jakhol.caloriccounter.R;
import cz.vsb.jakhol.caloriccounter.fragments.AddFoodFragment;
import cz.vsb.jakhol.caloriccounter.models.Food;
import cz.vsb.jakhol.caloriccounter.models.NutritionValuePer100g;
import cz.vsb.jakhol.caloriccounter.stores.DayMenuStore;
import cz.vsb.jakhol.caloriccounter.stores.FoodStore;

import java.util.Locale;

public class AddFoodToDayMenuActivity extends AppCompatActivity {

    private TextView name;

    private TextView fiber;
    private TextView proteins;
    private TextView calories;
    private TextView fats;
    private TextView carbs;

    private Button addFoodButton;

    private EditText weight;

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_to_day_menu);

        Intent intent = getIntent();
        index = intent.getIntExtra(AddFoodFragment.INDEX_OF_SELECTED_FOOD, 0);

        initComponents();
        setValuesForComponents(index);
    }

    private void setValuesForComponents(Integer index) {
        Food food = FoodStore.getFoodList().get(index);
        name.setText(food.getName());
        NutritionValuePer100g nutritions = food.getNutritionValuePer100g();
        fiber.setText(String.format(Locale.getDefault(), "%d", nutritions.getFiber()));
        proteins.setText(String.format(Locale.getDefault(), "%d", nutritions.getProteins()));
        calories.setText(String.format(Locale.getDefault(), "%d", nutritions.getTotalCalories()));
        carbs.setText(String.format(Locale.getDefault(), "%d", nutritions.getCarbohydrates()));
        fats.setText(String.format(Locale.getDefault(), "%d", nutritions.getFats()));

    }

    private void initComponents() {
        fiber = findViewById(R.id.text_info_fiber_value);
        proteins = findViewById(R.id.text_info_proteins_value);
        calories = findViewById(R.id.text_info_cal_value);
        carbs = findViewById(R.id.text_info_carbs_value);
        fats = findViewById(R.id.text_info_fats_value);
        name = findViewById(R.id.text_info_name);

        addFoodButton = findViewById(R.id.button_info_add);
        weight = findViewById(R.id.input_info_grams);
        addFoodButton.setOnClickListener(addFoodListener);
    }

    private Button.OnClickListener addFoodListener = view -> {
        int grams = Integer.parseInt(weight.getText().toString());
        DayMenuStore.getDayMenu().addFood(FoodStore.getFoodList().get(index), grams);
        finish();

    };

}
