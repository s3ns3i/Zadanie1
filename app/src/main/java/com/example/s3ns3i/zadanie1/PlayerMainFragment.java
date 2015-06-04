package com.example.s3ns3i.zadanie1;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by s3ns3i on 2015-06-04.
 */
public class PlayerMainFragment extends Fragment implements MediaPlayer.OnPreparedListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NAME = "name";
    private static final String PATH = "path";
    private static final String INDEX = "index";

    /**
     * It will determine interval of seek bar updates.
     */
    final int UPDATE_TIME = 1000;

    /**
     * Interface used to communicate with activity.
     */
    OnFragmentInteractionListener mListener;
    /**
     * Updates time of the song.
     */
    Timer timer;
    /**
     * Shows current time of the song.
     */
    TextView currentSongTimeTextView;
    /**
     * Shows duration of the song.
     */
    TextView songLengthTextView;
    /**
     * Shows song name.
     */
    TextView songNameTextView;
    /**
     * Plays and pauses song.
     */
    Button playSongButton;
    /**
     * Plays music.
     */
    MediaPlayer mp;
    //
    boolean prepared;
    //
    Handler seekBarHandler;
    //
    /**
     * Can be used to rewind song and shows progress.
     */
    SeekBar seekBar;
    /**
     * Opens playlist. -- PlaylistFragment
     */
    Button openPlaylistButton;
    String mSongName;
    String mSongPath;
    String mSongIndex;

    public PlayerMainFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public static PlayerMainFragment newInstance(String name, String path, String index) {
        if(name == null || path == null || index == null){
            return new PlayerMainFragment();
        }
        PlayerMainFragment fragment = new PlayerMainFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putString(PATH, path);
        args.putString(INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * If there are arguments, then put them into fragment.
         * It will be used f.e. for marking currently playing song.
         */
        if (getArguments() != null) {
            mSongName = getArguments().getString(NAME);
            mSongPath = getArguments().getString(PATH);
            mSongIndex = getArguments().getString(INDEX);
            Uri uri = Uri.parse(mSongPath);
                    Log.d("s3ns3i: ", uri.toString());
                    if(uri != null) {
                        if(mp == null)
                            mp = new MediaPlayer();
                        if(mp.isPlaying())
                            mp.stop();
                        mp.setOnPreparedListener(this);
                        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mp.setDataSource(getActivity().getApplicationContext(), uri);
                            mp.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_third_app, container, false);

        /**
         * INITIALIZATION OF LAYOUT ELEMENTS
         */
        currentSongTimeTextView = (TextView) rootView.findViewById(R.id.currentSongTimeTextView);
        songLengthTextView = (TextView) rootView.findViewById(R.id.songLengthTextView);
        songNameTextView = (TextView) rootView.findViewById(R.id.songNameTextView);
        if(mp != null) {
            songLengthTextView.setText(TimeConvert.convertFromMilliseconds(mp.getDuration()));
            songNameTextView.setText(mSongName);
        }
        playSongButton = (Button) rootView.findViewById(R.id.playSongButton);
        seekBarHandler = new Handler();
        seekBar = (SeekBar) rootView.findViewById(R.id.seekBar);

        openPlaylistButton = (Button) rootView.findViewById(R.id.openPlaylistButton);
        /**
         * Playlist button logic.
         */
        openPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onFragmentInteraction(mSongIndex);
                }
            }
        });

        /**
         * Play/Pause button logic.
         */
        playSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if song is playing.
                if(mp.isPlaying()) {
                    mp.stop();
                    //Set flag to false.
                    //THIS MAYBE CAN BE DELETED.
                    prepared = false;
                }
                else if(!prepared) {
                    try {
                        mp.prepare();
                        mp.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        prepared = true;
        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                currentSongTimeTextView.setText(Integer.toString(mp.getCurrentPosition()));
//            }
//        }, UPDATE_TIME);
        mp.start();
//        final Handler handler = new Handler();
//        MediaPlayerTimer mediaPlayerTimer = new MediaPlayerTimer(currentSongTimeTextView, mp);
//        handler.removeCallbacks(mediaPlayerTimer);
////        handler.postDelayed(mediaPlayerTimer, UPDATE_TIME);
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//      /* do what you need to do */
//                String currentTime;
                // TODO Omg co za wkurzający syf. Muszę rozwiązać problem timera.
                // TODO każą użyć Handlera, ale on ma tę samą przeszkodę co Timer - referencja do
                // TODO obiektu musi być konst. KURWA JEGO MAĆ.
//                currentTime = TimeConvert.convertFromMilliseconds(mp.getCurrentPosition());
//                currentSongTimeTextView.setText(currentTime);
//      /* and here comes the "trick" */
//                handler.postDelayed(this, 100);
//            }
//        };
//        handler.postDelayed(runnable, 1000);

//        And we also need the Runnable for the Handler

//        timer.schedule(new MediaPlayerTimer(this, mp), UPDATE_TIME);
    }

//    @Override
//    public void getTime(String time) {
//        currentSongTimeTextView.setText(time);
//    }

    public interface OnFragmentInteractionListener {
        /**
         * It will send index of the song.
         * @param songIndex - current index at playlist.
         */
        void onFragmentInteraction(String songIndex);
    }

//    private Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//      /* do what you need to do */
//            String currentTime;
//            currentTime = TimeConvert.convertFromMilliseconds(mediaPlayer.getCurrentPosition());
//            currentSongTimeTextView.setText(currentTime);
//      /* and here comes the "trick" */
//            handler.postDelayed(this, 100);
//        }
//    };
}