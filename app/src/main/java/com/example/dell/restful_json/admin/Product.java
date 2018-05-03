package com.example.dell.restful_json.admin;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by HOME on 15-Apr-18.
 */

public class Product {
    private static Context context;
    private String url = "http://thoitranggymer.com";

    public Product(Context c) {
        this.context = c;
    }

    public JSONArray getProduct() {
        JSONArray jsonArray = null;

        Get get =  new Get();
        get.execute(this.url + "/products");

        try {
            String data = get.get();
            jsonArray = new JSONArray(data);

//            for(int i=0; i<jsonArray.length(); i++)
//            {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                Toast.makeText(context, jsonObject+"", Toast.LENGTH_LONG).show();
//            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    public void updateProduct(int id, String name, int category, int price, String intro, String description) {

        String des = description.replaceAll("\\s","+");
        String intr = intro.replaceAll("\\s","+");
        String ten = name.replaceAll("\\s","+");
        Post post =  new Post();
        post.execute(this.url + "/products/update?id=" + id + "&name=" + ten + "&cate_id=" + category + "&price=" + price + "&intro=" + intr + "&description=" + des);

        try {
            String data = post.get();
            JSONObject obj = new JSONObject(data);
            if(Boolean.valueOf(obj.get("status").toString())) {
                Toast.makeText(context, "Update successfully", Toast.LENGTH_SHORT).show();
            }
            //Toast.makeText(context, data+"", Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void postProduct(String name, int category, int price, String intro, String description,String image) {

        Post post =  new Post();
        post.execute(this.url + "/products/store?name=" + name + "&cate_id=" + category + "&price=" + price + "&intro=" + intro + "&description=" + description + "&image=" + image);
        try {
            String data = post.get();
            JSONObject obj = new JSONObject(data);
            if(Boolean.valueOf(obj.get("status").toString())) {
                Toast.makeText(context, "Create successfully", Toast.LENGTH_SHORT).show();
            }
            // Toast.makeText(context, data+"", Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void deleteProduct(int id) {

        Get get = new Get();
        get.execute(this.url + "/products/destroy/" + id );

        try {
            String data = get.get();
            JSONObject obj = new JSONObject(data);
            if(Boolean.valueOf(obj.get("status").toString())) {
                Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
            }
            //Toast.makeText(context, data+"", Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
