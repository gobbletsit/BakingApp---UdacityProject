package com.example.android.bakingapp.detail.view;

import android.net.Uri;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import com.example.android.bakingapp.data.Step;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;

public interface StepDetailView {

    void showStepDetails(Step step);

    void initializePlayer(SimpleExoPlayer simpleExoPlayer);

    void releasePlayer(SimpleExoPlayer simpleExoPlayer);

    void onNextButtonClick(int position, Step nextStep, ArrayList<Step> steps);

    void initializeMediaSession(MediaSessionCompat mediaSessionCompat);

}
