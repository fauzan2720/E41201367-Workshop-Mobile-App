package com.example.manajemenfile_crud_sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateDataActivity extends AppCompatActivity {

    DataHelper dbHelper;
    Button btnKembali, btnSimpan;
    EditText txtNomor, txtNama, txtTglLahir, txtJnsKelamin, txtAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data);

        // menginisialisasi
        dbHelper = new DataHelper(this);
        txtNomor = findViewById(R.id.txtNomor);
        txtNama = findViewById(R.id.txtNama);
        txtTglLahir = findViewById(R.id.txtTglLahir);
        txtJnsKelamin = findViewById(R.id.txtJnsKelamin);
        txtAlamat = findViewById(R.id.txtAlamat);
        btnKembali = findViewById(R.id.btnKembali);
        btnSimpan = findViewById(R.id.btnSimpan);

        // ketika menekan tombol kembali
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // menyelesaikan activity yang sekarang, dan menuju activity sebelumnya
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                // menyimpana data kedalam database
                db.execSQL("INSERT INTO biodata (no, nama, tgl, jk, alamat) VALUES ('" +
                        txtNomor.getText().toString() + "','" +
                        txtNama.getText().toString() + "','" +
                        txtTglLahir.getText().toString() + "','" +
                        txtJnsKelamin.getText().toString() + "','" +
                        txtAlamat.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan!", Toast.LENGTH_LONG).show();
                ListDataActivity.ma.RefreshList();
                finish(); // menyelesaikan activity yang sekarang, dan menuju activity sebelumnya
            }
        });
    }
}