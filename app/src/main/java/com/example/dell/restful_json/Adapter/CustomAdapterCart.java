package com.example.dell.restful_json.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.restful_json.Model.OOP.GioHang;
import com.example.dell.restful_json.R;
import com.example.dell.restful_json.View2.HienThiMenuLoaiSP.Trangchu_activity;
import com.example.dell.restful_json.View2.ThueSach.GioHang_activity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Dell on 4/23/2018.
 */

public class CustomAdapterCart extends ArrayAdapter<GioHang> {
    Context context;
    int resource;
    List<GioHang> objects;
    public CustomAdapterCart(@NonNull Context context, int resource, @NonNull List<GioHang> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    public class ViewHolder{
        ImageView img;
        TextView txtName,txtPrice,txtHienThiSL;
        Button btnTang,btnGiam;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(this.resource,parent,false);
            viewHolder.img=convertView.findViewById(R.id.imgHinhDongCart);
            viewHolder.txtName=convertView.findViewById(R.id.txtNameDongCart);
            viewHolder.txtPrice=convertView.findViewById(R.id.txtPriceDongCart);
            viewHolder.txtHienThiSL=convertView.findViewById(R.id.txtHienThiSL);
            viewHolder.btnTang=convertView.findViewById(R.id.btnTangSL);
            viewHolder.btnGiam=convertView.findViewById(R.id.btnGiamSL);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        GioHang gioHang=objects.get(position);
        viewHolder.txtName.setText(gioHang.getPro_name());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtPrice.setText(decimalFormat.format(gioHang.getPrice())+ "VND");
        Picasso.with(context).load(gioHang.getImage()).into(viewHolder.img);
        viewHolder.txtHienThiSL.setText(gioHang.getQuantity() +"");

        //setting tăng giàm số lượng
        int sl=Integer.parseInt(viewHolder.txtHienThiSL.getText().toString());
        if(sl>=10)
        {
            viewHolder.btnTang.setVisibility(View.INVISIBLE);
            viewHolder.btnGiam.setVisibility(View.VISIBLE);
        }else if(sl<=1){
            viewHolder.btnGiam.setVisibility(View.INVISIBLE);
            viewHolder.btnTang.setVisibility(View.VISIBLE);
        }else if(sl>=1)
        {
            viewHolder.btnGiam.setVisibility(View.VISIBLE);
            viewHolder.btnTang.setVisibility(View.VISIBLE);
        }
        viewHolder.btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slnew=Integer.parseInt(viewHolder.txtHienThiSL.getText().toString())+1;
                int slhientai= Trangchu_activity.gioHangArrayList.get(position).getQuantity();
                int giahientai=Trangchu_activity.gioHangArrayList.get(position).getPrice();
                Trangchu_activity.gioHangArrayList.get(position).setQuantity(slnew);
                int giamoinhat=(slnew*giahientai)/slhientai;
                Trangchu_activity.gioHangArrayList.get(position).setPrice(giamoinhat);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                viewHolder.txtPrice.setText(decimalFormat.format(giamoinhat)+ "VND");
                GioHang_activity.Event();
                if(slnew>9)
                {
                    viewHolder.btnTang.setVisibility(View.INVISIBLE);
                    viewHolder.btnGiam.setVisibility(View.VISIBLE);
                    viewHolder.txtHienThiSL.setText(String.valueOf(slnew));
                }else {
                    viewHolder.btnTang.setVisibility(View.VISIBLE);
                    viewHolder.btnGiam.setVisibility(View.VISIBLE);
                    viewHolder.txtHienThiSL.setText(String.valueOf(slnew));

                }
            }
        });
        viewHolder.btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slnew=Integer.parseInt(viewHolder.txtHienThiSL.getText().toString())-1;
                int slhientai= Trangchu_activity.gioHangArrayList.get(position).getQuantity();
                int giahientai=Trangchu_activity.gioHangArrayList.get(position).getPrice();
                Trangchu_activity.gioHangArrayList.get(position).setQuantity(slnew);
                int giamoinhat=(slnew*giahientai)/slhientai;
                Trangchu_activity.gioHangArrayList.get(position).setPrice(giamoinhat);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                viewHolder.txtPrice.setText(decimalFormat.format(giamoinhat)+ "VND");
                GioHang_activity.Event();
                if(slnew<2)
                {
                    viewHolder.btnTang.setVisibility(View.VISIBLE);
                    viewHolder.btnGiam.setVisibility(View.INVISIBLE);
                    viewHolder.txtHienThiSL.setText(String.valueOf(slnew));
                }else {
                    viewHolder.btnTang.setVisibility(View.VISIBLE);
                    viewHolder.btnGiam.setVisibility(View.VISIBLE);
                    viewHolder.txtHienThiSL.setText(String.valueOf(slnew));

                }
            }
        });

        return convertView;
    }
}
