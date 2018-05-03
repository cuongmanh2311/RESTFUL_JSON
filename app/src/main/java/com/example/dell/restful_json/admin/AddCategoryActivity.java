package com.example.dell.restful_json.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dell.restful_json.R;

public class AddCategoryActivity extends AppCompatActivity {

    EditText edtName, edtParent, edtDes;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        edtName = (EditText) findViewById(R.id.edtName);
        edtParent = (EditText) findViewById(R.id.edtParent);
        edtDes = (EditText) findViewById(R.id.edtDes);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categories c = new Categories(AddCategoryActivity.this);
                c.postcategories(
                        edtName.getText().toString().trim(),
                        Integer.parseInt(edtParent.getText().toString().trim()),
                        edtDes.getText().toString().trim()
                );
            }
        });

    }
}
