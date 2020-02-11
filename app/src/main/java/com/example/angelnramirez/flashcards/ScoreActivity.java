package com.example.angelnramirez.flashcards;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import com.example.angelnramirez.flashcards.sql_lite.DatabaseHelper;
import com.example.angelnramirez.flashcards.sql_lite.level;
import java.util.ArrayList;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class ScoreActivity extends AppCompatActivity {
    ArrayList<level> ScoreTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        //FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        ScoreTab = databaseHelper.getScore();
        Resources res = getResources();

        final TableView tb =  findViewById(R.id.TableView);
        tb.setColumnCount(4);
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this,res.getStringArray(R.array.col_array)));

        String [][] DataTable = databaseHelper.setArray(4); //Convert Arraylist to [][]String
        tb.setDataAdapter(new SimpleTableDataAdapter(this, DataTable));



    }
}
