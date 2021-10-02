package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import java.util.ArrayList;

public class BidangLombaActivity extends AppCompatActivity {

    // membuat variabel
    RecyclerView rv;
    RecyclerView.Adapter rvAdapter;
    RecyclerView.LayoutManager rvLayoutManager;
    ArrayList<ItemModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidang_lomba);

    // awal Recycler View
        rv = findViewById(R.id.recycleView); // menginisialisasi
        rv.setHasFixedSize(true);

        rvLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(rvLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        data = new ArrayList<>();
        for (int i = 0; i < Data.headLine.length; i++) { // memanggil data berdasarkan panjang data
            data.add(new ItemModel(
                    Data.headLine[i],
                    Data.subHeadLine[i],
                    Data.iconList[i]
            ));
        }

        rvAdapter = new AdapterRecyclerView(this, data);
        rv.setAdapter(rvAdapter);
        // akhir Recycler View
    }
}