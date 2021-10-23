package com.example.manajemenfile_crud_sqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ListDataActivity extends AppCompatActivity {

    String[] daftar;
    ListView listData;
    protected Cursor cursor;
    DataHelper dbCenter;
    public static ListDataActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        ma = this;
        dbCenter = new DataHelper(this);
        RefreshList();
    }

    void RefreshList() { // untuk pengaksesan dan penampilan data
        // memanggil semua data dari tabel biodata
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) { // dipanggil berdasarkan indeks ke-i
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(1).toString();
        }

        // data dipanggil kedalam lst view
        listData = findViewById(R.id.listData);
        listData.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listData.setSelected(true);
        listData.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String selection = daftar[i];
                final CharSequence[] dialogItem ={"Lihat Biodata", "Update Biodata", "Hapus Biodata"}; // membuat beberapa pilihan
                AlertDialog.Builder builder = new AlertDialog.Builder(ListDataActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item) {
                            case 0: // jika menekan Lihat Biodata
                                Intent intent = new Intent(getApplicationContext(), ReadDataActivity.class); // berpindah halaman
                                intent.putExtra("nama", selection); // data yang dipanggil berdasarkan namanya
                                startActivity(intent);
                                break;
                            case 1: // jika menekan Update Biodata
                                Intent intent2 = new Intent(getApplicationContext(), UpdateDataActivity.class);
                                intent2.putExtra("nama", selection);
                                startActivity(intent2);
                                break;
                            case 2: // Jika menekan Hapus Biodata
                                // membuat alert untuk meyakinkan menghapus data
                                AlertDialog.Builder builder = new AlertDialog.Builder(ListDataActivity.this);
                                builder.setMessage("Apakah anda yakin ingin menghapus?");
                                builder.setCancelable(true);
                                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() { // jika klik hapus
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        SQLiteDatabase db = dbCenter.getWritableDatabase();
                                        db.execSQL("DELETE FROM biodata WHERE nama = '"+selection+"'"); // hapus data berdasarkan data pada nama
                                        RefreshList();
                                        Toast.makeText(ListDataActivity.this, "Data berhasil dihapus!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() { // jika klik batal
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel(); // batal hapus
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listData.getAdapter()).notifyDataSetInvalidated();
    }

    public void btn(View view) {
        Intent intent = new Intent(ListDataActivity.this, CreateDataActivity.class); // berpindah ke halaman tambah data
        startActivity(intent); // mulai proses berpindah
    }
}