package com.example.finalapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class InsertMed extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private EditText med_name;
    private EditText med_time;
    private EditText med_type;
    int set = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_med);

        init();
        findViewById(R.id.pick_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePicker();
                newFragment.show(getSupportFragmentManager(),"TimePicker");
            }
        });

        findViewById(R.id.pick_days).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertMed.this,DatePicker.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.submit1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Submit();
            }
        });
    }
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, BroadcastRec.class);
        set++;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, set, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    private void Submit() {
        String med_T = med_time.getText().toString();
        String med_n = med_name.getText().toString();
        String med_ty = med_type.getText().toString();

        Toast.makeText(getApplicationContext(), med_n, Toast.LENGTH_LONG).show();
        Intent submit = new Intent();
        submit.putExtra("key1", med_n);
        submit.putExtra("key2", med_T);
        submit.putExtra("key3",med_ty);
        setResult(Activity.RESULT_OK, submit);
        finish();
    }

    private void init() {
        med_name = (EditText) findViewById(R.id.name_med);
        med_type = (EditText) findViewById(R.id.time_med1);
    }

    @Override
    public void onTimeSet(android.widget.TimePicker timePicker, int i, int i1) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, i);
        c.set(Calendar.MINUTE, i1);
        c.set(Calendar.SECOND, 0);

        startAlarm(c);
    }
}
