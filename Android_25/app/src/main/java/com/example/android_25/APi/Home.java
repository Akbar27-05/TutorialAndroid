package com.example.android_25.APi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android_25.Adapter.HomeAdapter;
import com.example.android_25.Helper.ConnHelper;
import com.example.android_25.Model.ModelHome;
import com.example.android_25.Response;
import com.example.android_25.databinding.ActivityHomeBinding;
import com.example.android_25.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends AsyncTask<String, Void, Response> {
    String url = "http://10.0.2.2:5000/api/Home/Item";
    Context context;
    ActivityHomeBinding bind;
    List<ModelHome> modelHomeList = new ArrayList<>();

    ArrayAdapter adapterSpinner;
    List<String> namaProduk = new ArrayList<>();

    public Home(Context context, ActivityHomeBinding bind) {
        this.context = context;
        this.bind = bind;
    }

    @Override
    protected Response doInBackground(String... strings) {
        ConnHelper ch = new ConnHelper(url, context);
        return ch.getData();
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        Log.d("homes", response.data);
        bind.rv.setHasFixedSize(true);
        bind.rv.setLayoutManager(new GridLayoutManager(context, 2));
        try{
            JSONArray ja = new JSONArray(response.data);
            for(int i = 0; i<ja.length();i++){
                JSONObject jsonObject = ja.getJSONObject(i);

                modelHomeList.add(new ModelHome(jsonObject.getString("name"), jsonObject.getString("description"), jsonObject.getInt("id"), jsonObject.getInt("price"), jsonObject.getInt("stock")));

            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        bind.rv.setAdapter(new HomeAdapter(context, modelHomeList));
    }
}
