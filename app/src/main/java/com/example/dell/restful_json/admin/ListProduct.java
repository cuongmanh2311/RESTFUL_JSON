package com.example.dell.restful_json.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dell.restful_json.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListProduct extends AppCompatActivity {
    String url = "https://cuongmanh2311.000webhostapp.com";
    ListView listProduct;
    ArrayList arrProduct;
    JSONArray jsonArrayProduct;
    JSONObject obj;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        listProduct = (ListView) findViewById(R.id.listProduct);
        toolbar=findViewById(R.id.toolbarAdd);



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        arrProduct = new ArrayList<>();

        Product p = new Product(this);
        jsonArrayProduct = p.getProduct();
        for(int i=0; i<jsonArrayProduct.length(); i++){
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArrayProduct.getJSONObject(i);
                arrProduct.add(jsonObject.get("name"));
                //Toast.makeText(this, jsonObject.get("name").toString(), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrProduct);
        listProduct.setAdapter(adapter);


        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ListProduct.this, position+"", Toast.LENGTH_SHORT).show();

                try {
                    Intent i = new Intent(ListProduct.this, DetailProductActivity.class);
                    i.putExtra("productDetail", jsonArrayProduct.get(position).toString());
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        listProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ListProduct.this);
                alert.setTitle("DELETE");
                alert.setIcon(R.mipmap.ic_launcher);
                alert.setMessage("Are you sure to delete?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Product p = new Product(ListProduct.this);
                        try {
                            JSONObject obj = (JSONObject) jsonArrayProduct.get(position);
                            //Toast.makeText(ListProduct.this, obj.get("id")+"", Toast.LENGTH_SHORT).show();
                            p.deleteProduct(Integer.parseInt(obj.get("id")+""));
                            arrProduct.remove(position);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alert.show();
                return false;
            }
        });


    }

    // create menu add
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_product, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // action click on menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuAdd:
                Intent i = new Intent(ListProduct.this, AddProductActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
