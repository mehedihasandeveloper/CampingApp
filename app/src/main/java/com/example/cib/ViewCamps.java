package com.example.cib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewCamps extends AppCompatActivity {
    ArrayList list;

    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_camps);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Database db = new Database(getApplicationContext(), "CampingRequest", null, 1);
        list = new ArrayList<>();
        list = db.getCamps();

        sa = new SimpleAdapter(this, list, R.layout.template_design,
                new String[]{"campname", "location", "number", "price"},
                new int[]{R.id.textView25, R.id.textView26, R.id.textView27, R.id.textView28}

        ){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
//                ImageView editBtn = v.findViewById(R.id.emp_edit_btn);
                ImageView delBtn = v.findViewById(R.id.emp_del_btn);


                delBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HashMap<String, String> employee;
                        try {
                            employee = (HashMap<String, String>) list.get(position);
                            System.out.println(employee);
                            list.remove(position);
                            notifyDataSetChanged();
                        } catch (Exception e) {

                        }
                    }
                });
//                editBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        HashMap<String, String> employee = null;
//                        try {
//                            employee = (HashMap<String, String>) list.get(position);
//                            System.out.println(employee);
//                        } catch (Exception e) {
//                        }
//                        Intent intent = new Intent(ViewCamps.this, Addnewplace.class);
//
//                        intent.putExtra("id", employee.get("id"));
//                        intent.putExtra("campname", employee.get("campname"));
//                        intent.putExtra("location", employee.get("location"));
//                        intent.putExtra("number", employee.get("number"));
//                        intent.putExtra("price", employee.get("price"));
//                        intent.putExtra("description", employee.get("description"));
//
//                        startActivity(intent);
//                    }
//                });
                return v;
            }
        };
        ListView lv = findViewById(R.id.lv);
        lv.setAdapter(sa);
    }
}