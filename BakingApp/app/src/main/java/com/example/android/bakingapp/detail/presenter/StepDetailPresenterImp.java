package com.example.android.bakingapp.detail.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.detail.view.StepDetailView;
import com.example.android.bakingapp.list_detail.view.MySessionCallback;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

public class StepDetailPresenterImp implements StepDetailPresenter {

    private StepDetailView mStepDetailView;

    @Override
    public void setView(StepDetailView stepDetailView) {
        this.mStepDetailView = stepDetailView;
    }

    @Override
    public void showStepDetails(Step step) {
        mStepDetailView.showStepDetails(step);
    }

    @Override
    public void releasePlayer(SimpleExoPlayer simpleExoPlayer) {
        simpleExoPlayer.stop();
        simpleExoPlayer.release();
        simpleExoPlayer = null;
        mStepDetailView.releasePlayer(simpleExoPlayer);


    }

    @Override
    public void onNextButtonClick(int position, ArrayList<Step> stepArrayList) {
        Step nextStep = stepArrayList.get(position);
        mStepDetailView.onNextButtonClick(position, nextStep, stepArrayList);
    }

    @Override
    public void initializeMediaPlayer(Context context, String mediaURL, SimpleExoPlayer simpleExoPlayer, SimpleExoPlayerView simpleExoPlayerView, long playerPosition) {
        if (simpleExoPlayer == null){

            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(simpleExoPlayer);

            if (mediaURL.equals("")){
                simpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource
                        (context.getResources(), R.drawable.no_video_available));
            } else {
                String userAgent = Util.getUserAgent(context, "BakingApplication");
                MediaSource mediaSource = new ExtractorMediaSource(getRecipeMediaUri(mediaURL),
                        new DefaultDataSourceFactory(context, userAgent),
                        new DefaultExtractorsFactory(), null, null);

                // to use for orientation change
                if (playerPosition != C.TIME_UNSET){
                    simpleExoPlayer.seekTo(playerPosition);
                }

                simpleExoPlayer.prepare(mediaSource);
                simpleExoPlayer.setPlayWhenReady(false);
                mStepDetailView.initializePlayer(simpleExoPlayer);
            }
        }
    }

    @Override
    public void initializeMediaSession(Context context, MediaSessionCompat mediaSessionCompat, PlaybackStateCompat.Builder stateBuilder, String tag, SimpleExoPlayer simpleExoPlayer) {

        mediaSessionCompat = new MediaSessionCompat(context, tag);

        mediaSessionCompat.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        mediaSessionCompat.setMediaButtonReceiver(null);

        stateBuilder = new PlaybackStateCompat.Builder().setActions(PlaybackStateCompat.ACTION_PLAY |
        PlaybackStateCompat.ACTION_PAUSE | PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mediaSessionCompat.setPlaybackState(stateBuilder.build());

        mediaSessionCompat.setCallback(new MySessionCallback(simpleExoPlayer));

        mediaSessionCompat.setActive(true);

        mStepDetailView.initializeMediaSession(mediaSessionCompat);
    }

    private Uri getRecipeMediaUri(String videoURL){
        Uri uri = Uri.parse(videoURL);
        return uri;
    }


}
