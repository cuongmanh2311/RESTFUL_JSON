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

/**
 * Created by Dell on 4/10/2018.
 */
public class DowloadJSON_product extends AsyncTask<Integer,Void,String> {
    String duongdan="";
    public DowloadJSON_product (String link)
    {
        this.duongdan=link;
    }
    @Override
    protected String doInBackground(Integer... integers) {
        try {
            URL url=new URL(duongdan+integers[0]);
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
