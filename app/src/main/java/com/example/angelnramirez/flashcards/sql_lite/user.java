package com.example.angelnramirez.flashcards.sql_lite;

public class user {
    int IdUser_us;
    String UserName_us;

    public user(){}

    public user(int idUser_us, String userName_us) {
        IdUser_us = idUser_us;
        UserName_us = userName_us;
    }

    public int getIdUser_us() {
        return IdUser_us;
    }

    public void setIdUser_us(int idUser_us) {
        IdUser_us = idUser_us;
    }

    public String getUserName_us() {
        return UserName_us;
    }

    public void setUserName_us(String userName_us) {
        UserName_us = userName_us;
    }
}
