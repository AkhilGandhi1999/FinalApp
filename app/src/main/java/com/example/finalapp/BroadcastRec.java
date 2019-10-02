package com.example.finalapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

public class BroadcastRec extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final NotificationDemo notificationDemo = new NotificationDemo(context);
        notificationDemo.set_channel1("Alarm","Working");
        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);
        mediaPlayer.start();
    }
}
