package com.example.dell.restful_json.View2.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dell.restful_json.Model.OOP.Customer;
import com.example.dell.restful_json.Presenter2.XuLiLogin.XuLiLoginPresenterLogic;
import com.example.dell.restful_json.R;
import com.example.dell.restful_json.View2.Register.Register_activity;
import com.example.dell.restful_json.View2.HienThiMenuLoaiSP.Trangchu_activity;
import com.example.dell.restful_json.admin.MainActivity2;


/**
 * Created by Dell on 3/29/2018.
 */

public class Login_activity extends AppCompatActivity implements XuLiLoginImp {
    EditText edtEmailLogin,edtPassLogin;
    Button btnLogin;
    LinearLayout btnRegister;
    XuLiLoginPresenterLogic xuLiLoginPresenterLogic;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap_layout);
        AnhXa();
        SuKien();
    }
    //Add event
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

    //Register process
    private void XuLiDangKi() {
        Intent iDangKi=new Intent(Login_activity.this,Register_activity.class);
        startActivity(iDangKi);
    }

    //Login process
    private void XuLiDangNhap() {
        progressDialog=ProgressDialog.show(Login_activity.this,"Loading...","Loading...");
        progressDialog.setMessage("Loading...");
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        });
        t.start();
            xuLiLoginPresenterLogic=new XuLiLoginPresenterLogic(Login_activity.this);
            Customer customer=new Customer();
            customer.setEmail(edtEmailLogin.getText().toString());
            customer.setPassword(edtPassLogin.getText().toString());
            xuLiLoginPresenterLogic.XuLiLogin(Login_activity.this,customer);
    }
    //Add control
    private void AnhXa() {
        edtEmailLogin=findViewById(R.id.edtEmailLogin);
        edtPassLogin=findViewById(R.id.edtPassLogin);
        btnLogin=findViewById(R.id.btnlogin);
        btnRegister=findViewById(R.id.btnRegisterLogin);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

    }

    //Receive info of user and put info to home page
    @Override
    public void getUserLogin(Context context,Customer customer) {
        if(customer!=null)
        {
            SharedPreferences sharedPreferences=context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
            String user_name=sharedPreferences.getString("username","");
            int id=sharedPreferences.getInt("user_id",0);
            String email=sharedPreferences.getString("email","");
            String password=sharedPreferences.getString("password","");
            int role=sharedPreferences.getInt("role",0);

            Log.d("cuong2",user_name+" "+id);


            if(role==1)
            {
                Intent imain2=new Intent(Login_activity.this, MainActivity2.class);
                startActivity(imain2);
            }if(role==4){
                Intent iTrangChu=new Intent(Login_activity.this, Trangchu_activity.class);
                iTrangChu.putExtra("name",user_name);
                iTrangChu.putExtra("id",id);
                iTrangChu.putExtra("email",email);
                iTrangChu.putExtra("password",password);
                startActivity(iTrangChu);
            }
        }else{
            Toast.makeText(Login_activity.this,R.string.loginfail,Toast.LENGTH_LONG).show();
        }
    }

    //Receive info login failed
    @Override
    public void NhanThongBao(String s) {
        Toast.makeText(Login_activity.this, R.string.loginfail, Toast.LENGTH_SHORT).show();
    }
}
