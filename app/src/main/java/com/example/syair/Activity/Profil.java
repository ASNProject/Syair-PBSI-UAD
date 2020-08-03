package com.example.syair.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syair.Adapter.RequestNilai;
import com.example.syair.Adapter.RequestProfil;
import com.example.syair.Holder.NilaiHolder;
import com.example.syair.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profil extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private TextView nama, mail, sekolah;
    private FirebaseRecyclerAdapter<RequestNilai, NilaiHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        nama = findViewById(R.id.datanama);
        mail = findViewById(R.id.dataemail);
        sekolah = findViewById(R.id.datasekolah);
        mRecycler = findViewById(R.id.recycleview2);
        mRecycler.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        Query query = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<RequestNilai>()
                .setQuery(query, RequestNilai.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<RequestNilai, NilaiHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull NilaiHolder nilaiHolder, int i, @NonNull RequestNilai requestNilai) { nilaiHolder
                    .bindToPerusahaan(requestNilai, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(Profil.this, "Tersimpan", Toast.LENGTH_SHORT).show();
                        }
                    });

            }

            @NonNull
            @Override
            public NilaiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new NilaiHolder(inflater.inflate(R.layout.item_nilai, parent, false));
            }
        };
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

        data();
    }
    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }
    private Query getQuery(DatabaseReference mDatabase) {
        FirebaseUser user = mAuth.getCurrentUser();
        Query query = mDatabase.child("Data User").child("Daftar Akun").child(user.getDisplayName()).child("Nilai");
        return query;
    }

    private void data(){
        FirebaseUser user = mAuth.getCurrentUser();
    mDatabase.child("Data User").child("Daftar Akun").child(user.getDisplayName()).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            RequestProfil requestProfil = snapshot.getValue(RequestProfil.class);
            nama.setText(requestProfil.getNamapengguna());
            mail.setText(requestProfil.getEmail());
            sekolah.setText(requestProfil.getAsalsekolah());
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    }
}