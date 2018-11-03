package cz.vsb.jakhol.caloriccounter.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import cz.vsb.jakhol.caloriccounter.activities.MainActivity;

public class NotificationsIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 3;

    public NotificationsIntentService() {
        super("NotificationsIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Caloric counter");
        builder.setContentText("Look how much calories did you received today!");
//        builder.setSmallIcon(R.drawable.whatever);
        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);
    }
}
