package com.akbar27.latihaninsertdata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {


    private MyAdapter adptr;
    private String WEB_URL = "https://script.google.com/macros/s/AKfycbwINuXU2hV1sWV7EYn9zAPQ1zRyMLkrPARP6dN4CjpFGran7THNyCtwBXGdgYrTLjQ2/exec";

    //ListView listView;
    List<DataModel> dataModels = new ArrayList<DataModel>();

    String strName, strPhone, strAddress;


//     CustomAdapter cusAdptr;
//
//    ArrayList<String> listName = new ArrayList<String>();
//    ArrayList<String> listPhone = new ArrayList<String>();
//    ArrayList<String> listAddress = new ArrayList<String>();

    EditText etName, etPhone, etAddress;
    RecyclerView dataRecycle;
    Button btnInsert;

    JSONArray jsonArray;
    // ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adptr = new MyAdapter(this);

        dataRecycle = findViewById(R.id.dataRecycler);
        dataRecycle.setHasFixedSize(true);
        dataRecycle.setAdapter(adptr);
        dataRecycle.setLayoutManager(new LinearLayoutManager(this));


        load();
        SelectData();
    }

    public void load(){
        etName = findViewById(R.id.etStudent);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        btnInsert = findViewById(R.id.btnInsert);

//        progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setMessage("Loading...");

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudentData();
                // progressDialog.show();
            }
        });

    }

    private void SelectData(){
        String url = WEB_URL+"action=get";

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);


         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    jsonArray = response.getJSONArray("items");

                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String name = object.getString("name");
                        String phone = object.getString("phone");
                        String address = object.getString("address");

                        DataModel dataModel = new DataModel(name,phone,address);

                        adptr.addModel(dataModel);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

//                IntStream.range(1, jsonArray.length())
//                        .forEach(i->{
//                            try {
//                                JSONArray json = jsonArray.getJSONArray(i);
//                                strName = json.getString(0);
//                                strPhone = json.getString(1);
//                                strAddress = json.getString(2);
//
//                                listName.add(strName);
//                                listPhone.add(strPhone);
//                                listAddress.add(strAddress);
//
//                                cusAdptr = new CustomAdapter(getApplicationContext(), listName, listPhone, listAddress);
//                                listView.setAdapter(cusAdptr);
//
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
//                        });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonObjectRequest);
    }

    public void addStudentData(){
        String sName = etName.getText().toString();
        String sPhone = etPhone.getText().toString();
        String sAddress = etAddress.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, WEB_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                etName.setText("");
                etAddress.setText("");
                etPhone.setText("");

                Toast.makeText(MainActivity.this, "Data berhasil di simpan!", Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(getApplicationContext(), SuccessActivity.class);
//                startActivity(intent);
                // progressDialog.hide();
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
                params.put("action", "addStudent");
                params.put("vName",sName);
                params.put("vPhone",sPhone);
                params.put("vAddress",sAddress);

                return params;
            }
        };

        int socketTimeOut = 50000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}