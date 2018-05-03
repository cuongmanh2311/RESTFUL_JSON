package com.example.dell.restful_json.Model.DowloadAllProduct;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Dell on 3/12/2018.
 */

public class DowloadDetailSp extends AsyncTask<String,Void,String> {
    @Override
    protected void onPreExecute() {

        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... strings) {

        try{
            URL url=new URL("http://192.168.174.2/qlspserver/api/product/?product_id="+strings[0]);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
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
        }catch (Exception e)
        {
        }
        return null;
    }
}

