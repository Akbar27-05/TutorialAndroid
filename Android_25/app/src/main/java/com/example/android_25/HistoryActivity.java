package com.example.android_25;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android_25.APi.History;
import com.example.android_25.APi.Home;
import com.example.android_25.databinding.ActivityHistoryBinding;

import javax.net.ssl.HostnameVerifier;

public class HistoryActivity extends AppCompatActivity {
    ActivityHistoryBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        History h = new History(HistoryActivity.this, bind);
        h.execute();
        button();
    }

    public void button(){
        bind.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HistoryActivity.this, CartActivity.class));
            }
        });

        bind.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HistoryActivity.this, HomeActivity.class));
            }
        });
    }
}