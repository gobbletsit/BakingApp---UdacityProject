package com.example.android.bakingapp.detail.presenter;

import android.content.Context;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.detail.view.StepDetailView;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.util.ArrayList;

public interface StepDetailPresenter {

    void setView(StepDetailView stepDetailView);

    void showStepDetails(Step step);

    void releasePlayer(SimpleExoPlayer simpleExoPlayer);

    void onNextButtonClick(int position, ArrayList<Step> stepArrayList);

    void initializeMediaPlayer(Context context, String mediaURL, SimpleExoPlayer simpleExoPlayer, SimpleExoPlayerView simpleExoPlayerView, long playerPosition);

    void initializeMediaSession(Context context, MediaSessionCompat mediaSessionCompat, PlaybackStateCompat.Builder stateBuilder, String tag, SimpleExoPlayer simpleExoPlayer);

}
