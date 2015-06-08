package com.example.s3ns3i.zadanie1;

import android.app.Activity;
import android.app.FragmentManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import java.io.File;

public class ThirdApp extends Activity
        implements PlaylistFragment.OnFragmentInteractionListener
        , PlayerMainFragment.OnFragmentInteractionListener {

    private MediaPlayer mediaPlayer;
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    private File[] songs;
    private int index = 0;

    private int currentIndex = 0;
    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    private final String TAG_PLAYLIST = "playlist";
    private final String TAG_PLAYER = "player";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_app);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlayerMainFragment())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
            PlaylistFragment fragment = (PlaylistFragment) getFragmentManager().findFragmentByTag(TAG_PLAYLIST);
            if(fragment != null && fragment.isVisible()) {
                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.container, PlayerMainFragment
                                .newInstance(index), TAG_PLAYER)
                        .commit();
            }
            else
                super.onBackPressed();
    }

    @Override
    public void onFragmentInteraction(int songIndex) {
        index = songIndex;
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.container, PlayerMainFragment
                        .newInstance(index), TAG_PLAYER)
                .commit();
    }

    @Override
    public void sendPlaylist(File[] songs) {
        this.songs = songs;
    }

    @Override
    public void onPlaylistOpen(int songIndex) {
        index = songIndex;
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaylistFragment
                        .newInstance(index), TAG_PLAYLIST)
                .commit();
    }

    @Override
    public File changeSong(int index) {
        if(index < 0) {
            this.index = index;
            return songs[songs.length];
        }
        else if (index >= songs.length) {
            this.index = index;
            return songs[0];
        }
        else {
            this.index = index;
            return songs[this.index];
        }
    }
}
