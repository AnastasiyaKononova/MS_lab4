package com.lab31.admin.lab4_2_2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    Button btnRep;
    DBHelper dbHelper2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        btnRep = (Button) findViewById(R.id.btnRep);
        btnRep.setOnClickListener(this);

        /*etName = (EditText) findViewById(R.id.etName);
        etID = (EditText) findViewById(R.id.etID);
        etDate = (EditText) findViewById(R.id.etDate);*/
        dbHelper2 = new DBHelper(this);

    }

    public void onClick(View v) {

        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // получаем данные из полей ввода
        /*String name = etName.getText().toString();
        String id_n = etID.getText().toString();
        String date = etDate.getText().toString();*/

        // подключаемся к БД
        SQLiteDatabase db3 = Main2Activity.dbHelper2.getWritableDatabase();


        switch (v.getId()) {
            case R.id.btnRep:
                int version = db3.getVersion();
                Log.d(LOG_TAG, "--- Update classmates: ---");
                Cursor c = db3.query("classmates", null, null, null, null, null, null);
                c.moveToLast();
                int id_nColIndex = c.getColumnIndex("id_n");
                int dateColIndex = c.getColumnIndex("date");
                // подготовим значения для обновления
                /*if (version == 1) {
                    cv.put("id_n", c.getString(id_nColIndex));
                    cv.put("name", "Иванов Иван Иванович");
                    cv.put("date", c.getString(dateColIndex));
                    // обновляем по id
                    int updCount = db3.update("classmates", cv, "id_n = ?",
                            new String[]{c.getString(id_nColIndex)});
                    Log.d(LOG_TAG, "updated rows count = " + updCount);
                }*/
                if (version == 3) {
                    cv.put("id_n", c.getString(id_nColIndex));
                    cv.put("first_name", "Иван");
                    cv.put("middle_name", "Иванович");
                    cv.put("last_name", "Иванов");
                    cv.put("date", c.getString(dateColIndex));
                    // обновляем по id
                    int updCount = db3.update("classmates", cv, "id_n = ?",
                            new String[]{c.getString(id_nColIndex)});
                    Log.d(LOG_TAG, "updated rows count = " + updCount);
                }
                c.moveToNext();
                break;

        }
        // закрываем подключение к БД
        dbHelper2.close();
    }

    /*class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB2", null, 1);
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
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }*/

}
