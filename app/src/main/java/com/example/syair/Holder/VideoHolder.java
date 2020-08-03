package com.example.syair.Holder;

import android.app.Application;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.syair.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class VideoHolder extends RecyclerView.ViewHolder{

    View view;
    SimpleExoPlayer exoPlayer;
    private PlayerView mExoplayer;



    public VideoHolder(@NonNull View itemView) {
        super(itemView);
        view= itemView;
    }

    public void setView(final Application ctx, String judul, String deskrip, final String url){
        mExoplayer = view.findViewById(R.id.vid);
        TextView jud = itemView.findViewById(R.id.tetle);
        TextView desk = itemView.findViewById(R.id.deskripsi);

        jud.setText(judul);
        desk.setText(deskrip);

        try
            {
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter.Builder(ctx).build();
                TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
                exoPlayer = (SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(ctx);
                Uri video = Uri.parse(url);
                DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("video");
                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
                MediaSource mediaSource = new ExtractorMediaSource(video, dataSourceFactory, extractorsFactory, null, null);
                mExoplayer.setPlayer(exoPlayer);
                exoPlayer.prepare(mediaSource);
                exoPlayer.setPlayWhenReady(false);

        }catch (Exception e){
            Log.e("ViewHolder2", "exoplayer error" + e.toString());
        }
    }
}
