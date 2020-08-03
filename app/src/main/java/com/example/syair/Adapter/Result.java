package com.example.syair.Adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syair.Activity.Register;
import com.example.syair.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Result extends AppCompatActivity {
    private TextView b, s, h,k;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        b = findViewById(R.id.benar);
        h = findViewById(R.id.total);
        k = findViewById(R.id.kal);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        final String bener = getIntent().getStringExtra("bener");
        final String seleh = getIntent().getStringExtra("seleh");
        final String jum = getIntent().getStringExtra("jumlah");
        SimpleDateFormat sdf = new SimpleDateFormat("'Tanggal:' dd.MM.yyyy 'Pukul:' HH:mm:ss");
        String datetime = sdf.format(new Date());
        b.setText(bener);
        k.setText(jum);


        double hb = Double.parseDouble(b.getText().toString());
      //  double hs = Double.parseDouble(s.getText().toString());
        double j = Double.parseDouble(k.getText().toString());
       // Toast.makeText(Result.this, jum, Toast.LENGTH_SHORT).show();
        double hasil = (hb/j)*100;
        h.setText(String.format("%.1f", hasil));
        h.setTextColor(Color.RED);

      submitnilai(new RequestNilai(datetime, h.getText().toString()));

    }
    private void submitnilai(RequestNilai requestNilai){
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Data User").child("Daftar Akun").child(user.getDisplayName()).child("Nilai").push().setValue(requestNilai)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
}