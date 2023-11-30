package com.akbar27.latihanshow;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.akbar27.latihanshow.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiPostBuah extends AsyncTask<String, Void, Response> {
    ActivityMainBinding bind;
    public String API_URL;
    SharedPreferences sp;
    Context context;

    public ApiPostBuah(Context context, ActivityMainBinding bind) {
        this.context = context;
        this.bind = bind;
        sp = context.getSharedPreferences(MainActivity.StringSp, Context.MODE_PRIVATE);
        String nama = sp.getString("nama", "");
        String sisa = sp.getString("sisa", "");
        API_URL = "https://script.google.com/macros/s/AKfycbwKWGURGG7D0wbCXJOhoVFbTYJrNVq7NE50kugFBY4432kICg6oxzNH97k5l88UzcGZ/exec?action=insert&nama="+nama+"&sisa="+sisa+"";

    }

    @Override
    protected Response doInBackground(String... strings) {

        Response response = new Response("");
        try {
            URL url = new URL(API_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization","Bearer ");
            con.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while  ((line = br.readLine()) != null){
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
        Toast.makeText(context, "Berhasil menambahkan", Toast.LENGTH_SHORT).show();
    }
}
