package com.example.dell.restful_json.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dell.restful_json.Model.OOP.ThongTin;
import com.example.dell.restful_json.R;

import java.util.List;

/**
 * Created by Dell on 3/11/2018.
 */

public class CustomAdapter extends ArrayAdapter<ThongTin> {
    Context context;
    int resource;

    List<ThongTin> objects;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<ThongTin> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    private class ViewHolder{
        TextView txtSTT,txtName,txtPrice;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(this.resource,parent,false);
            ViewHolder viewHolder=new ViewHolder();
            viewHolder.txtSTT=convertView.findViewById(R.id.txtSTT);
            viewHolder.txtName=convertView.findViewById(R.id.txtName);
            viewHolder.txtPrice=convertView.findViewById(R.id.txtPrice);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder= (ViewHolder) convertView.getTag();
        ThongTin thongTin=objects.get(position);
        viewHolder.txtSTT.setText(String.valueOf(thongTin.getMa()));
        viewHolder.txtName.setText(thongTin.getTen());
        viewHolder.txtPrice.setText(thongTin.getDongia()+"VND");
        return convertView;
    }
}
