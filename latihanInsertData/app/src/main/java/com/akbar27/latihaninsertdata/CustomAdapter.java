package com.akbar27.latihaninsertdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    ArrayList<String> listName;
    ArrayList<String> listPhone;
    ArrayList<String> listAddress;

    public CustomAdapter(Context context, ArrayList<String> listName, ArrayList<String> listPhone, ArrayList<String> listAddress){
        this.context = context;
        this.listName = listName;
        this.listPhone = listPhone;
        this.listAddress = listAddress;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listName.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.custom_list_data, null);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvPhone = view.findViewById(R.id.tvPhone);
        TextView tvAddress = view.findViewById(R.id.tvAddress);

        tvName.setText(listName.get(i));
        tvPhone.setText(listPhone.get(i));
        tvAddress.setText(listAddress.get(i));


        return null;
    }
}
