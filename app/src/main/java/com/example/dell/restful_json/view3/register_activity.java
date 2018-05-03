package com.example.dell.restful_json.view3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.restful_json.Model.OOP.Customer;
import com.example.dell.restful_json.R;

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
 * Created by Dell on 3/18/2018.
 */

public class register_activity extends AppCompatActivity{
    EditText edtUsername,edtEmail,edtPass;
    Button btnRegister;
    public String link="https://cuongmanh2311.000webhostapp.com/users/store?";
    public static int REQUEST_CODE=261;
    Customer customeR;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangki_layout);
        AnhXa();
        SuKien();

    }

    private void SuKien() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLiDangKi();
            }
        });
    }

    private void XuLiDangKi() {
       /* String name=edtUsername.getText().toString();
        String email=edtEmail.getText().toString();
        String pass=edtPass.getText().toString();
        if()*/
        Customer c=new Customer();
        c.setName(edtUsername.getText().toString());
        c.setEmail(edtEmail.getText().toString());
        c.setPassword(edtPass.getText().toString());
        c.setRole(1);
        GetData g=new GetData();
        g.execute(c);
    }

    private void AnhXa() {
      /*  edtUsername=findViewById(R.id.edtUsername);
        edtEmail=findViewById(R.id.edtEmailRegister);
        edtPass=findViewById(R.id.edtPassRegister);
        btnRegister=findViewById(R.id.btnRegister);
        customeR=new Customer();*/
    }

    public class GetData extends AsyncTask<Customer,Void,Boolean>
    {
        @Override
        protected Boolean doInBackground(Customer... customers) {
            try {
                customeR=customers[0];
                URL url=new URL(link+"name="+URLEncoder.encode(customeR.getName())+"&email="+URLEncoder.encode(customeR.getEmail())
                +"&role="+customeR.getRole()+"&password="+URLEncoder.encode(customeR.getPassword()));
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
                JSONObject jsonObject=new JSONObject(builder.toString());
                Boolean status=jsonObject.getBoolean("status");
                Log.d("kt1",status+" ");
                return status;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } /*catch (JSONException e) {
                e.printStackTrace();
            }*/ catch (JSONException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Boolean s) {
            super.onPostExecute(s);

            if(s.booleanValue()==true)
            {
                Toast.makeText(register_activity.this,"Đăng kí thành công",Toast.LENGTH_LONG).show();
                Intent intent=getIntent();
                intent.putExtra("email",customeR.getEmail());
                intent.putExtra("password",customeR.getPassword());
                setResult(REQUEST_CODE,intent);
                finish();

            }else{
                Toast.makeText(register_activity.this,"Đăng kí thất bại",Toast.LENGTH_LONG).show();
            }
        }


    }
}
