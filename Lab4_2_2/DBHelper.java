package com.lab31.admin.lab4_2_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Admin on 02.12.2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    final String LOG_TAG = "myLogs";
    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "classmates", null, 3);
    }
    @Override
    public void onCreate(SQLiteDatabase db2) {
        // создаем таблицу с полями
        db2.execSQL("create table classmates ("
                + "id integer primary key autoincrement, "
                + "id_n,"
                + "first_name text, "
                + "middle_name text, "
                + "last_name text, "
                + "date text);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int
            newVersion) {
        Log.d(LOG_TAG, " --- onUpgrade database from " + oldVersion
                + " to " + newVersion + " version --- ");
        if (oldVersion < newVersion) {
            db2.execSQL("drop table " + "classmates" + ";");
            onCreate(db2);
        }
        /*if (newVersion == 2) {
            db2.execSQL("drop table classmates;");
            db2.execSQL("create table classmates ("
                    + "id integer primary key autoincrement, "
                    + "id_n,"
                    + "first_name text, "
                    + "middle_name text, "
                    + "last_name text, "
                    + "date text);");
        }*/
    }
}
