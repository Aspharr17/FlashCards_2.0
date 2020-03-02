package com.example.angelnramirez.flashcards;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.InputStreamReader;

public class LevelMenu extends AppCompatActivity implements View.OnClickListener {

    GridLayout mainGrid;
    RadioGroup selection;
    RadioButton mode;
    ImageButton btnBacklvlMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setuI();
        setSingleEvent(mainGrid);


    }
    private void setuI()
    {
        mainGrid = findViewById(R.id.mainGrid);
        selection = findViewById(R.id.selection_group);
        btnBacklvlMenu = findViewById(R.id.btnBackLvlMenu);
        btnBacklvlMenu.setOnClickListener(this);
        Button btnExLvl = findViewById(R.id.btnExLvl);
        btnExLvl.setOnClickListener(this);

    }
    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int cardSelected = i+1;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selection(cardSelected);

                }
            });
        }
    }

    private void selection(int cardSelected)
    {
        Intent intent;

        mode = findViewById(selection.getCheckedRadioButtonId());
        if(mode.getText().equals("Practica")) intent = new Intent(LevelMenu.this,Playground.class);
        else intent = new Intent(LevelMenu.this,Testground.class);

        intent.putExtra("card",cardSelected);
        intent.putExtra("mode",1);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnBackLvlMenu)
        {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(v.getId() == R.id.btnExLvl)
        {
            Intent intent = new Intent(v.getContext(), ExtraLevelsActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
