package cz.vsb.jakhol.caloriccounter.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import cz.vsb.jakhol.caloriccounter.R;
import cz.vsb.jakhol.caloriccounter.adapters.EatedFoodAdapter;
import cz.vsb.jakhol.caloriccounter.stores.DataStore;


public class FoodMenuFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_menu, container, false);
        ListView listFoodMenu = view.findViewById(R.id.list_food_menu);

        EatedFoodAdapter eatedFoodAdapter = new EatedFoodAdapter(getContext(), R.layout.list_menu_layout, new DataStore(getContext()).getDayMenu().getFoodList());

        listFoodMenu.setAdapter(eatedFoodAdapter);

        return view;
    }
}
