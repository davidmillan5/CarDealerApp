package com.example.cardealerapp;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;


public class ActivityOpenHelper extends SQLiteOpenHelper {


    public ActivityOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Clients(id text primary key, " +
                "fullName text not null, email text not null, active text default 'On')");
        db.execSQL("create table Vehicles(plate text primary key, " +
                "model text not null, brand text not null, active text default 'On')");
        db.execSQL("create table Sales(invoice text primary key, date text not null, " +
                "id text not null, plate text not null, active text default 'On', constraint pk_sales " +
                "foreign key(id) references Clients(id), foreign key(plate) references Vehicles(plate))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}