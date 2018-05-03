package com.example.dell.restful_json.View2.HienThiMenuLoaiSP;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.dell.restful_json.Adapter.AdapterHienThiSpTheoDm;
import com.example.dell.restful_json.Adapter.ExpandAdpater;
import com.example.dell.restful_json.Model.OOP.Categories;
import com.example.dell.restful_json.Model.OOP.GioHang;
import com.example.dell.restful_json.Model.OOP.Product;
import com.example.dell.restful_json.Presenter2.XuLiHienThiMenuLoaiSP.XuLiMenuLoaiSPPresenterLogic;
import com.example.dell.restful_json.Presenter2.XuLiHienThiToanBoSanPham.HienThiToanBoSpPresenterLogic;
import com.example.dell.restful_json.R;
import com.example.dell.restful_json.View2.HienThiSPTheoDM.HienThiSanPhamTheoMaDanhMuc;
import com.example.dell.restful_json.View2.HienThiThongTinCustomer.HienThiThongTinCus_activity;
import com.example.dell.restful_json.View2.ThongTinApp.ThongTinAppActivity;
import com.example.dell.restful_json.View2.ThueSach.GioHang_activity;
import com.example.dell.restful_json.View2.ThueSach.GioSach_activity;
import com.example.dell.restful_json.View2.ThueSach.ThongBaoKQAddBill;
import com.example.dell.restful_json.View2.TimKiemSanPham.Timkiem_activity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Dell on 3/29/2018.
 */

