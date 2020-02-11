package com.example.angelnramirez.flashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LevelMenu extends AppCompatActivity {

    GridLayout mainGrid;
    RadioGroup selection;
    RadioButton mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mainGrid = findViewById(R.id.mainGrid);
        selection = findViewById(R.id.selection_group);
        setSingleEvent(mainGrid);

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
        startActivity(intent);
    }


}
