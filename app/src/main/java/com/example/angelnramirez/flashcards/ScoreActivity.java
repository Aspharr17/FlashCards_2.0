package com.example.angelnramirez.flashcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.angelnramirez.flashcards.sql_lite.DatabaseHelper;

import com.example.angelnramirez.flashcards.sql_lite.level;
import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class ScoreActivity extends AppCompatActivity {
    ArrayList<level> ScoreTab;


    //Llevar a Strings en raw
    private String Hcol[] = {"Nivel", "Palabras", "Puntaje mas alto", "Intentos"};
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

        final TableView tb =  findViewById(R.id.TableView);
        tb.setColumnCount(4);
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this,Hcol));

        DataTable = new String[ScoreTab.size()][5];
        for (int i = 0; i<ScoreTab.size();i++)
        {
            DataTable[i][0] = String.valueOf(ScoreTab.get(i).getLevel());
            DataTable[i][1] = String.valueOf(ScoreTab.get(i).getNWords());
            DataTable[i][2] = String.valueOf(ScoreTab.get(i).getHighS());
            DataTable[i][3] = String.valueOf(ScoreTab.get(i).getAtmps());
        }
        tb.setDataAdapter(new SimpleTableDataAdapter(this, DataTable));


    }
}
