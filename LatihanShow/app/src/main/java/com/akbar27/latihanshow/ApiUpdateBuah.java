package com.akbar27.latihanshow;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.akbar27.latihanshow.databinding.ActivityPostBuahBinding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiUpdateBuah extends AsyncTask<String, Void, Response> {

    SharedPreferences sp;
    public String API_URL;
    ActivityPostBuahBinding  bind;
    Context context;

    public ApiUpdateBuah(Context context, ActivityPostBuahBinding bind) {
        this.context = context;
        this.bind = bind;

        sp = context.getSharedPreferences(UpdateBuahActivity.UpdateSp, Context.MODE_PRIVATE);
        String id = sp.getString("id", "");
        String nama = sp.getString("nama", "");
        String sisa = sp.getString("sisa", "");

        API_URL = "https://script.google.com/macros/s/AKfycbwKWGURGG7D0wbCXJOhoVFbTYJrNVq7NE50kugFBY4432kICg6oxzNH97k5l88UzcGZ/exec?action=update&id="+id+"&nama="+nama+"&sisa="+sisa+"";
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

            while ((line = br.readLine()) != null) {
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
        Toast.makeText(context, "Berhasil mengupdate", Toast.LENGTH_SHORT).show();
    }
}
