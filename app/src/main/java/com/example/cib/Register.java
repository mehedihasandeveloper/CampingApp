package com.example.cib;

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

public class Register extends AppCompatActivity {
    EditText registerFullName;
    EditText registerPass;
    EditText registerMail;
    EditText registerUsername;
    Button registerSubmit;
    TextView alreadyRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        registerFullName = findViewById(R.id.registerName);
        registerPass = findViewById(R.id.registerPassword);
        registerMail = findViewById(R.id.registerEmail);
        registerUsername = findViewById(R.id.signupUsername);
        registerSubmit = findViewById(R.id.registerButton);
        alreadyRegister = findViewById(R.id.login);
        alreadyRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        registerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = registerFullName.getText().toString();
                String password = registerPass.getText().toString();
                String eMail = registerMail.getText().toString();
                String userName = registerUsername.getText().toString();
                Database db = new Database(getApplicationContext(),"CIB", null, 1);
                if (fullname.length()==0 ||password.length()==0 ||eMail.length()==0 ||userName.length()==0) {
                    Toast.makeText(getApplicationContext(), "Please Fill All The Data Field.", Toast.LENGTH_SHORT).show();
                }else {
                    db.addUser(fullname, password, eMail, userName);
                    Toast.makeText(getApplicationContext(), "Record Inserted..", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, Login.class));
                }
            }
        });
    }
}