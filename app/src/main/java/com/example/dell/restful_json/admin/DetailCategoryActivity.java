package com.example.dell.restful_json.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dell.restful_json.R;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailCategoryActivity extends AppCompatActivity {

    EditText edtName, edtParent, edtDes;
    Button btnUpdate, btnSave;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);
        edtName = (EditText) findViewById(R.id.edtName);
        edtParent = (EditText) findViewById(R.id.edtParent);
        edtDes = (EditText) findViewById(R.id.edtDes);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnSave = (Button) findViewById(R.id.btnSave);

        edtName.setEnabled(false);
        edtParent.setEnabled(false);
        edtDes.setEnabled(false);

        try {
            JSONObject obj = new JSONObject(getIntent().getStringExtra("categoryDetail"));
            id = obj.get("id").toString();
            edtName.setText(obj.get("name").toString());
            edtParent.setText(obj.get("parent_id").toString());
            edtDes.setText(obj.get("description").toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.setEnabled(true);
                edtParent.setEnabled(true);
                edtDes.setEnabled(true);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categories c = new Categories(DetailCategoryActivity.this);
                c.updateCategories(
                        Integer.parseInt(id),
                        edtName.getText().toString().trim(),
                        Integer.parseInt(edtParent.getText().toString().trim()),
                        edtDes.getText().toString().trim()
                );

                edtName.setEnabled(false);
                edtParent.setEnabled(false);
                edtDes.setEnabled(false);

            }
        });

    }
}
