package com.example.finalapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListMed extends AppCompatActivity {

    public static final String SHARED_PEF = "savedpref";
    public static final String ARRAY_LIST = "ArrayList";
    // public static final String DES_LIST = "DesList";


    RecyclerView recyclerView;
    //  RecyclerView recyclerView1;
    MyAdaptor myAdaptor;
    MyAdaptor myAdaptor1;
    ArrayList<Model> models = new ArrayList<>();
    ArrayList<Model> models1 = new ArrayList<>();


    public String title = " ", des = " ";
    Model m = new Model();
    Model m1 = new Model();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 0:
                if (resultCode == Activity.RESULT_OK) {
                    title = data.getStringExtra("key1");
                    des = data.getStringExtra("key2");
                    Toast.makeText(getApplicationContext(), title, Toast.LENGTH_LONG).show();
                    models = getList(title, des);
                    myAdaptor = new MyAdaptor(getApplicationContext(), models);
                    recyclerView.setAdapter(myAdaptor);
                    saveData();
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_med);

        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));


        LoadData();
        //myAdaptor = new MyAdaptor(getApplicationContext(), models);

        findViewById(R.id.float_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent list_all = new Intent(getApplicationContext(), InsertMed.class);
                startActivityForResult(list_all,0);
            }
        });
        //  recyclerView.setAdapter(myAdaptor);

    }

    private ArrayList<Model> getList(String title, String des) {

        m = new Model();
        m.setTitle(title);
        m.setDescription(des);
        m.setImg(R.drawable.pills);
        models.add(m);
        return models;

    }

    public void saveData() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PEF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(models);
        editor.putString(ARRAY_LIST, json);
        editor.apply();
      //  Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
    }

    public void LoadData() {
        String json;
        SharedPreferences sharedPreferences1 = getSharedPreferences(SHARED_PEF, Context.MODE_PRIVATE);
        json = sharedPreferences1.getString(ARRAY_LIST, null);
        if (json == null) {
            Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            return;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Model>>() {
        }.getType();

        models = gson.fromJson(json, type);


        //    m2 = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences1.getString(ARRAY_LIST, ObjectSerializer.serialize(new ArrayList<String>())));


        //  Toast.makeText(getApplicationContext(),models1.toString(), Toast.LENGTH_LONG).show();


      /* for(int i=0;i<models1.size();i++)
        {
            m = new Model();
            m.setTitle("News Feed");
            m.setDescription("This is news feed description");
            m.setImg(R.drawable.pills);
            Toast.makeText(getApplicationContext(), "Load", Toast.LENGTH_LONG).show();
        }*/
        Toast.makeText(getApplicationContext(), "Load", Toast.LENGTH_LONG).show();
        myAdaptor = new MyAdaptor(getApplicationContext(), models);
        recyclerView.setAdapter(myAdaptor);
    }

}