package com.example.dell.restful_json.Adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.restful_json.Model.OOP.Categories;
import com.example.dell.restful_json.Model.XuLiMenuJSON.XuLiJSONMenu;
import com.example.dell.restful_json.R;
import com.example.dell.restful_json.View2.HienThiSPTheoDM.HienThiSanPhamTheoMaDanhMuc;
import com.example.dell.restful_json.View2.Login.Login_activity;

import java.util.List;

/**
 * Created by Dell on 3/31/2018.
 */

public class ExpandAdpater extends BaseExpandableListAdapter {
    Context context;
    List<Categories>categoriesList;
    public  ExpandAdpater(Context context, List<Categories> categoriesList)
    {
        this.context=context;
        this.categoriesList=categoriesList;

        XuLiJSONMenu xuLiJSONMenu=new XuLiJSONMenu();
        for(int i=0;i<categoriesList.size();i++)
        {
            int maloai=categoriesList.get(i).getId();
            categoriesList.get(i).setListcon(xuLiJSONMenu.GetLoaiSPTheoMaLoaiCha(maloai));
        }
    }
    //đếm group cha
    @Override
    public int getGroupCount() {
        return categoriesList.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupCha) {
        //kt nếu viewcha có view còn thì return 1
        if(categoriesList.get(vitriGroupCha).getListcon().size()!=0)
        {
            return 1;
        }
        return 0;
    }

    @Override
    public Object getGroup(int vitriGroupCha) {
        return categoriesList.get(vitriGroupCha);
    }

    @Override
    public Object getChild(int vitriGroupCha, int vitriGroupCon) {
        return categoriesList.get(vitriGroupCha).getListcon().get(vitriGroupCon);
    }

    @Override
    public long getGroupId(int vitriGroupCha) {
        return categoriesList.get(vitriGroupCha).getId();
    }
    @Override
    public long getChildId(int vitriGroupCha, int vitriGroupCon) {
        return categoriesList.get(vitriGroupCha).getListcon().get(vitriGroupCon).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int vitriGroupCha, boolean isExpaned, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.custom_layout_groupcha,viewGroup,false);
        TextView txt=view.findViewById(R.id.txtLoaisp);
        ImageView img=view.findViewById(R.id.imgAdd);
        txt.setText(categoriesList.get(vitriGroupCha).getName());

        //đếm xem list cha có sản phẩm náo hay k
        int dem=categoriesList.get(vitriGroupCha).getListcon().size();
        if(dem>0)
        {
            img.setVisibility(View.VISIBLE);
        }else {
            img.setVisibility(View.INVISIBLE);
        }

         //kiểm tra nếu như xổ menu xuong thì hiển thi dấu -
        if(isExpaned)
        {
            img.setImageResource(R.mipmap.ic_remove_black_24dp);
            view.setBackgroundResource(R.color.colortoolbar);

        }else{
            img.setImageResource(R.mipmap.ic_add_black_24dp);
        }

