package com.akbar27.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView tvPilihan;
    EditText stdName, stdRollNo, stdClass;
    Button saveData;
    MyAdapter myAdapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView rvData;
    List<DataModel> listData;
    DataModel dataModel;

    String url_web = "https://script.google.com/macros/s/AKfycbz5n5KIVaD69P6HehX3redodRFMSOUeXacoKvaLppNEOuyq35HZ9C7xXR9IH5wWgYG9/exec";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load();
        stdName = findViewById(R.id.stdName);
        stdRollNo = findViewById(R.id.stdRollNo);
        stdClass = findViewById(R.id.stdClass);
        saveData = findViewById(R.id.saveData);
        rvData = findViewById(R.id.rvData);

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    saveData();
                }
        });


        getData();
    }

    public void load(){
        tvPilihan = findViewById(R.id.tvPilihan);
    }

    public void getData(){
        String url = url_web+"?action=get";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listData = new ArrayList<>();
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i =0; i<jsonArray.length();i++){
                        dataModel = new DataModel();
                        JSONObject object = jsonArray.getJSONObject(i);

                        dataModel.setId(object.getString("id"));
                        dataModel.setName(object.getString("name"));
                        dataModel.setRoll(object.getString("roll"));
                        dataModel.setStdClass(object.getString("stdClass"));

                        listData.add(dataModel);

                    }
                    linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                    rvData.setLayoutManager(linearLayoutManager);

                    myAdapter = new MyAdapter(MainActivity.this, listData);
                    rvData.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    private void saveData() {

        String name = stdName.getText().toString();
        String rollNumber = stdRollNo.getText().toString();
        String className = stdClass.getText().toString();

        if (tvPilihan.equals("insert")){
            String url = url_web;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    stdName.setText("");
                    stdRollNo.setText("");
                    stdClass.setText("");

                    myAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<>();
                    params.put("action", "create");
                    params.put("name", name);
                    params.put("roll", rollNumber);
                    params.put("stdClass", className);

                    return params;
                }
            };

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);
        }else {
            String url = url_web+"?action=create&id="+dataModel.getId()+"&name="+dataModel.getName()+"&roll="+dataModel.getRoll()+"&stdClass="+dataModel.getStdClass();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);
        }
    }
}