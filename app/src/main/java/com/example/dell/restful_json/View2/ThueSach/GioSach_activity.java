package com.example.dell.restful_json.View2.ThueSach;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.dell.restful_json.Adapter.AdapterHienThiSpTheoDm;
import com.example.dell.restful_json.Adapter.CustomAdapterSachDaThue;
import com.example.dell.restful_json.Model.OOP.Product;
import com.example.dell.restful_json.Presenter2.XuLiHienThiChiTietSanPham.XuLiDisplayCTSPPresenterlogic;
import com.example.dell.restful_json.Presenter2.XuLiHienThiSachDaThue.XuLiHienThiSachDaThuePresenterLogic;
import com.example.dell.restful_json.R;
import com.example.dell.restful_json.View2.HienThiMenuLoaiSP.Trangchu_activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 4/16/2018.
 */

public class GioSach_activity extends AppCompatActivity implements GioSachImp {
    int id_user;
    ListView listView;
    Toolbar toolbar;

    CustomAdapterSachDaThue customAdapterSachDaThue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sachdathue);

        Intent intent=getIntent();
        id_user=intent.getIntExtra("user_id",0);
        Log.d("cho",id_user+"");

        listView=findViewById(R.id.listviewShach);
        toolbar=findViewById(R.id.toolbarSachDaThue);
        setSupportActionBar(toolbar);


        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle("My books");


        XuLiHienThiSachDaThuePresenterLogic x=new XuLiHienThiSachDaThuePresenterLogic(GioSach_activity.this);
        x.HienThiSachThue(id_user);
    }
    @Override
    public void NhanMaSach(List<Integer> ds) {
        Log.d("nhan",ds.size()+"");
        XuLiDisplayCTSPPresenterlogic xuLiDisplayCTSPPresenterlogic=new XuLiDisplayCTSPPresenterlogic(GioSach_activity.this);
        xuLiDisplayCTSPPresenterlogic.GetSanPham(ds);
    }

    @Override
    public void NhanThongTinSach(ArrayList<Product> list) {
        Log.d("test", list.get(0).getName()+"--"+list.get(0).getImage()+"--"+list.get(0).getDescription());
        if(list!=null)
        {
            customAdapterSachDaThue = new CustomAdapterSachDaThue(GioSach_activity.this, R.layout.custom_layout_list_sp, list);
            listView.setAdapter(customAdapterSachDaThue);
            customAdapterSachDaThue.notifyDataSetChanged();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)

            finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
