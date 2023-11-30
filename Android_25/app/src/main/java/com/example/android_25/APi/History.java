package com.example.android_25.APi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.android_25.Adapter.HistoryAdapter;
import com.example.android_25.Adapter.HomeAdapter;
import com.example.android_25.Helper.ConnHelper;
import com.example.android_25.Helper.SP;
import com.example.android_25.Model.ModelHistory;
import com.example.android_25.Model.ModelHome;
import com.example.android_25.Response;
import com.example.android_25.databinding.ActivityHistoryBinding;
import com.example.android_25.databinding.ActivityHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class History extends AsyncTask<String, Void, Response> {
    String url = "http://10.0.2.2:5000/api/History/Transaction/";
    Context context;
    ActivityHistoryBinding bind;
    List<ModelHistory> modelHomeList = new ArrayList<>();


    public History(Context context, ActivityHistoryBinding bind) {
        this.context = context;
        this.bind = bind;
    }

    @Override
    protected Response doInBackground(String... strings) {
        SP sp = new SP(context);
        url = url + Integer.parseInt(sp.getToken());
        //Log.d("fance", url);
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

                modelHomeList.add(new ModelHistory(jsonObject.getString("name"), jsonObject.getInt("id"), jsonObject.getInt("count"), jsonObject.getInt("price")));
                bind.rv.setAdapter(new HistoryAdapter(context, modelHomeList));
            }

            if(response.data == null){
                Toast.makeText(context, "Anda masih belum melakukan transaksi", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}


