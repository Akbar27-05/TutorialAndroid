package com.example.android_25;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android_25.APi.Service;
import com.example.android_25.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        Service s = new Service(CartActivity.this, bind);
        s.execute();
        button();

        bind.total.setText(getIntent().getStringExtra("subtotal"));
    }

    public void button(){
        bind.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, HomeActivity.class));
            }
        });

        bind.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, HistoryActivity.class));
            }
        });
    }
}