package com.example.android_25.APi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.android_25.Adapter.HomeAdapter;
import com.example.android_25.Helper.ConnHelper;
import com.example.android_25.Model.ModelHome;
import com.example.android_25.Model.ModelService;
import com.example.android_25.Response;
import com.example.android_25.databinding.ActivityCartBinding;
import com.example.android_25.databinding.ActivityDetailBinding;
import com.example.android_25.databinding.ActivityHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Service extends AsyncTask<String, Void, Response> {
    String url = "http://10.0.2.2:5000/api/Checkout/Service";
    Context context;
    ActivityCartBinding bind;
    List<ModelService> modelHomeList = new ArrayList<>();

    ArrayAdapter adapterSpinner;
    List<String> namaService = new ArrayList<>();

    public Service(Context context, ActivityCartBinding bind) {
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

                modelHomeList.add(new ModelService(jsonObject.getInt("id"), jsonObject.getInt("duration"), jsonObject.getInt("price"), jsonObject.getString("name")));

                namaService.add(jsonObject.getString("name"));
                adapterSpinner = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, namaService);
                bind.spinner.setAdapter(adapterSpinner);
                bind.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(context, "You choose : "+bind.spinner.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
