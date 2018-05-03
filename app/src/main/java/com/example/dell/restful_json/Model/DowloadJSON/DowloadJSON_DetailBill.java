package com.example.dell.restful_json.Model.DowloadJSON;

import android.os.AsyncTask;

import com.example.dell.restful_json.Model.LinkAPI;
import com.example.dell.restful_json.Model.OOP.BIllDeatil;
import com.example.dell.restful_json.Model.OOP.Bill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Dell on 4/24/2018.
 */

public class DowloadJSON_DetailBill extends AsyncTask<BIllDeatil, Void, String> {
    @Override
    protected String doInBackground(BIllDeatil... bIllDeatils) {
        try {
            BIllDeatil bIllDeatil=bIllDeatils[0];
            URL url=new URL(LinkAPI.API_BillDetail+"bill_id="+bIllDeatil.getBill_id()+"&pro_id="+bIllDeatil.getPro_id()+"&price="
                    +bIllDeatil.getPrice()+"&quantity="+bIllDeatil.getQuantity()+"&total="+bIllDeatil.getTotal());
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


