package com.example.finalapp;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomeFragment extends Fragment   {

    private View view;
    private Button bt1, bt2;
    private TextView name;
    TimePickerDialog timePickerDialog;
    NotificationManagerCompat notificationManagerCompat;

    DatabaseReference userdata;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final NotificationDemo notificationDemo = new NotificationDemo(getContext());

        view = inflater.inflate(R.layout.fragment_home, null);

        userdata = FirebaseDatabase.getInstance().getReference("users");


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

        userdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot userd : dataSnapshot.getChildren())
                {
                    User user = userd.getValue(User.class);
                    name.setText(user.name);
                    Toast.makeText(getContext(),"Welcome back " + user.name,Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

     /*   view.findViewById(R.id.pick_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePicker();
                newFragment.show(getFragmentManager(),"TimePicker");
            }
        });
        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = et1.getText().toString();
                String mess = et2.getText().toString();
                notificationDemo.set_channel1(title,mess);
            }
        });

        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = et1.getText().toString();
                String mess = et2.getText().toString();
                notificationDemo.set_channel2(title,mess);
            }
        });

        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = et1.getText().toString();
                String mess = et2.getText().toString();
                Bitmap largeicon =  BitmapFactory.decodeResource(getResources(),R.drawable.bb);
                notificationDemo.set_channel3(title,mess,largeicon);
            }
        });*/
        return view;
    }

}



