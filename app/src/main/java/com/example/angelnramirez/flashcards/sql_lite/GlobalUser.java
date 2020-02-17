package com.example.angelnramirez.flashcards.sql_lite;

import android.app.Application;

public class GlobalUser extends Application
{
    private int User;
    private String UserName;

    public int getUser() {
        return User;
    }

    public void setUser(int user) {
        User = user;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
