package com.example.cardealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InvoiceActivity extends AppCompatActivity {

    EditText etinvoice2;

    TextView InvoiceNumber, InvoiceDate, InvoiceClient, InvoiceIdClient, InvoiceCarModel, InvoiceCarBrand, InvoiceCarPlate;

    String invoice;

    ActivityOpenHelper admin = new ActivityOpenHelper(this,"Cardealers.db",null,1);

    long response;

    byte sw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        getSupportActionBar().hide();
        etinvoice2 = findViewById(R.id.etinvoice2);
        InvoiceNumber = findViewById(R.id.InvoiceNumber);
        InvoiceDate = findViewById(R.id.InvoiceDate);
        InvoiceIdClient = findViewById(R.id.InvoiceIdClient);
        InvoiceCarPlate = findViewById(R.id.InvoiceCarPlate);
        InvoiceCarBrand = findViewById(R.id.InvoiceCarBrand);
        InvoiceCarModel = findViewById(R.id.InvoiceCarModel);
        InvoiceClient = findViewById(R.id.InvoiceClient);
        sw = 0;
    }

    public void Search(View view){
        invoice = etinvoice2.getText().toString();
        if(!invoice.isEmpty()){
            SQLiteDatabase db = admin.getReadableDatabase();
         //   Cursor row = db.rawQuery("select * from Sales where invoice ='"+invoice+"'",null);

            Cursor row = db.rawQuery("select s.invoice, c.id, s.date, v.plate, c.fullName, v.model, v.brand " +
                    "from Sales as s inner join Clients as c on s.id = c.id inner join Vehicles as v on s.plate = v.plate " +
                    "where invoice ='"+invoice+"'",null);
            if(row.moveToNext()){
                sw = 1;
                InvoiceNumber.setText(row.getString(0));
                InvoiceIdClient.setText(row.getString(1));
                InvoiceDate.setText(row.getString(2));
                InvoiceCarPlate.setText(row.getString(3));
                InvoiceClient.setText(row.getString(4));
                InvoiceCarModel.setText(row.getString(5));
                InvoiceCarBrand.setText(row.getString(6));
            }else{
                Toast.makeText(this, "Enter a Valid Invoice Number", Toast.LENGTH_SHORT).show();
                db.close();
            }
        }else{
            Toast.makeText(this, "Invoice Number is needed to verify", Toast.LENGTH_SHORT).show();
            etinvoice2.requestFocus();
        }
    }


    public void Back(View view){
        Intent intmain = new Intent(this,MainActivity.class);
        startActivity(intmain);
    }



}