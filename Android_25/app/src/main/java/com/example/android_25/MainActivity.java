package com.example.android_25;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android_25.APi.Login;
import com.example.android_25.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding  bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        button();
    }

    public void button(){
        bind.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()){

                } else {
                    Login al = new Login(bind, MainActivity.this);
                    al.execute(bind.email.getText().toString(), bind.password.getText().toString());
                }
//                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }

    public Boolean validation(){
        String email = bind.email.getText().toString();
        String password = bind.password.getText().toString();
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Semua field harus terisi", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}