package com.example.android_25.APi;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.android_25.Helper.ConnHelper;
import com.example.android_25.Helper.SP;
import com.example.android_25.HomeActivity;
import com.example.android_25.MainActivity;
import com.example.android_25.Response;
import com.example.android_25.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AsyncTask<String, Void, Response> {
    String url = "http://10.0.2.2:5000/api/Login";
    ActivityMainBinding bind;
    Context context;
    public static String token;

    public Login(ActivityMainBinding bind, Context context) {
        this.bind = bind;
        this.context = context;
    }

    @Override
    protected Response doInBackground(String... strings) {
        JSONObject jo = new JSONObject();
        try{
            jo.put("email", strings[0]);
            jo.put("password", strings[1]);
        } catch(Exception e){
            e.printStackTrace();
        }

        ConnHelper ch = new ConnHelper(url, context);
        return ch.postData(jo);
    }


    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        Log.d("data", response.data);
        if(response.data.isEmpty()){
            Toast.makeText(context, "Email atau Password salah", Toast.LENGTH_SHORT).show();
        } else{
            try {
                JSONObject jsonObject= new JSONObject(response.data);

                SP sp = new SP(context);
                sp.setToken(String.valueOf(jsonObject.getInt("id")));
                sp.setEmail(jsonObject.getString("email"));
                sp.setName(jsonObject.getString("name"));
                sp.setPhone(jsonObject.getString("phoneNumber"));
                context.startActivity(new Intent(context, HomeActivity.class));
                Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
