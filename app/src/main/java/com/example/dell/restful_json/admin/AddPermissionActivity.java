package com.example.dell.restful_json.admin;

import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

import com.example.dell.restful_json.R;

/**
 * Created by Dell on 5/2/2018.
 */

public class AddPermissionActivity extends AppCompatActivity {
    EditText edtName;
    Button btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_permission);
        edtName = (EditText) findViewById(R.id.edtNamePermission);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Permission p = new Permission(AddPermissionActivity.this);
                p.postPermission(edtName.getText().toString().trim());
                edtName.setText("");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddPermissionActivity.this, ListPermissionActivity.class);
                startActivity(i);
            }
        });

    }
}
