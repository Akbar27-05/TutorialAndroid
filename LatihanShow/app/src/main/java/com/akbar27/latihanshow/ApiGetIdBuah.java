package com.akbar27.latihanshow;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.akbar27.latihanshow.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

public class ApiGetIdBuah extends AsyncTask<String, Void, Response> {
    ActivityMainBinding bind;
    public String API_URL;
    Context context;

    SharedPreferences sp;

    public static final String GetData = "GetData";

    public ApiGetIdBuah(Context context, ActivityMainBinding bind, String id) {
        this.context = context;

        API_URL = "https://script.google.com/macros/s/AKfycbwKWGURGG7D0wbCXJOhoVFbTYJrNVq7NE50kugFBY4432kICg6oxzNH97k5l88UzcGZ/exec?action=getId&id="+id;
        this.bind = bind;
    }

    @Override
    protected Response doInBackground(String... strings) {

        Response response = new Response("");

        try {
            URL url = new URL(API_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer ");
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

        try {
            JSONObject jsonObject = new JSONObject(response.data);
            JSONArray datas = jsonObject.getJSONArray("data");
            for(int i = 0; i<datas.length();i++){
                JSONArray dta = datas.getJSONArray(i);

                String id = dta.getString(0);
                String nama = dta.getString(1);
                String sisa = dta.getString(2);

                sp = context.getSharedPreferences(GetData, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("id", id);
                editor.putString("nama", nama);
                editor.putString("sisa", sisa);
                editor.apply();

                if(response.data == null){
                    Toast.makeText(context, "Anda masih belum melakukan transaksi", Toast.LENGTH_SHORT).show();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
