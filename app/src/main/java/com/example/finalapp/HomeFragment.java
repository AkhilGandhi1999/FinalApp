package com.example.finalapp;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;


import com.google.firebase.auth.FirebaseAuth;



public class HomeFragment extends Fragment   {

    private View view;
    private Button bt1, bt2;
    private FirebaseAuth mAuth;
    private EditText et1,et2;
    TimePickerDialog timePickerDialog;
    NotificationManagerCompat notificationManagerCompat;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final NotificationDemo notificationDemo = new NotificationDemo(getContext());

        mAuth = FirebaseAuth.getInstance();
        view = inflater.inflate(R.layout.fragment_home, null);

        et1 = view.findViewById(R.id.ed1);
        et2 = view.findViewById(R.id.ed2);

        view.findViewById(R.id.buttonsign_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent main = new Intent(getContext(), MainActivity.class);
                main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
            }
        });

        view.findViewById(R.id.pick_time).setOnClickListener(new View.OnClickListener() {
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
        });
        return view;
    }




}




