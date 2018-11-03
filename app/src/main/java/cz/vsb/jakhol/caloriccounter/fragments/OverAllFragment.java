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
import cz.vsb.jakhol.caloriccounter.stores.DataStore;

import java.util.Locale;

public class OverAllFragment extends Fragment {

    private ProgressBar proteinsBar;
    private ProgressBar carbohydratesBar;
    private ProgressBar fatsBar;
    private ProgressBar fiberBar;

    private TextView proteinsTextView;
    private TextView carbohydratesTextView;
    private TextView fatsTextView;
    private TextView fiberTextView;
    private TextView totalCalTextView;


    private CircularProgressIndicator totalCaloriesBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_over_all, container, false);
        findViews(view);
        countProgress();
        return view;
    }

    private void countProgress() {
        DayMenu menu = new DataStore(getContext()).getDayMenu();

        String format = "%d / %d";
        proteinsTextView.setText(String.format(Locale.getDefault(), format, menu.getCurrent().getProteins(), menu.getTotal().getProteins()));
        carbohydratesTextView.setText(String.format(Locale.getDefault(), format, menu.getCurrent().getCarbohydrates(), menu.getTotal().getCarbohydrates()));
        fatsTextView.setText(String.format(Locale.getDefault(), format, menu.getCurrent().getFats(), menu.getTotal().getFats()));
        fiberTextView.setText(String.format(Locale.getDefault(), format, menu.getCurrent().getFiber(), menu.getTotal().getFiber()));
        totalCalTextView.setText(String.format(Locale.getDefault(), format, menu.getCurrent().getCalories(), menu.getTotal().getCalories()));

        double progress = (menu.getCurrent().getProteins() / (double) menu.getTotal().getProteins()) * 100.0;
        proteinsBar.setProgress((int) progress);
        progress = (menu.getCurrent().getCarbohydrates() / (double) menu.getTotal().getCarbohydrates()) * 100.0;
        carbohydratesBar.setProgress((int) progress);
        progress = (menu.getCurrent().getFats() / (double) menu.getTotal().getFats()) * 100.0;
        fatsBar.setProgress((int) progress);
        progress = (menu.getCurrent().getFiber() / (double) menu.getTotal().getFiber()) * 100.0;
        fiberBar.setProgress((int) progress);
        totalCaloriesBar.setProgress(menu.getCurrent().getCalories(), menu.getTotal().getCalories());
    }

    private void findViews(View view) {
        proteinsTextView = view.findViewById(R.id.text_proteins_summary);
        carbohydratesTextView = view.findViewById(R.id.text_carbohydrates_summary);
        fatsTextView = view.findViewById(R.id.text_fats_summary);
        fiberTextView = view.findViewById(R.id.text_fiber_summary);
        totalCalTextView = view.findViewById(R.id.text_total_cal_summary);


        totalCaloriesBar = view.findViewById(R.id.bar_total_cal);

        proteinsBar = view.findViewById(R.id.bar_proteins);
        carbohydratesBar = view.findViewById(R.id.bar_carbohydrates);
        fatsBar = view.findViewById(R.id.bar_fats);
        fiberBar = view.findViewById(R.id.bar_fiber);
    }

}
