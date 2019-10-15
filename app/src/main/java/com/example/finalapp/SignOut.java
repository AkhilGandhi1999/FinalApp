package com.example.finalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignOut extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextView t1,t2,t3;
    DatabaseReference databaseuser ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out);

        mAuth = FirebaseAuth.getInstance();
        init();

        databaseuser = FirebaseDatabase.getInstance().getReference("users");

        databaseuser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot m : dataSnapshot.getChildren()) {
                        User user = new User();
                        user = m.getValue(User.class);
                        t1.setText(user.name);
                        t2.setText(user.email);
                        t3.setText("Age : " + user.age);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        findViewById(R.id.buttonsign_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent mains = new Intent(SignOut.this,MainActivity.class);
                mains.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mains);
                finishAffinity();
            }
        });

    }
    private void init()
    {
        t1 = (TextView) findViewById(R.id.out1);
        t2 = (TextView) findViewById(R.id.out2);
        t3 = (TextView) findViewById(R.id.out3);

    }
}
