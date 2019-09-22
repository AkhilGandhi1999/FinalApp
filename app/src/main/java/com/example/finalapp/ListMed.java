package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ListMed extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdaptor myAdaptor;
    ArrayList<Model> models = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_med);

        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        myAdaptor = new MyAdaptor(getApplicationContext(),getList());

        findViewById(R.id.float_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent list_all = new Intent(getApplicationContext(),ListMed.class);
                startActivity(list_all);
            }
        });

        recyclerView.setAdapter(myAdaptor);


    }

    private ArrayList<Model> getList()
    {

        Model m = new Model();
        m.setTitle("News Feed");
        m.setDescription("This is news feed description");
        m.setImg(R.drawable.pills);
        models.add(m);


        m = new Model();
        m.setTitle("News Feed");
        m.setDescription("This is news feed description");
        m.setImg(R.drawable.pills);
        models.add(m);


        m = new Model();
        m.setTitle("News Feed");
        m.setDescription("This is news feed description");
        m.setImg(R.drawable.pills);
        models.add(m);


        m = new Model();
        m.setTitle("News Feed");
        m.setDescription("This is news feed description");
        m.setImg(R.drawable.pills);
        models.add(m);

        m = new Model();
        m.setTitle("News Feed");
        m.setDescription("This is news feed description");
        m.setImg(R.drawable.pills);
        models.add(m);

        m = new Model();
        m.setTitle("News Feed");
        m.setDescription("This is news feed description");
        m.setImg(R.drawable.pills);
        models.add(m);

        m = new Model();
        m.setTitle("News Feed");
        m.setDescription("This is news feed description");
        m.setImg(R.drawable.pills);
        models.add(m);

        m = new Model();
        m.setTitle("News Feed");
        m.setDescription("This is news feed description");
        m.setImg(R.drawable.pills);
        models.add(m);
        return  models;

    }
}
