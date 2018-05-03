package com.example.dell.restful_json.View2.HienThiThongTinCustomer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.restful_json.Model.OOP.Customer;
import com.example.dell.restful_json.Presenter2.XuLiUpdateUser.UpdateUserLogic;
import com.example.dell.restful_json.Presenter2.XuLiUpdateUser.UpdateUserPresenterImp;
import com.example.dell.restful_json.R;
import com.example.dell.restful_json.View2.HienThiMenuLoaiSP.Trangchu_activity;
import com.example.dell.restful_json.View2.Login.Login_activity;

/**
 * Created by Dell on 4/18/2018.
 */

public class HienThiThongTinCus_activity extends AppCompatActivity implements UpdateUserImp{
    EditText edtName,edtEmail,edtPass;
    Button confirm;
    String name,email,pass;
    int id;
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_editnv);
        AnhXa();
        final Intent intent=getIntent();
        name=intent.getStringExtra("name");
        email=intent.getStringExtra("email");
        pass=intent.getStringExtra("password");
        id=intent.getIntExtra("id",0);
        edtName.setText(name);
        edtPass.setText(pass);
        edtEmail.setText(email);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLiUpdateUser();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(HienThiThongTinCus_activity.this,Login_activity.class);
                startActivity(intent1);
            }
        });
    }

    private void XuLiUpdateUser() {
        String mail=edtEmail.getText().toString();
        String pass=edtPass.getText().toString();
        if(!mail.equals("")&&!pass.equals(""))
        {
            Customer customer=new Customer();
            customer.setId(id);
            customer.setName(edtName.getText().toString());
            customer.setPassword(pass);
            UpdateUserLogic updateUserLogic=new UpdateUserLogic(HienThiThongTinCus_activity.this);
            updateUserLogic.UpdateUser(customer);
        }else{
            Toast.makeText(this, "bạn vui lòng nhập email hoặc password", Toast.LENGTH_SHORT).show();
        }


    }

    private void AnhXa() {
        edtName=findViewById(R.id.nameSua);
        edtEmail=findViewById(R.id.mailSua);
        edtPass=findViewById(R.id.pswrdSua);
        confirm=findViewById(R.id.confirmSua);
        imageView=findViewById(R.id.backSua);
    }

    @Override
    public void thongbaosuauser(boolean b) {
        Toast.makeText(this, "Update thành công", Toast.LENGTH_SHORT).show();
    }
}
