package com.example.finalapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MedDes extends AppCompatActivity {

    TextView t1, t2;
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

        Bitmap bitmap = BitmapFactory.decodeByteArray(mbytes,0,mbytes.length);

     //   actionBar.setTitle(ititle);

        t1.setText(ititle);
        t2.setText(ides);
        i1.setImageBitmap(bitmap);

    }

    private void init() {
        t1 = (TextView) findViewById(R.id.des_txt1);
        t2 = (TextView) findViewById(R.id.des_txt2);
        i1 = (ImageView) findViewById(R.id.img_des1);
    }
}
