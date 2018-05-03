package com.example.dell.restful_json.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.restful_json.Model.OOP.Product;
import com.example.dell.restful_json.Model.OOP.ThongTin;
import com.example.dell.restful_json.R;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by Dell on 4/8/2018.
 */

public class CustomProductAdapter extends ArrayAdapter<Product> {
    Context context;
    int resource;

    List<Product> objects;
    public CustomProductAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    private class ViewHolder{
        TextView txtNameP,txtPriceP;
        ImageView imgviewP;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(this.resource,parent,false);
             Product product=objects.get(position);
            TextView  txtNameP=convertView.findViewById(R.id.txtNameP);
            TextView txtPriceP=convertView.findViewById(R.id.txtGiaP);
            ImageView  imgviewP=convertView.findViewById(R.id.imgProduct);


            txtNameP.setText(product.getName());
            txtPriceP.setText(String.valueOf(product.getPrice()+"  VND"));
            imgviewP.setImageBitmap(product.getBitmap());

            return convertView;
    }
 /* public class XuLiHinhAnh extends AsyncTask<String,Void,Bitmap>
  {
      private ImageView imageView;
      public XuLiHinhAnh(ImageView imageView)
      {
          this.imageView=imageView;
      }
      @Override
      protected void onPostExecute(Bitmap bitmap) {
          super.onPostExecute(bitmap);
          imageView.setImageBitmap(bitmap);
      }

      @Override
      protected Bitmap doInBackground(String... strings) {
         try{
             InputStream inputStream = new java.net.URL(strings[0]).openStream();
             return BitmapFactory.decodeStream(inputStream);
         }catch (Exception e){

         }
          return null;
      }
  }*/
}

