package com.example.angelnramirez.flashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnBackProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setUI();
    }

    protected void setUI()
    {
        btnBackProfile = findViewById(R.id.btnBackProfile);
        btnBackProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnBackProfile)
        {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }


    }
}
