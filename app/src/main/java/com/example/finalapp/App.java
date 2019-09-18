package com.example.finalapp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.widget.Button;

public class App extends Application {

    //Using Compat for backward comp
    //to define repetative things in oncreate method
    //notification channel is used to create the notification

    public static final String Channel_1_id = "channel1";//explain what the channel is about
    public static final String Channel_2_id = "channel2";//Describe the channel purpose
    public static final String Channel_3_id = "channel3";
    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }
    private void createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) // Above oreo we set the default properties which the use change
        {
            NotificationChannel channel = new NotificationChannel(
                    Channel_1_id,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("This is channel 1");
            NotificationChannel channel2 = new NotificationChannel(
                    Channel_2_id,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is channel 2");

            NotificationChannel channel3 = new NotificationChannel(
                    Channel_3_id,
                    "Channel 3",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel3.setDescription("This is Channel 3");
            //Manager AlWAYS USES SYSTEM SERVICES and creates various channels

            NotificationManager notman = getSystemService(NotificationManager.class);
            notman.createNotificationChannel(channel);
            notman.createNotificationChannel(channel2);
            notman.createNotificationChannel(channel3);
        }
    }
}
