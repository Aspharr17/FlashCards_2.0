package com.example.angelnramirez.flashcards.sql_lite;

public class level {

    int Level;
    int NWords;
    int HighS;
    int Atmps;

    public level() {
    }

    public level(int level, int NWords, int highS, int atmps) {
        Level = level;
        this.NWords = NWords;
        HighS = highS;
        Atmps = atmps;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public int getNWords() {
        return NWords;
    }

    public void setNWords(int NWords) {
        this.NWords = NWords;
    }

    public int getHighS() {
        return HighS;
    }

    public void setHighS(int highS) {
        HighS = highS;
    }

    public int getAtmps() {
        return Atmps;
    }

    public void setAtmps(int atmps) {
        Atmps = atmps;
    }
}
