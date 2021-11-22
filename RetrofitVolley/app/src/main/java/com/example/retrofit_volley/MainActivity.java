package com.example.retrofit_volley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofit_volley.retrofit.activity.LoginRetrofitActivity;
import com.example.retrofit_volley.retrofit.activity.RetrofitActivity;
import com.example.retrofit_volley.volley.LoginVolleyActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void actionRetrofit(View view) {
        Intent retro = new Intent(this, RetrofitActivity.class);
        startActivity(retro);
    }

    public void actionVolley(View view) {
        Intent volley = new Intent(this, LoginVolleyActivity.class);
        startActivity(volley);
    }
}
