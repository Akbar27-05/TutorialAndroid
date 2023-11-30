package com.example.android_25;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android_25.APi.Foto;
import com.example.android_25.APi.History;
import com.example.android_25.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding bind;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        String name = getIntent().getStringExtra("name");
        int id = getIntent().getIntExtra("id", 0);
        int price = getIntent().getIntExtra("price", 0);
        String description = getIntent().getStringExtra("description");
        int stok = getIntent().getIntExtra("stock", 0);

        bind.productName.setText(name);
        bind.productPrice.setText("Rp. "+price+"");
        bind.productDescription.setText(description);
        bind.stock.setText("Stock : "+stok);
        setTotal();

//        Foto h = new Foto(DetailActivity.this, bind);
//        h.execute();

        button();
    }

    public void button(){
        bind.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int stok = getIntent().getIntExtra("stock", 0);
                if(count < stok){
                    count++;
                    bind.count.setText(count+"");
                    setTotal();
                    //Toast.makeText(DetailActivity.this, stok+"", Toast.LENGTH_SHORT).show();
                } else {
                    bind.increase.setClickable(false);

                }
            }
        });

        bind.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count > 1){
                    count--;
                    bind.count.setText(count+"");
                    setTotal();
                } else {
                    bind.decrease.setClickable(false);
                }
            }
        });

        bind.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailActivity.this, CartActivity.class);
                i.putExtra("subtotal", bind.total.getText().toString());
                startActivity(i);
            }
        });

        bind.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailActivity.this, CartActivity.class));
            }
        });

        bind.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailActivity.this, HistoryActivity.class));
            }
        });
    }

    public void setTotal(){
        int price = getIntent().getIntExtra("price", 0);

        bind.total.setText("Total : IDR"+price*count);
    }
}