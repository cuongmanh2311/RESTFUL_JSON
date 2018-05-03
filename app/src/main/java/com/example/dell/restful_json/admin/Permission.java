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

public class Permission {
    private static Context context;
    private String url = "https://thoitranggymer.com";

    public Permission(Context c) {
        this.context = c;
    }

    public JSONArray getAllPermission() {
        JSONArray jsonArray = null;

        Get download =  new Get();
        download.execute(this.url + "/permission");

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

    public void getPermissionById(int id) {
        Get download = new Get();
        download.execute(this.url + "/permission/show/" + id);

        try {
            String data = download.get();
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void postPermission(String name) {
        String ten = name.replaceAll("\\s","+");
        Post post =  new Post();
        post.execute(this.url + "/permission/store?name=" + ten);

        try {
            String data = post.get();
            JSONObject obj = new JSONObject(data);
            if(Boolean.valueOf(obj.get("status").toString())) {
                Toast.makeText(context, "Create successfully", Toast.LENGTH_SHORT).show();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // api bi loi
//    public void updatePermission(String name) {
//        Post post =  new Post();
//        post.execute("https://cuongmanh2311.000webhostapp.com/permission/store?name=" + name);
//    }

    // api bi loi
//    public void deletePermission() {
//        Get download = new Get();
//        download.execute("https://cuongmanh2311.000webhostapp.com/permission/delete/" + id);
//
//        try {
//            String data = download.get();
//            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
//
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }

}
