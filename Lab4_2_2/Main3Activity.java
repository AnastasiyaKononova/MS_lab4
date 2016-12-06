package com.lab31.admin.lab4_2_2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    Button btnAdd;
    EditText etName, etID, etDate;

    DBHelper dbHelper2;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etID = (EditText) findViewById(R.id.etID);
        etDate = (EditText) findViewById(R.id.etDate);
        dbHelper2 = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // получаем данные из полей ввода
        String name = etName.getText().toString();
        String id_n = etID.getText().toString();
        String date = etDate.getText().toString();

        // подключаемся к БД
        SQLiteDatabase db2 =  Main2Activity.dbHelper2.getWritableDatabase();


        switch (v.getId()) {
            case R.id.btnAdd:
                Log.d(LOG_TAG, "--- Insert in mytable: ---");
                // подготовим данные для вставки в виде пар: наименование столбца - значение

                int version = db2.getVersion();
                /*if(version == 1) {
                    cv.put("id_n", id_n);
                    cv.put("name", name);
                    cv.put("date", date);
                    // вставляем запись и получаем ее ID
                    long rowID = db2.insert("classmates", null, cv);
                    Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                }*/

                    cv.put("id_n", "9");
                    cv.put("first_name", "Алексей");
                    cv.put("middle_name", "Иванович");
                    cv.put("last_name", "Петров");
                    cv.put("date", "28.11.2016");
                    // вставляем запись и получаем ее ID
                    long rowID = db2.insert("classmates", null, cv);
                    Log.d(LOG_TAG, "row inserted, ID = " + rowID);

                break;

        }
        // закрываем подключение к БД
        dbHelper2.close();
    }



    /*class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db2) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db2.execSQL("create table classmates ("
                    + "id integer primary key autoincrement,"
                    + "id_n,"
                    + "name text,"
                    + "date text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {

        }
    }*/
}
