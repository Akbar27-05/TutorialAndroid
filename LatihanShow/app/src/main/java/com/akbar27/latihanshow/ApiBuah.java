package com.akbar27.latihanshow;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.akbar27.latihanshow.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiBuah extends AsyncTask<String, Void, Response> {
    String Api_Url = "https://script.google.com/macros/s/AKfycbwKWGURGG7D0wbCXJOhoVFbTYJrNVq7NE50kugFBY4432kICg6oxzNH97k5l88UzcGZ/exec?action=get";
    Context context;
    ActivityMainBinding bind;
    List<BuahModelClass> buahModelClassList= new ArrayList<>();

    public ApiBuah(Context context, ActivityMainBinding bind) {
        this.context = context;
        this.bind = bind;
    }

    @Override
    protected Response doInBackground(String... strings) {
//        StringBuilder builder = new StringBuilder();
        Response response = new Response("");
        try {
            URL url = new URL(Api_Url);
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
        bind.rv.setHasFixedSize(true);
        bind.rv.setLayoutManager(new GridLayoutManager(context, 1));
        try{
            JSONObject jsonObject = new JSONObject(response.data);
            JSONArray users = jsonObject.getJSONArray("data");
            for(int i = 0; i<users.length();i++){
                JSONObject usr = users.getJSONObject(i);

                buahModelClassList.add(new BuahModelClass(usr.getString("id"), usr.getString("nama"), usr.getString("sisa")));
                bind.rv.setAdapter(new Adapter(context, buahModelClassList));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
