package com.example.scheduler;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;

public class AlarmReceiver extends BroadcastReceiver {
    Scheduler scheduler;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null){
            int id = extras.getInt("id");
            getSchedular(id, context);
        }
    }

    private void getSchedular(int id, Context context){
        Cursor c = new DbSchedulersHelper(context).getAllData();

        if (c.getCount() != 0) {
            List<Scheduler> array = new ArrayList<>();
            while (c.moveToNext()) {
                Scheduler cl = new Scheduler(Integer.parseInt(c.getString(0)),
                        Integer.parseInt(c.getString(1)),
                        Long.parseLong(c.getString(2)),
                        (long)Integer.parseInt(c.getString(3)),
                        c.getString(1));
                array.add(cl);
            }

            for (int i = 0 ; i < array.size() ; i++){
                if (array.get(i).id == id){
                    scheduler = array.get(i);
                }
            }
        }

        createNotification(context);
    }

    public void createNotification(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_starlut";
            String description = "schedular channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_starlut", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(context, AlarmShow.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("id", scheduler.id);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "channel_starlut");


        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        String time = new SimpleDateFormat("hh:mm a").format(scheduler.timeDifference);

        mBuilder.setContentTitle("Meeting at " + time);
        String timeDif = scheduler.timeDifference / 60 / 1000 + " min";
        mBuilder.setContentText("Click for details");
        mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText("Click for details"));

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(scheduler.id, mBuilder.build());
    }
}
