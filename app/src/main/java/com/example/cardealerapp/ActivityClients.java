package com.example.cardealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityClients extends AppCompatActivity {

    EditText etidentification, etfullname, etemail;

    CheckBox checkboxActive;

    String identification, fullname, email;

    ActivityOpenHelper admin = new ActivityOpenHelper(this,"Cardealers.db",null,1);

    long response;

    byte sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);
        getSupportActionBar().hide();

        etidentification = findViewById(R.id.etidentification);
        etfullname = findViewById(R.id.etfullname);
        etemail = findViewById(R.id.etemail);
        checkboxActive = findViewById(R.id.checkboxActive);
        sw = 0;

    }


    public void Save(View view){
        identification = etidentification.getText().toString();
        fullname = etfullname.getText().toString();
        email = etemail.getText().toString();
        if(identification.isEmpty() || fullname.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Please enter all of the data require", Toast.LENGTH_SHORT).show();
            etidentification.requestFocus();
        }else{
            SQLiteDatabase db = admin.getWritableDatabase();
            ContentValues register = new ContentValues();
            register.put("identification", identification);
            register.put("fullname", fullname);
            register.put("email",email);
            if(sw== 0){
                response = db.insert("Clients",null,register);
            }else{
                response = db.update("Clients",register,"identification = '"+identification+"'",null);
                sw = 0;
            }

            if(response == 0){
                Toast.makeText(this, "Error Saving The Information", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Register Save", Toast.LENGTH_SHORT).show();
            }

        }

    }


}