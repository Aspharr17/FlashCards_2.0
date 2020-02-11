package com.example.angelnramirez.flashcards.Words;

import android.content.Context;

import com.example.angelnramirez.flashcards.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WordsHelper {

    Context context;
    public WordsHelper(Context context) {
        this.context = context;
    }

    public String[] wordsReader(int sline) throws IOException {
        String[] words = new String[]{};
        String line;
        int i = 1;

        InputStream is = context.getResources().openRawResource(R.raw.words);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        try {
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
}
