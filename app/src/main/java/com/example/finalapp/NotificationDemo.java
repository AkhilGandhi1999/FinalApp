package com.example.finalapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.finalapp.App.Channel_1_id;

public class NotificationDemo extends AppCompatActivity {
    public NotificationManagerCompat notificationManagerCompat;// compat for backward compatibility

    Context appCompatActivity;

    public NotificationDemo(Context context)
    {
        appCompatActivity =  context;
        notificationManagerCompat = NotificationManagerCompat.from(appCompatActivity);
    }


    public void set_channel1(String title,String mess) // View important when using onclick function
    {
        Intent noti = new Intent(appCompatActivity, DismissRec.class);
        PendingIntent noti_pen = PendingIntent.getActivity(appCompatActivity, 0, noti, 0);//Pending intent for activity

        Intent broadcast = new Intent(
                appCompatActivity, NotificationRec.class);
        Intent broadcast1 = new Intent(
                appCompatActivity, NotificationRec.class);
        broadcast1.putExtra("cancel",900);
        broadcast1.putExtra("toames",title);
        broadcast.putExtra("toastmessage", title); //Name needs to match put extra
        PendingIntent action_int = PendingIntent.getBroadcast(appCompatActivity, 0, broadcast, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent action_int1 = PendingIntent.getBroadcast(appCompatActivity, 1, broadcast1, PendingIntent.FLAG_UPDATE_CURRENT);

        //Pending Intent for broadcasting the signal in the app
        // takes flag as last argument and req code as well
        Bitmap largeIcon = BitmapFactory.decodeResource(appCompatActivity.getResources(), R.drawable.medicine);


        Notification notification = new NotificationCompat.Builder(appCompatActivity, Channel_1_id)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSmallIcon(R.drawable.ic_alarm)//Can have various properties
                .setLargeIcon(largeIcon)
                .setContentTitle(title).setContentText(mess)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(noti_pen)
                .addAction(R.mipmap.ic_launcher, "Taken", action_int)
                .addAction(R.mipmap.ic_launcher, "Dismiss", action_int1)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setColor(Color.BLUE)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();//category for type of notification to send

        notificationManagerCompat.notify(1, notification);//id helps in updating and doing other operations on the notification

    }

   /* public void set_channel2(String title,String mess) {


        Notification notification = new NotificationCompat.Builder(appCompatActivity, Channel_2_id)
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentTitle(title)
                .setContentText(mess).setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        //Always add small icon for notifications or app crashes
        notificationManagerCompat.notify(2, notification);
    }

    public void set_channel3(String title,String mess)
    {

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.medicine);
        Notification notification = new NotificationCompat.Builder(appCompatActivity, Channel_2_id)
                .setSmallIcon(R.drawable.ic_alarm)
                .setLargeIcon(largeIcon)
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
    }*/
}
