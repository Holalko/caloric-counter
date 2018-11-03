package cz.vsb.jakhol.caloriccounter.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cz.vsb.jakhol.caloriccounter.services.NotificationsIntentService;

public class NotificationReceiver extends BroadcastReceiver {

    public NotificationReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context, NotificationsIntentService.class);
        context.startService(intent1);
    }
}