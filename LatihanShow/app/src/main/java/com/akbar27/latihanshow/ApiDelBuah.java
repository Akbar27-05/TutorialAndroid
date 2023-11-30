package com.akbar27.latihanshow;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.akbar27.latihanshow.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiDelBuah extends AsyncTask<String, Void, Response> {

    ActivityMainBinding binding;
    Context context;
    String API_URL;

    public ApiDelBuah(Context context, ActivityMainBinding binding, String id) {
        this.context = context;
        this.binding = binding;

        API_URL = "https://script.google.com/macros/s/AKfycbwKWGURGG7D0wbCXJOhoVFbTYJrNVq7NE50kugFBY4432kICg6oxzNH97k5l88UzcGZ/exec?action=delete&id="+id;
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

        Toast.makeText(context, "Berhasil menghapus", Toast.LENGTH_SHORT).show();
    }
}
