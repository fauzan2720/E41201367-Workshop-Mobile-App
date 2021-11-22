package com.example.retrofit_volley.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.retrofit_volley.R;
import com.example.retrofit_volley.retrofit.activity.RetrofitActivity;

import java.util.HashMap;
import java.util.Map;

public class LoginVolleyActivity extends AppCompatActivity {

    private EditText etNoTelpOrEmail, etPassword;
    private String noTelpOrEmail, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_volley);

        etNoTelpOrEmail = findViewById(R.id.etNoTelpOrEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    public void actionMasuk(View view) {
        Masuk();
    }

    public void Masuk() {
        noTelpOrEmail = etNoTelpOrEmail.getText().toString();
        password = etPassword.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.9/koneksiAPI/volley/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("1")) {
                            startActivity(new Intent(getApplicationContext(), RetrofitActivity.class));
                            Toast.makeText(getApplicationContext(), "Login berhasil!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Login gagal!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("noTelpOrEmail", noTelpOrEmail);
                params.put("password", password);
                return params;
            }
        };

//        Volley.newRequestQueue(this).add(request);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}