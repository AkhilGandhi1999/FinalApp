package com.example.finalapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationRec extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Also add this file in manifest as receiver for broadcasting the signal in the app

        String message = intent.getStringExtra("toastmessage"); //Name needs to match put extra

        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();// to display the message
    }
}
