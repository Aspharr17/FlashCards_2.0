package com.example.angelnramirez.flashcards.sql_lite;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String  DATABASE_NAME= "Levels.db";
    private String col[] = {"Level_sct", "NWords_sct", "HighSc_sct", "Atmps_sct"};
    ArrayList<level> array;
    String sqlTables = "Score_table";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public ArrayList<level> getScore()
    {   array = new ArrayList<>();

        try {
            SQLiteDatabase db = getReadableDatabase();
            SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
            qb.setTables(sqlTables);
            Cursor cursor = qb.query(db, col, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    level level = new level();
                    level.setLevel(cursor.getInt(0));
                    level.setNWords(cursor.getInt(1));
                    level.setHighS(cursor.getInt(2));
                    level.setAtmps(cursor.getInt(3));
                    array.add(level);

                } while (cursor.moveToNext());
            }
        }catch (SQLiteAssetException e){
            e.printStackTrace();
        }
        return array;
        }
    //Update max score and attempts
    public void updateScore(int level, int score)
    {
        int atmps, maxScore;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+sqlTables+" WHERE Level_sct ="+level ,null);
        if (cursor.moveToFirst())
        {
            atmps = cursor.getInt(3);
            maxScore = cursor.getInt(2);
            ContentValues contentValues = new ContentValues();
            contentValues.put(col[3], atmps+1);
            if(score > maxScore) contentValues.put(col[2], score);

            db.update(sqlTables, contentValues, "Level_sct = ?", new String[]{Integer.toString(level)});

        }

    }
    //Turn ArrayList (From getScore) to a [][]String to be used in Tablelist
    public String [][] setArray(int ncol)
    {
          String [][] DataTable = new String[array.size()][ncol];

        for (int i = 0; i<array.size();i++)
        {
            DataTable[i][0] = String.valueOf(array.get(i).getLevel());
            DataTable[i][1] = String.valueOf(array.get(i).getNWords());
            DataTable[i][2] = String.valueOf(array.get(i).getHighS());
            DataTable[i][3] = String.valueOf(array.get(i).getAtmps());
        }
        return DataTable;
    }

}
