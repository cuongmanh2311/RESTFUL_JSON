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

public class Users {
    private static Context context;
    private String url;

    public Users(Context c, String url) {
        this.context = c;
        this.url = url;
    }

    public void getAllUsers() {
        Get download =  new Get();
        download.execute(this.url + "/users");

        try {
            String data = download.get();
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

    public void getUserById(int id) {
        Get get = new Get();
        get.execute(this.url + "/users/show/" + id);

        try {
            String data = get.get();
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void login(String email, String password) {
        Post post =  new Post();
        post.execute(this.url + "/users/login?email=" + email + "&password=" + password);

        try {
            String data = post.get();
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void postUser(String name, String email, String password, int role) {
        Post post =  new Post();
        post.execute(this.url + "/users/store?name=" + name + "&email=" + email + "&password=" + password + "&role=" + role);

        try {
            String data = post.get();
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    // API loi
//    public void updateUser(int userId, String name, String email, String password, int role) {
//        Post post =  new Post();
//        post.execute("https://cuongmanh2311.000webhostapp.com/users/update?name=" + name + "&email=" + email + "&password=" + password + "&role=" + role);
//
//        try {
//            String data = post.get();
//            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
//
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }

        public void deleteUser(int userId) {
        Get get =  new Get();
        get.execute(this.url + "/users/destroy/" + userId);

        try {
            String data = get.get();
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void search(String name) {
        Get get =  new Get();
        get.execute(this.url + "/users/search/?name=" + name);

        try {
            String data = get.get();
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void Permission(int role) {
        Get get =  new Get();
        get.execute(this.url + "/users/permission/?role=" + role);

        try {
            String data = get.get();
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void UserPermission(int role) {
        Get get =  new Get();
        get.execute(this.url + "/users/user_permission/?role=" + role);

        try {
            String data = get.get();
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
