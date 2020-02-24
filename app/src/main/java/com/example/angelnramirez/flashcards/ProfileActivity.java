package com.example.angelnramirez.flashcards;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
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
    RadioButton rb1,rb2,rb3;

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
        ImageButton btnEdit1 = findViewById(R.id.btnEdit1);
        ImageButton btnEdit2 = findViewById(R.id.btnEdit2);
        ImageButton btnEdit3 = findViewById(R.id.btnEdit3);
        ImageButton btnDel1 = findViewById(R.id.btnDel1);
        ImageButton btnDel2 = findViewById(R.id.btnDel2);
        ImageButton btnDel3 = findViewById(R.id.btnDel3);
        radioGroup = findViewById(R.id.rgUsers);
        btnBackProfile.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnDel1.setOnClickListener(this);
        btnDel2.setOnClickListener(this);
        btnDel3.setOnClickListener(this);
        btnEdit1.setOnClickListener(this);
        btnEdit2.setOnClickListener(this);
        btnEdit3.setOnClickListener(this);
    }
    protected void rbNames()
    {
        //Set users names in radio buttons
        String [] UsersName = databaseHelper.getUsers();
        rb1 = findViewById(R.id.rb1);
        rb1.setText(UsersName[0]);
        rb2 = findViewById(R.id.rb2);
        rb2.setText(UsersName[1]);
        rb3 = findViewById(R.id.rb3);
        rb3.setText(UsersName[2]);

    }
    protected void showDel(final int id, String nameUser)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Desea Eliminar al usuario: "+nameUser+ "?");
        builder.setMessage("Se perderá el avance guardado");
        builder.setPositiveButton(
                android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHelper.deleteName(id);
                        Toast toast = Toast.makeText(getApplicationContext(),"Eliminado",Toast.LENGTH_SHORT);
                        toast.show();
                        rbNames();
                        globalUser.setUserName(null);
                        globalUser.setUser(0);
                    }
                }

        );
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast toast = Toast.makeText(getApplicationContext(),"Cancelado",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        builder.show();

    }
    protected void showEdit(final int id)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nombre de usuario: ");
        final EditText txtInput = new EditText(this);
        builder.setView(txtInput);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newName = txtInput.getText().toString();
                databaseHelper.updateName(id,newName);
                rbNames();
                globalUser.setUserName(newName);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast toast = Toast.makeText(getApplicationContext(),"Cancelado",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        builder.show();
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
        else if(v.getId() == R.id.btnEdit1)
        {
            showEdit(1);
        }
        else if(v.getId() == R.id.btnEdit2)
        {
            showEdit(2);
        }
        else if(v.getId() == R.id.btnEdit3)
        {
            showEdit(3);
        }
        else if(v.getId() == R.id.btnDel1)
        {
            showDel(1, rb1.getText().toString());
        }
        else if(v.getId() == R.id.btnDel2)
        {
            showDel(2, rb1.getText().toString());

        }
        else if(v.getId() == R.id.btnDel3)
        {
            showDel(3, rb1.getText().toString());

        }


    }

}
