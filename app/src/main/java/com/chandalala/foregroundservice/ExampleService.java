package com.chandalala.foregroundservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;

public class ExampleService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //This will be triggered when we start our foreground service
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Onstart Command runs on the UI thread

        String input = intent.getStringExtra("inputExtra");

        //When you click the notification, this is what will happen
        Intent notificationIntent = new Intent(this, MainActivity.class);

        //To set the intent on a notification, you have to create a pending intent
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this
                ,0
                ,notificationIntent
                ,0);


        Notification notification = new Notification.Builder(this, App.CHANNEL_ID)
                .setContentTitle("Example foreground service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(pendingIntent)//runs when the notification has been clicked
                .build();

        startForeground(1, notification);

        //You can also stop service by calling stopSelf()

        //if its heavy work, do it on a background thread

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
