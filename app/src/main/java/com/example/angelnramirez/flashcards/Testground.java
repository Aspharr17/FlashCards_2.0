package com.example.angelnramirez.flashcards;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.angelnramirez.flashcards.Words.WordsHelper;

import java.io.IOException;

public class Testground extends AppCompatActivity implements View.OnClickListener {
    String words [];
    TextView txtWordsTest;
    Button btnStartTest;
    ImageButton btnBackTest;
    TextToSpeech ts;
    int nword = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testground);
        //FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getValues();
        setUI();
    }
    protected void setUI()
    {
        txtWordsTest = findViewById(R.id.txtWordsTest);
        btnBackTest = findViewById(R.id.btnBackTest);
        btnStartTest = findViewById(R.id.btnStartT);
        txtWordsTest.setOnClickListener(this);
        btnStartTest.setOnClickListener(this);
        btnBackTest.setOnClickListener(this);
    }
    protected void getValues()
    {
        if(getIntent() != null)
        {
            int info = getIntent().getIntExtra("card",0);
            WordsHelper wordsHelper = new WordsHelper(this);
            try {
                words = wordsHelper.wordsReader(info);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnBackTest) finish();
    }
}
