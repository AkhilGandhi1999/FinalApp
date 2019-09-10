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

public class MainActivity extends AppCompatActivity {
    Button bt1;
    private EditText email_ed;
    Button login_bt;
    private FirebaseAuth mAuth;
    private EditText pass_ed;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int)R.layout.activity_main);
        this.mAuth = FirebaseAuth.getInstance();
        initialize();
        this.login_bt.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.loginUser();
            }
        });
        this.bt1.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(view.getContext(), SignUp.class));
            }
        });
    }

    /* access modifiers changed from: private */
    public void loginUser() {
        String email = this.email_ed.getText().toString();
        String password = this.pass_ed.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please Enter your email id", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please Enter your Password", Toast.LENGTH_LONG).show();
        } else {
            this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this.getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
                        MainActivity.this.startActivity(new Intent(MainActivity.this, navbar.class));
                        MainActivity.this.finish();
                        return;
                    }
                    Toast.makeText(MainActivity.this.getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void initialize() {
        this.bt1 = (Button) findViewById(R.id.button2);
        this.login_bt = (Button) findViewById(R.id.button);
        this.email_ed = (EditText) findViewById(R.id.editText3);
        this.pass_ed = (EditText) findViewById(R.id.editText4);
    }
}
