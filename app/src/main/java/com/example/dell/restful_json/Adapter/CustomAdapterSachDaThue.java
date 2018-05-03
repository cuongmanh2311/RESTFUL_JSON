package com.example.dell.restful_json.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.restful_json.Model.OOP.Product;
import com.example.dell.restful_json.R;
import com.example.dell.restful_json.View2.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Dell on 4/16/2018.
 */

public class CustomAdapterSachDaThue extends ArrayAdapter<Product> {
    Context context;
    List<Product> sanPhamList;
    int layout;

    public CustomAdapterSachDaThue(Context context, int resource,  List<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.sanPhamList = objects;
        this.layout = resource;
    }

    public class Viewholder {
        ImageView imHinhSanPham;
        TextView txtTenSP,txtGiaTien,txtStatus,txtNgayMuon;

        }

    @SuppressLint("WrongViewCast")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null)
        {
               LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               convertView=layoutInflater.inflate(this.layout,parent,false);
                 Viewholder  viewholder=new Viewholder();
                viewholder.imHinhSanPham=convertView.findViewById(R.id.imgHinhSp2);
                viewholder.txtTenSP=convertView.findViewById(R.id.txttenSp2);
                viewholder.txtGiaTien=convertView.findViewById(R.id.txtGiaSp2);
                viewholder.txtStatus=convertView.findViewById(R.id.txtstatus);
                viewholder.txtNgayMuon=convertView.findViewById(R.id.txtNgayMuon);
                convertView.setTag(viewholder);
        }
            Viewholder viewholder= (Viewholder) convertView.getTag();

        Product sanPham = sanPhamList.get(position);
        Picasso.with(context).load(sanPham.getImage()).resize(140,140).centerInside().into(viewholder.imHinhSanPham);
        viewholder.txtTenSP.setText(sanPham.getName());
        viewholder.txtNgayMuon.setText(sanPham.getCreated_at());
        int giatien = sanPham.getPrice();
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        viewholder.txtGiaTien.setText(gia + " VNĐ ");
        int status=sanPham.getStatus();
        if(status==0)
        {
            viewholder.txtStatus.setText("Chưa trả");
        }
        return convertView;
    }

    }





