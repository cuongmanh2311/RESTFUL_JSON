package com.example.dell.restful_json.Presenter2.XuLiLogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSON_login;
import com.example.dell.restful_json.Model.OOP.Customer;
import com.example.dell.restful_json.View2.Login.XuLiLoginImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 3/29/2018.
 */

public class XuLiLoginPresenterLogic implements XuLiLoginPresenterImp {
    XuLiLoginImp xuLiLoginImp;
    public XuLiLoginPresenterLogic(XuLiLoginImp xuLiLoginImp) {
        this.xuLiLoginImp = xuLiLoginImp;
    }

    @Override
    public void XuLiLogin(Context context,Customer customer) {
        DowloadJSON_login dowloadJSON_login=new DowloadJSON_login();
        dowloadJSON_login.execute(customer);
        try {
            String kq=dowloadJSON_login.get();
            if(kq!=null)
            {
                JSONObject jsonObject=new JSONObject(kq.toString());
                if(jsonObject.has("id"))
                {
                    Customer customer1=new Customer();
                    customer1.setName(jsonObject.getString("name"));
                    customer1.setId(jsonObject.getInt("id"));
                    customer1.setEmail(jsonObject.getString("email"));
                    customer1.setPassword(jsonObject.getString("password"));
                    customer1.setRole(jsonObject.getInt("role"));
                    Log.d("cuong",customer1.getName()+customer1.getId());


                    SharedPreferences sharedPreferences=context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();

                    editor.putString("username",customer1.getName());
                    editor.putInt("user_id",customer1.getId());
                    editor.putString("email",customer1.getEmail());
                    editor.putString("password",customer1.getPassword());
                    editor.putInt("role",customer1.getRole());
                    editor.commit();
                    xuLiLoginImp.getUserLogin(context,customer);
                }else{
                    String fall="Đăng nhập thất bại!";
                    xuLiLoginImp.NhanThongBao(fall);
                }
            }else{
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
