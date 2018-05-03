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
 * Created by Dell on 4/18/2018.
 */

public class Dowload_JSON_updateUser extends AsyncTask<Customer,Void,String> {
    @Override
    protected String doInBackground(Customer... customers) {
        try {
            Customer c=customers[0];
            URL url=new URL(LinkAPI.link_updateuser
                    +"id="+c.getId()
                    +"&name="+ URLEncoder.encode(c.getName())
                    +"&password="+URLEncoder.encode(c.getPassword()));
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
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
            return builder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
