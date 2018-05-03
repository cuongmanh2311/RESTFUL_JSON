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
 * Created by Dell on 3/28/2018.
 */

public class login_activity extends AppCompatActivity {
    EditText edtEmailLogin,edtPassLogin;
    Button btnLogin,btnRegister;
    String email,pass;
    Customer customeR;
    private String link="https://cuongmanh2311.000webhostapp.com/users/login?";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap_layout);
        AnhXa();
        SuKien();
    }

    private void SuKien() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLiDangNhap();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLiDangKi();

            }
        });
    }

    private void XuLiDangKi() {
        Intent iDangKi=new Intent(login_activity.this,register_activity.class);
        startActivityForResult(iDangKi,RESULT_OK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK)
        {
            if(resultCode==261)
            {
                String email=data.getStringExtra("email");
                String password=data.getStringExtra("password");
                edtEmailLogin.setText(email);
                edtPassLogin.setText(password);
            }
        }
    }

    private void XuLiDangNhap() {
            customeR.setEmail(edtEmailLogin.getText().toString());
            customeR.setPassword(edtPassLogin.getText().toString());
            Login_JSON l=new Login_JSON();
            l.execute(customeR);

    }
    class Login_JSON extends AsyncTask<Customer,Void,Customer>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           /* edtEmailLogin.setText("");
            edtPassLogin.setText("");*/
        }

        @Override
        protected void onPostExecute(Customer customer) {
            super.onPostExecute(customer);
            Intent iTrangChu=new Intent(login_activity.this,headpage_activity.class);
            startActivity(iTrangChu);
        }

        @Override
        protected Customer doInBackground(Customer... customers) {
            try {
                customeR=customers[0];
                URL url=new URL(link+"email="+URLEncoder.encode(customeR.getEmail())
                       +"&password="+URLEncoder.encode(customeR.getPassword()));
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
                String name=jsonObject.getString("name");
                Log.d("kt1",name);

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
    }
    private void AnhXa() {
        edtEmailLogin=findViewById(R.id.edtEmailLogin);
        edtPassLogin=findViewById(R.id.edtPassLogin);
        btnLogin=findViewById(R.id.btnlogin);
        btnRegister=findViewById(R.id.btnRegisterLogin);
        customeR=new Customer();
    }
}
