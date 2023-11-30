package com.example.android_25.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_25.DetailActivity;
import com.example.android_25.Model.ModelHistory;
import com.example.android_25.Model.ModelHome;
import com.example.android_25.databinding.ItemHistoryBinding;
import com.example.android_25.databinding.ItemHomeBinding;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    Context context;
    List<ModelHistory> data = new ArrayList<>();

    public HistoryAdapter(Context context, List<ModelHistory> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoryBinding binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HistoryAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        ModelHistory mh = data.get(position);
        holder.bind.namaProduk.setText(mh.getName());
        holder.bind.hargaProduk.setText(mh.getPrice()+"");
        holder.bind.jumlahProduk.setText("Count : "+mh.getCount());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemHistoryBinding bind;
        public ViewHolder(@NonNull ItemHistoryBinding i) {
            super(i.getRoot());
            bind = i;
        }
    }
}
