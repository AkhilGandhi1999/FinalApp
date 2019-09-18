package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import static com.example.finalapp.App.Channel_1_id; //from app created
import static com.example.finalapp.App.Channel_2_id;

public class NotificationDemo extends AppCompatActivity {

   // public EditText et1, et2;

    public NotificationManagerCompat notificationManagerCompat;// compat for backward compatibility

    Context appCompatActivity;

    public NotificationDemo(Context context)
    {
        appCompatActivity =  context;
        notificationManagerCompat = NotificationManagerCompat.from(appCompatActivity);
    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_demo);

        notificationManagerCompat = NotificationManagerCompat.from(this);// no new required but manager compat for backward compatibility
       // initialize();


    }*/

    public void set_channel1(String title,String mess) // View important when using onclick function
    {

        Intent noti = new Intent(appCompatActivity, HomeFragment.class);
        PendingIntent noti_pen = PendingIntent.getActivity(appCompatActivity, 0, noti, 0);//Pending intent for activity

        Intent broadcast = new Intent(
                appCompatActivity, NotificationRec.class);
        broadcast.putExtra("toastmessage", mess); //Name needs to match put extra
        PendingIntent action_int = PendingIntent.getBroadcast(appCompatActivity, 0, broadcast, PendingIntent.FLAG_UPDATE_CURRENT);
        //Pending Intent for broadcasting the signal in the app
        // takes flag as last argument and req code as well


        Notification notification = new NotificationCompat.Builder(appCompatActivity, Channel_1_id)
                .setSmallIcon(R.drawable.ic_alarm)//Can have various properties
                .setContentTitle(title).setContentText(mess)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(noti_pen)
                .addAction(R.mipmap.ic_launcher, "Toast", action_int)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setColor(Color.BLUE)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();//category for type of notification to send

        notificationManagerCompat.notify(1, notification);//id helps in updating and doing other operations on the notification

    }

    public void set_channel2(String title,String mess) {


        Notification notification = new NotificationCompat.Builder(appCompatActivity, Channel_2_id)
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentTitle(title)
                .setContentText(mess).setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        //Always add small icon for notifications or app crashes
        notificationManagerCompat.notify(2, notification);
    }

    public void set_channel3(String title,String mess,Bitmap largeicon)
    {

        Notification notification = new NotificationCompat.Builder(appCompatActivity, Channel_2_id)
                .setSmallIcon(R.drawable.ic_alarm)
                .setLargeIcon(largeicon)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("lkajsd;lasdj;alskdj;asldkj;dafiuwipoeriuwepoirujksdfj;sjdfs;dlfkjds;lfkj s;fkjsn;dklfj;sldfkjs;ldfkjs;dfkljs;dflkjs;dflkjs;dlfkjs;dlfkjs;dflkjs;dlfkjs;dflk")
                .setBigContentTitle("Big Content")
                        .setSummaryText("Summary Text")
                )
                .setContentTitle(title)
                .setContentText(mess).setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        //Always add small icon for notifications or app crashes
        notificationManagerCompat.notify(3, notification);
    }



}
