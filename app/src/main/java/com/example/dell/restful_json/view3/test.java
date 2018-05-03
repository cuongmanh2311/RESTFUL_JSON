package com.example.dell.restful_json.view3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.dell.restful_json.Adapter.CustomProductAdapter;
import com.example.dell.restful_json.Model.LinkAPI;
import com.example.dell.restful_json.Model.OOP.Product;
import com.example.dell.restful_json.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 4/8/2018.
 */

public class test extends AppCompatActivity {
    ListView listView;
    List<Product>list;
    CustomProductAdapter customProductAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.test_layout);
        AnhXa();




    }

    private void AnhXa() {
      //  listView=findViewById(R.id.listviewP);
        list=new ArrayList<>();
        customProductAdapter=new CustomProductAdapter(this,R.layout.custom_product_adapter,list);
        listView.setAdapter(customProductAdapter);
        customProductAdapter.notifyDataSetChanged();
        DowloadProduct dowloadProduct=new DowloadProduct();
        dowloadProduct.execute();
    }

    public class DowloadProduct extends AsyncTask<String,Void,List<Product>>
    {
        @Override
        protected void onPostExecute(List<Product> products) {
            super.onPostExecute(products);

            customProductAdapter.clear();
            customProductAdapter.addAll(products);
        }

        @Override
        protected List<Product> doInBackground(String... strings) {

            List<Product>listProduct=new ArrayList<>();
            try {
                URL url=new URL(LinkAPI.link_product);
                HttpURLConnection   httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                InputStreamReader inputStreamReader=new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line=bufferedReader.readLine();
                StringBuilder builder=new StringBuilder();
                while(line!=null)
                {
                    builder.append(line);
                    line=bufferedReader.readLine();
                }
                JSONArray jsonArray=new JSONArray(builder.toString());
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    Product product=new Product();
                    if(jsonObject.has("image"))
                    {
                        product.setImage(jsonObject.getString("image"));
                        Log.d("hinh",product.getImage());
                        url=new URL(product.getImage());
                        httpURLConnection= (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        Bitmap bitmap= BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                        product.setBitmap(bitmap);
                    }
                    product.setId(jsonObject.getInt("id"));
                    product.setName(jsonObject.getString("name"));
                    product.setCate_id(jsonObject.getInt("cate_id"));
                    product.setPrice(jsonObject.getInt("price"));
                    product.setIntro(jsonObject.getString("intro"));
                    product.setDescription(jsonObject.getString("description"));
                    product.setView(jsonObject.getInt("view"));
                    listProduct.add(product);
                    Log.d("dd",product.getName()+"-"+product.getImage());
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return listProduct;
        }
    }
}
