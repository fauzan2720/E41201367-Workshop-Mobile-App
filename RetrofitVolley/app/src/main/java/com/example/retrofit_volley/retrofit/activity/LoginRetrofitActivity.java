package com.example.retrofit_volley.retrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofit_volley.R;
import com.example.retrofit_volley.retrofit.API.APIRequestData;
import com.example.retrofit_volley.retrofit.API.RetroServer;
import com.example.retrofit_volley.retrofit.model.DataModel;
import com.example.retrofit_volley.retrofit.model.LoginRequest;
import com.example.retrofit_volley.retrofit.model.LoginResponse;
import com.example.retrofit_volley.retrofit.model.ResponseModel;
import com.example.retrofit_volley.volley.LoginVolleyActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRetrofitActivity extends AppCompatActivity {

//    private EditText etNoTelp, etPassword;
//    private String noTelp, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_retrofit);

//        etNoTelp = findViewById(R.id.etNoTelp);
//        etPassword = findViewById(R.id.etPassword);
    }

//    public void actionMasuk(View view) {
//        if(TextUtils.isEmpty(etNoTelp.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())){
//            Toast.makeText(LoginRetrofitActivity.this,"Inputan masih kosong!", Toast.LENGTH_LONG).show();
//        }else{
//            //proceed to login
//            userLogin();
//        }
//    }

//    private void userLogin() {
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setno_telp(etNoTelp.getText().toString());
//        loginRequest.setPassword(etPassword.getText().toString());
//
//        Call<LoginResponse> loginResponseCall = RetroServer.getUserService().userLogin(loginRequest);
//        loginResponseCall.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//
//                if(response.isSuccessful()){
//                    Toast.makeText(LoginRetrofitActivity.this,"Login berhasil!", Toast.LENGTH_LONG).show();
//                    LoginResponse loginResponse = response.body();
//
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            startActivity(new Intent(LoginRetrofitActivity.this, RetrofitActivity.class).putExtra("data",loginResponse.getno_telp()));
//                        }
//                    },700);
//
//                }else{
//                    Toast.makeText(LoginRetrofitActivity.this,"Login Failed", Toast.LENGTH_LONG).show();
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                Toast.makeText(LoginRetrofitActivity.this,"Throwable: "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }
}