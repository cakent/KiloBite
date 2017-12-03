package com.bignerdranch.android.kilobite;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import java.util.Calendar;
import java.util.List;

/**
 * Created by kent on 12/3/17.
 */

public class NotifcationSender extends BroadcastReceiver {

    private User mUser;
    @Override
    public void onReceive(Context context, Intent intent)  {
        mUser=UserLab.get(context).getUser(1);

        List<String> prefDays= mUser.getPrefDays();
        Calendar c = Calendar.getInstance();
        String dayOfWeek = getDayOfWeek(c.get(Calendar.DAY_OF_WEEK));
        if(prefDays.contains(dayOfWeek)) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
            Intent intent2 = new Intent(context, DashboardActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent2, 0);
            builder.setContentIntent(pendingIntent);
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
            builder.setContentTitle("Get up! It's time to work!");
            builder.setContentText("You have work to do. Good luck!");
            builder.setSubText("Tap to view Workout");
            builder.setAutoCancel(true);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            // Will display the notification in the notification bar
            notificationManager.notify(1, builder.build());
        }

    }

    private String getDayOfWeek(int value){
        String day = "";
        switch(value){
            case 1:
                day="Sunday";
                break;
            case 2:
                day="Monday";
                break;
            case 3:
                day="Tuesday";
                break;
            case 4:
                day="Wednesday";
                break;
            case 5:
                day="Thursday";
                break;
            case 6:
                day="Friday";
                break;
            case 7:
                day="Saturday";
                break;
        }
        return day;


}}

