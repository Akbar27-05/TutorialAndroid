package com.akbar27.latihaninsertdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private Context context;
    private List<DataModel> dataModelList;

    public MyAdapter(Context context) {
        this.context = context;
        dataModelList = new ArrayList<>();
    }

    public void addModel(DataModel dataModel){
        dataModelList.add(dataModel);
        notifyDataSetChanged();
    }

    public void clear(){
        dataModelList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_row,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindViews(dataModelList.get(position));
    }

    @Override
    public int getItemCount() {

        return dataModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName,tvPhone,tvAddress;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }

        public void bindViews(DataModel dataModel) {
            tvName.setText(dataModel.getName());
            tvPhone.setText(dataModel.getPhone());
            tvAddress.setText(dataModel.getAddress());
        }
    }
}
