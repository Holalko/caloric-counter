package cz.vsb.jakhol.caloriccounter.activites;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import cz.vsb.jakhol.caloriccounter.R;

public class BoardActivity extends Activity {

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        initBottomNavigation();

    }

    private void initBottomNavigation() {
        bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.inflateMenu(R.menu.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(bottomNavigationSelect);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationSelect = item -> {
        int id = item.getItemId();
        switch (id) {
            case R.id.navigation_overall : {
                return  true;
//                break;
            }
            case R.id.navigation_add : {
                return  true;
//                break;
            }
            case R.id.navigation_profile: {
                return  true;
//                break;
            }
            default:
                return false;

        }

    };

}
