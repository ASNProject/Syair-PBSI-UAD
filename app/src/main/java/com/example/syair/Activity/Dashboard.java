package com.example.syair.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syair.Adapter.Result;
import com.example.syair.Materi.ContohSyair;
import com.example.syair.Materi.PengertianSyair;
import com.example.syair.Materi.Soal;
import com.example.syair.Materi.Timeline1;
import com.example.syair.Materi.Timeline2;
import com.example.syair.Materi.Timeline3;
import com.example.syair.Materi.Timeline4;
import com.example.syair.Materi.Timeline5;
import com.example.syair.R;
import com.example.syair.Sharepreferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private TextView pengguna, line1, line2, line3, line4, line5, pancingan;
    Sharepreferences sessions;
    private Button pelajaridisini;
    private ImageView materi, pilcontoh,pilsoal,pilvideo, side;
    private CardView t1, t2, t3, t4, t5;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sessions = new Sharepreferences(Dashboard.this.getApplicationContext());
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        user = mAuth.getCurrentUser();
        pengguna = findViewById(R.id.namapengguna);
        line1 = findViewById(R.id.tampilantimeline1);
        line2 = findViewById(R.id.tampilantimeline2);
        line3 = findViewById(R.id.tampilantimeline3);
        line4 = findViewById(R.id.tampilantimeline4);
        line5 = findViewById(R.id.tampilantimeline5);
        t1 = findViewById(R.id.timeline1);
        t2 = findViewById(R.id.timeline2);
        t3 = findViewById(R.id.timeline3);
        t4 = findViewById(R.id.timeline4);
        t5 = findViewById(R.id.timeline5);
        pelajaridisini = findViewById(R.id.buttonpelajaridisini);
        materi = findViewById(R.id.pilihmateri);
        pilcontoh = findViewById(R.id.pilihcontoh);
        pilsoal = findViewById(R.id.pilihsoal);
        pilvideo = findViewById(R.id.pilihvideo);
        pancingan = findViewById(R.id.pancing);
        side = findViewById(R.id.sidebar);

        datauser();
        tampilanline1();
        tampilanline2();
        tampilanline3();
        tampilanline4();
        tampilanline5();

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, Timeline1.class);
                startActivity(i);
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, Timeline2.class);
                startActivity(i);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, Timeline3.class);
                startActivity(i);
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, Timeline4.class);
                startActivity(i);
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, Timeline5.class);
                startActivity(i);
            }
        });

        side.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Dashboard.this, v);
                popupMenu.setOnMenuItemClickListener(Dashboard.this);
                popupMenu.inflate(R.menu.sidebar);
                popupMenu.show();
            }
        });
        pelajaridisini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bpelajari();
            }
        });
        materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materis();
            }
        });
        pilcontoh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contohs();
            }
        });
        pilvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, Video.class);
                startActivity(i);
            }
        });
        pilsoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Data User").child("Soal");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            String timeline1 = snapshot.child("jumlah").getValue().toString();
                            pancingan.setText(timeline1);
                            String f = pancingan.getText().toString();
                            Intent is = new Intent(Dashboard.this, Soal.class);
                            is.putExtra("f", f);
                            startActivity(is);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
    private void datauser(){
        final FirebaseUser user = mAuth.getCurrentUser();
        if (user !=null){
            if (user.getDisplayName() !=null){
                pengguna.setText(user.getDisplayName());
            }
        }
    }
    private void tampilanline1(){
        mDatabase.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String timeline1 = snapshot.child("Timeline").child("1").child("tampilan").getValue().toString();
                    line1.setText(timeline1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void tampilanline2(){
        mDatabase.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String timeline1 = snapshot.child("Timeline").child("2").child("tampilan").getValue().toString();
                    line2.setText(timeline1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void tampilanline3(){
        mDatabase.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String timeline1 = snapshot.child("Timeline").child("3").child("tampilan").getValue().toString();
                    line3.setText(timeline1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void tampilanline4(){
        mDatabase.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String timeline1 = snapshot.child("Timeline").child("4").child("tampilan").getValue().toString();
                    line4.setText(timeline1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void tampilanline5(){
        mDatabase.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String timeline1 = snapshot.child("Timeline").child("5").child("tampilan").getValue().toString();
                    line5.setText(timeline1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void bpelajari(){
        Intent i = new Intent(Dashboard.this, PengertianSyair.class);
        startActivity(i);
    }

    private void materis(){
        Intent i = new Intent(Dashboard.this, PengertianSyair.class);
        startActivity(i);
    }
    private void contohs(){
        Intent i = new Intent(Dashboard.this, ContohSyair.class);
        startActivity(i);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.profil:
                Intent iq = new Intent(Dashboard.this, Profil.class);
                startActivity(iq);
                break;
            case R.id.credit:
                Intent ia = new Intent(Dashboard.this, SideBar.class);
                startActivity(ia);
                break;
            case R.id.signout:
                sessions.setEmail("");
                sessions.setPassword("");
                Intent i = new Intent(Dashboard.this, Login.class);
                startActivity(i);
                finish();
                break;
        }
        return true;
    }
}