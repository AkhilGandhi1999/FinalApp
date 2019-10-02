package com.example.finalapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
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


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);


        initialize();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null)
        {
          login();
        }


        login_bt.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                loginUser();
            }
        });

        bt1.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent signup_int = new Intent(MainActivity.this,SignUp.class);
                startActivityForResult(signup_int,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case 1:
                if(resultCode== Activity.RESULT_OK)
                {
                    Toast.makeText(MainActivity.this, "You Can Now Login", Toast.LENGTH_LONG).show();

                }
                    break;

        }
    }

    public void loginUser() {
        String email = this.email_ed.getText().toString();
        String password = this.pass_ed.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(MainActivity.this, "Please Enter your email id", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this, "Please Enter your Password", Toast.LENGTH_LONG).show();
        } else {


            this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();
                        email_ed.setText("");
                        pass_ed.setText("");
                        login();

                    }
                    else {
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void login()
    {
        Intent profile = new Intent(MainActivity.this, navbar.class);
       // profile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(profile);
        finish();
    }
    private void initialize() {
        bt1 = (Button) findViewById(R.id.button2);
        login_bt = (Button) findViewById(R.id.button);
        email_ed = (EditText) findViewById(R.id.editText3);
        pass_ed = (EditText) findViewById(R.id.editText4);
    }
}
