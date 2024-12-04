package com.example.collegealertapplication.data;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegealertapplication.R;

import java.util.ArrayList;

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.AdViewHolder> {

    private Context context;
    private ArrayList<Ad> adList;

    public AdAdapter(Context context, ArrayList<Ad> adList) {
        this.context = context;
        this.adList = adList;
    }

    @NonNull
    @Override
    public AdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_ads, parent, false);
        return new AdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdViewHolder holder, int position) {
        Ad ad = adList.get(position);
        holder.titleTv.setText(ad.getTitle());
        holder.dateTv.setText(ad.getDate());
        holder.imageIv.setImageResource(ad.getImageResource());
    }

    @Override
    public int getItemCount() {
        return adList.size();
    }

    public static class AdViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv, dateTv;
        ImageView imageIv;

        public AdViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.titleTv);
            dateTv = itemView.findViewById(R.id.dateTv);
            imageIv = itemView.findViewById(R.id.imageIv);
        }
    }
}
