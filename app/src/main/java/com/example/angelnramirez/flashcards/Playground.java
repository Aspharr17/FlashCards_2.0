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
import com.example.angelnramirez.flashcards.sql_lite.DatabaseHelper;

import java.io.IOException;
import java.util.Locale;

public class Playground extends AppCompatActivity implements View.OnClickListener {
    String [] words;
    TextView txtWords;
    Button btnStartP;
    ImageButton btnNext;
    TextToSpeech ts;
    int nword = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);
        //FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getValues();
        setUI();
    }
    protected void setUI()
    {
        txtWords = findViewById(R.id.txtWordsP);
        btnStartP = findViewById(R.id.btnStartP);
        btnNext = findViewById(R.id.btnNext);
        ImageButton btnBack = findViewById(R.id.btnBackP);
        btnStartP.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        ts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    ts.setLanguage(Locale.getDefault());
                    ts.setSpeechRate(.7f);
                }
            }
        });
    }
    protected void getValues()
    {
        if(getIntent() != null)
        {
            int info = getIntent().getIntExtra("card",0);
            int mode = getIntent().getIntExtra("mode",1);
            WordsHelper wordsHelper = new WordsHelper(this);
            try {
                words = wordsHelper.wordsReader(mode,info);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    protected void show()
    {
        txtWords.setText(words[nword]);
        ts.speak(words[nword],TextToSpeech.QUEUE_FLUSH,null);

        txtWords.postDelayed(new Runnable() {
            @Override
            public void run() {
                //After 3 seconds
            txtWords.setText("");
            nword++;
            if(nword<words.length) btnNext.setVisibility(View.VISIBLE);
            else
            {
                finish();
            }
            }
        }, 3000);


    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnStartP)
        {
            btnStartP.setVisibility(View.INVISIBLE);
            show();
        }
        if(v.getId() == R.id.btnNext)
        {
            btnNext.setVisibility(View.INVISIBLE);
            show();
        }
        if(v.getId() == R.id.btnBackP) finish();
    }
}
