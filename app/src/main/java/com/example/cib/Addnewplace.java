package com.example.cib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Addnewplace extends AppCompatActivity {

    EditText cname;
    EditText clocation;
    EditText cnumber;
    EditText cprice;
    EditText cdescription;
    Button csubmit;
    String vId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addnewplace);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cname = findViewById(R.id.campName);
        clocation = findViewById(R.id.campLocation);
        cnumber = findViewById(R.id.contactNumber);
        cprice = findViewById(R.id.campPrice);
        cdescription = findViewById(R.id.editTextTextMultiLine);
        csubmit = findViewById(R.id.campSubmit);
        csubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cName = cname.getText().toString();
                String cLocation = clocation.getText().toString();
                String cNumber = cnumber.getText().toString();
                String cPrice = cprice.getText().toString();
                String cDescription = cdescription.getText().toString();

                Intent it = getIntent();
                String vName = null;
                String vLocation = null;
                String vNumber = null;
                String vPrice = null;
                String vDescription = null;

                if (it != null) {
                    vId = it.getStringExtra("id");
                    vName = it.getStringExtra("campname");
                    vLocation = it.getStringExtra("location");
                    vNumber = it.getStringExtra("number");
                    vPrice = it.getStringExtra("price");
                    vDescription = it.getStringExtra("description");

                    cname.setText(vName);
                    clocation.setText(vLocation);
                    cnumber.setText(vNumber);
                    cprice.setText(vPrice);
                    cdescription.setText(vDescription);

                }


                Database db = new Database(getApplicationContext(), "CampingRequest", null, 1);
                if (cName.length() == 0 || cLocation.length() == 0 || cNumber.length() == 0 || cPrice.length() == 0 || cDescription.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please Fill All The Data Field.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (vId != null) {
                        db.updateCamp(Long.valueOf(it.getStringExtra("id")), cName, cLocation, cNumber, cPrice, cDescription);
                        Toast.makeText(getApplicationContext(), "Record Updated..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Addnewplace.this, AppHome.class));
                    }else{
                        db.addCamp(cName, cLocation, cNumber, cPrice, cDescription);
                        Toast.makeText(getApplicationContext(), "Record Inserted..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Addnewplace.this, ViewCamps.class));
                    }

                }
            }
        });

    }
}