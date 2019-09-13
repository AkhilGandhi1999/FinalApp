package com.example.finalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment implements View.OnClickListener {

     private View view;
    private Button bt1;
     private FirebaseAuth mAuth;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        view = inflater.inflate(R.layout.fragment_home, null);
        bt1 = view.findViewById(R.id.buttonsign_out);
        bt1.setOnClickListener(this);
        return view;


    }

    @Override
    public void onClick(View view) {

        FirebaseAuth.getInstance().signOut();
     Intent main = new Intent(getContext(),MainActivity.class);
     startActivity(main);
    }
}
