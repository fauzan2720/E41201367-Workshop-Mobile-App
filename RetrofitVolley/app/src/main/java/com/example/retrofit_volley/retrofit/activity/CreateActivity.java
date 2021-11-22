package com.example.retrofit_volley.retrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofit_volley.R;
import com.example.retrofit_volley.retrofit.API.APIRequestData;
import com.example.retrofit_volley.retrofit.API.RetroServer;
import com.example.retrofit_volley.retrofit.model.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {

    private EditText etNama, etNoTelp, etEmail, etAlamat, etPassword;
    private String nama, noTelp, email, alamat, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        etNama = findViewById(R.id.etNama);
        etNoTelp = findViewById(R.id.etNoTelp);
        etEmail = findViewById(R.id.etEmail);
        etAlamat = findViewById(R.id.etAlamat);
        etPassword = findViewById(R.id.etPassword);
    }

    public void actionDaftar(View view) {
        nama = etNama.getText().toString();
        noTelp = etNoTelp.getText().toString();
        email = etEmail.getText().toString();
        alamat = etAlamat.getText().toString();
        password = etPassword.getText().toString();

        if (nama.trim().equals("")) {
            etNama.setError("Wajib diisi!");
        } else if (noTelp.trim().equals("")) {
            etNoTelp.setError("Wajib diisi!");
        } else if (email.trim().equals("")) {
            etEmail.setError("Wajib diisi!");
        } else if (alamat.trim().equals("")) {
            etAlamat.setError("Wajib diisi!");
        } else if (password.trim().equals("")) {
            etPassword.setError("Wajib upload!");
        } else {
            simpanData();
        }
    }

    private void simpanData() {
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class); // menghubungkan class interface ke retrofit
        Call<ResponseModel> simpanData = ardData.ardCreateData(nama, noTelp, email, alamat, password);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(CreateActivity.this, "Kode: " +kode +" | Pesan: " +pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(CreateActivity.this, "Gagal daftar: " +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}