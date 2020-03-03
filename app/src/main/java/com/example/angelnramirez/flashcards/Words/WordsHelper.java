package com.example.angelnramirez.flashcards.Words;


import android.content.Context;
import com.example.angelnramirez.flashcards.R;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class WordsHelper {

    Context context;

    public WordsHelper(Context context) {
        this.context = context;
    }
    //este metodo lee las palabras del documento extra words
    public String[] wordsReader(int mode, int sline) throws IOException {
        String[] words = new String[]{};
        String line;
        InputStream is;
        InputStreamReader isr;
        if(mode == 1)
        {
            is = context.getResources().openRawResource(R.raw.words);
            isr = new InputStreamReader(is);
        }
        else
        {
            FileInputStream fis = context.openFileInput("extrawords.txt");
            isr = new InputStreamReader(fis, "UTF-8");

        }

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
        String [] startingWords = extraReader(context, "extrawords.txt");

        startingWords[nlvl-1] = text;
        System.out.println(text);
        String wordsToWrite ="";
        for (int i = 0; i<startingWords.length; i++)
        {
            extraReader(context, "extrawords.txt");
            wordsToWrite= wordsToWrite+ startingWords[i]+"\n";

        }
        try
        {
            OutputStream fos = context.openFileOutput("extrawords.txt", Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            fos.write(wordsToWrite.getBytes());
            osw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        extraReader(context, "extrawords.txt");
    }
    public void cleanExtra()
    {
        try
        {
            String cleaner = "vacio\nvacio\nvacio\n";
            OutputStream fos = context.openFileOutput("extrawords.txt", Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            fos.write(cleaner.getBytes());
            osw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public String[] extraReader(Context context, String filename) {
        String[] words = new String[3];
        int i = 0;
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                words[i] = line;
                System.out.println(words[i]+i);
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}
