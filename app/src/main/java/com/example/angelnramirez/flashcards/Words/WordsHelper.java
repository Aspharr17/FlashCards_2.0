package com.example.angelnramirez.flashcards.Words;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import com.example.angelnramirez.flashcards.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class WordsHelper {

    Context context;

    public WordsHelper(Context context) {
        this.context = context;
    }

    public String[] wordsReader(int mode, int sline) throws IOException {
        String[] words = new String[]{};
        String line;
        InputStream is;
        if(mode == 1)
        {
            is = context.getResources().openRawResource(R.raw.words);
        }
        else
        {
            is = context.getResources().openRawResource(R.raw.extrawords);
        }
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        int i = 1;
        try
        {
            while ((line = br.readLine())!= null)
            {
                if(i == sline)
                {
                    words = line.split(",");
                    }
                i++;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

         return words;
    }

    public void wordsWriter(int nlvl, String text)
    {
        //Recibe las palabras que YA ESTAN en el documento,
        String [] startingWords = TitleReader();

        startingWords[nlvl-1] = text;
        System.out.println(text);
        String wordsToWrite ="";
        for (int i = 0; i<startingWords.length; i++)
        {
            System.out.println("Title before writing");
            TitleReader();

            //SE HACE UN STRING DE LO QUE SE VA A ESCRIBIR,
            //TODAS LAS PALABRAS Y TODOS LOS NIVELES EN UN SOLO STRING

            wordsToWrite= wordsToWrite+ startingWords[i]+"\n";

        }
        System.out.println("we are gonna write: \n"+wordsToWrite);
        try
        {
            //Inicio del escribidor
            OutputStream fos = context.openFileOutput("extrawords.txt", Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            fos.write(wordsToWrite.getBytes());
            osw.flush();
            osw.close();
            //FIN del escribidor
        }
        catch (Exception e)
        {
            System.out.println("Eception");
        }
        System.out.println("Title After Writer");
        TitleReader();
    }

    public String[] TitleReader()
    {
        //LEE las palabras del documento EXTRA, retona un arreglo con los NIVELES SEPARADOS, PALABRAS JUNTAS
        InputStream is = context.getResources().openRawResource(R.raw.extrawords);
        InputStreamReader isr = new InputStreamReader(is);

        BufferedReader br = new BufferedReader(isr);
        String[] words = new String[3];
        String line;
        int i = 0;
        try {
            while ((line= br.readLine())!= null)
            {
                words[i] = line;
                System.out.println(words[i]+i);

                i++;

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return words;
    }
}
