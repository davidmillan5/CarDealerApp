package com.example.cardealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivitySales extends AppCompatActivity {

    EditText etinvoice, etdate, etIdClient, etCarPlate;

    String invoice, date, id, plate;

    ActivityOpenHelper admin = new ActivityOpenHelper(this,"Cardealers.db",null,1);

    long response;

    byte sw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        etinvoice = findViewById(R.id.etinvoice);
        etdate = findViewById(R.id.etdate);
        etIdClient = findViewById(R.id.etIdClient);
        etCarPlate = findViewById(R.id.etCarPlate);
        sw = 0;

    }

    public void Save(View view){
        invoice = etinvoice.getText().toString();
        date = etdate.getText().toString();
        id = etIdClient.getText().toString();
        plate = etCarPlate.getText().toString();

        if(invoice.isEmpty() || date.isEmpty() || id.isEmpty() || plate.isEmpty()){
            Toast.makeText(this, "You need to enter all of the information!", Toast.LENGTH_SHORT).show();
            etinvoice.requestFocus();
        }else{

        }
    }









}