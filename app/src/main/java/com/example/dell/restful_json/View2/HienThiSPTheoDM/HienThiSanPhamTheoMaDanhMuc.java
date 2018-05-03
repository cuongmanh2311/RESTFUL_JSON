package com.example.dell.restful_json.View2.HienThiSPTheoDM;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dell.restful_json.Adapter.AdapterHienThiSpTheoDm;
import com.example.dell.restful_json.Model.OOP.Product;
import com.example.dell.restful_json.Presenter2.XuLiHienThiSanPhamTheoDanhMuc.XuLiSPTheoDMPresenterLogic;
import com.example.dell.restful_json.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 4/11/2018.
 */

public class HienThiSanPhamTheoMaDanhMuc extends Fragment implements HienThiSPTheoDMImp, View.OnClickListener  {
    RecyclerView recyclerView;
    Button btnLocDanhMuc,btnThayDoiTrangThaiRecycler;
    List<Product> productList;
    AdapterHienThiSpTheoDm adapterHienThiSpTheoDm;
    boolean danglist=true;  //gridlayout
    RecyclerView.LayoutManager layoutManager;
    XuLiSPTheoDMPresenterLogic xuLiSPTheoDMPresenterLogic;
    Toolbar toolbar;
    int maloai;
    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.layout_hienthisanphamtheodanhmuc,container,false);
        setHasOptionsMenu(false);
        //ánh xạ
        recyclerView=view.findViewById(R.id.recycleViewHienThiSanPhamTheoDanhMuc);
       // btnLocDanhMuc=view.findViewById(R.id.btnLocDanhMuc);
        btnThayDoiTrangThaiRecycler=view.findViewById(R.id.btnThayDoiTrangThaiRecycler);
        toolbar=view.findViewById(R.id.toolbarSPTheoDM);
        productList=new ArrayList<>();

        Bundle bundle=getArguments();
        maloai=bundle.getInt("maloai");

        String tenloai=bundle.getString("tenloai");
        Log.d("hung",maloai+"---"+tenloai);

        xuLiSPTheoDMPresenterLogic=new XuLiSPTheoDMPresenterLogic(this);
        xuLiSPTheoDMPresenterLogic.GetSpTheoDm(maloai);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //((AppCompatActivity)getActivity()).getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle(tenloai);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack("TrangchuActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        btnThayDoiTrangThaiRecycler.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_main,menu);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id)
        {
            case R.id.btnThayDoiTrangThaiRecycler:
                danglist=!danglist;
                xuLiSPTheoDMPresenterLogic.GetSpTheoDm(maloai);
                break;
        }
    }

    @Override
    public void LayDanhSachSPTheoDm(List<Product> ds) {
        if(danglist)
        {
            layoutManager= new GridLayoutManager(getContext(),2);
            adapterHienThiSpTheoDm=  new AdapterHienThiSpTheoDm(getContext(),R.layout.custom_layout_recycleview_sp,ds);
        }else {
            layoutManager=new LinearLayoutManager(getContext());
            adapterHienThiSpTheoDm= new AdapterHienThiSpTheoDm(getContext(),R.layout.custom_layout_list_sp,ds);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterHienThiSpTheoDm);
        Log.d("test",ds.size()+"fff");
        adapterHienThiSpTheoDm.notifyDataSetChanged();
    }
}
