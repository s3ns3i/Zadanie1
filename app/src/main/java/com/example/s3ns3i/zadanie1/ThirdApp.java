package com.example.s3ns3i.zadanie1;

import android.app.Activity;
import android.app.FragmentManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import java.io.File;
import java.util.Random;

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

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }

    boolean loop;
    boolean shuffle;

    private int currentIndex = 100000000;
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
    public Song changeSong(int index){
        // This is considering playback! If user selects song, see code below
        // This means that shuffle option is enabled.
        if(index == -100){
            int number = this.index;
            while(this.index == number){
                number = generateRandomNumber(0, songs.length - 1);
            }
            this.index = number;
        }
        // This means that loop option is enabled.
        else if(index == -101) {
            this.index++;
            if (this.index < 0) {
                this.index = songs.length - 1;
            } else if (this.index >= songs.length) {
                this.index = 0;
            }
        }
        else if(index < 0) {
            this.index = songs.length - 1;
        }
        else if (index >= songs.length) {
            this.index = 0;
        }
        else {
            this.index = index;
        }
        Song song = new Song(songs[this.index].getName(), songs[this.index].getPath(), this.index);
        return song;
    }

    private int generateRandomNumber(int minimum, int maximum){
        Random generator = new Random();
        return generator.nextInt(maximum) + minimum;
    }
//    @Override
//    public File changeSong(int index) {
//        if(index < 0) {
//            this.index = index;
//            return songs[songs.length];
//        }
//        else if (index >= songs.length) {
//            this.index = index;
//            return songs[0];
//        }
//        else {
//            this.index = index;
//            return songs[this.index];
//        }
//    }
}
