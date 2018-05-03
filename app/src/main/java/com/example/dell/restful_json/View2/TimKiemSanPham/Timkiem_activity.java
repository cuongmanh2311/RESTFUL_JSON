package com.example.dell.restful_json.View2.TimKiemSanPham;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dell.restful_json.Adapter.AdapterHienThiSpTheoDm;
import com.example.dell.restful_json.Model.OOP.Product;
import com.example.dell.restful_json.Presenter2.XuLiTimKiemSanPham.SearchSPPresnterLogic;
import com.example.dell.restful_json.R;

import java.util.List;

/**
 * Created by Dell on 4/20/2018.
 */

public class Timkiem_activity extends AppCompatActivity implements TimkiemImp,SearchView.OnQueryTextListener{
    Toolbar toolbar;
    RecyclerView recyclerView;
    SearchSPPresnterLogic searchSPPresnterLogic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timkiem_layout);
        AnhXa();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbartimkiem);
        recyclerView=findViewById(R.id.recyTimKiem);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem,menu);
        MenuItem item=menu.findItem(R.id.itemSearch);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(item);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getListTimKiem(List<Product> list) {
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2 );
        AdapterHienThiSpTheoDm adapterHienThiSpTheoDm=new AdapterHienThiSpTheoDm(this,R.layout.custom_layout_recycleview_sp,list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterHienThiSpTheoDm);
        adapterHienThiSpTheoDm.notifyDataSetChanged();

    }
    //click enter to search product
    @Override
    public boolean onQueryTextSubmit(String query) {
        searchSPPresnterLogic=new SearchSPPresnterLogic(Timkiem_activity.this);
        searchSPPresnterLogic.SearchSP(query);

        return false;
    }
    //điền chữ tới đâu t  hì tìm tới đó
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
