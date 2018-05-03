package com.example.dell.restful_json.Model.DowloadJSON;

import android.os.AsyncTask;
import android.util.Log;

import com.example.dell.restful_json.Model.LinkAPI;
import com.example.dell.restful_json.Model.OOP.Customer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Dell on 3/29/2018.
 */

public class DowloadJSON_login extends AsyncTask<Customer,Void,String>{
    @Override
    protected String doInBackground(Customer... customers) {
        try {
            Customer c=customers[0];
            //B1: khai báo đường dẫn url
            //chuỗi truyền lên thông qua parameter thì phải mã hóa nó =URLencoder.encode
            URL url=new URL(LinkAPI.link_login+"email="+ URLEncoder.encode(c.getEmail())
                    +"&password="+URLEncoder.encode(c.getPassword()));
            //B2: điền url vào browser
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            //dữ liệu put lên web là yêu cầu dạng JSON -còn dữ liệu bt thì k cần
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            //mở luồng đọc data từ URL
            InputStreamReader inputStreamReader=new InputStreamReader(httpURLConnection.getInputStream());
            //buffer có thể đọc nội dung chứa unicode
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
