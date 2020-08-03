package com.example.syair;

import android.content.Context;
import android.content.SharedPreferences;

public class Sharepreferences {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int private_mode = 0;
    private static final String PREF_NAME="Pegawai";

    public Sharepreferences (Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, private_mode);
        editor = pref.edit();
    }
    public void setEmail (String email){
        editor.putString("email", email);
        editor.commit();
    }
    public String getEmail (){
        return pref.getString("email", null);
    }

    public void setPassword (String password){
        editor.putString("password", password);
        editor.commit();
    }
    public String getPassword (){
        return pref.getString("password", null);
    }
}
