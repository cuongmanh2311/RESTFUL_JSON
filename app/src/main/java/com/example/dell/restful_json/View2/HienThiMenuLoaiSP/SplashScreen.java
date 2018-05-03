package com.example.dell.restful_json.View2.HienThiMenuLoaiSP;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.dell.restful_json.R;
import com.example.dell.restful_json.View2.Login.Login_activity;

/**
 * Created by Dell on 5/1/2018.
 */

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=3000;
    private  boolean Internet_check=true;
    private ProgressBar spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);

        spinner=findViewById(R.id.progressbar);
        spinner.setVisibility(View.VISIBLE);
        PostDeplayMethod();
    }

    private void PostDeplayMethod() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean InternetResult=checkConnection();
                if(InternetResult)
                {
                    Intent intent=new Intent(SplashScreen.this, Login_activity.class);
                    startActivity(intent);
                    finish();
                }else{
                    spinner.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.GONE);
                    DialoAppear();
                }
            }
        },SPLASH_TIME_OUT);
    }

    private void DialoAppear() {
        AlertDialog.Builder builder=new AlertDialog.Builder(SplashScreen.this);
        builder.setTitle("Network Error");
        builder.setMessage("No Internet Connectivity");

        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    PostDeplayMethod();
            }
        });
        builder.show();
    }
    //check internet status of mobile
    protected  boolean isOnline()
    {
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=cm.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnectedOrConnecting())

        {
            return  true;
        }else{
            return false;

    }
    }
    //Return status internet of mobile
    private boolean checkConnection() {
        if(isOnline())
        {
            return Internet_check;
        }else{
            Internet_check=false;
            return Internet_check;
        }
    }
}
