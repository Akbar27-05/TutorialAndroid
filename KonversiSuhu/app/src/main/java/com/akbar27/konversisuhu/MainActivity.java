package com.akbar27.konversisuhu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText etNilai;
    TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load();
        // isiSpiner();
    }

    public void load(){
        spinner = findViewById(R.id.spinner);
        etNilai = findViewById(R.id.etNilai);
        tvHasil = findViewById(R.id.tvHasil);
    }

//    public void isiSpiner(){ // ngisi value spiner manual tanpa entries
//        String[] isi = {"Celcius To Reamur", "Celcius To Fahrenheit", "Celcius To Kelvin"};
//        ArrayAdapter<String> adptr = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, isi);
//        spinner.setAdapter(adptr);
//    }

    public void btnKonversi(View view) {
        String pilihan = spinner.getSelectedItem().toString();

        if (etNilai.getText().toString().equals("")){
            Toast.makeText(this, "Nilai tidak boleh ksong", Toast.LENGTH_SHORT).show();
        }else {
            if (pilihan.equals("Celcius To Reamur")){
                CtoR();
            } else if (pilihan.equals("Celcius To Fahrenheit")) {
                CtoF();
            } else if (pilihan.equals("Celcius To Kelvin")) {
                CtoK();
            } else if (pilihan.equals("Reamur To Celcius")) {
                RtoC();
            }else {
                Toast.makeText(this, "Belum di buat", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void CtoR() {
        double suhu = Double.parseDouble(etNilai.getText().toString());
        double hasil = (4.0/5.0) * suhu;

        tvHasil.setText(hasil + "");
    }

    public void CtoF(){
        int suhu = Integer.parseInt(etNilai.getText().toString());
        int hasil = (9/5) * suhu + 32;

        tvHasil.setText(hasil + "");
    }

    public void CtoK(){
        int suhu = Integer.parseInt(etNilai.getText().toString());
        int hasil = suhu + 273;

        tvHasil.setText(hasil + "");
    }

    public  void RtoC(){
        int suhu = Integer.parseInt(etNilai.getText().toString());
        int hasil = (5/4) * suhu;

        tvHasil.setText(hasil + "");
    }

}