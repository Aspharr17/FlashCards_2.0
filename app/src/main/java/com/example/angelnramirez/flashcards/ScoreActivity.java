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


    //Llevar a Strings en raw
    private String [] Hcol;

   // String [] Hcol = {l,"c2","c3","c4"};

    private String [][] DataTable;


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
        String[] Hcol = res.getStringArray(R.array.col_array);

        final TableView tb =  findViewById(R.id.TableView);
        tb.setColumnCount(4);
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this,Hcol));

        DataTable = databaseHelper.setArray(4); //Convert Arraylist to [][]String
        tb.setDataAdapter(new SimpleTableDataAdapter(this, DataTable));


    }
}
