package cz.vsb.jakhol.caloriccounter.activites;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import cz.vsb.jakhol.caloriccounter.R;

public class BoardActivity extends Activity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.inflateMenu(R.menu.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });

    }
}
