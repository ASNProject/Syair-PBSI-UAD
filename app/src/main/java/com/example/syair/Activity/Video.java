package com.example.syair.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.syair.Adapter.RequestVideo;
import com.example.syair.Holder.VideoHolder;
import com.example.syair.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Video extends AppCompatActivity {
    FirebaseDatabase mFirebaseDatabase2;
    DatabaseReference mref2;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        recyclerView = findViewById(R.id.recyclevideo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFirebaseDatabase2 = FirebaseDatabase.getInstance();
        mref2 = mFirebaseDatabase2.getReference().child("Data User").child("Video");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<RequestVideo> options =
                new FirebaseRecyclerOptions.Builder<RequestVideo>()
                .setQuery(mref2,RequestVideo.class)
                .build();
        FirebaseRecyclerAdapter<RequestVideo, VideoHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<RequestVideo, VideoHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull VideoHolder videoHolder, int i, @NonNull RequestVideo requestVideo) {
                        videoHolder.setView(getApplication(),requestVideo.getJudul(), requestVideo.getDeskripsi(),requestVideo.getUrl());
                    }

                    @NonNull
                    @Override
                    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_video,parent,false);

                        return new VideoHolder(view);
                    }
                };

        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}