package com.example.scheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbSchedulersHelper extends SQLiteOpenHelper {
    public static final String dbName = "scheduler.db";
    public static final String tableName = "scheduler";
    public static final String col_id = "id";
    public static final String col_client_id = "client_id";
    public static final String col_time = "time";
    public static final String col_location = "location";
    public static final String col_time_diff = "time_difference";

    public DbSchedulersHelper(@Nullable Context context) {
        super(context, dbName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table client " +
                "(id integer primary key autoincrement, " +
                col_client_id+" integer," +
                col_time + " integer," +
                col_time_diff + " integer," +
                col_location +" text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + tableName);
        onCreate(db);

    }

    public boolean insertData(int clientId, long time, String location, long timeDif) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_client_id, clientId);
        contentValues.put(col_time, time);
        contentValues.put(col_time_diff, timeDif);
        contentValues.put(col_location, location);
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
