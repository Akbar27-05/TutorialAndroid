package com.akbar27.selectdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String url = "https://script.googleusercontent.com/macros/echo?user_content_key=BSCPEsNK0A91gMP7Szj8L3JeegPCUQA3ZSz9hOYZ3Pmei0_OeccJET37Yce6SabHLrr-xX7x0f-N9pqb0wynfymJ4lkuHD9cm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnKWZ-pYyIa_LWJv871PLEoWHAvEpSBwP_s3c63hdIr4XWGV7qtdBDj3U7sFjJ_ubMkl2ocs343hkjiiOqUU8kRLNC2AvJXe0lEALmaJ23tE4Enz32mD4cb4&lib=M-TmT_fXiZRexZt6urhUuIvUO5JaNTeqG";
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AdapterData adapterData;
    List<DataModel> listData;
    DataModel dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvData);

        getData();
    }
    
    private void getData(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listData = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("items");

                    for (int i = 0; i<jsonArray.length(); i++){
                        dataModel = new DataModel();
                        JSONObject data = jsonArray.getJSONObject(i);

                        dataModel.setName(data.getString("name"));
                        dataModel.setPhone(data.getString("phone"));
                        dataModel.setAddress(data.getString("address"));

                        listData.add(dataModel);
                    }

                    linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    adapterData = new AdapterData(MainActivity.this, listData);
                    recyclerView.setAdapter(adapterData);
                    adapterData.notifyDataSetChanged();

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
}










