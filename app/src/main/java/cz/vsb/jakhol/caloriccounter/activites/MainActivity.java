package cz.vsb.jakhol.caloriccounter.activites;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import cz.vsb.jakhol.caloriccounter.R;
import cz.vsb.jakhol.caloriccounter.fragments.AddFoodFragment;
import cz.vsb.jakhol.caloriccounter.fragments.OverAllFragment;
import cz.vsb.jakhol.caloriccounter.models.DayMenu;

public class MainActivity extends AppCompatActivity {

    public static final DayMenu DAY_MENU = getDayMenu();

    // TODO return DayMenu from sqlite
    private static DayMenu getDayMenu() {
        return new DayMenu();
    }


    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        initBottomNavigation();
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