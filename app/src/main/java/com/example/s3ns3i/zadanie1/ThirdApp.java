package com.example.s3ns3i.zadanie1;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;


public class ThirdApp extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_app);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_third_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements MediaPlayer.OnPreparedListener {

        //Path to the music folder.
        final String MEDIA_PATH = new String("/sdcard/Music/");
        TextView filePath;
        TextView fileName;
        TextView intentResultCode;
        Button playSongButton;
        int YOUR_RESULT_CODE;
        MediaPlayer mp;
        boolean prepared;
        //private ArrayList<MediaStore.Audio.Media>
        //private ArrayList<HashMap<String, String>> songList = new ArrayList<HashMap<String, String>>();

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_third_app_debug, container, false);

            filePath = (TextView) rootView.findViewById(R.id.filePath);
            fileName = (TextView) rootView.findViewById(R.id.fileName);
            intentResultCode = (TextView) rootView.findViewById(R.id.intentResultCode);
            playSongButton = (Button) rootView.findViewById(R.id.playSongButton);
            playSongButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Check if song is playing.
                    if(mp.isPlaying()) {
                        mp.stop();
                        //Set flag to false.
                        //THIS MAYBE CAN BE DELETED.
                        prepared = false;
                    }
                    else if(!prepared) {
                        //It's better to run prepare in separate thread.
//                        mp.prepareAsync();
                        try {
                            mp.prepare();
                            mp.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("file/*");
            YOUR_RESULT_CODE = 1337;
            startActivityForResult(intent, YOUR_RESULT_CODE);
            //Reading songs from our folder.
            //getPlaylist();
            return rootView;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == YOUR_RESULT_CODE){
                if(resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    String type = data.getType();
                    Log.d("s3ns3i: ", uri + " " + type);
                    if(uri != null) {
                        filePath.setText(data.getData().getEncodedPath());
                        String path = uri.getPath();
                        fileName.setText(path);
                        mp = new MediaPlayer();
                        mp.setOnPreparedListener(this);
                        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mp.setDataSource(getActivity().getApplicationContext(), uri);
//                            mp.setDataSource(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    intentResultCode.setText(String.valueOf(YOUR_RESULT_CODE));
                }
            }
        }

        private void getPlaylist(){
            File musicFolder = new File(MEDIA_PATH);

            //Inner class that lists files with extension .mp3 and .MP3
            if(musicFolder.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String filename) {
                    return (filename.endsWith(".mp3") || filename.endsWith(".MP3"));
                }
            }).length > 0){

            }
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            prepared = true;
            mp.start();
        }
    }
}
