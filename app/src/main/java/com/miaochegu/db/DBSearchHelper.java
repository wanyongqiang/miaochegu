package com.miaochegu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSearchHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "miaochegu.db";
    private static final int DATABASE_VERSION = 1;

    public DBSearchHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS t_car" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, carname VARCHAR, cartype VARCHAR, carinfo VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
