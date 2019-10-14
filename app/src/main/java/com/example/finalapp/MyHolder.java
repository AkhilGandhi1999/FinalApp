package com.example.finalapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView img1;
    TextView t1,t2,t3;
    ItemClickListener itemClickListener;


     MyHolder(@NonNull View itemView) {
        super(itemView);
        this.img1 = itemView.findViewById(R.id.img1);
        this.t1 = itemView.findViewById(R.id.txt1);
        this.t2 = itemView.findViewById(R.id.txt2);
        this.t3 = itemView.findViewById(R.id.start_end);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClickListener(view, getAdapterPosition());
    }
    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener = ic;
    }
}
