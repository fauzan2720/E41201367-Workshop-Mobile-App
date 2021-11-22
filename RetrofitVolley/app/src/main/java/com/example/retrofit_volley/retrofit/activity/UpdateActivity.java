package com.example.retrofit_volley.retrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class UpdateActivity extends AppCompatActivity {

    private EditText etNama, etNoTelp, etEmail, etAlamat, etPassword;
    private int id;
    private String nama, noTelp, email, alamat, password, getNama, getNoTelp, getEmail, getAlamat, getPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent terimaData = getIntent(); // menerima data dari adapter data
        id = terimaData.getIntExtra("id", -1); // jika yg diterima id yg salah, karena -1 tidak ada
        nama = terimaData.getStringExtra("nama");
        noTelp = terimaData.getStringExtra("noTelp");
        email = terimaData.getStringExtra("email");
        alamat = terimaData.getStringExtra("alamat");
        password = terimaData.getStringExtra("password");

        etNama = findViewById(R.id.etNama);
        etNoTelp = findViewById(R.id.etNoTelp);
        etEmail = findViewById(R.id.etEmail);
        etAlamat = findViewById(R.id.etAlamat);
        etPassword = findViewById(R.id.etPassword);

        etNama.setText(nama);
        etNoTelp.setText(noTelp);
        etEmail.setText(email);
        etAlamat.setText(alamat);
        etPassword.setText(password);
    }

    private void updateData() {
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class); // menghubungkan class interface ke retrofit
        Call<ResponseModel> updateData = ardData.ardUpdateData(id, getNama, getNoTelp, getEmail, getAlamat, getPassword);

        updateData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UpdateActivity.this, "Kode: " +kode +" | Pesan: " +pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Gagal daftar: " +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void actionPerbarui(View view) {
        // mengirim data yang berasal dari inputan
        getNama = etNama.getText().toString();
        getNoTelp = etNoTelp.getText().toString();
        getEmail = etEmail.getText().toString();
        getAlamat = etAlamat.getText().toString();
        getPassword = etPassword.getText().toString();

        updateData();
    }
}