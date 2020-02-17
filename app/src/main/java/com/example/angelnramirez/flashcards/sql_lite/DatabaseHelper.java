package com.example.angelnramirez.flashcards.sql_lite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String  DATABASE_NAME= "Database.db";
    private String [] col = {"Level_sct","NWords_sct", "HighSc_sct", "Atmps_sct","IdUser_sct"};
    private String [] colUser = {"IDUser_us","UserName_us"};
    private String score_table = "Score_table";
    private String users_table = "Users_table";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public String [] getUsers()
    {
        String [] UsersName = new String[3];
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + users_table, null);
            if (cursor.moveToFirst()) {
                //Starts in 0
                int i = 0;
                do {
                    UsersName[i] = cursor.getString(1);
                    i++;
                }while (cursor.moveToNext());

            }
            cursor.close();
        }
        catch (SQLiteAssetException e){
            e.printStackTrace();
        }

        return UsersName;
    }
    public void UpdateName(int id,String name)
    {
        String [] UsersName = new String[3];
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(colUser[1],name);
            db.update(users_table,contentValues,colUser[0]+"=?",new String[]{String.valueOf(id)});
        }catch (SQLiteAssetException e){
            e.printStackTrace();
        }
    }
    //Update max score and attempts
    public void updateScore(int level, int score, int idUser)
    {
        int atmps, maxScore;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+score_table+" WHERE Level_sct ="+level+" AND IdUser_sct = "+idUser ,null);
        if (cursor.moveToFirst())
        {
            atmps = cursor.getInt(3);
            maxScore = cursor.getInt(2);
            ContentValues contentValues = new ContentValues();
            contentValues.put(col[3], atmps+1);
            if(score > maxScore) contentValues.put(col[2], score);

            db.update(score_table, contentValues, "Level_sct = ? AND IdUser_sct = ? ",
                    new String[]{Integer.toString(level), Integer.toString(idUser)});

        }
        cursor.close();

    }

    //Turn ArrayList (From getScore) to a [][]String to be used in Tablelist
    public String [][] setArray(int ncol, int idUser)
    {
        ArrayList<level> array = getScore(idUser);
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
    public ArrayList<level> getScore(int idUser)
    {   ArrayList<level> array = new ArrayList<>();

        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM "+score_table+" WHERE IdUser_sct = "+idUser,null);
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

}
