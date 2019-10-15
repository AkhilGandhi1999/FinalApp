package com.example.finalapp;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class NotificationRec extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {

        String message = intent.getStringExtra("toastmessage");//Name needs to match put extra
        String dd = intent.getStringExtra("dates");
        String tak = "TAKEN";
        String ntak = "NOT TAKEN";
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(1);
        int ch = intent.getIntExtra("cancel",0);
        String msg1 = intent.getStringExtra("toames");

        if(ch==900)
        {
            GraphFragment graphFragment = new GraphFragment(msg1,dd,ntak);
        }
        else {

            GraphFragment graphFragment = new GraphFragment(message, dd, tak);
            //Toast.makeText(context, message, Toast.LENGTH_SHORT).show();// to display the message
        }
    }
}
