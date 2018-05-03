package com.example.dell.restful_json.Model.DowloadJSON;

import android.os.AsyncTask;

import com.example.dell.restful_json.Model.LinkAPI;
import com.example.dell.restful_json.Model.OOP.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Dell on 4/13/2018.
 */

public class DowloadJSON_themsach extends AsyncTask<Integer,Void,String> {
    LinkAPI linkAPI = new LinkAPI();

    @Override
    protected String doInBackground(Integer... integers) {
        try {

            URL url = new URL(LinkAPI.link_sachdathue + "user_id=" + integers[0]
                    + "&pro_id=" + integers[1]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            StringBuilder builder = new StringBuilder();
            while (line != null) {
                builder.append(line);
                line = bufferedReader.readLine();
            }
            return builder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}