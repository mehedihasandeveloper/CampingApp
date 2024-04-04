package com.example.cib;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    EditText logUsername;
    EditText logPass;
    Button loginButton;

    TextView notRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        logUsername = findViewById(R.id.loginUsername);
        logPass = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginSubmit);
        notRegister = findViewById(R.id.newUser);

        notRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        Database db = new Database(getApplicationContext(), "CIB", null, 1);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userName = logUsername.getText().toString();
                String password = logPass.getText().toString();

                if(userName.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(), "Please Fill All The Data Field.", Toast.LENGTH_SHORT).show();
                }else {
                    if (db.login(userName,password)==1){
                        Toast.makeText(getApplicationContext(),"Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, Addnewplace.class));
                    }else {
                        Toast.makeText(getApplicationContext(),"Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}