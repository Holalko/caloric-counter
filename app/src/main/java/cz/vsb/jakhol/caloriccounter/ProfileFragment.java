package cz.vsb.jakhol.caloriccounter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cz.vsb.jakhol.caloriccounter.models.User;
import cz.vsb.jakhol.caloriccounter.stores.DayMenuStore;
import cz.vsb.jakhol.caloriccounter.stores.UserStore;

import java.util.Locale;


public class ProfileFragment extends Fragment {

    private EditText inputName;
    private EditText inputAge;
    private EditText inputWeight;
    private EditText inputHeight;
    private EditText inputGoalWeight;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initComponents(view);
        setValues();

        return view;
    }

    private void setValues() {
        User user = UserStore.getUser();

        inputName.setText(user.getNickname());
        inputAge.setText(String.format(Locale.getDefault(), "%d", user.getAge()));
        inputWeight.setText(String.format(Locale.getDefault(), "%1$,.2f", user.getWeight()));
        inputHeight.setText(String.format(Locale.getDefault(), "%1$,.2f", user.getHeightInCm()));
        inputGoalWeight.setText(String.format(Locale.getDefault(), "%1$,.2f", user.getGoalWeight()));

    }

    private void initComponents(View view) {
        inputName = view.findViewById(R.id.input_name);
        inputAge = view.findViewById(R.id.input_age);
        inputWeight = view.findViewById(R.id.input_weight);
        inputHeight = view.findViewById(R.id.input_height);
        inputGoalWeight = view.findViewById(R.id.input_goal_weight);

        Button changeButton;
        changeButton = view.findViewById(R.id.button_change);
        changeButton.setOnClickListener(onClickListener);
    }

    private Button.OnClickListener onClickListener = view -> {
        String name = inputName.getText().toString();
        int age = Integer.parseInt(inputAge.getText().toString());
        double weigth = getDoubleFromInput(inputWeight);
        double height = getDoubleFromInput(inputHeight);
        double goalWeight = getDoubleFromInput(inputGoalWeight);

        User user = UserStore.getUser();
        user.setWeight(weigth);
        user.setAge(age);
        user.setGoalWeight(goalWeight);
        user.setNickname(name);
        user.setHeightInCm(height);
        DayMenuStore.getDayMenu().countTotalNutrients();
        Toast.makeText(getContext(), "Profile changed", Toast.LENGTH_SHORT).show();

        view.getContext();
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    };

    private double getDoubleFromInput(EditText editText) {
        String value = editText.getText().toString().replace(",", ".");
        if ("".equals(value)) {
            return 0.;
        }
        value = value.replaceAll("\\s+", "");
        return Double.parseDouble(value);
    }

}
