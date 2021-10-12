package com.example.fragmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailKompetisiActivity extends AppCompatActivity {

    private ImageView ivDefault;
    private TextView tvDefault, tvDefault2, tvDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kompetisi);

        ivDefault = findViewById(R.id.ivDefault);
        tvDefault = findViewById(R.id.tvDefault);
        tvDefault2 = findViewById(R.id.tvDefault2);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);

        Intent i = getIntent();

        int icon = i.getIntExtra("IMAGE_DEFAULT", 0);
        String namaLomba = i.getStringExtra("TEXT_DEFAULT");
        String namaLomba2 = i.getStringExtra("TEXT_DEFAULT2");
        String deskripsi = i.getStringExtra("TEXT_DESKRIPSI");

        ivDefault.setImageResource(icon);
        tvDefault.setText(namaLomba);
        tvDefault2.setText(namaLomba2);
        tvDeskripsi.setText(deskripsi);
    }
}