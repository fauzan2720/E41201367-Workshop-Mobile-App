package com.example.retrofit_volley.retrofit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.retrofit_volley.R;
import com.example.retrofit_volley.retrofit.API.APIRequestData;
import com.example.retrofit_volley.retrofit.API.RetroServer;
import com.example.retrofit_volley.retrofit.adapter.AdapterData;
import com.example.retrofit_volley.retrofit.model.DataModel;
import com.example.retrofit_volley.retrofit.model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {

    private RecyclerView rvDataAnggota;
    private RecyclerView.Adapter adDataAnggota;
    private RecyclerView.LayoutManager lmDataAnggota;
    private List<DataModel> listData = new ArrayList<>();
    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        rvDataAnggota = findViewById(R.id.rvListAnggota);
        srlData = findViewById(R.id.srlData);
        pbData = findViewById(R.id.pbData);

        lmDataAnggota = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDataAnggota.setLayoutManager(lmDataAnggota);

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                retrieveData();
                srlData.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveData();
    }

    public void retrieveData() {
        pbData.setVisibility(View.VISIBLE); // memulai refresh

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class); // menghubungkan class interface ke retrofit
        Call<ResponseModel> tampilData = ardData.ardRetrieveData();

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                int kode = response.body().getKode();
//                String pesan = response.body().getPesan();
//                Toast.makeText(RetrofitActivity.this, "Kode: " +kode +" | Pesan: " +pesan, Toast.LENGTH_SHORT).show();

                listData = response.body().getData();

                adDataAnggota = new AdapterData(RetrofitActivity.this, listData);
                rvDataAnggota.setAdapter(adDataAnggota);
                adDataAnggota.notifyDataSetChanged();

                pbData.setVisibility(View.INVISIBLE); // menghentikan refresh
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "Gagal menghubungi server: " +t.getMessage(), Toast.LENGTH_SHORT).show();

                pbData.setVisibility(View.INVISIBLE); // menghentikan refresh
            }
        });
    }

    public void actionCreate(View view) {
        Intent i = new Intent(this, CreateActivity.class);
        startActivity(i);
    }
}