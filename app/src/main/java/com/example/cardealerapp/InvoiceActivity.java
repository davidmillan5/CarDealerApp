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

    String invoice, id, date, plate, fullName, model, brand;

    ActivityOpenHelper admin = new ActivityOpenHelper(this,"Cardealers.db",null,1);

    long response;

    byte sw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        getSupportActionBar().hide();
        etinvoice2 = findViewById(R.id.etinvoice2);
        sw = 0;
    }

    public void Search(){
        invoice = etinvoice2.getText().toString();
        if(!invoice.isEmpty()){
            SQLiteDatabase db = admin.getReadableDatabase();
            Cursor row = db.rawQuery("select invoice, c.id, date, v.plate, fullName, model, brand " +
                    "from Sales s inner join Clients c on s.id = c.id inner join Vehicles v on s.plate = v.plate " +
                    "where invoice ='"+invoice+"'",null);
            if(row.moveToNext()){
                sw = 1;
                InvoiceNumber.setText(row.getString(1));
                InvoiceIdClient.setText(row.getString(2));
                InvoiceDate.setText(row.getString(3));
                InvoiceCarPlate.setText(row.getString(4));
                InvoiceClient.setText(row.getString(5));
                InvoiceCarModel.setText(row.getString(6));
                InvoiceCarBrand.setText(row.getString(7));
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