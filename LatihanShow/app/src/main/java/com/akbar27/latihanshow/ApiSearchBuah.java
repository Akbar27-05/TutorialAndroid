package com.akbar27.latihanshow;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.akbar27.latihanshow.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class ApiSearchBuah extends AsyncTask<String, Void, Response> {

    Context context;
    ActivityMainBinding binding;
    String API_URL;

    List<BuahModelClass> buahModelClassList = new ArrayList<>();

    public ApiSearchBuah(Context context, ActivityMainBinding binding, String search) {
        this.context = context;
        this.binding = binding;

        API_URL = "https://script.google.com/macros/s/AKfycbwKWGURGG7D0wbCXJOhoVFbTYJrNVq7NE50kugFBY4432kICg6oxzNH97k5l88UzcGZ/exec?action=getNama&nama="+search+"";
    }

    @Override
    protected Response doInBackground(String... strings) {
        Response response = new Response("");

        try {
            URL url = new URL(API_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer");
            con.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while((line = br.readLine()) != null){
                sb.append(line+"\n");
            }
            response.data = sb.toString();
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(new GridLayoutManager(context, 1));
        try{
            JSONObject jsonObject = new JSONObject(response.data);
            JSONArray users = jsonObject.getJSONArray("data");
            for(int i = 0; i<users.length();i++){
                JSONArray usr = users.getJSONArray(i);

                buahModelClassList.add(new BuahModelClass(usr.getString(0), usr.getString(1), usr.getString(2)));
                binding.rv.setAdapter(new Adapter(context, buahModelClassList));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
