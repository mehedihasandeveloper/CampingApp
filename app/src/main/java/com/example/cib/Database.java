package com.example.cib;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String empQuery = " create table user(id integer PRIMARY KEY AUTOINCREMENT," +
                " fullname text, password text, email text, username text)";

        String empQuery1 = " create table campZone(id integer PRIMARY KEY AUTOINCREMENT," +
                " campname text, location text, number text, price text, description text)";

        db.execSQL(empQuery);
        db.execSQL(empQuery1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addUser(String fullname, String password, String email, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fullname", fullname);
        values.put("password", password);
        values.put("email", email);
        values.put("username", username);
        db.insert("user", null, values);
        db.close();
    }

    public void addCamp(String campname, String location, String number, String price, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("campname", campname);
        values.put("location", location);
        values.put("number", number);
        values.put("price", price);
        values.put("description", description);
        db.insert("campZone", null, values);
        db.close();
    }

    public void updateCamp(Long id, String campname, String location, String number,String price, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("campname", campname);
        values.put("location", location);
        values.put("number", number);
        values.put("price", price);
        values.put("description", description);
        db.update("campZone", values, "id = ?", new String[]{id + ""});
        db.close();
    }

    public int login(String username, String password){
        String[] arr = new String[2];
        arr[0] = username;
        arr[1] = password;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from user where username = ? and password = ? ", arr);
        if (c.moveToFirst()){
            return 1;
        }
        return 0;
    }


    public ArrayList<HashMap<String, String>> getCamps(){
        HashMap<String, String> employee;
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor c = db.rawQuery("select * from campZone ", null);
        ArrayList<HashMap<String, String>> employeeList = new ArrayList<>(c.getCount());
        if (c.moveToFirst()){
            do {

                employee = new HashMap<>();
                employee.put("campname", c.getString(0));
                employee.put("location", c.getString(1));
                employee.put("number", c.getString(2));
                employee.put("price", c.getString(3));
                employeeList.add(employee);
            }while (c.moveToNext());
        }
        db.close();
        return  employeeList;
    }

    public void deleteEmployee(int id){
        SQLiteDatabase  sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("campZone",  " id  = ? ", new String[]
                {String.valueOf(id)});
    }

}
