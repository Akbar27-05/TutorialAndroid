package com.akbar27.latihanshow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.akbar27.latihanshow.databinding.ActivityMainBinding;
import com.akbar27.latihanshow.databinding.ActivityPostBuahBinding;

public class UpdateBuahActivity extends AppCompatActivity {

    SharedPreferences sp;
    ActivityPostBuahBinding binding;

    public static final String UpdateSp = "Update";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBuahBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = this.getSharedPreferences(ApiGetIdBuah.GetData, Context.MODE_PRIVATE);
        String id = sp.getString("id", "");
        String nama = sp.getString("nama", "");
        String sisa = sp.getString("sisa", "");

        binding.idBuah.setText(id);
        binding.etNamaBuahUpdate.setText(nama);
        binding.etSisaBuahUpdate.setText(sisa);

        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SharedPreferences sharedPreferences = getSharedPreferences(ApiGetIdBuah.GetData, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.clear();
//                editor.commit();

                String id = binding.idBuah.getText().toString();
                String nama = binding.etNamaBuahUpdate.getText().toString();
                String sisa = binding.etSisaBuahUpdate.getText().toString();

                sp = getSharedPreferences(UpdateSp, MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("id",id);
                editor.putString("nama",nama);
                editor.putString("sisa",sisa);
                editor.apply();

                ApiUpdateBuah update = new ApiUpdateBuah(UpdateBuahActivity.this, binding);
                update.execute();

                Intent intent = new Intent(UpdateBuahActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
