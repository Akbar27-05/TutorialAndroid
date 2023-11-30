package com.example.android_25.APi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android_25.Adapter.HomeAdapter;
import com.example.android_25.Helper.ConnHelper;
import com.example.android_25.Model.ModelFoto;
import com.example.android_25.Model.ModelHome;
import com.example.android_25.Response;
import com.example.android_25.databinding.ActivityDetailBinding;
import com.example.android_25.databinding.ActivityHomeBinding;
import com.example.android_25.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Foto extends AsyncTask<String, Void, Response> {
    String url = "http://10.0.2.2:5000/api/Home/Item/Photo/";
    Context context;
    ActivityDetailBinding bind;
    List<ModelFoto> modelHomeList = new ArrayList<>();


    public Foto(Context context, ActivityDetailBinding bind) {
        this.context = context;
        this.bind = bind;
    }

    @Override
    protected Response doInBackground(String... strings) {
        url = url+strings[0];
        ConnHelper ch = new ConnHelper(url, context);
        return ch.getData();
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        Log.d("homes", response.data);
        try{
            JSONObject jo = new JSONObject(response.data);
            try{
                URL url = new URL("http://10.0.2.2:5000/api/Home/Item/Photo/1");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();
                Bitmap img = BitmapFactory.decodeStream(is);
                bind.iv.setImageBitmap(img);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
