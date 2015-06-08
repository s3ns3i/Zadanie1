package com.example.s3ns3i.zadanie1;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import java.util.TimerTask;

/**
 * Created by s3ns3i on 2015-06-04.
 */
public class MediaPlayerTimer extends TimerTask {
    TextView currentTimeTextView;
    MediaPlayer mediaPlayer;
    Handler handler;
    String currentTime;
    public MediaPlayerTimer(TextView currentTimeTextView, MediaPlayer mediaPlayer){
        this.currentTimeTextView = currentTimeTextView;
        this.mediaPlayer = mediaPlayer;
        handler = new Handler(Looper.getMainLooper());
    }
    @Override
    public void run() {
//        String currentTime = TimeConvert.convertFromMilliseconds(mediaPlayer.getCurrentPosition());
//        currentTimeTextView.setText(currentTime);
        currentTime = TimeConvert.convertFromMilliseconds(mediaPlayer.getCurrentPosition());
        Log.d("MediaPlayerTimer.run()", currentTime);
        currentTimeTextView.setText(currentTime);
//        handler.sendMessage(new Message().)
    }
//    SendCurrentTime listener;
//    MediaPlayer mediaPlayer;
//    public MediaPlayerTimer(Fragment fragment, MediaPlayer mediaPlayer){
//        try {
//            this.listener = (SendCurrentTime) fragment;
//        }
//        catch(ClassCastException e){
//            throw new ClassCastException(fragment.toString()
//                    + " must implement SendCurrentTime");
//        }
//        this.mediaPlayer = mediaPlayer;
//    }
//    @Override
//    public void run() {
//        String currentTimeTextView = TimeConvert.convertFromMilliseconds(mediaPlayer.getCurrentPosition());
//        listener.getTime(currentTimeTextView);
//    }
//    public interface SendCurrentTime{
//        void getTime(String time);
//    }
}
