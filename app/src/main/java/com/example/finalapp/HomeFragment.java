package com.example.finalapp;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;


public class HomeFragment extends Fragment   {

    private View view;
    private Button bt1, bt2;
    private TextView name;
    TimePickerDialog timePickerDialog;
    NotificationManagerCompat notificationManagerCompat;

    DatabaseReference userdata;
    DatabaseReference models1;
    ArrayList<Model> models = new ArrayList<>();

    RecyclerView recyclerView;
    MyAdaptor myAdaptor;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final NotificationDemo notificationDemo = new NotificationDemo(getContext());

        view = inflater.inflate(R.layout.fragment_home, null);

        userdata = FirebaseDatabase.getInstance().getReference("users");
        models1 = FirebaseDatabase.getInstance().getReference("models");

        recyclerView = view.findViewById(R.id.top);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        name = view.findViewById(R.id.username);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent out = new Intent(getContext(),SignOut.class);
                startActivity(out);
            }
        });

        view.findViewById(R.id.out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent out1 = new Intent(getContext(),SignOut.class);
                startActivity(out1);
            }
        });
        view.findViewById(R.id.list_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent list = new Intent(getContext(),ListMed.class);
                list.putExtra("type","MORNING");
                startActivity(list);
            }
        });
        view.findViewById(R.id.list_view1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent list = new Intent(getContext(),ListMed.class);
                list.putExtra("type1","AFTERNOON");
                startActivity(list);
            }
        });
        view.findViewById(R.id.list_view2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent list = new Intent(getContext(),ListMed.class);
                list.putExtra("type2","EVENING");
                startActivity(list);
            }
        });
        view.findViewById(R.id.list_view3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent list = new Intent(getContext(),ListMed.class);
                list.putExtra("type3","NIGHT");
                startActivity(list);
            }
        });

        models1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot m : dataSnapshot.getChildren())
                {
                    String json = m.getValue(String.class);
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<Model>>() {
                    }.getType();
                    models = gson.fromJson(json, type);
                    Collections.sort(models, Model.MORNING);
                    Log.i("olo",Integer.toString(models.size()));
                    myAdaptor = new MyAdaptor(getContext(), models);
                    recyclerView.setAdapter(myAdaptor);
                  //  Toast.makeText(getContext(),models.toString(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        userdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot userd : dataSnapshot.getChildren())
                {
                    User user = userd.getValue(User.class);
                    name.setText(user.name);
                 //  Toast.makeText(getContext(),"Welcome back " + user.name,Toast.LENGTH_LONG).show();
                }
            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }

}



