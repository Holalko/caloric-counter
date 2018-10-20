package cz.vsb.jakhol.caloriccounter.activites;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;
import cz.vsb.jakhol.caloriccounter.R;
import cz.vsb.jakhol.caloriccounter.models.DayMenu;
import cz.vsb.jakhol.caloriccounter.models.Food;
import cz.vsb.jakhol.caloriccounter.models.NutritionValuePer100g;

public class BoardActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    ProgressBar proteinsBar;
    ProgressBar carbohydratesBar;
    ProgressBar fatsBar;
    ProgressBar fiberBar;


    TextView proteinsTextView;
    TextView carbohydratesTextView;
    TextView fatsTextView;
    TextView fiberTextView;

    CircularProgressIndicator totalCaloriesBar;

    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        initBottomNavigation();
        proteinsTextView = findViewById(R.id.text_proteins_summary);
        carbohydratesTextView = findViewById(R.id.text_carbohydrates_summary);
        fatsTextView = findViewById(R.id.text_fats_summary);
        fiberTextView = findViewById(R.id.text_fiber_summary);

        proteinsBar = findViewById(R.id.bar_proteins);
        carbohydratesBar = findViewById(R.id.bar_carbohydrates);
        fatsBar = findViewById(R.id.bar_fats);
        fiberBar = findViewById(R.id.bar_fiber);

        totalCaloriesBar = findViewById(R.id.bar_total_cal);

        DayMenu dayMenu = new DayMenu();
        proteinsTextView.setText("0/" + dayMenu.getTotal().getProteins());
        carbohydratesTextView.setText("0/" + dayMenu.getTotal().getCarbohydrates());
        fatsTextView.setText("0/" + dayMenu.getTotal().getFats());
        fiberTextView.setText("0/" + dayMenu.getTotal().getFiber());

        dayMenu.addFood(new Food("Tvaroh", new NutritionValuePer100g(2, 10, 20, 0)), 250);

        double progress = (dayMenu.getCurrent().getProteins() / (double) dayMenu.getTotal().getProteins()) * 100.0;
        proteinsBar.setProgress((int) progress);
        progress = (dayMenu.getCurrent().getCarbohydrates() / (double) dayMenu.getTotal().getCarbohydrates()) * 100.0;
        carbohydratesBar.setProgress((int) progress);
        progress = (dayMenu.getCurrent().getFats() / (double) dayMenu.getTotal().getFats()) * 100.0;
        fatsBar.setProgress((int) progress);
        progress = (dayMenu.getCurrent().getFiber() / (double) dayMenu.getTotal().getFiber()) * 100.0;
        fiberBar.setProgress((int) progress);
        totalCaloriesBar.setProgress(dayMenu.getCurrent().getCalories(), dayMenu.getTotal().getCalories());

    }

    private void initBottomNavigation() {
        bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.inflateMenu(R.menu.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(bottomNavigationSelect);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationSelect = item -> {
        int id = item.getItemId();
        fragmentManager = getSupportFragmentManager();
        switch (id) {
            case R.id.navigation_overall: {
                return true;
//                break;
            }
            case R.id.navigation_add: {
                fragment = new AddFoodFragment();
                break;
            }
            case R.id.navigation_profile: {
                return true;
//                break;
            }
            case R.id.navigation_menu: {
                return true;
//                break;
            }
            default:
                return false;
        }
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.board_container, fragment).commit();
        return true;

    };

}
