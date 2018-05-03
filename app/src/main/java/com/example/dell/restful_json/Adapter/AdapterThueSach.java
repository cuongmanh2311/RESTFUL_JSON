package com.example.dell.restful_json.Adapter;

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

import com.example.dell.restful_json.Model.OOP.GioHang;
import com.example.dell.restful_json.Model.OOP.Product;
import com.example.dell.restful_json.R;
import com.example.dell.restful_json.View2.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Dell on 4/13/2018.
 */

public class AdapterThueSach extends RecyclerView.Adapter<AdapterThueSach.Viewholder>{
    Context context;
    List<Product> gioHangList;
    int layout;

    public AdapterThueSach(Context context ,List<Product> gioHangList){
        this.context = context;
        this.gioHangList = gioHangList;
        this.layout = layout;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imHinhSanPham,imgDelete;
        TextView txtTenSP,txtGiaTien;
        CardView cardView;

        public Viewholder(View itemView) {
            super(itemView);
            imHinhSanPham = (ImageView) itemView.findViewById(R.id.imgHinhSpThue);
            imgDelete = (ImageView) itemView.findViewById(R.id.imgXoaThueSach);
            txtTenSP = (TextView) itemView.findViewById(R.id.txttenSpThue);
            txtGiaTien = (TextView) itemView.findViewById(R.id.txtGiaSpThue);
            cardView = (CardView) itemView.findViewById(R.id.cardViewThue);
        }
    }


    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_thuesach,parent,false);

        Viewholder viewholder = new Viewholder(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        Product gioHang = gioHangList.get(position);
        Picasso.with(context).load(gioHang.getImage()).resize(140,140).centerInside().into(holder.imHinhSanPham);
        holder.txtTenSP.setText(gioHang.getName());

        int giatien = gioHang.getPrice();
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        holder.txtGiaTien.setText(gia + " VNĐ ");
        holder.txtGiaTien.setText(gioHang.getView()+"views");

        holder.cardView.setTag(gioHang.getId());

        /*holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChitietsanpham = new Intent(context, ChiTietSanPhamActivity.class);
                iChitietsanpham.putExtra("masp",(int)v.getTag());
                context.startActivity(iChitietsanpham);
            }
        });
*/
    }



    @Override
    public int getItemCount() {
        return gioHangList.size();
    }
}
