package com.example.syair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.syair.Activity.Login;
import com.example.syair.Activity.Register;

public class MainActivity extends AppCompatActivity {
    private int timesplashscreen=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(MainActivity.this, Login.class);
                startActivity(login);
                finish();
            }
        },timesplashscreen);
    }
}