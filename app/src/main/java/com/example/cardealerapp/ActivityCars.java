package com.example.cardealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityCars extends AppCompatActivity {

    EditText jetplate, jetbrand, jetmodel;
    CheckBox jcbactive;
    String plate, brand, model;

    ActivityOpenHelper admin = new ActivityOpenHelper(this,"Cardealers.db",null,1);

    long response;

    byte sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        getSupportActionBar().hide();

        jetplate = findViewById(R.id.etplate);
        jetbrand = findViewById(R.id.etbrand);
        jetmodel = findViewById(R.id.etmodel);
        jcbactive = findViewById(R.id.checkboxActive);
        sw = 0;
    }

    public void Save(View view){
        plate = jetplate.getText().toString();
        brand = jetbrand.getText().toString();
        model = jetmodel.getText().toString();

        if(plate.isEmpty() || brand.isEmpty() || model.isEmpty()){
            Toast.makeText(this, "Please fill out the required fields", Toast.LENGTH_SHORT).show();
            jetplate.requestFocus();
        }else{
            SQLiteDatabase database = admin.getWritableDatabase();

            ContentValues row = new ContentValues();
            row.put("plate",plate);
            row.put("model",model);
            row.put("brand",brand);
            
            if(sw==0){
                response = database.insert("Vehicles",null,row);
            }else{
                response = database.update("Vehicles",row,"plate'"+plate+"'",null);
                sw = 0;
            }
            
            if(response == 0){
                Toast.makeText(this, "Problem Occurred While Saving Your Data", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Data  Saved Successfully", Toast.LENGTH_SHORT).show();
                clearFields();
            }
            database.close();
        }
    }


    public void Validate(View view){
        plate = jetplate.getText().toString();

        if(!plate.isEmpty()){
            SQLiteDatabase database = admin.getReadableDatabase();
            Cursor row = database.rawQuery("select * from Vehicles where plate='"+plate+"'",null);

            if(row.moveToNext()){
                sw = 1;
                jetmodel.setText(row.getString(1));
                jetbrand.setText(row.getString(2));
                jcbactive.setChecked(row.getString(3).equals("On"));
            }else{
                Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();
            }

            database.close();
        }else{
            Toast.makeText(this, "Plate is required", Toast.LENGTH_SHORT).show();
            jetplate.requestFocus();
        }
    }

    public void Erase(View view){
        if(sw == 1){
            sw = 0;
            SQLiteDatabase database = admin.getWritableDatabase();
            ContentValues row = new ContentValues();

            row.put("active","Off");
            response = database.update("Vehicles",row,"plate'"+plate+"'",null);

            if(response>0){
                Toast.makeText(this, "Row erased", Toast.LENGTH_SHORT).show();
                clearFields();
            }else{
                Toast.makeText(this, "Error, row could not be erased", Toast.LENGTH_SHORT).show();
            }
            database.close();
        }else{
            Toast.makeText(this, "Search the plate first", Toast.LENGTH_SHORT).show();
            jetplate.requestFocus();
        }
    }
    public void Cancel(View view){
        clearFields();
    }

    private void clearFields(){
        jetplate.setText("");
        jetmodel.setText("");
        jetbrand.setText("");
        jcbactive.setChecked(false);
        jetplate.requestFocus();
        sw = 0;
    }
}