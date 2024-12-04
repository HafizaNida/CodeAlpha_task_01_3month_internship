package com.example.collegealertapplication.data;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegealertapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterImagesPicked extends RecyclerView.Adapter<AdapterImagesPicked.HolderImagesPicked> {

    private Context context;
    private List<Uri> imageList;
    private OnCloseButtonClickListener closeButtonClickListener;

    public AdapterImagesPicked(Context context) {
        this.context = context;
        this.imageList = new ArrayList<>();
    }

    public void addImage(Uri imageUri) {
        if (imageUri != null) {
            imageList.add(imageUri);
            notifyDataSetChanged();
        }
    }

    public void setOnCloseButtonClickListener(OnCloseButtonClickListener listener) {
        this.closeButtonClickListener = listener;
    }

    public List<String> getImageUrls() {
        List<String> imageUrlList = new ArrayList<>();
        for (Uri uri : imageList) {
            imageUrlList.add(uri.toString());
        }
        return imageUrlList;
    }

    @NonNull
    @Override
    public HolderImagesPicked onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_images_picked, parent, false);
        return new HolderImagesPicked(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderImagesPicked holder, int position) {
        Uri imageUri = imageList.get(position);
        holder.bind(imageUri);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    static class HolderImagesPicked extends RecyclerView.ViewHolder {
        ImageView imageView;

        public HolderImagesPicked(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageIv);
        }

        public void bind(Uri imageUri) {
            if (imageUri != null) {
                // Load image using Picasso (or any other image loading library)
                Picasso.get().load(imageUri).into(imageView);
            }
        }
    }

    // Interface for handling close button clicks
    public interface OnCloseButtonClickListener {
        void onCloseButtonClick(int position);
    }
}

