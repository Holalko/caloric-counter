package cz.vsb.jakhol.caloriccounter.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
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
import cz.vsb.jakhol.caloriccounter.receivers.NotificationReceiver;
import cz.vsb.jakhol.caloriccounter.scanner.IntentIntegrator;
import cz.vsb.jakhol.caloriccounter.scanner.IntentResult;
import cz.vsb.jakhol.caloriccounter.stores.DataStore;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final Long EVERY_DAY = 1000 * 60 * 60 * 24L;

    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        initBottomNavigation();


        initFirstFragment();
        setNotificationReceiver();
    }

    private Calendar getRepeaterTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 1);
        return calendar;
    }

    private void setNotificationReceiver() {
        Intent notifyIntent = new Intent(this,NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (getApplicationContext(),0  , notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                getRepeaterTime().getTimeInMillis(), pendingIntent);
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
