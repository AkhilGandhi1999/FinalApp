package com.example.finalapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private EditText confirm_ed;
    private EditText email_ed;
    private FirebaseAuth mAuth;
    private EditText pass_ed;
    private Button sign_up;
    private EditText name, age, gender;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_sign_up);


        initialize();
        mAuth = FirebaseAuth.getInstance();
        sign_up.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                reg_new();
            }
        });
    }


    public void reg_new() {

        String email = email_ed.getText().toString();
        String password = pass_ed.getText().toString();
        String n = name.getText().toString();
        String a = age.getText().toString();
        String g = gender.getText().toString();

        if (!TextUtils.equals(password, confirm_ed.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Password not match", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
        } else {
            this.mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUp.this.getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();

                        Intent nav = new Intent(SignUp.this, navbar.class);
                        nav.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(nav);
                        finish();

                    } else {
                        Toast.makeText(SignUp.this.getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }

    private void initialize() {
        email_ed = (EditText) findViewById(R.id.editText);
        pass_ed = (EditText) findViewById(R.id.editText2);
        confirm_ed = (EditText) findViewById(R.id.editText5);
        name = (EditText) findViewById(R.id.user_name);

        age = (EditText) findViewById(R.id.user_age);

        gender = (EditText) findViewById(R.id.user_gender);

        sign_up = (Button) findViewById(R.id.button3);
    }
}
