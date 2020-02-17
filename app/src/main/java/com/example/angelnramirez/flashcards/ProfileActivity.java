package com.example.angelnramirez.flashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.angelnramirez.flashcards.sql_lite.DatabaseHelper;
import com.example.angelnramirez.flashcards.sql_lite.GlobalUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    GlobalUser globalUser;
    RadioGroup radioGroup;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        globalUser = (GlobalUser)getApplicationContext();
        databaseHelper = new DatabaseHelper(getApplicationContext());
        setUI();
    }

    protected void setUI()
    {

        rbNames();
        ImageButton btnBackProfile = findViewById(R.id.btnBackProfile);
        ImageButton btnSave = findViewById(R.id.btnSave);
        radioGroup = findViewById(R.id.rgUsers);
        btnBackProfile.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }
    protected void rbNames()
    {
        //Set users names in radio buttons
        String [] UsersName = databaseHelper.getUsers();
        RadioButton rb1 = findViewById(R.id.rb1);
        rb1.setText(UsersName[0]);
        RadioButton rb2 = findViewById(R.id.rb2);
        rb2.setText(UsersName[1]);
        RadioButton rb3 = findViewById(R.id.rb3);
        rb3.setText(UsersName[2]);

    }
    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnBackProfile)
        {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        else if(v.getId()==R.id.btnSave)
        {
            int radioID = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(radioID);

            globalUser.setUserName(""+radioButton.getText());

            if(radioID == R.id.rb1) globalUser.setUser(1);
            else if(radioID == R.id.rb2) globalUser.setUser(2);
            else if(radioID == R.id.rb3) globalUser.setUser(3);

            //databaseHelper.UpdateName(1,"Prueba2 ");
            rbNames();
            //El nombre se usara como parametro para el metodo SELECT USER en DBHELPER
            Toast toast = Toast.makeText(v.getContext(),"Guardado",Toast.LENGTH_LONG);
            toast.show();
        }


    }
}
