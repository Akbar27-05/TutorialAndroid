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
import com.example.android_25.Model.ModelHome;
import com.example.android_25.databinding.ItemHomeBinding;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Context context;
    List<ModelHome> data = new ArrayList<>();

    public HomeAdapter(Context context, List<ModelHome> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeBinding binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        ModelHome mh = data.get(position);
        holder.bind.namaProduk.setText(mh.getName());
        holder.bind.hargaProduk.setText(mh.getPrice()+"");
        holder.bind.lm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("id", mh.getId());
                i.putExtra("name", mh.getName());
                i.putExtra("price", mh.getPrice());
                i.putExtra("description", mh.getDescription());
                i.putExtra("stock", mh.getStock());
                context.startActivity(i);
            }
        });

        try{
            URL url = new URL("http://10.0.2.2:5000/api/Home/Item/Photo/"+mh.getId());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            Bitmap img = BitmapFactory.decodeStream(is);
            holder.bind.iv.setImageBitmap(img);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemHomeBinding bind;
        public ViewHolder(@NonNull ItemHomeBinding i) {
            super(i.getRoot());
            bind = i;
        }
    }
}

