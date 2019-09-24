package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertMed extends AppCompatActivity {

    private EditText med_name;
    private EditText med_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_med);

        init();

        findViewById(R.id.submit1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Submit();
            }
        });
    }

    private void Submit() {
        String med_T = med_time.getText().toString();
        String med_n = med_name.getText().toString();
        Toast.makeText(getApplicationContext(), med_n, Toast.LENGTH_LONG).show();
        Intent submit = new Intent();
        submit.putExtra("key1", med_n);
        submit.putExtra("key2", med_T);
        setResult(Activity.RESULT_OK, submit);
        finish();
    }

    private void init() {
        med_name = (EditText) findViewById(R.id.name_med);
        med_time = (EditText) findViewById(R.id.time_med);
    }
}
