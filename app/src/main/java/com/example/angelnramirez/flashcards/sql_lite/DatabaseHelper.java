package com.example.angelnramirez.flashcards.sql_lite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteAssetHelper {
    public static final String  DATABASE_NAME= "Levels.db";
    public static final String TABLE_NAME = "Score_table";
    public static final String COL_1 = "Level_sct";
    public static final String COL_2 = "NWords_sct";
    public static final String COL_3 = "HighSc_sct";
    public static final String COL_4= "Atmps_sct";
    private  SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();

    }
    public ArrayList<level> getScore()
    {
        ArrayList<level> array = new ArrayList<>();
        String col[] = new String[]{COL_1,COL_2,COL_3,COL_4};

        Cursor cursor = db.query(TABLE_NAME,col,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            do {

                level level = new level();
                level.setLevel(cursor.getInt(0));
                level.setNWords(cursor.getInt(1));
                level.setHighS(cursor.getInt(2));
                level.setAtmps(cursor.getInt(3));
                array.add(level);

                }while (cursor.moveToNext());
            }
        return array;
        }





}
