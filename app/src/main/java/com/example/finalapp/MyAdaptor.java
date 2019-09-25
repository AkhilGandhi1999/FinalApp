package com.example.finalapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MyAdaptor extends RecyclerView.Adapter<MyHolder> {


    Context c;
    ArrayList<Model> models;


    public MyAdaptor(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        holder.t1.setText(models.get(position).getTitle());
        holder.t2.setText(models.get(position).getDescription());
        holder.img1.setImageResource(models.get(position).getImg());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                String gtitle = models.get(position).getTitle();
                String gdes = models.get(position).getDescription();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) holder.img1.getDrawable();

                Bitmap bitmap = bitmapDrawable.getBitmap();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                byte[] bytes = stream.toByteArray();
                Intent change = new Intent(c, MedDes.class);
                change.putExtra("title", gtitle);
                change.putExtra("des", gdes);
                change.putExtra("image", bytes);
                change.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(change);

            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
