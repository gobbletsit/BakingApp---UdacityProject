package com.example.android.bakingapp.list_detail.view;

import android.support.v4.media.session.MediaSessionCompat;

import com.google.android.exoplayer2.SimpleExoPlayer;

public class MySessionCallback extends MediaSessionCompat.Callback {

    private SimpleExoPlayer mExoPlayer;

    public MySessionCallback(SimpleExoPlayer simpleExoPlayer) {
        this.mExoPlayer = simpleExoPlayer;
    }

    @Override
    public void onPlay() {
        mExoPlayer.setPlayWhenReady(true);
    }

    @Override
    public void onPause() {
        mExoPlayer.setPlayWhenReady(false);
    }

}
