package com.example.finalapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListMed extends AppCompatActivity {

    public static final String SHARED_PEF = "savedpref";
    public static final String ARRAY_LIST = "ArrayList";
    // public static final String DES_LIST = "DesList";


    RecyclerView recyclerView;
    MyAdaptor myAdaptor;
    ArrayList<Model> models = new ArrayList<>();
    ArrayList<Model> models2 = new ArrayList<>();
    ArrayList<Model> models3 = new ArrayList<>();
    ArrayList<Model> models4 = new ArrayList<>();
    ArrayList<Model> models5 = new ArrayList<>();

    TextView text;

    String type, type2, type3, type4;


    public String title = " ", des = " ", ty = "";
    Model m = new Model();
    Model m2 = new Model();
    Model m3 = new Model();
    Model m4 = new Model();
    Model m5 = new Model();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 0:
                if (resultCode == Activity.RESULT_OK) {
                    title = data.getStringExtra("key1");
                    des = data.getStringExtra("key2");
                    ty = data.getStringExtra("key3");
                    Toast.makeText(getApplicationContext(), title, Toast.LENGTH_LONG).show();
                    models = getList(title, des, ty);
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


        text = (TextView) findViewById(R.id.na);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        type2 = intent.getStringExtra("type1");
        type3 = intent.getStringExtra("type2");
        type4 = intent.getStringExtra("type3");

        Toast.makeText(getApplicationContext(), type, Toast.LENGTH_SHORT).show();

        if (!(type == null)) {
            LoadData(type);
        } else if (!(type2 == null)) {
            LoadData(type2);
        } else if (!(type3 == null)) {
            LoadData(type3);
        } else if (!(type4 == null)) {
            LoadData(type4);
        }


        findViewById(R.id.float_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent list_all = new Intent(getApplicationContext(), InsertMed.class);
                startActivityForResult(list_all, 0);
            }
        });

    }

    private ArrayList<Model> getList(String title, String des, String ty) {

        m = new Model();
        m.setTitle(title);
        m.setDescription(des);
        m.setType(ty);
        m.setImg(R.drawable.medicine);
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
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
    }

    public void LoadData(String types) {
        String json;
        SharedPreferences sharedPreferences1 = getSharedPreferences(SHARED_PEF, Context.MODE_PRIVATE);
        json = sharedPreferences1.getString(ARRAY_LIST, null);
        if (json == null) {
            Toast.makeText(getApplicationContext(), "Nothing to Show", Toast.LENGTH_SHORT).show();
            return;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Model>>() {
        }.getType();

        models = gson.fromJson(json, type);


        if (types.equals("MORNING")) {

            text.setText("Morning Medicines");
            for (int i = 0; i < models.size(); i++) {
                m2 = models.get(i);
                if (m2.getType().equals(types)) {
                    models2.add(m2);
                }
            }

            myAdaptor = new MyAdaptor(getApplicationContext(), models2);
            recyclerView.setAdapter(myAdaptor);
        } else if (types.equals("AFTERNOON")) {

            text.setText("Afternoon Medicines");

            for (int i = 0; i < models.size(); i++) {
                m3 = models.get(i);
                if (m3.getType().equals(types)) {
                    models3.add(m3);
                }
            }

            myAdaptor = new MyAdaptor(getApplicationContext(), models3);
            recyclerView.setAdapter(myAdaptor);
        } else if (types.equals("EVENING")) {
            text.setText("Evening Medicines");

            for (int i = 0; i < models.size(); i++) {
                m4 = models.get(i);
                if (m4.getType().equals(types)) {
                    models4.add(m4);
                }
            }

            myAdaptor = new MyAdaptor(getApplicationContext(), models4);
            recyclerView.setAdapter(myAdaptor);
        } else if (types.equals("NIGHT")) {
            text.setText("Night Medicines");

            for (int i = 0; i < models.size(); i++) {
                m5 = models.get(i);
                if (m5.getType().equals(types)) {
                    models5.add(m5);
                }
            }

            myAdaptor = new MyAdaptor(getApplicationContext(), models5);
            recyclerView.setAdapter(myAdaptor);
        }

        //Collections.sort(models, Model.MORNING);

    }

}