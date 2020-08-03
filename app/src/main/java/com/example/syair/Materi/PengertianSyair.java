package com.example.syair.Materi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.syair.Activity.Dashboard;
import com.example.syair.R;
import com.example.syair.Sharepreferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PengertianSyair extends AppCompatActivity {
    private TextView pengertian;
    Sharepreferences sessions;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengertian_syair);
        sessions = new Sharepreferences(PengertianSyair.this.getApplicationContext());
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        user = mAuth.getCurrentUser();
        pengertian = findViewById(R.id.isipengertian);

        pengertiansyair();
    }

    private void pengertiansyair(){
        mDatabase.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              if (snapshot.exists()){
                  String isi = snapshot.child("Materi").child("Pengertian").getValue().toString();
                  pengertian.setText(isi);
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}