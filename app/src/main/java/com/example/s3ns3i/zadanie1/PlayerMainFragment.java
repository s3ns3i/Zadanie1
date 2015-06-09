package com.example.s3ns3i.zadanie1;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

/**
 * Created by s3ns3i on 2015-06-04.
 */
public class PlayerMainFragment extends Fragment implements MediaPlayer.OnPreparedListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String INDEX = "index";

    /**
     * It will determine interval of seek bar updates.
     */
    final int UPDATE_TIME = 1000;

    ThirdApp activity;
    /**
     * Interface used to communicate with activity.
     */
    OnFragmentInteractionListener mListener;
    /**
     * Shows current time of the song.
     */
    ImageView songImageView;
    TextView currentSongTimeTextView;
    /**
     * Shows duration of the song.
     */
    TextView songLengthTextView;
    /**
     * Shows songs name.
     */
    TextView songTitleTextView;
    /**
     * Shows songs artist.
     */
    TextView songArtistTextView;
    /**
     * Plays and pauses song.
     */
    TextView shuffleLabel;
    TextView loopLabel;

    Button playSongButton;
    Button previousSongButton;
    Button nextSongButton;
    Button shuffleButton;
    Button loopButton;
    /**
     * Plays music.
     */
    MediaPlayer mp;
    //
    boolean prepared;
    boolean playOnNext;
    boolean shuffle;
    boolean loop;
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
    Song currentSong;

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

    public static PlayerMainFragment newInstance(Integer index) {
        if(index == null){
            return new PlayerMainFragment();
        }
        PlayerMainFragment fragment = new PlayerMainFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX, index);
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
        activity = (ThirdApp) getActivity();
        loop = activity.isLoop();
        shuffle = activity.isShuffle();
        currentSong = new Song();
        if (getArguments() != null) {
            prepareSong(getArguments().getInt(INDEX));
        }
        else
            currentSong.setIndex(0);
        playOnNext = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_third_app, container, false);

        /**
         * INITIALIZATION OF LAYOUT ELEMENTS
         */
        songImageView = (ImageView) rootView.findViewById(R.id.songImageView);
        currentSongTimeTextView = (TextView) rootView.findViewById(R.id.currentSongTimeTextView);
        songLengthTextView = (TextView) rootView.findViewById(R.id.songLengthTextView);
        songTitleTextView = (TextView) rootView.findViewById(R.id.songTitleTextView);
        songArtistTextView = (TextView) rootView.findViewById(R.id.songArtistTextView);
        shuffleLabel = (TextView) rootView.findViewById(R.id.shuffleLabel);
        loopLabel = (TextView) rootView.findViewById(R.id.loopLabel);

        mp = activity.getMediaPlayer();
        if(mp != null) {
            updateUI();
        }
        playSongButton = (Button) rootView.findViewById(R.id.playSongButton);
        previousSongButton = (Button) rootView.findViewById(R.id.previousSongButton);
        nextSongButton = (Button) rootView.findViewById(R.id.nextSongButton);
        shuffleButton = (Button) rootView.findViewById(R.id.shufflePlaylistButton);
        loopButton = (Button) rootView.findViewById(R.id.loopPlaylistButton);
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
                    playOnNext = true;
                    mListener.onPlaylistOpen(currentSong.getIndex());
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
                if (mp != null) {
                    if (mp.isPlaying()) {
                        playOnNext = false;
                        mp.stop();
                        playSongButton.setBackgroundResource(R.drawable.play_button);
                        //Set flag to false.
                        prepared = false;
                    } else if (!prepared) {
                        try {
                            mp.prepare();
                            playOnNext = true;
                            mp.start();
                            playSongButton.setBackgroundResource(R.drawable.pause_button);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        previousSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = currentSong.getIndex();
                if(shuffle)
                    prepareSong(-100);
                else if(loop)
                    prepareSong(-101);
                else
                    prepareSong(--i);
                // Also Update UI.
                updateUI();
            }
        });

        nextSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = currentSong.getIndex();
                if(shuffle)
                    prepareSong(-100);
                else if(loop)
                    prepareSong(-101);
                else
                    prepareSong(++i);
                // Also Update UI.
                updateUI();
            }
        });

        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shuffle){
                    shuffle = false;
                    activity.setShuffle(false);
                    shuffleLabel.setText("OFF");
                }
                else{
                    shuffle = true;
                    activity.setShuffle(true);
                    shuffleLabel.setText("ON");
                }
            }
        });

        loopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loop){
                    loop = false;
                    activity.setLoop(false);
                    loopLabel.setText("OFF");
                }
                else{
                    loop = true;
                    activity.setLoop(true);
                    loopLabel.setText("ON");
                }
            }
        });

        return rootView;
    }

    private void prepareSong(int index){
//        File file;
//        file = mListener.changeSong(index);
//        currentSong.setName(file.getName());
//        currentSong.setPath(file.getPath());
        //Getting song from activity.
        currentSong = mListener.changeSong(index);
        // If index is the same, then return. We don't need to play the same song from the start,
        // user might just changed his mind and closed playlist
        if(activity.getCurrentIndex() == index)
            return;
//        currentSong.setIndex(index);
        currentSong.setIndex(currentSong.getIndex());
        activity.setCurrentIndex(currentSong.getIndex());
//        Uri uri = Uri.parse(file.getPath());
        Uri uri = Uri.parse(currentSong.getPath());
        Log.d("s3ns3i: ", uri.toString());
        // Prepare song.
        if(uri != null) {
            if(mp == null) {
                mp = new MediaPlayer();
                activity.setMediaPlayer(mp);
                mp.setOnPreparedListener(this);
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            }
            if(mp.isPlaying()) {
                playOnNext = true;
                mp.stop();
                playSongButton.setBackgroundResource(R.drawable.play_button);
            }
            try {
                mp.reset();
                mp.setDataSource(getActivity().getApplicationContext(), uri);
                mp.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUI(){
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(currentSong.getPath());
        byte[] data = mediaMetadataRetriever.getEmbeddedPicture();
        if(data != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Log.d("updateUI", "height: " + Integer.toString(bitmap.getHeight())
                    + " width: " + Integer.toString(bitmap.getWidth()));
            songImageView.setImageBitmap(bitmap);
            songImageView.setAdjustViewBounds(false);
        }
        else
            songImageView.setImageBitmap(null);
        songLengthTextView.setText(TimeConvert.convertFromMilliseconds(mp.getDuration()));
        songTitleTextView.setText(
                mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE));
        songArtistTextView.setText(
                mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
        if(shuffle){
            shuffleLabel.setText("ON");
        }
        else{
            shuffleLabel.setText("OFF");
        }
        if(loop){
            loopLabel.setText("ON");
        }
        else{
            loopLabel.setText("OFF");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        prepared = true;
//        playSongButton.setText("Pause");
        if (playOnNext){
            playSongButton.setBackgroundResource(R.drawable.pause_button);
            mp.start();
            playOnNext = false;
        }
    }

    public interface OnFragmentInteractionListener {
        /**
         * It will send index of the song.
         * @param songIndex - current index at playlist.
         */
        void onPlaylistOpen(int songIndex);

        /**
         * Method that requests current song (file) from the activity.
         * @param index - index of the song we currently want.
         * @return
         */
//        File changeSong(int index);
        Song changeSong(int index);
    }
}