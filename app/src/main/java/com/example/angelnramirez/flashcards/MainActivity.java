package com.example.angelnramirez.flashcards;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Placeholder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity
{
    GifImageView giv_ship;
    private Placeholder placeholder;
    private ConstraintLayout mainlayout;
    private Button button;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(mainlayout);

                placeholder.setContentId(giv_ship.getId());
                try
                {
                    mp.start();

                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });

    }

    protected void setView ()
    {
        giv_ship = findViewById(R.id.giv_ship);
        placeholder = findViewById(R.id.placeholder);
        mainlayout = findViewById(R.id.MainLayout);
        button = findViewById(R.id.button);

        try {
            mp = MediaPlayer.create(this, R.raw.shiplauncher);
            mp.prepare();

        }
        catch (Exception e){
            e.printStackTrace();

        }


    }
}
