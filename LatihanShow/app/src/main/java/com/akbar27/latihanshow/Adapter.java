package com.akbar27.latihanshow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akbar27.latihanshow.databinding.ActivityMainBinding;
import com.akbar27.latihanshow.databinding.ActivityPostBuahBinding;
import com.akbar27.latihanshow.databinding.ItemShowBinding;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    ActivityMainBinding binding;
    private Context mContext;
    private List<BuahModelClass> mData;
    SharedPreferences sp;

    public Adapter(Context mContext, List<BuahModelClass> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemShowBinding show = ItemShowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new MyViewHolder(show);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind.id.setText(mData.get(position).getId());
        holder.bind.namaBuah.setText(mData.get(position).getNama());
        holder.bind.stockBuah.setText(mData.get(position).getSisa());

        holder.bind.tvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext, holder.bind.tvMenu);
                popupMenu.inflate(R.menu.menu_option);


                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.menu_update){

                            ApiGetIdBuah apiGetIdBuah = new ApiGetIdBuah(mContext, binding, mData.get(position).getId());
                            apiGetIdBuah.execute();

                            Intent intent = new Intent(mContext, UpdateBuahActivity.class);
                            mContext.startActivity(intent);


//                            Toast.makeText(mContext, nama, Toast.LENGTH_SHORT).show();


                        }else if(menuItem.getItemId() == R.id.menu_delete){
                            ApiDelBuah del = new ApiDelBuah(mContext, binding, mData.get(position).getId());
                            del.execute();

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        ItemShowBinding bind;

        public MyViewHolder(@NonNull ItemShowBinding i) {
            super(i.getRoot());

            bind = i;
        }
    }
}
