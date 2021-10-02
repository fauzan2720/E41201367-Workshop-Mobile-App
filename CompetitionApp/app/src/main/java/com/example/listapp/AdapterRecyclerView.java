package com.example.listapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.viewHolder> {

    private ArrayList<ItemModel> dataItem;
    private Context context;

    AdapterRecyclerView(Context context, ArrayList<ItemModel> dataItem) {
        this.context = context;
        this.dataItem = dataItem;
    }

    @NonNull
    @Override
    public AdapterRecyclerView.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerView.viewHolder holder, int position) {
        TextView textTittle = holder.textTittle;
        TextView textSubTittle = holder.textSubTittle;
        ImageView imageIcon = holder.imageIcon;

        textTittle.setText(dataItem.get(position).getName());
        textSubTittle.setText(dataItem.get(position).getType());
        imageIcon.setImageResource(dataItem.get(position).getImage());

        holder.parentLayout.setOnClickListener(view -> {
            Toast.makeText(context, "Anda memilih lomba: "+dataItem.get(position).getName(), Toast.LENGTH_SHORT).show(); // memberi pesan

            if (dataItem.get(position).getName().equals("Hackathon")) { // akan berjalan jika salah satu list berjalan, membaca berdasarkan nama lombanya yang dicek pada data di file Data.java
                Intent i = new Intent(context, DetailLombaActivity.class);
                i.putExtra("IMAGE_DEFAULT", R.drawable.hackathon);
                i.putExtra("TEXT_DEFAULT", "Hackathon");
                i.putExtra("TEXT_DEFAULT2", "Hackathon");
                i.putExtra("TEXT_DESKRIPSI", "Hackthon adalah lomba membuat aplikasi berbasis web, desktop, ataupun mobile.");
                context.startActivity(i);
            } else if (dataItem.get(position).getName().equals("Innovation Creation")) {
                Intent i = new Intent(context, DetailLombaActivity.class);
                i.putExtra("IMAGE_DEFAULT", R.drawable.innovation_creation);
                i.putExtra("TEXT_DEFAULT", "Innovation Creation");
                i.putExtra("TEXT_DEFAULT2", "Innovation Creation");
                i.putExtra("TEXT_DESKRIPSI", "Innovation Creation adalah lomba untuk menciptakan karya inovasi mengikuti perkembangan teknologi terkini.");
                context.startActivity(i);
            } else if (dataItem.get(position).getName().equals("Web Design")) {
                Intent i = new Intent(context, DetailLombaActivity.class);
                i.putExtra("IMAGE_DEFAULT", R.drawable.web_design);
                i.putExtra("TEXT_DEFAULT", "Web Design");
                i.putExtra("TEXT_DEFAULT2", "Web Design");
                i.putExtra("TEXT_DESKRIPSI", "Web Design adalah lomba menciptakan desain dengan tema mewujudkan teknologi baru dimasa pandemi.");
                context.startActivity(i);
            } else if (dataItem.get(position).getName().equals("Game Developer")) {
                Intent i = new Intent(context, DetailLombaActivity.class);
                i.putExtra("IMAGE_DEFAULT", R.drawable.game);
                i.putExtra("TEXT_DEFAULT", "Game Developer");
                i.putExtra("TEXT_DEFAULT2", "Game Developer");
                i.putExtra("TEXT_DESKRIPSI", "Game Developer adalah lomba untuk mengembangkan software game.");
                context.startActivity(i);
            } else if (dataItem.get(position).getName().equals("Business Plan")) {
                Intent i = new Intent(context, DetailLombaActivity.class);
                i.putExtra("IMAGE_DEFAULT", R.drawable.business);
                i.putExtra("TEXT_DEFAULT", "Business Plan");
                i.putExtra("TEXT_DEFAULT2", "Business Plan");
                i.putExtra("TEXT_DESKRIPSI", "Business Plan adalah lomba menciptakan ide bisnis yang berhubungan dengan teknologi informasi.");
                context.startActivity(i);
            } else if (dataItem.get(position).getName().equals("Networking")) {
                Intent i = new Intent(context, DetailLombaActivity.class);
                i.putExtra("IMAGE_DEFAULT", R.drawable.networking);
                i.putExtra("TEXT_DEFAULT", "Networking");
                i.putExtra("TEXT_DEFAULT2", "Networking");
                i.putExtra("TEXT_DESKRIPSI", "Networking adalah lomba untuk untuk menguji kemampuan analisa dan troubleshooting peserta mengenai jaringan komputer dengan simulasi Packet Tracer.");
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView textTittle, textSubTittle;
        ImageView imageIcon;
        LinearLayoutCompat parentLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            textTittle = itemView.findViewById(R.id.textTittle);
            textSubTittle = itemView.findViewById(R.id.textSubTitle);
            imageIcon = itemView.findViewById(R.id.imageList);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
