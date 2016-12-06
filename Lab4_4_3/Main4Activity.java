package com.lab31.admin.lab4_4_3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    DBHelper dbHelper;
    TextView tvMin;
    TextView tvMax;
    TextView tvCount;
    TextView tvSum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        tvMin = (TextView) findViewById(R.id.tvMin);
        tvMax = (TextView) findViewById(R.id.tvMax);
        tvCount = (TextView) findViewById(R.id.tvCount);
        tvSum = (TextView) findViewById(R.id.tvSum);

        String[] columns = new String[]{"count(*) as Count", "min(population) as Min",
                "max(population) as Max", "sum(population) as Sum"};
        dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query("regionBel", columns, null, null, null, null, null);

        if (c != null) {
            if (c.moveToFirst()) {
                // определяем номера столбцов по имени в выборке
                int countColIndex = c.getColumnIndex("Count");
                int minColIndex = c.getColumnIndex("Min");
                int maxColIndex = c.getColumnIndex("Max");
                int sumColIndex = c.getColumnIndex("Sum");

                tvCount.setText("Количество городов в базе данных = " + c.getInt(countColIndex));
                tvMin.setText("Минимальная численность населения = " + c.getInt(minColIndex) + " чел.");
                tvMax.setText("Максимальная численность населения = " + c.getInt(maxColIndex) + " чел.");
                tvSum.setText("Суммарная численность населения = " + c.getInt(sumColIndex) + " чел.");
            }
            c.close();
        }
    }
}
