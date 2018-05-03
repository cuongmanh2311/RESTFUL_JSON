package com.example.dell.restful_json.Model.DowloadJSON;

import android.os.AsyncTask;

import com.example.dell.restful_json.Model.LinkAPI;
import com.example.dell.restful_json.Model.OOP.Bill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Dell on 4/24/2018.
 */

public class DowloadJSONBILL extends AsyncTask<Bill, Void, String> {
    @Override
    protected String doInBackground(Bill... bills) {
        try {
            Bill bill=bills[0];
            URL url=new URL(LinkAPI.API_Bill+"member_id="+bill.getMember_id()+"&total="+bill.getTotal()+"&status="+bill.getStatus());
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
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
