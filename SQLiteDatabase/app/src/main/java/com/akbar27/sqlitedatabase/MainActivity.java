package com.akbar27.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etBarang, etStock, etHarga;
    TextView tvPilihan;


    List<Barang> dataBarang = new ArrayList<Barang>();
    BarangAdapter adptr;

    RecyclerView rcvBarang;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load();
        selectData();
    }

    public void load(){
        db = new Database(this);

        db.buatTabel();

        etBarang = findViewById(R.id.etBarang);
        etStock = findViewById(R.id.etStock);
        etHarga = findViewById(R.id.etHarga);
        tvPilihan = findViewById(R.id.tvPilihan);

        rcvBarang = findViewById(R.id.rcvBarang);

        rcvBarang.setLayoutManager(new LinearLayoutManager(this)); // menampilkan data adaptr ke rcv
        rcvBarang.setHasFixedSize(true);
    }

    public void btnSimpan(View v){
        String barang = etBarang.getText().toString();
        String stock = etStock.getText().toString();
        String harga = etHarga.getText().toString();
        String pilihan = tvPilihan.getText().toString();

        if (barang.isEmpty() || stock.isEmpty() || harga.isEmpty()){
            pesan("Data kosong");
        }else {
            if (pilihan.equals("insert")){
                String sql = "insert into tblBarang (barang,stock,harga) values ('"+barang+"',"+stock+","+harga+")";
                if (db.runSQL(sql)){
                    pesan("Insert");
                }else {
                    pesan("Insert gagal");
                }
            }else {
                pesan("Update");
            }
        }

        etBarang.setText("");
        etStock.setText("");
        etHarga.setText("");
        tvPilihan.setText("insert");
    }

    public void pesan(String isi){
        Toast.makeText(this, isi, Toast.LENGTH_SHORT).show();
    }

    public void selectData(){
        String sql = "select * from tblBarang order by barang ASC";
        Cursor cursor = db.select(sql);

        dataBarang.clear();

        if (cursor.getCount() > 0){ // kondisi jika data yg di database lebih dari 0
            while (cursor.moveToNext()){
                String idBarang = cursor.getString(0);
                String barang = cursor.getString(1);
                String stock = cursor.getString(2);
                String harga = cursor.getString(3);

                dataBarang.add(new Barang(idBarang,barang,stock,harga)); // memasukan kedlm model
            }

            adptr = new BarangAdapter(this, dataBarang);
            rcvBarang.setAdapter(adptr);
            adptr.notifyDataSetChanged();
        }else {
            pesan("Data Kosong");
        }
    }
}