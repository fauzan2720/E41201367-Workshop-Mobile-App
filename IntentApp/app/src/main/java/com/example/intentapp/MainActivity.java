package com.example.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // membuat variabel
    private EditText etNama, etNoTelp, etMap, etBrowsing;
    private Button btnKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // menginisialisasi id
        etNama = findViewById(R.id.etNama);
        etNoTelp = findViewById(R.id.etNoTelp);
        etMap = findViewById(R.id.etMap);
        etBrowsing = findViewById(R.id.etBrowsing);
        btnKirim = findViewById(R.id.btnKirim);

        // ketika tombol kirim di tekan
        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kirim = new Intent(MainActivity.this, OutputActivity.class); // beralih ke activity lain
                // mengirim data ke OutputActivity
                kirim.putExtra("key_nama", etNama.getText().toString());
                kirim.putExtra("key_notelp", etNoTelp.getText().toString());
                kirim.putExtra("key_alamat", etMap.getText().toString());
                kirim.putExtra("key_browsing", etBrowsing.getText().toString());
                startActivity(kirim); // menjalankan intent
            }
        });
    }

    // buka email
    public void buka_email (View view){ // membuat method dimanana method tersebut dipanggil kedalam Button pada file xml
        String notelp = String.format("smsto: %s", etNoTelp.getText().toString()); // smsto: %s berarti mengirim sms berdasarkan nomor telepon yang diinputkan
        Intent kirim = new Intent(Intent.ACTION_SENDTO, Uri.parse(notelp)); // beralih ke aplikasi pesan
        startActivity(kirim); // menjalankan intent
    }

    // buka map
    public void buka_map(View view){ // membuat method dimanana method tersebut dipanggil kedalam Button pada file xml
        String alamat = String.format("geo: 0, 0?q=%s", etMap.getText().toString()); // geo: berarti membaca informasi alamat map berdasarkan alamat map yang diinputkan
        Intent cek = new Intent(Intent.ACTION_VIEW, Uri.parse(alamat));  // beralih ke google maps
        startActivity(cek); // menjalankan intent
    }

    // buka url
    public void buka_browser(View view) { // membuat method dimanana method tersebut dipanggil kedalam Button pada file xml
        String url = etBrowsing.getText().toString(); // memanggil data url yang diinputkan user
        Intent buka = new Intent(Intent.ACTION_VIEW, Uri.parse(url)); // beralih ke alamat url yang di inputkan user
        startActivity(buka); // menjalankan intent
    }
}