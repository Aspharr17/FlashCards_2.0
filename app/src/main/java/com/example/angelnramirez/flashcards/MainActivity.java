package com.example.angelnramirez.flashcards;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Placeholder;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    GifImageView giv_ship;
    private Placeholder placeholder;
    private ConstraintLayout mainlayout;
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
        ImageButton start = findViewById(R.id.btn_start);
        ImageButton score = findViewById(R.id.btn_score);
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
            /*TransitionManager.beginDelayedTransition(mainlayout);

            final ChangeBounds transition = new ChangeBounds();
            transition.setDuration(1500L);

            TransitionManager.beginDelayedTransition(mainlayout,transition);
            placeholder.setContentId(giv_ship.getId());
            */

            try
            {
                mp.start();

            }catch (Exception e){
                e.printStackTrace();
            }

            Transition transition2 = new Slide(Gravity.START);
            transition2.setDuration(1500);
            transition2.setInterpolator(new DecelerateInterpolator());
            getWindow().setEnterTransition(transition2);
            Intent intent = new Intent(v.getContext(), LevelMenu.class);
            startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());

        }
        else if(v.getId() == R.id.btn_score)
        {
            Intent intent = new Intent(v.getContext(),ScoreActivity.class);
            startActivity(intent);

        }
    }
}