public class Trangchu_activity extends AppCompatActivity implements HienThiMenuImp ,HienThiAllSpImp,AppBarLayout.OnOffsetChangedListener{
    DrawerLayout drawerLayout;
    ViewFlipper viewFlipper;
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    ExpandableListView expandableListView;
    XuLiMenuLoaiSPPresenterLogic xuLiMenuLoaiSPPresenterLogic;
    HienThiToanBoSpPresenterLogic hienThiToanBoSpPresenterLogic;
    int user_id;
    String name,email,password;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    AdapterHienThiSpTheoDm adapterHienThiSpTheoDm;
    RecyclerView.LayoutManager layoutManager;
    boolean danglist=true;
    public static ArrayList<GioHang>gioHangArrayList;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.trangchu_layout);

        AnhXa();
        LoadLocale();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.mo,R.string.dong)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();



        xuLiMenuLoaiSPPresenterLogic=new XuLiMenuLoaiSPPresenterLogic(Trangchu_activity.this);
        xuLiMenuLoaiSPPresenterLogic.GetDataCategories();

        hienThiToanBoSpPresenterLogic=new HienThiToanBoSpPresenterLogic(Trangchu_activity.this);
        hienThiToanBoSpPresenterLogic.HienThiSp();

        appBarLayout.addOnOffsetChangedListener(this);

        //set image cho viewFlipper
        SetFilpper();

    }

    private void SetFilpper() {
        ArrayList<String>arrayList=new ArrayList<>();
        arrayList.add("http://swetiservices.com/wp-content/uploads/2016/04/email-marketing-ebook-slider.jpg");
        arrayList.add("http://www.creativindie.com/wp-content/uploads/2015/01/forest2.png");
        arrayList.add("http://www.bisonpictures.com/wp-content/uploads/2015/08/book_cover_mockup.png");
        arrayList.add("https://i.ytimg.com/vi/dF6SKMY6BWQ/maxresdefault.jpg");
        for(int i=0;i<arrayList.size();i++)
        {
            ImageView imageView=new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(arrayList.get(i)).into(imageView);
            //imagview phù hợp vs viewflipper
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animationRight= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_banner);
        Animation animationLeft= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_banner_left);
        viewFlipper.setInAnimation(animationRight);
        viewFlipper.setOutAnimation(animationLeft);
    }

    private void AnhXa() {
        drawerLayout=findViewById(R.id.drawerlayout);
        toolbar=findViewById(R.id.toolbar);
        expandableListView=findViewById(R.id.expanlistview);
        recyclerView=findViewById(R.id.recycleviewTrangChu);
        appBarLayout=findViewById(R.id.appbarlayout);
        viewFlipper=findViewById(R.id.viewFilpper);
        //cấp phát bộ nhớ nếu k có data
        if(gioHangArrayList!=null)
        {

        }else{
            gioHangArrayList=new ArrayList<>();
        }
    }
    @Override
    public void GetMenuCategories(List<Categories> categories) {
        Log.d("sp01",categories.get(0).getName());
        ExpandAdpater expandAdpater=new ExpandAdpater(this,categories);
        expandableListView.setAdapter(expandAdpater);
        expandAdpater.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m=getMenuInflater();
        m.inflate(R.menu.menu_main,menu);

       MenuItem item_login=menu.findItem(R.id.itemDangNhap);
        MenuItem item_dangxuat=menu.findItem(R.id.itemDangXuat);
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        user_id=intent.getIntExtra("id",0);
        Log.d("cuong3",name+" "+user_id);
        item_login.setTitle(name);
        email=intent.getStringExtra("email");
        password=intent.getStringExtra("password");

        SharedPreferences sharedPreferences=Trangchu_activity.this.getSharedPreferences("tenlogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name2",name);
        editor.putInt("id2",user_id);
        editor.commit();
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id2=item.getItemId();
        switch (id2)
        {
            case R.id.itemDangNhap:
                Intent intent2=new Intent(Trangchu_activity.this, HienThiThongTinCus_activity.class);
                intent2.putExtra("name",name);
                intent2.putExtra("email",email);
                intent2.putExtra("password",password);
                intent2.putExtra("id",user_id);
                startActivity(intent2);
                break;
            case R.id.itemGioHang:
                progressDialog=ProgressDialog.show(Trangchu_activity.this,"Loading...","Loading...");
                progressDialog.setMessage("Loading...");
                Thread t=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                });
                t.start();
                Intent intent=new Intent(Trangchu_activity.this, GioSach_activity.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
                break;
            case R.id.itemDangXuat:
                finish();
                break;
            case R.id.itemSearchTrangChu:
              Intent intentTimKiem=new Intent(Trangchu_activity.this, Timkiem_activity.class);
              startActivity(intentTimKiem);
                break;
            case R.id.itemCart:
                Intent iCart=new Intent(Trangchu_activity.this, GioHang_activity.class);
                startActivity(iCart);
                break;
            case R.id.itemTT:
                Intent iTT=new Intent(Trangchu_activity.this, ThongTinAppActivity.class);
                startActivity(iTT);
                break;
            case R.id.itemNgonNgu:
                showChangeLanguageDialog();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void showChangeLanguageDialog() {
        final String[]item={"English","Japanese","Vietnamese"};
        AlertDialog.Builder builder=new AlertDialog.Builder(Trangchu_activity.this);
        builder.setTitle(R.string.chonngongu);
        builder.setSingleChoiceItems(item, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0)
                {
                    setLocale("en");
                    recreate();
                }
                else if(i==1)
                {
                    setLocale("ja");
                    recreate();
                }
                else if(i==2)
                {
                    setLocale("vi");
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });
        AlertDialog mdialog=builder.create();
        mdialog.show();
    }

    private void setLocale(String lan) {
        Locale locale=new Locale(lan);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        //save data to share reference
        SharedPreferences.Editor editor= (SharedPreferences.Editor) getSharedPreferences("setting",MODE_PRIVATE).edit();
        editor.putString("My_lan",lan);
        editor.apply();
    }
    //load langua
    public void LoadLocale()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("setting", Activity.MODE_PRIVATE);
        String nn=sharedPreferences.getString("My_lan","");
        setLocale(nn);
    }
    @Override
    public void GetAllBook(List<Product> list) {
        Log.d("this",list.size()+"");
        if(danglist)
        {
            layoutManager= new GridLayoutManager(Trangchu_activity.this,2);
            adapterHienThiSpTheoDm=  new AdapterHienThiSpTheoDm(Trangchu_activity.this,R.layout.custom_layout_recycleview_sp,list);

        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterHienThiSpTheoDm);
        adapterHienThiSpTheoDm.notifyDataSetChanged();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
    }
}

