package cz.vsb.jakhol.caloriccounter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import cz.vsb.jakhol.caloriccounter.R;
import cz.vsb.jakhol.caloriccounter.models.Food;
import cz.vsb.jakhol.caloriccounter.models.NutritionValuePer100g;

import java.util.List;
import java.util.Locale;

public class FoodAdapter extends ArrayAdapter<Food> {

    List<Food> foodList;
    private int layoutResourceId;
    Context context;

    public FoodAdapter(@NonNull Context context, int resource, @NonNull List<Food> foodList) {
        super(context, resource, foodList);
        this.layoutResourceId = resource;
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FoodHolder holder = null;
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new FoodHolder();
            holder.setTextNameValue(row.findViewById(R.id.text_list_name));
            holder.setTextCaloriesValue(row.findViewById(R.id.text_list_cal_value));
            holder.setTextProteinsValue(row.findViewById(R.id.text_list_proteins_value));
            holder.setTextCarbohydratesValue(row.findViewById(R.id.text_list_carbs_value));
            holder.setTextFatsValue(row.findViewById(R.id.text_list_fats_value));
            holder.setTextFiber(row.findViewById(R.id.text_list_fiber_value));

            row.setTag(holder);
        } else {
            holder = (FoodHolder) row.getTag();
        }

        Food food = foodList.get(position);
        NutritionValuePer100g nutritions = food.getNutritionValuePer100g();
        holder.getTextNameValue().setText(food.getName());

        holder.getTextCaloriesValue().setText(String.format(Locale.getDefault(), "%d", nutritions.getTotalCalories()));
        holder.getTextProteinsValue().setText(String.format(Locale.getDefault(), "%d", nutritions.getProteins()));
        holder.getTextCarbohydratesValue().setText(String.format(Locale.getDefault(), "%d", nutritions.getCarbohydrates()));
        holder.getTextFatsValue().setText(String.format(Locale.getDefault(), "%d", nutritions.getFats()));
        holder.getTextFiber().setText(String.format(Locale.getDefault(), "%d", nutritions.getFiber()));

        return row;
    }


    static class FoodHolder {
        TextView textNameValue;
        TextView textCaloriesValue;
        TextView textProteinsValue;
        TextView textCarbohydratesValue;
        TextView textFiber;
        TextView textFatsValue;


        public TextView getTextFiber() {
            return textFiber;
        }

        public void setTextFiber(TextView textFiber) {
            this.textFiber = textFiber;
        }


        public TextView getTextNameValue() {
            return textNameValue;
        }

        public void setTextNameValue(TextView textNameValue) {
            this.textNameValue = textNameValue;
        }

        public TextView getTextCaloriesValue() {
            return textCaloriesValue;
        }

        public void setTextCaloriesValue(TextView textCaloriesValue) {
            this.textCaloriesValue = textCaloriesValue;
        }

        public TextView getTextProteinsValue() {
            return textProteinsValue;
        }

        public void setTextProteinsValue(TextView textProteinsValue) {
            this.textProteinsValue = textProteinsValue;
        }

        public TextView getTextCarbohydratesValue() {
            return textCarbohydratesValue;
        }

        public void setTextCarbohydratesValue(TextView textCarbohydratesValue) {
            this.textCarbohydratesValue = textCarbohydratesValue;
        }

        public TextView getTextFatsValue() {
            return textFatsValue;
        }

        public void setTextFatsValue(TextView textFatsValue) {
            this.textFatsValue = textFatsValue;
        }
    }
}
