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
import android.widget.TextView;
import android.widget.Toast;

import com.example.angelnramirez.flashcards.sql_lite.GlobalUser;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    GifImageView giv_ship;
    private Placeholder placeholder;
    private ConstraintLayout mainlayout;
    MediaPlayer mp;
    GlobalUser globalUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        globalUser = (GlobalUser) getApplicationContext();
        setObjects();

    }

    protected void setObjects()
    {
        giv_ship = findViewById(R.id.giv_ship);
        placeholder = findViewById(R.id.placeholder);
        mainlayout = findViewById(R.id.MainLayout);
        ImageButton start = findViewById(R.id.btn_start);
        ImageButton score = findViewById(R.id.btn_score);
        ImageButton profile = findViewById(R.id.btnProfile);
        TextView txtUser = findViewById(R.id.txtUserScore);
        start.setOnClickListener(this);
        score.setOnClickListener(this);
        profile.setOnClickListener(this);
        setSound();
        getUser();
        txtUser.setText("Usuario: "+ globalUser.getUserName());

    }
    protected void getUser()
    {
        if(globalUser.getUserName()==null)
        {
            globalUser.setUserName("Sin sesion iniciada");
        }
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
    protected void transition()
    {
        TransitionManager.beginDelayedTransition(mainlayout);
        final ChangeBounds transition = new ChangeBounds();
        transition.setDuration(200);
        TransitionManager.beginDelayedTransition(mainlayout,transition);
        placeholder.setContentId(giv_ship.getId());
        try
        {
            mp.start();

        }catch (Exception e){
            e.printStackTrace();
        }
        placeholder.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LevelMenu.class);
                startActivity(intent);
                finish();
            }
        },700);
    }
    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_start)
        {
            transition();

        }
        else if(v.getId() == R.id.btn_score)
        {
            if(globalUser.getUser()!= 0)
            {
                Intent intent = new Intent(v.getContext(),ScoreActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast toast = Toast.makeText(this,"No disponible, Inicia Sesion",Toast.LENGTH_LONG);
                toast.show();
            }

        }
        else if(v.getId() == R.id.btnProfile)
        {
            Intent intent = new Intent(v.getContext(),ProfileActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
