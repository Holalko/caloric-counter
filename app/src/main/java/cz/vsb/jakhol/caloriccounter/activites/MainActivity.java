package cz.vsb.jakhol.caloriccounter.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import cz.vsb.jakhol.caloriccounter.fragments.ProfileFragment;
import cz.vsb.jakhol.caloriccounter.R;
import cz.vsb.jakhol.caloriccounter.fragments.AddFoodFragment;
import cz.vsb.jakhol.caloriccounter.fragments.FoodMenuFragment;
import cz.vsb.jakhol.caloriccounter.fragments.OverAllFragment;
import cz.vsb.jakhol.caloriccounter.models.User;
import cz.vsb.jakhol.caloriccounter.scanner.IntentIntegrator;
import cz.vsb.jakhol.caloriccounter.scanner.IntentResult;
import cz.vsb.jakhol.caloriccounter.stores.DataStore;

public class MainActivity extends AppCompatActivity {


    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        initBottomNavigation();


        initFirstFragment();
    }

    private void initFirstFragment() {
        fragmentManager = getSupportFragmentManager();
        DataStore dataStore = new DataStore(getApplicationContext());
        User user = dataStore.getUser();

        if(user == null){
            fragment = new ProfileFragment();
        } else {
            fragment = new OverAllFragment();
        }

        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.board_container, fragment).commit();
    }

    private void initBottomNavigation() {
        BottomNavigationView bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.inflateMenu(R.menu.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(bottomNavigationSelect);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationSelect = item -> {
        int id = item.getItemId();
        fragmentManager = getSupportFragmentManager();
        switch (id) {
            case R.id.navigation_overall: {
                fragment = new OverAllFragment();
                break;
            }
            case R.id.navigation_add: {
                fragment = new AddFoodFragment();
                break;
            }
            case R.id.navigation_profile: {
                fragment = new ProfileFragment();
                break;
            }
            case R.id.navigation_menu: {
                fragment = new FoodMenuFragment();
                break;
            }
            default:
                return false;
        }
        DataStore dataStore = new DataStore(getApplicationContext());
        User user = dataStore.getUser();

        if(user == null){
            fragment = new ProfileFragment();
        }
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.board_container, fragment).commit();
        return true;

    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            EditText searchInput = findViewById(R.id.input_search);


            searchInput.setText(scanContent);
        }
    }
}
