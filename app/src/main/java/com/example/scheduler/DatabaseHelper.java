package com.example.scheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String dbName = "scheduler.db";
    public static final String tableName = "client";
    public static final String col_id = "id";
    public static final String col_name = "name";
    public static final String col_address = "address";
    public static final String col_company = "company";
    public static final String col_designation = "designation";

    public DatabaseHelper(@Nullable Context context) {
        super(context, dbName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table client " +
                "(id integer primary key autoincrement, " +
                "name text," +
                "address text," +
                "company text," +
                "designation text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + tableName);
        onCreate(db);

    }

    public boolean insertData(String name, String address, String company, String designation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_name, name);
        contentValues.put(col_address, address);
        contentValues.put(col_company, company);
        contentValues.put(col_designation, designation);
        if (db.insert(tableName, null, contentValues) == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + tableName, null);

        return res;

    }
}