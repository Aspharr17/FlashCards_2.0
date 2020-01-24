package com.example.angelnramirez.flashcards;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Placeholder;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    GifImageView giv_ship;
    private Placeholder placeholder;
    private ConstraintLayout mainlayout;
    private ImageButton start;
    private ImageButton score;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setObjects();

    }

    protected void setObjects()
    {
        giv_ship = findViewById(R.id.giv_ship);
        placeholder = findViewById(R.id.placeholder);
        mainlayout = findViewById(R.id.MainLayout);
        start = findViewById(R.id.btn_start);
        score = findViewById(R.id.btn_score);
        start.setOnClickListener(this);
        score.setOnClickListener(this);
        setSound();

    }
    protected void setSound()
    {
        try {
            mp = MediaPlayer.create(this, R.raw.shiplauncher);
            mp.prepare();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_start)
        {
            //TransitionManager.beginDelayedTransition(mainlayout);

            final ChangeBounds transition = new ChangeBounds();
            transition.setDuration(1500L);
            TransitionManager.beginDelayedTransition(mainlayout,transition);
            placeholder.setContentId(giv_ship.getId());
            try
            {
                mp.start();

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        else if(v.getId() == R.id.btn_score)
        {
            Intent intent = new Intent(v.getContext(),ScoreActivity.class);
            startActivity(intent);

        }
    }
}
