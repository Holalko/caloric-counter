package cz.vsb.jakhol.caloriccounter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import cz.vsb.jakhol.caloriccounter.AddFoodToDayMenuActivity;
import cz.vsb.jakhol.caloriccounter.R;
import cz.vsb.jakhol.caloriccounter.activites.MainActivity;
import cz.vsb.jakhol.caloriccounter.adapters.FoodAdapter;
import cz.vsb.jakhol.caloriccounter.models.Food;
import cz.vsb.jakhol.caloriccounter.models.NutritionValuePer100g;
import cz.vsb.jakhol.caloriccounter.stores.FoodStore;

import java.util.ArrayList;
import java.util.List;

public class AddFoodFragment extends Fragment {

    public static final String INDEX_OF_SELECTED_FOOD = "index_of_food";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_food, container, false);

        ListView listView = view.findViewById(R.id.list_add_food);
        FoodAdapter foodAdapter = new FoodAdapter(getContext(), R.layout.list_food_layout, FoodStore.foodList);

        listView.setAdapter(foodAdapter);

        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Intent intent = new Intent(getActivity(), AddFoodToDayMenuActivity.class);
            intent.putExtra(INDEX_OF_SELECTED_FOOD, i);
            startActivity(intent);
        });

        return view;

    }
}

