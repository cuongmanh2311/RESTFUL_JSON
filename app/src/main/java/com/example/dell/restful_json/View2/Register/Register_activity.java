package com.example.dell.restful_json.View2.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.restful_json.Model.OOP.Customer;
import com.example.dell.restful_json.Presenter2.XuLiLogin.XuLiLoginPresenterLogic;
import com.example.dell.restful_json.Presenter2.XuLiRegister.XuLiRegisterPresenterLogic;
import com.example.dell.restful_json.R;
import com.example.dell.restful_json.view3.register_activity;

/**
 * Created by Dell on 3/29/2018.
 */

public class Register_activity extends AppCompatActivity implements XuLiRegisterImp {
    EditText edtUsername,edtEmail,edtPass;
    Button btnRegister;
    XuLiRegisterPresenterLogic xuLiRegisterPresenterLogic;
    String name,email,pass;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangki_layout);
        AnhXa();
        GetDataFromView();
        SuKien();

    }

    private void GetDataFromView() {
        name=edtUsername.getText().toString();
        email=edtEmail.getText().toString();
        pass=edtPass.getText().toString();
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
        GetDataFromView();
        Customer customer=new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(pass);
        customer.setRole(4);
        if(email.equals("")||pass.equals(""))
        {
            Toast.makeText(this, R.string.chuanhaptt, Toast.LENGTH_SHORT).show();
        }
        xuLiRegisterPresenterLogic=new XuLiRegisterPresenterLogic(Register_activity.this);
        xuLiRegisterPresenterLogic.XuLiRegister(customer);
    }

    private void AnhXa() {
        edtUsername=findViewById(R.id.edtUsernameDK);
        edtEmail=findViewById(R.id.edtEmaailDK);
        edtPass=findViewById(R.id.edtPassDK);
        btnRegister=findViewById(R.id.btnRegisterDK);
    }

    @Override
    public void NhanThongBao(Boolean s) {
        if(s.booleanValue()==true)
        {
            Toast.makeText(Register_activity.this,R.string.dkithanhcong,Toast.LENGTH_LONG).show();
            finish();
        }else{
            Toast.makeText(Register_activity.this,R.string.dkithatbai,Toast.LENGTH_LONG).show();
        }

    }
}
