package com.lab31.admin.lab4_2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    Button btnRead, btnClear;
    EditText etName, etID, etDate;

    static DBHelper dbHelper2;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       /* btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);*/

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etID = (EditText) findViewById(R.id.etID);
        etDate = (EditText) findViewById(R.id.etDate);

        // создаем объект для создания и управления версиями БД
        dbHelper2 = new DBHelper(this);
    }


    @Override
    public void onClick(View v) {

        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // получаем данные из полей ввода
        /*String name = etName.getText().toString();
        String id_n = etID.getText().toString();
        String date = etDate.getText().toString();*/

        // подключаемся к БД
        SQLiteDatabase db2 = dbHelper2.getWritableDatabase();


        switch (v.getId()) {

            case R.id.btnClear:
                Log.d(LOG_TAG, "--- Clear classmates: ---");
                // удаляем все записи
                int clearCount = db2.delete("classmates", null, null);
                Log.d(LOG_TAG, "deleted rows count = " + clearCount);

                Log.d(LOG_TAG, "--- Insert in classmates: ---");
                // подготовим данные для вставки в виде пар: наименование столбца - значение

                String name = "Синичкин Андрей Анатольевич";

                String date = "28.11.2016";
                cv.put("id_n", "1");
                cv.put("name", name);
                cv.put("date", date);
                long rowID = db2.insert("classmates", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);

                cv.put("id_n", "2");
                cv.put("name", "Дорошкевич Кристина Михайловна");
                cv.put("date", "28.11.2016");
                long rowID2 = db2.insert("classmates", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID2);

                cv.put("id_n", "3");
                cv.put("name", "Рекиш Антон Анатольевич");
                cv.put("date", "28.11.2016");
                long rowID3 = db2.insert("classmates", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID3);

                cv.put("id_n", "4");
                cv.put("name", "Котович Марина Михайловна");
                cv.put("date", "28.11.2016");
                long rowID4 = db2.insert("classmates", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID4);

                cv.put("id_n", "5");
                cv.put("name", "Атрохов Станислав Витальевич");
                cv.put("date", "28.11.2016");
                // вставляем запись и получаем ее ID
                long rowID5 = db2.insert("classmates", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID5);
                break;


            case R.id.btnRead:
                Log.d(LOG_TAG, "--- Rows in classmates: ---");
                // делаем запрос всех данных из таблицы mytable, получаем Cursor
                Cursor c = db2.query("classmates", null, null, null, null, null, null);

                // ставим позицию курсора на первую строку выборки
                // если в выборке нет строк, вернется false
                if (c.moveToFirst()) {

                    // определяем номера столбцов по имени в выборке

                    int idColIndex = c.getColumnIndex("id");
                    int id_nColIndex = c.getColumnIndex("id_n");
                    int nameColIndex = c.getColumnIndex("name");
                    int dateColIndex = c.getColumnIndex("date");

                    do {
                        // получаем значения по номерам столбцов и пишем все в лог
                        Log.d(LOG_TAG,
                                "ID = " + c.getInt(idColIndex) +
                                        "ID_N = " + c.getString(id_nColIndex) +
                                        ", name = " + c.getString(nameColIndex) +
                                        ", date = " + c.getString(dateColIndex));
                        // переход на следующую строку
                        // а если следующей нет (текущая - последняя), то false - выходим из цикла
                    } while (c.moveToNext());
                } else
                    Log.d(LOG_TAG, "0 rows");
                c.close();
                break;
        }
        // закрываем подключение к БД
        dbHelper2.close();
    }



    class DBHelper extends SQLiteOpenHelper {

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
        public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {

        }
    }
}
