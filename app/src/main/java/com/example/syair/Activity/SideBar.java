package com.example.syair.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.syair.R;

public class SideBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_bar);
        getSupportActionBar();
    }
}