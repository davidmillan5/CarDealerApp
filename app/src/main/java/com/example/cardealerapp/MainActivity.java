package com.example.cardealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Clients(View view){
        Intent intClients = new Intent(this,ActivityClients.class);
        startActivity(intClients);
    }

    public void Vehicles(View view){
        Intent intVehicles = new Intent(this, ActivityCars.class);
        startActivity(intVehicles);
    }

    public void Sales(View view){
        Intent intSales = new Intent(this, ActivitySales.class);
        startActivity(intSales);
    }

}