package com.example.syair.Materi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.syair.Activity.Contoh;
import com.example.syair.Activity.Login;
import com.example.syair.Adapter.RequestContoh;
import com.example.syair.Holder.ContohViewHolder;
import com.example.syair.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ContohSyair extends AppCompatActivity {
    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<RequestContoh, ContohViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contoh_syair);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRecycler = findViewById(R.id.recycleview);
        mRecycler.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        Query query = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<RequestContoh>()
                .setQuery(query, RequestContoh.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<RequestContoh, ContohViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ContohViewHolder contohViewHolder, final int i, @NonNull RequestContoh requestContoh) {
                contohViewHolder.bindToPerusahaan(requestContoh, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = String.valueOf(i);
                        Intent is = new Intent(ContohSyair.this, Contoh.class);
                        is.putExtra("id", id);
                        startActivity(is);
                       // Toast.makeText(ContohSyair.this, id, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @NonNull
            @Override
            public ContohViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new ContohViewHolder(inflater.inflate(R.layout.item_contoh, parent, false));
            }
        };
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

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
        Query query = mDatabase.child("Data User").child("Materi").child("Contoh");
        return query;
    }
}