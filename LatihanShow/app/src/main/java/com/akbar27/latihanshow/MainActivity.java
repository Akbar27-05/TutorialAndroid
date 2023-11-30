package com.akbar27.latihanshow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.akbar27.latihanshow.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sp;
    ActivityMainBinding binding;

    public static final String StringSp = "hii";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ApiBuah buah = new ApiBuah(MainActivity.this, binding);
        buah.execute();

        //initializeUserlist();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etNamaBuah.getText().toString().isEmpty() || binding.etSisaBuah.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Data Kosong!", Toast.LENGTH_SHORT).show();
                }else {
                    String namaBuah = binding.etNamaBuah.getText().toString();
                    String sisaBuah = binding.etSisaBuah.getText().toString();

                    sp = getSharedPreferences(StringSp, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("nama", namaBuah);
                    editor.putString("sisa", sisaBuah);
                    editor.apply();

                    ApiPostBuah postBuah = new ApiPostBuah(MainActivity.this, binding);
                    postBuah.execute();

                    binding.etNamaBuah.setText("");
                    binding.etSisaBuah.setText("");
                }
                ApiBuah buah = new ApiBuah(MainActivity.this, binding);
                buah.execute();
                
                // new fetchData().start();
            }
        });

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String srch = binding.etSearch.getText().toString();

                if (srch.equals("")){
                    ApiBuah buah = new ApiBuah(MainActivity.this, binding);
                    buah.execute();
                }

                ApiSearchBuah search = new ApiSearchBuah(MainActivity.this, binding, srch);
                search.execute();
            }
        });
    }

//    public void initializeUserlist(){
//        userList = new ArrayList<>();
//        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userList);
//        binding.buahList.setAdapter(listAdapter);
//    }

//    class fetchData extends Thread{
//        String data = "";
//
//        @Override
//        public void run(){
//            mainHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    progressDialog = new ProgressDialog(MainActivity.this);
//                    progressDialog.setMessage("Fetching data");
//                    progressDialog.setCancelable(false);
//                    progressDialog.show();
//                }
//            });
//
//            try {
//                URL url = new URL("https://script.google.com/macros/s/AKfycbzYksqSZPMNaDvYu1gdIXMgbJTYGSaSa8sry0Vz_-apOk2JAOkb02Ae687uh-5mAaiY/exec?action=get");
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                String line;
//
////                while ((line = bufferedReader.readLine()) != null){
////                    data = data+line;
////                }
//
//                if (!data.isEmpty()){
//                    JSONObject jsonObject = new JSONObject(data);
//                    JSONArray users = jsonObject.getJSONArray("data"); // ngmbil nama objk
//
//                    buahModelClasses.clear();
//                    for(int i=0; i<users.length(); i++){
//                        JSONObject names = users.getJSONObject(i);
//                        String name = names.getString("nama");
//                        String sisa = names.getString("sisa");
//                        buahModelClasses.add(new BuahModelClass(name, sisa));
//                        binding.rv.setAdapter(new Adapter(context, buahModelClasses));
//                    }
//                }
//
////            } catch (MalformedURLException e) {
////                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (JSONException e) {
//                throw new RuntimeException(e);
//            }
//
//            mainHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    if (progressDialog.isShowing())
//                        progressDialog.dismiss();
//                    listAdapter.notifyDataSetChanged();
//
//                }
//            });
//
//        }
//    }
}