        //sự kiện click vào từng item dòng dữ liệu
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("loaisp",categoriesList.get(vitriGroupCha).getId()+"-"+categoriesList.get(vitriGroupCha).getName());
                FragmentManager fragmentManager=((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                HienThiSanPhamTheoMaDanhMuc hienThiSPTheoDM_activity=new HienThiSanPhamTheoMaDanhMuc();
                Bundle bundle=new Bundle();
                bundle.putInt("maloai",categoriesList.get(vitriGroupCha).getId());
                bundle.putString("tenloai",categoriesList.get(vitriGroupCha).getName());
                hienThiSPTheoDM_activity.setArguments(bundle);

                fragmentTransaction.addToBackStack("TrangchuActivity");
                fragmentTransaction.replace(R.id.content,hienThiSPTheoDM_activity);
                fragmentTransaction.commit();

                return false;
            }
        });
        return view;
    }

    @Override
    public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean b, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       /* view=inflater.inflate(R.layout.custom_layout_groupchild,viewGroup,false);

        ExpandableListView expandableListView=view.findViewById(R.id.expChild);*/
        SecondExpand secondExpand=new SecondExpand(context);
        ExpandAdpater secondAdpater=new ExpandAdpater(context,categoriesList.get(vitriGroupCha).getListcon());
        secondExpand.setAdapter(secondAdpater);
        secondAdpater.notifyDataSetChanged();
        secondExpand.setGroupIndicator(null);
        return secondExpand;
    }
    //tạo 1 expanableListview vs chiều rộng và cao của từng phone
    public class SecondExpand extends ExpandableListView{
        public SecondExpand(Context context) {
            super(context);
        }
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            //màn hình phone, windowmanage quản lí chiều rộng và cao
            WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display=windowManager.getDefaultDisplay();
            Point size=new Point();
            display.getSize(size);

            int width=size.x;
            int height=size.y;

          //  widthMeasureSpec=MeasureSpec.makeMeasureSpec(width,MeasureSpec.AT_MOST);
            heightMeasureSpec=MeasureSpec.makeMeasureSpec(height,MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            //AT_MOST: tu dong tinh toan chieu cao va chieu dai

        }
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

/*    public class SecondAdpater extends BaseExpandableListAdapter{

        List<Categories>listcon;
        public  SecondAdpater( List<Categories> listcon)
        {

            this.listcon=listcon;

            XuLiJSONMenu xuLiJSONMenu=new XuLiJSONMenu();
            for(int i=0;i<listcon.size();i++)
            {
                int maloai=listcon.get(i).getId();
                listcon.get(i).setListcon(xuLiJSONMenu.GetLoaiSPTheoMaLoaiCha(maloai));
            }
        }
        //đếm group cha
        @Override
        public int getGroupCount() {
            return listcon.size();
        }

        @Override
        public int getChildrenCount(int vitriGroupCha) {
            return listcon.get(vitriGroupCha).getListcon().size();
        }

        @Override
        public Object getGroup(int vitriGroupCha) {
            return listcon.get(vitriGroupCha);
        }

        @Override
        public Object getChild(int vitriGroupCha, int vitriGroupCon) {
            return listcon.get(vitriGroupCha).getListcon().get(vitriGroupCon);
        }

        @Override
        public long getGroupId(int vitriGroupCha) {
            return listcon.get(vitriGroupCha).getId();
        }

        @Override
        public long getChildId(int vitriGroupCha, int vitriGroupCon) {
            return listcon.get(vitriGroupCha).getListcon().get(vitriGroupCon).getId();
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int vitriGroupCha, boolean isExpaned, View view, ViewGroup viewGroup) {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.custom_layout_groupcha,viewGroup,false);
            TextView txt=view.findViewById(R.id.txtLoaisp);
            ImageView img=view.findViewById(R.id.imgAdd);
            txt.setText(listcon.get(vitriGroupCha).getName());

            //đếm xem list cha có sản phẩm náo hay k
            int dem2=listcon.get(vitriGroupCha).getListcon().size();
            if(dem2>0)
            {
                img.setVisibility(View.VISIBLE);
            }else {
                img.setVisibility(View.INVISIBLE);
            }
            //kiểm tra nếu như xổ menu xuong thì hiển thi dấu -
            if(isExpaned)
            {
                img.setImageResource(R.mipmap.ic_remove_black_24dp);

            }else{
                img.setImageResource(R.mipmap.ic_add_black_24dp);
            }
            return view;
        }

        @Override
        public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean b, View view, ViewGroup viewGroup) {
           TextView textView=new TextView(context);
           textView.setText(listcon.get(vitriGroupCha).getListcon().get(vitriGroupCon).getName());
           textView.setPadding(10,5,5,5);
           textView.setTextColor(Color.RED);
            textView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

            return textView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }*/
}
