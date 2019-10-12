package com.example.finalapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;

public class BroadcastRec extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String noti = " ";
        NotificationDemo notificationDemo = new NotificationDemo(context);
        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);
        int can = intent.getIntExtra("cancel",0);

        if(can==900)
        {
            mediaPlayer.stop();
            Log.i("ee","rr");
        }
        else
        {
            noti =  intent.getStringExtra("final_not");
            Log.i("eeppoo",noti);
            notificationDemo.set_channel1(noti,"Working");
            mediaPlayer.start();
        }
    }
}
