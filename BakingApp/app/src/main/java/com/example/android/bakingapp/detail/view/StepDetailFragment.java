package com.example.android.bakingapp.detail.view;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.example.android.bakingapp.BakingApplication;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.detail.presenter.StepDetailPresenter;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
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

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepDetailFragment extends Fragment implements StepDetailView {

    @Inject
    StepDetailPresenter stepDetailPresenter;

    private SimpleExoPlayer mExoPlayer;
    private MediaSessionCompat mMediaSession;
    private PlaybackStateCompat.Builder mStateBuilder;

    private SimpleExoPlayerView simpleExoPlayerView;
    private TextView stepDescription;
    private Button nextStepButton;

    private StepCallback stepCallback;

    private Step step;

    private ArrayList<Step> stepsList;

    private int position;
    private long playerPosition = C.TIME_UNSET;

    public static final String TAG = StepDetailFragment.class.getSimpleName();

    public static StepDetailFragment newInstance (Step step, int position, ArrayList<Step> steps){
        StepDetailFragment stepDetailFragment = new StepDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("step", step);
        args.putInt("position", position);
        args.putParcelableArrayList("steps", steps);
        stepDetailFragment.setArguments(args);
        return stepDetailFragment;
    }

    public StepDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        stepCallback = (StepCallback) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        ((BakingApplication)getActivity().getApplication()).createStepDetailComponent().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);

        simpleExoPlayerView = view.findViewById(R.id.detail_exo_player_view);
        stepDescription = view.findViewById(R.id.step_detail_description_text_view);
        nextStepButton = view.findViewById(R.id.step_detail_next_button);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null){
            step = savedInstanceState.getParcelable("step");
            stepsList = savedInstanceState.getParcelableArrayList("steps_list");
            position = savedInstanceState.getInt("position");
            playerPosition = savedInstanceState.getLong("player_position");

        } else {
            if (getArguments() != null && getArguments().containsKey("step")){
                step = getArguments().getParcelable("step");
                position = getArguments().getInt("position");
                stepsList = getArguments().getParcelableArrayList("steps");
            }
        }

        stepDetailPresenter.setView(this);
        stepDetailPresenter.initializeMediaSession(getActivity(), mMediaSession, mStateBuilder, TAG, mExoPlayer);
        if (step != null){
            stepDetailPresenter.showStepDetails(step);
            stepDetailPresenter.initializeMediaPlayer(getActivity(), step.getVideoURL(), mExoPlayer, simpleExoPlayerView, playerPosition);
            nextStepButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // to switch step fragments
                    int nextPosition = position + 1;
                    if (stepsList != null){
                        // not to go over
                        if (nextPosition != stepsList.size())
                            stepDetailPresenter.onNextButtonClick(nextPosition, stepsList);
                    }
                }
            });
        }




    }

    @Override
    public void onResume() {
        super.onResume();
        if (playerPosition != C.TIME_UNSET){
            mExoPlayer.seekTo(playerPosition);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mExoPlayer != null){
            playerPosition = mExoPlayer.getCurrentPosition();
            stepDetailPresenter.releasePlayer(mExoPlayer);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showStepDetails(Step step) {
        stepDescription.setText(step.getDescription());
    }

    @Override
    public void initializePlayer(SimpleExoPlayer simpleExoPlayer) {
        this.mExoPlayer = simpleExoPlayer;
    }

    @Override
    public void releasePlayer(SimpleExoPlayer simpleExoPlayer) {
        this.mExoPlayer = simpleExoPlayer;

    }

    @Override
    public void onNextButtonClick(int position, Step nextStep, ArrayList<Step> steps) {
        stepCallback.onNextButtonClick(position, nextStep, steps);
    }

    @Override
    public void initializeMediaSession(MediaSessionCompat mediaSessionCompat) {
        this.mMediaSession = mediaSessionCompat;
    }

    public interface StepCallback{

        void onNextButtonClick(int position, Step nextStep, ArrayList<Step> steps);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("step", step);
        outState.putParcelableArrayList("steps_list", stepsList);
        outState.putInt("position", position);
        outState.putLong("player_position", playerPosition);
    }
}
