package com.example.cardealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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
            SQLiteDatabase dbWritable = admin.getWritableDatabase();

            SQLiteDatabase dbReadable = admin.getReadableDatabase();

            ContentValues row = new ContentValues();

            Cursor cursorClient = dbReadable.rawQuery("select id from Clients where id='"+id+"'",null);
            Cursor cursorVehicle = dbReadable.rawQuery("select plate from Vehicles where plate='"+plate+"'",null);

            if(cursorClient.moveToNext() && cursorVehicle.moveToNext()){

                row.put("invoice",invoice);
                row.put("date", date);
                row.put("id", id);
                row.put("plate",plate);

                response = dbWritable.insert("Sales",null,row);

                if(response == 0){
                    Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
                    Clean_fields();
                }

            }else{
                Toast.makeText(this, "Id and Plate do not exist, please enter valid data", Toast.LENGTH_SHORT).show();
            }
            dbReadable.close();
            dbWritable.close();
        }
    }

    public void Cancel(View view){
        Clean_fields();
    }

    public void Back(View view){
        Intent intmain = new Intent(this,MainActivity.class);
        startActivity(intmain);
    }

    public void Generate(View view){
        Intent intInvoice = new Intent(this, InvoiceActivity.class);
        startActivity(intInvoice);
    }


    private void Clean_fields(){
        etinvoice.setText("");
        etdate.setText("");
        etCarPlate.setText("");
        etIdClient.requestFocus();
    }






}