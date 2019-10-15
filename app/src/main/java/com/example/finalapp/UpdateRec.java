package com.example.finalapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class UpdateRec extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       InsertMed med = new InsertMed(9090);
    }
}
