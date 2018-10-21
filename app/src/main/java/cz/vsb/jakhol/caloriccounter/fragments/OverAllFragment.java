package cz.vsb.jakhol.caloriccounter.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;
import cz.vsb.jakhol.caloriccounter.R;
import cz.vsb.jakhol.caloriccounter.models.DayMenu;
import cz.vsb.jakhol.caloriccounter.models.Food;
import cz.vsb.jakhol.caloriccounter.models.NutritionValuePer100g;

public class OverAllFragment extends Fragment {

    ProgressBar proteinsBar;
    ProgressBar carbohydratesBar;
    ProgressBar fatsBar;
    ProgressBar fiberBar;


    TextView proteinsTextView;
    TextView carbohydratesTextView;
    TextView fatsTextView;
    TextView fiberTextView;

    CircularProgressIndicator totalCaloriesBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_over_all, container, false);

        findViews(view);


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


        return view ;

    }

    private void findViews(View view){
        proteinsTextView = view.findViewById(R.id.text_proteins_summary);
        carbohydratesTextView = view.findViewById(R.id.text_carbohydrates_summary);
        fatsTextView = view.findViewById(R.id.text_fats_summary);
        fiberTextView = view.findViewById(R.id.text_fiber_summary);


        totalCaloriesBar = view.findViewById(R.id.bar_total_cal);

        proteinsBar = view.findViewById(R.id.bar_proteins);
        carbohydratesBar = view.findViewById(R.id.bar_carbohydrates);
        fatsBar = view.findViewById(R.id.bar_fats);
        fiberBar = view.findViewById(R.id.bar_fiber);
    }

}
