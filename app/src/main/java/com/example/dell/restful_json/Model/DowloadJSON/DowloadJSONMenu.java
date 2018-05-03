package com.example.dell.restful_json.Model.DowloadJSON;

import android.os.AsyncTask;
import android.util.Log;

import com.example.dell.restful_json.Model.LinkAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dell on 3/31/2018.
 */

public class DowloadJSONMenu extends AsyncTask<String,Void,String> {


    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url=new URL(LinkAPI.getLink_categoriesCon+strings[0]);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
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


            return builder.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
