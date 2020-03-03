package com.example.angelnramirez.flashcards;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.angelnramirez.flashcards.Words.WordsHelper;

public class ExtraLevelsActivity extends AppCompatActivity implements View.OnClickListener {
    RadioGroup selection;
    RadioButton mode;
    Button btnExlvl1,btnExlvl2,btnExlvl3;
    WordsHelper wordsHelper = new WordsHelper(this);
    String wordsToSave ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_levels);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setUI();


    }
    private void setUI()
    {
        selection = findViewById(R.id.selection_group_ExLvl);
        ImageButton btnBackLvlMenuExLvl = findViewById(R.id.btnBackExtraLvl);
        btnBackLvlMenuExLvl.setOnClickListener(this);

        btnExlvl1 = findViewById(R.id.btnExlvl1);
        btnExlvl2 = findViewById(R.id.btnExLvl2);
        btnExlvl3 = findViewById(R.id.btnExLvl3);
        Button btnborrar = findViewById(R.id.botonborrar);
        btnExlvl1.setOnClickListener(this);
        btnExlvl2.setOnClickListener(this);
        btnExlvl3.setOnClickListener(this);
        btnborrar.setOnClickListener(this);
        Button btnBacktoLvlM = findViewById(R.id.btnBacktoLvlM);
        btnBacktoLvlM.setOnClickListener(this);
        //Agregue el boton borrar, falta el diseno
        btnborrar.setText("Borrar");
        setTitle();


    }
    private void setTitle()
    {
        String [] Title = wordsHelper.extraReader(getApplicationContext(), "extrawords.txt");
        if(Title[0].equals("vacio")) btnExlvl1.setText(Title[0]);
        else                         btnExlvl1.setText("1");

        if(Title[1].equals("vacio")) btnExlvl2.setText(Title[1]);
        else                         btnExlvl2.setText("2");

        if(Title[2].equals("vacio")) btnExlvl3.setText(Title[2]);
        else btnExlvl3.setText("Nivel 3");

    }
    private void addWordsDialog(final int nlvl)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Crear nivel");
        builder.setMessage("Agregue palabra: ");
        final EditText txtInput = new EditText(this);
        builder.setView(txtInput);
        builder.setNeutralButton("Sig. Palabra", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(wordsToSave.equals("")) wordsToSave = txtInput.getText().toString();
                else wordsToSave = wordsToSave+","+txtInput.getText().toString();

                addWordsDialog(nlvl);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                wordsToSave = "";
            }
        });
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(wordsToSave.equals("") && txtInput.getText().toString().equals(""))
                {
                    addWordsDialog(nlvl);
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Agregue palabras antes de guardar",Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    if(wordsToSave.equals("")) wordsToSave = txtInput.getText().toString();
                    else wordsToSave = wordsToSave+","+txtInput.getText().toString();
                    wordsHelper.wordsWriter(nlvl,wordsToSave);
                    setTitle();
                    wordsToSave ="";
                }
            }
        });

        builder.show();


    }
    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.botonborrar)
        {
            wordsHelper.cleanExtra();
            setTitle();
        }
        if(v.getId() == R.id.btnBackExtraLvl)
        {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(v.getId() == R.id.btnBacktoLvlM)
        {
            Intent intent = new Intent(v.getContext(), LevelMenu.class);
            startActivity(intent);
            finish();
        }
        if(v.getId() == R.id.btnExlvl1)
        {
            //Level one selected
            if(btnExlvl1.getText().equals("vacio"))
            {
                addWordsDialog(1);
            }
            else
            {
                Intent intent;

                mode = findViewById(selection.getCheckedRadioButtonId());
                if(mode.getText().equals("Practica")) intent = new Intent(ExtraLevelsActivity.this,Playground.class);
                else intent = new Intent(ExtraLevelsActivity.this,Testground.class);

                intent.putExtra("card",1);
                intent.putExtra("mode",2);
                startActivity(intent);
            }

        }
        if(v.getId() ==R.id.btnExLvl2)
        {
            //Level one selected
            if(btnExlvl2.getText().equals("vacio"))
            {
                addWordsDialog(2);
            }
            else
            {
                Intent intent;
                mode = findViewById(selection.getCheckedRadioButtonId());
                if(mode.getText().equals("Practica")) intent = new Intent(ExtraLevelsActivity.this,Playground.class);
                else intent = new Intent(ExtraLevelsActivity.this,Testground.class);

                intent.putExtra("card",2);
                startActivity(intent);
            }

        }
        if(v.getId() == R.id.btnExLvl3)
        {
            //Level three selected
            //Level one selected
            if(btnExlvl3.getText().equals("vacio"))
            {
                addWordsDialog(3);
            }
            else
            {
                Intent intent;
                mode = findViewById(selection.getCheckedRadioButtonId());
                if(mode.getText().equals("Practica")) intent = new Intent(ExtraLevelsActivity.this,Playground.class);
                else intent = new Intent(ExtraLevelsActivity.this,Testground.class);

                intent.putExtra("card",3);
                startActivity(intent);
            }

        }
    }
}
