package com.example.finalapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {

    ImageView img1;
    TextView t1,t2;


    public MyHolder(@NonNull View itemView) {
        super(itemView);
        this.img1 = itemView.findViewById(R.id.img1);
        this.t1 = itemView.findViewById(R.id.txt1);
        this.t2 = itemView.findViewById(R.id.txt2);
    }
}
