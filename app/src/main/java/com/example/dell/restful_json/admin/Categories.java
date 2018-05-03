package com.example.dell.restful_json.admin;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by HOME on 08-Apr-18.
 */

public class Categories {
    private static Context context;
    private String url = "https://thoitranggymer.com";

    public Categories(Context c) {
        this.context = c;
    }

    public JSONArray getCategories() {
        JSONArray jsonArray = null;

        Get download =  new Get();
        download.execute(this.url + "/categories");

        try {
            String data = download.get();
            jsonArray = new JSONArray(data);
//            for(int i=0; i<jsonArray.length(); i++)
//            {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
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

    public JSONObject getCategoryById(int id) {
        JSONObject obj = null;
        Get download = new Get();
        download.execute(this.url + "/categories/show/" + id);

        try {
            String data = download.get();
            obj = new JSONObject(data);
            //Toast.makeText(context, data, Toast.LENGTH_SHORT).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // cuong chua lam xong

//    public void searchCategories(String name) {
//        Get download =  new Get();
//        download.execute("https://cuongmanh2311.000webhostapp.com/categories/search/" + name);
//
//        try {
//            String data = download.get();
//            Log.d("catesearch", data);
//            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
//
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }

    public void postcategories(String name, int id, String description) {
        Post post =  new Post();
        post.execute(this.url + "/categories/store?name=" + name + "&parent_id=" + id + "&description=" + description);
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

    public void updateCategories(int id, String name, int parent_id, String descripton) {
        String des = descripton.replaceAll("\\s","+");
        String ten = name.replaceAll("\\s","+");
        Post post = new Post();
        post.execute(this.url + "/categories/update?id=" + id + "&name=" + ten + "&parent_id=" + parent_id + "&description=" + des);

        try {
            String data = post.get();
            JSONObject obj = new JSONObject(data);
            if(Boolean.valueOf(obj.get("status").toString())) {
                Toast.makeText(context, "Update successfully", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategories(int id) {
        Get get = new Get();
        get.execute(this.url + "/categories/destroy/" + id);

        try {
            String data = get.get();
            JSONObject obj = new JSONObject(data);
            if(Boolean.valueOf(obj.get("status").toString())) {
                Toast.makeText(context, "delete successfully", Toast.LENGTH_SHORT).show();
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

    public void getAllChild(int id) {
        Get get = new Get();
        get.execute(this.url + "/categories/parent?id=" + id);

        try {
            String data = get.get();
            JSONArray jsonArray = new JSONArray(data);
            for(int i=0; i<jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Toast.makeText(context, jsonObject+"", Toast.LENGTH_LONG).show();
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
