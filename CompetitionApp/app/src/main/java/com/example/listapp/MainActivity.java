package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // membuat variabel
    private Spinner spinner;
    private AutoCompleteTextView autocomplete;
    private ListView listView;
    private TextView txtDetail;
    private Button btnDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // awal spinner
        spinner = findViewById(R.id.spinner); // menginsialisasi

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.text_light, Data.status);
        // text_light adalah file xml yang didalamnya adalah text yang mempunyai warna putih, saya membuatnya karena pada file main tidak bisa merubah color secara langsung
        spinner.setAdapter(adapter);

        // mengeset listener untuk memberikan aksi ketika item di tekan
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) { // jika item ditekan akan memberikan pesan
                Toast.makeText(getApplicationContext(),"Status Anda: "+adapter.getItem(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView adapterView) { // jika item tidak ditekan
            }
        }); // akhir spinner


    // awal text auto complite
        autocomplete = findViewById(R.id.autocompleteView); // menginsialisasi

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, Data.headLine);
        autocomplete.setAdapter(adapter2);
        autocomplete.setTextColor(Color.BLUE); // merubah warna biru pada text

        // mengeset listener untuk memberikan aksi ketika item di tekan
        autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { // menampilkan pesan ketika item di klik
                Toast.makeText(getApplicationContext(),"Anda memilih lomba: "+adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        }); // akhir auto complite


    // awal list view
        listView = findViewById(R.id.listView); // menginsialisasi

        // fungsi untuk menampilkan data list ke list view melalui adapter
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Data.headLine);
        listView.setAdapter(adapter3);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Anda memilih lomba: "+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        }); // akhir list view

    // awal fungsi tombol
        txtDetail = findViewById(R.id.txtDetail); // menginisialisasi
        btnDetail = findViewById(R.id.btnDetail);

        txtDetail.setOnClickListener(new View.OnClickListener() { // ketika text view detail di klik
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BidangLombaActivity.class); // beralhir ke activity bidang lomba
                startActivity(i);
            }
        });
        btnDetail.setOnClickListener(new View.OnClickListener() { // ketika button di klik
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BidangLombaActivity.class); // beralhir ke activity bidang lomba
                startActivity(i);
            }
        }); // akhir fungsi tombol
    }
}