package com.example.retrofit_volley.retrofit.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_volley.R;
import com.example.retrofit_volley.retrofit.API.APIRequestData;
import com.example.retrofit_volley.retrofit.API.RetroServer;
import com.example.retrofit_volley.retrofit.activity.RetrofitActivity;
import com.example.retrofit_volley.retrofit.activity.UpdateActivity;
import com.example.retrofit_volley.retrofit.model.DataModel;
import com.example.retrofit_volley.retrofit.model.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private Context context;
    private List<DataModel> listData;
    private int idAnggota;

    public AdapterData(Context context, List<DataModel> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_anggota, parent, false);
        HolderData holderData = new HolderData(layout);

        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dataModel = listData.get(position);

        holder.tvId.setText(String.valueOf(dataModel.getId()));
        holder.tvNama.setText(dataModel.getNama());
        holder.noTelp.setText(dataModel.getNo_telp());
        holder.tvAlamat.setText(dataModel.getAlamat());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvId, tvNama, noTelp, tvAlamat;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvId);
            tvNama = itemView.findViewById(R.id.tvNama);
            noTelp = itemView.findViewById(R.id.tvNoTelp);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(context);
//                    dialogPesan.setTitle("Perhatian!");
//                    dialogPesan.setIcon(R.drawable.ic_warning);
                    dialogPesan.setMessage("Pilih operasi yang akan dilakukan");
                    dialogPesan.setCancelable(true);

                    idAnggota = Integer.parseInt(tvId.getText().toString()); // merubah berdasaran id (foreign key)

                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteData();
                            dialogInterface.dismiss();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ((RetrofitActivity) context).retrieveData(); // agar menjalankan retrieve data/merefresh pada saat data dihapus
                                }
                            }, 1000); // memberi jeda selama 1 detik

                        }
                    });
                    dialogPesan.setNegativeButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getData();
                        }
                    });
                    dialogPesan.show();
                }
            });
        }

        private void deleteData() {
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class); // menghubungkan class interface ke retrofit
            Call<ResponseModel> hapusData = ardData.ardDeleteData(idAnggota); // dihapus berdasarkan nomor telepon

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    Toast.makeText(context, "Kode: " +kode +" | Pesan: " +pesan, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(context, "Gagal hapus data: " +t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void getData() {
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class); // menghubungkan class interface ke retrofit
            Call<ResponseModel> ambilData = ardData.ardGetData(idAnggota);

            ambilData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    listData = response.body().getData();

                    int varId = listData.get(0).getId();
                    String varNama = listData.get(0).getNama();
                    String varNoTelp = listData.get(0).getNo_telp();
                    String varEmail = listData.get(0).getEmail();
                    String varAlamat = listData.get(0).getAlamat();
                    String varPassword = listData.get(0).getPassword();

                    Intent kirim = new Intent(context, UpdateActivity.class); // kirim ke Update Data Activity
                    kirim.putExtra("id", varId);
                    kirim.putExtra("nama", varNama);
                    kirim.putExtra("noTelp", varNoTelp);
                    kirim.putExtra("email", varEmail);
                    kirim.putExtra("alamat", varAlamat);
                    kirim.putExtra("password", varPassword);
                    context.startActivity(kirim);
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(context, "Gagal menghubung server: " +t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
