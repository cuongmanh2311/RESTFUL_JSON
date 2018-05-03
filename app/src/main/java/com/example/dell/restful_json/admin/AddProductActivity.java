package com.example.dell.restful_json.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dell.restful_json.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity {

    EditText edtName, edtPrice, edtIntro, edtDes,edtImage;
    Button btnAdd;
    Spinner spinner;

    JSONArray jsonArray;
    ArrayList arrCate;
    ArrayList arrIdCate;
    int chooseId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        edtIntro = (EditText) findViewById(R.id.edtIntro);
        edtDes = (EditText) findViewById(R.id.edtDes);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        spinner = (Spinner) findViewById(R.id.spinner);
        edtImage=findViewById(R.id.edtImage);
        arrCate = new ArrayList<>();
        arrIdCate = new ArrayList<>();

        Categories c = new Categories(AddProductActivity.this);
        jsonArray = c.getCategories();
        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                //Toast.makeText(this, jsonObject.get("name")+"", Toast.LENGTH_SHORT).show();
                arrCate.add(jsonObject.get("name"));
                arrIdCate.add(jsonObject.get("id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter adapter = new ArrayAdapter(AddProductActivity.this, android.R.layout.simple_spinner_item, arrCate);
        spinner.setAdapter(adapter);

        // click item
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                chooseId = Integer.parseInt((arrIdCate.get(position).toString()));
                Toast.makeText(AddProductActivity.this, String.valueOf(chooseId), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = new Product(AddProductActivity.this);
                p.postProduct(
                        edtName.getText().toString().trim(),
                        chooseId,
                        Integer.parseInt(edtPrice.getText().toString().trim()),
                        edtIntro.getText().toString().trim(),
                        edtDes.getText().toString().trim(),
                        edtImage.getText().toString().trim()
                );
            }
        });

    }
}
