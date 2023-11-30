package com.akbar27.recyclerviewcardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView; // mangil rcv
    SiswaAdapter adptr; // memanggil class SiswaAdapter
    List<Siswa> siswaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load();
        isiData();
    }

    public void load(){
        recyclerView = findViewById(R.id.rcvSiswa);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void isiData(){
        siswaList = new ArrayList<Siswa>();
        siswaList.add(new Siswa("Joni", "Surabaya"));
        siswaList.add(new Siswa("Eko", "Surabaya"));
        siswaList.add(new Siswa("Tejo", "Surabaya"));
        siswaList.add(new Siswa("Siti", "Surabaya"));
        siswaList.add(new Siswa("Roni", "Surabaya"));
        siswaList.add(new Siswa("Neny", "Surabaya"));

        adptr = new SiswaAdapter(this,siswaList);
        recyclerView.setAdapter(adptr);
    }

    public void btnTambah(View view) {
        siswaList.add(new Siswa("Tono", "Jember"));
        adptr.notifyDataSetChanged();
    }
}