package com.example.finalapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;


public class NotificationRec extends BroadcastReceiver {
    DatabaseReference fetch;

    @Override
    public void onReceive(Context context, Intent intent) {
        //Also add this file in manifest as receiver for broadcasting the signal in the app
        Log.i("jjjj","jkjk");
      //  fetch = FirebaseDatabase.getInstance().getReference("rec");


        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);
        mediaPlayer.stop();
        String message = intent.getStringExtra("toastmessage"); //Name needs to match put extra
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();// to display the message
    }
}
