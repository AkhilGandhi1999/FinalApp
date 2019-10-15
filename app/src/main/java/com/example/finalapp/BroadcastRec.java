package com.example.finalapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BroadcastRec extends BroadcastReceiver {

    String id = "",med = "";
    String temp;
    int k =0;
    DatabaseReference fetch;
    ArrayList<String> notify;
    AudioManager am;
    int off ;
    @Override
    public void onReceive(final Context context, Intent intent) {
       am =  (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        notify = new ArrayList<>();
        final NotificationDemo notificationDemo = new NotificationDemo(context);
        final MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);
        fetch = FirebaseDatabase.getInstance().getReference("times");
        /*off = intent.getIntExtra("cancel",0);

        if(off==900)
        {
            am.setRingerMode(1);
            Log.i("cancan","canac");
            return;
        }*/
        fetch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot m : dataSnapshot.getChildren())
                {
                    temp = m.getValue(String.class);
                    Log.i("pop",temp);
                    notify.add(temp);
                    Log.i("popo",notify.get(0));

                }
                String medkey = notify.get(0);
                boolean flag = true;
                for(int i = 0;i < medkey.length();i++){
                    if(medkey.charAt(i) == '!'){
                        flag = false;
                        continue;
                    }
                    if(flag){
                        med = med + medkey.charAt(i);
                    }
                    else{
                        id = id + medkey.charAt(i);
                    }
                }
                Log.i("popo",med + " " + id);

                notificationDemo.set_channel1(med,"Time to take these Medicines");
              //  mediaPlayer.start();
                fetch.child(id).setValue(null);
                fetch.removeEventListener(this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
