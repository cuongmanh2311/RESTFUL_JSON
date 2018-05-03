package com.example.dell.restful_json.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dell.restful_json.R;

public class MainActivity2 extends AppCompatActivity {
    String url = "https://cuongmanh2311.000webhostapp.com";

    Button btnProduct, btnCate, btnPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnProduct = (Button) findViewById(R.id.btnProduct);
        btnCate = (Button) findViewById(R.id.btnCate);
        btnPermission = (Button) findViewById(R.id.btnPermission);

        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, ListProduct.class);
                startActivity(i);
            }
        });

        btnCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, ListCategoryActivity.class);
                startActivity(i);
            }
        });

        btnPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, ListPermissionActivity.class);
                startActivity(i);
            }
        });


    }


}
