package com.akbar27.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.akbar27.myapplication.databinding.ActivityUpdateDataBinding;

public class UpdateDataActivity extends AppCompatActivity {

    private DataModel dataModel;

    ActivityUpdateDataBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.stdName.getText().toString();
                String roll = binding.stdRollNo.getText().toString();
                String className = binding.stdClass.getText().toString();


            }
        });
    }
}