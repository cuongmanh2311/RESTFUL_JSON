package com.example.dell.restful_json.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dell.restful_json.Model.OOP.Product;
import com.example.dell.restful_json.R;
import com.example.dell.restful_json.View2.ChiTietSanPham.ChiTietSanPhamActivity;
import com.example.dell.restful_json.View2.HienThiMenuLoaiSP.Trangchu_activity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Dell on 4/10/2018.
 */

public class AdapterHienThiSpTheoDm extends RecyclerView.Adapter<AdapterHienThiSpTheoDm.Viewholder> {
    Context context;
    List<Product> sanPhamList;
    int layout;
    ProgressDialog progressDialog;

    public AdapterHienThiSpTheoDm(Context context, int layout, List<Product> sanPhamList){
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imHinhSanPham;
        TextView txtTenSP,txtGiaTien,txtView;
        CardView cardView;
        ProgressBar progressBar;

        public Viewholder(View itemView) {
            super(itemView);
            imHinhSanPham = (ImageView) itemView.findViewById(R.id.imgHinhSp1);
            txtTenSP = (TextView) itemView.findViewById(R.id.txttenSp1);
            txtGiaTien = (TextView) itemView.findViewById(R.id.txtGiaSp1);
            txtView = (TextView) itemView.findViewById(R.id.txtView);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
            progressBar=(ProgressBar)itemView.findViewById(R.id.progress_bar_quay);
        }
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout,parent,false);

        Viewholder viewholder = new Viewholder(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, int position) {
        Product sanPham = sanPhamList.get(position);
        Picasso.with(context).load(sanPham.getImage()).resize(140,140).centerInside().into(holder.imHinhSanPham, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
        holder.txtTenSP.setText(sanPham.getName());

        int giatien = sanPham.getPrice();
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        holder.txtGiaTien.setText(gia + " VNĐ ");
        holder.txtView.setText(sanPham.getView()+"views");

        holder.cardView.setTag(sanPham.getId());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog= ProgressDialog.show(context,"Loading...","Loading...");
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
                Intent iChitietsanpham = new Intent(context, ChiTietSanPhamActivity.class);
                iChitietsanpham.putExtra("masp",(int)v.getTag());
                context.startActivity(iChitietsanpham);
            }
        });
    }
    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }
}
