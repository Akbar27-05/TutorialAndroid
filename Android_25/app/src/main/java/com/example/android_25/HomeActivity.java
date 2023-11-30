package com.example.android_25;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android_25.APi.Home;
import com.example.android_25.Helper.SP;
import com.example.android_25.databinding.ActivityHistoryBinding;
import com.example.android_25.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding bind ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        SP sp = new SP(HomeActivity.this);

        bind.name.setText(" "+sp.getName());
        Home h = new Home(HomeActivity.this, bind);
        h.execute();

        button();
    }

    public void button(){
        bind.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CartActivity.class));
            }
        });

        bind.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, HistoryActivity.class));
            }
        });
    }

}