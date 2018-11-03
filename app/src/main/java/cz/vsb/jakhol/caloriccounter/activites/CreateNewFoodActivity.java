package cz.vsb.jakhol.caloriccounter.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cz.vsb.jakhol.caloriccounter.R;
import cz.vsb.jakhol.caloriccounter.models.Food;
import cz.vsb.jakhol.caloriccounter.models.NutritionValuePer100g;
import cz.vsb.jakhol.caloriccounter.scanner.IntentIntegrator;
import cz.vsb.jakhol.caloriccounter.scanner.IntentResult;
import cz.vsb.jakhol.caloriccounter.stores.DataStore;

public class CreateNewFoodActivity extends Activity {

    private EditText nameInput;
    private EditText proteinsInput;
    private EditText carbsInput;
    private EditText fatsInput;
    private EditText fiberInput;

    private TextView barcodeValue;

    private Button barcodeButton;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_food);

        initComponents();
    }

    private void initComponents() {
        nameInput = findViewById(R.id.text_create_name_value);
        proteinsInput = findViewById(R.id.text_create_proteins_value);
        carbsInput = findViewById(R.id.text_create_carbs_value);
        fatsInput = findViewById(R.id.text_create_fats_value);
        fiberInput = findViewById(R.id.text_create_fiber_value);

        barcodeValue = findViewById(R.id.text_create_barcode_value);

        barcodeButton = findViewById(R.id.button_create_barcode);
        createButton = findViewById(R.id.button_create_new_food);

        barcodeButton.setOnClickListener(barcodeOnClick);
        createButton.setOnClickListener(createOnClick);
    }

    private Button.OnClickListener barcodeOnClick = view -> {
        IntentIntegrator scanIntegrator = new IntentIntegrator(CreateNewFoodActivity.this);
        scanIntegrator.initiateScan();
    };

    // TODO validation
    private Button.OnClickListener createOnClick = view -> {
        String nameValue = nameInput.getText().toString();
        String proteinsValue = proteinsInput.getText().toString();
        String carbsValue = carbsInput.getText().toString();
        String fatsValue = fatsInput.getText().toString();
        String fiberValue = fiberInput.getText().toString();
        String barcodeValue2 = barcodeValue.getText().toString();

        NutritionValuePer100g nutritions = new NutritionValuePer100g(Integer.parseInt(carbsValue),
                Integer.parseInt(proteinsValue),
                Integer.parseInt(fatsValue),
                Integer.parseInt(fiberValue));
        Food newFood = new Food(nameValue, nutritions);
        newFood.setBarcodeNumber(barcodeValue2);

        DataStore dataStore = new DataStore(view.getContext());
        dataStore.addFood(newFood);
        // HIDE KEYBOARD
        view.getContext();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        Toast.makeText(view.getContext(), "Food created", Toast.LENGTH_SHORT).show();

        finish();
    };

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            barcodeValue.setText(scanContent);
        }
    }

}
