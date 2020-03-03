package com.example.angelnramirez.flashcards;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.angelnramirez.flashcards.sql_lite.DatabaseHelper;
import com.example.angelnramirez.flashcards.sql_lite.GlobalUser;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnBackScore;
    GlobalUser globalUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        //FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setObjects();
        setTable();

    }
    protected void setObjects()
    {
        TextView txtUserScore =findViewById(R.id.txtUserScore);
        btnBackScore = findViewById(R.id.btnBackScore);
        btnBackScore.setOnClickListener(this);
        globalUser = (GlobalUser)getApplicationContext();
        txtUserScore.setText(globalUser.getUserName());
    }
    protected void setTable()
    {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        Resources res = getResources();

        final TableView tb =  findViewById(R.id.TableView);
        tb.setColumnCount(4);
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this,res.getStringArray(R.array.col_array)));

        String [][] DataTable = databaseHelper.setArray(4,globalUser.getUser());
        tb.setDataAdapter(new SimpleTableDataAdapter(this, DataTable));
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnBackScore)
        {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
