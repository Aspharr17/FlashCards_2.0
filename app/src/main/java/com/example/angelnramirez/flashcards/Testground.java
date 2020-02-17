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
import com.example.angelnramirez.flashcards.sql_lite.GlobalUser;

import java.io.IOException;

public class Testground extends AppCompatActivity implements View.OnClickListener {
    String words [];
    TextView txtWordsTest;
    Button btnStartTest;
    ImageButton btnBackTest, btnIncorrectT, btnCorrectT;
    TextToSpeech ts;
    int nword = 0;
    int correct =0;
    int incorrect = 0;
    int level;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testground);
        //FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getValues();
        setUI();
        databaseHelper = new DatabaseHelper(getApplicationContext());
    }
    protected void setUI()
    {
        txtWordsTest = findViewById(R.id.txtWordsTest);
        btnBackTest = findViewById(R.id.btnBackTest);
        btnStartTest = findViewById(R.id.btnStartT);
        btnCorrectT =findViewById(R.id.btnCorrectT);
        btnIncorrectT = findViewById(R.id.btnIncorrectT);
        btnIncorrectT.setOnClickListener(this);
        btnCorrectT.setOnClickListener(this);
        txtWordsTest.setOnClickListener(this);
        btnStartTest.setOnClickListener(this);
        btnBackTest.setOnClickListener(this);
    }
    protected void getValues()
    {
        if(getIntent() != null)
        {
            level = getIntent().getIntExtra("card",0);
            WordsHelper wordsHelper = new WordsHelper(this);
            try {
                words = wordsHelper.wordsReader(level);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    protected void show()
    {
        btnCorrectT.setVisibility(View.INVISIBLE);
        btnIncorrectT.setVisibility(View.INVISIBLE);
        if(nword<words.length) {
            txtWordsTest.setText(words[nword]);
            txtWordsTest.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //After 3 seconds
                    txtWordsTest.setText("");
                    nword++;
                    btnCorrectT.setVisibility(View.VISIBLE);
                    btnIncorrectT.setVisibility(View.VISIBLE);
                }
            }, 3000);
        }
        else
        {
            finished();
        }

    }
    protected void finished()
    {

        txtWordsTest.setText("Aciertos: "+correct+"\nErrores: "+incorrect);
        GlobalUser globalUser = (GlobalUser)getApplicationContext();
        if(globalUser.getUser()!=0) databaseHelper.updateScore(level,correct,globalUser.getUser());

        txtWordsTest.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtWordsTest.setText("");
                finish();
            }
        },3000);
    }
    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.btnBackTest) finish();

        else if(v.getId()==R.id.btnStartT)

        {   btnStartTest.setVisibility(View.INVISIBLE);
            show();
        }
        else if(v.getId()==R.id.btnCorrectT)
        {
            correct++;
            show();
        }
        else if(v.getId()==R.id.btnIncorrectT)
        {
            incorrect++;
            show();
        }
    }
}
