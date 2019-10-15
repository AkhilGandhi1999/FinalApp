package com.example.finalapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MedDes extends AppCompatActivity {

    TextView t1, t2,t3;
    ImageView i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_des);

        //ActionBar actionBar = getSupportActionBar();
        init();

        Intent intent = getIntent();
        String ititle = intent.getStringExtra("title");
        String ides = intent.getStringExtra("des");
        byte[] mbytes = getIntent().getByteArrayExtra("image");
        String istart = intent.getStringExtra("start");




        Bitmap bitmap = BitmapFactory.decodeByteArray(mbytes, 0, mbytes.length);



        t1.setText(ititle);
        t2.setText(ides);
        t3.setText(istart);
        i1.setImageBitmap(bitmap);



    }

    private void init() {
        t1 = (TextView) findViewById(R.id.des_txt1);
        t2 = (TextView) findViewById(R.id.des_txt2);
        i1 = (ImageView) findViewById(R.id.img_des1);
        t3 = (TextView) findViewById(R.id.start_end);
    }
}
