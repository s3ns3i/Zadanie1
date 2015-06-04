package com.example.s3ns3i.zadanie1;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


//public class ThirdApp extends ActionBarActivity {
public class ThirdApp extends ActionBarActivity
        implements PlaylistFragment.OnFragmentInteractionListener
        , PlayerMainFragment.OnFragmentInteractionListener {

//    String mSongName;
//    String mSongPath;
//    String mSongIndex;
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

    @Override
    public void onFragmentInteraction(String songName, String songPath, String songIndex) {
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.container, PlayerMainFragment
                        .newInstance(songName, songPath, songIndex))
                .commit();
    }

    @Override
    public void onFragmentInteraction(String songIndex) {
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaylistFragment
                        .newInstance(songIndex))
                .commit();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
//    public static class PlaceholderFragment extends Fragment implements MediaPlayer.OnPreparedListener {
//
//        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//        private static final String NAME = "name";
//        private static final String PATH = "path";
//        private static final String INDEX = "index";
//
//        /**
//         * It will determine interval of seek bar updates.
//         */
//        final int UPDATE_TIME = 100;
//
//        /**
//         * Interface used to communicate with activity.
//         */
//        OnFragmentInteractionListener mListener;
//        /**
//         * Shows current time of the song.
//         */
//        TextView currentSongTimeTextView;
//        /**
//         * Shows duration of the song.
//         */
//        TextView songLengthTextView;
////        TextView filePath;
////        TextView fileName;
////        TextView intentResultCode;
//        /**
//         * Plays and pauses song.
//         */
//        Button playSongButton;
//        //
//        int YOUR_RESULT_CODE;
//        /**
//         * Plays music.
//         */
//        MediaPlayer mp;
//        //
//        boolean prepared;
//        //
//        Handler seekBarHandler;
//        //
//        /**
//         * Can be used to rewind song and shows progress.
//         */
//        SeekBar seekBar;
//        //private ArrayList<MediaStore.Audio.Media>
//        //private ArrayList<HashMap<String, String>> songList = new ArrayList<HashMap<String, String>>();
//        /**
//         * Opens playlist. -- PlaylistFragment
//         */
//        Button openPlaylistButton;
//        String mSongName;
//        String mSongPath;
//        String mSongIndex;
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public void onAttach(Activity activity) {
//            super.onAttach(activity);
//            try {
//                mListener = (OnFragmentInteractionListener) activity;
//            } catch (ClassCastException e) {
//                throw new ClassCastException(activity.toString()
//                        + " must implement OnFragmentInteractionListener");
//            }
//        }
//
//        public static PlaceholderFragment newInstance(String name, String path, String index) {
//            if(name == null || path == null || index == null){
//                return new PlaceholderFragment();
//            }
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putString(NAME, name);
//            args.putString(PATH, path);
//            args.putString(INDEX, index);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            /**
//             * If there are arguments, then put them into fragment.
//             * It will be used f.e. for marking currently playing song.
//             */
//            if (getArguments() != null) {
//                mSongName = getArguments().getString(NAME);
//                mSongPath = getArguments().getString(PATH);
//                mSongIndex = getArguments().getString(INDEX);
//            }
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
////            View rootView = inflater.inflate(R.layout.fragment_third_app_debug, container, false);
//            View rootView = inflater.inflate(R.layout.fragment_third_app, container, false);
//
//            /**
//             * INITIALIZATION OF LAYOUT ELEMENTS
//             */
//            currentSongTimeTextView = (TextView) rootView.findViewById(R.id.currentSongTimeTextView);
//            songLengthTextView = (TextView) rootView.findViewById(R.id.songLengthTextView);
//            playSongButton = (Button) rootView.findViewById(R.id.playSongButton);
//            seekBarHandler = new Handler();
//            seekBar = (SeekBar) rootView.findViewById(R.id.seekBar);
//
//            openPlaylistButton = (Button) rootView.findViewById(R.id.openPlaylistButton);
//            /**
//             * Playlist button logic.
//             */
//            openPlaylistButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    FragmentManager fragmentManager = getFragmentManager();
////
////                    fragmentManager.beginTransaction()
////                            .replace(R.id.container, PlaylistFragment.newInstance(mSongIndex))
////                            .commit();
//                    if (null != mListener) {
//                        // Notify the active callbacks interface (the activity, if the
//                        // fragment is attached to one) that an item has been selected.
//                        mListener.onFragmentInteraction(mSongIndex);
//                    }
//                }
//            });
//
//            /**
//             * Play/Pause button logic.
//             */
//            playSongButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Check if song is playing.
//                    if(mp.isPlaying()) {
//                        mp.stop();
//                        //Set flag to false.
//                        //THIS MAYBE CAN BE DELETED.
//                        prepared = false;
//                    }
//                    else if(!prepared) {
//                        // It's better to run prepare in separate thread.
//                        // It did not work so I did it other way xD
////                        mp.prepareAsync();
//                        try {
//                            mp.prepare();
//                            mp.start();
////                            seekBarHandler.postDelayed(
////                                    new SeekBarUpdate(mp, currentSongTimeTextView, 1000)
////                                    , UPDATE_TIME);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            });
////            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
////            intent.setType("file/*");
////            YOUR_RESULT_CODE = 1337;
////            startActivityForResult(intent, YOUR_RESULT_CODE);
//            //Reading songs from our folder.
//            //getPlaylist();
//            return rootView;
//        }
//
//        @Override
//        public void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
////            if(requestCode == YOUR_RESULT_CODE){
////                if(resultCode == RESULT_OK){
////                    Uri uri = data.getData();
////                    String type = data.getType();
////                    Log.d("s3ns3i: ", uri + " " + type);
////                    if(uri != null) {
//////                        filePath.setText(data.getData().getEncodedPath());
//////                        String path = uri.getPath();
//////                        fileName.setText(path);
////                        mp = new MediaPlayer();
////                        mp.setOnPreparedListener(this);
////                        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
////                        try {
////                            mp.setDataSource(getActivity().getApplicationContext(), uri);
////                            mp.prepare();
////                            currentSongTimeTextView.setText(mp.getDuration());
////                            songLengthTextView.setText(TimeConvert.convertFromMilliseconds(mp.getDuration()));
//////                            mp.setDataSource(path);
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
////                    }
//////                    intentResultCode.setText(String.valueOf(YOUR_RESULT_CODE));
////                }
////            }
//        }
//
//        @Override
//        public void onPrepared(MediaPlayer mp) {
//            prepared = true;
//            mp.start();
//        }
//
//        public interface OnFragmentInteractionListener {
//            /**
//             * It will send index of the song.
//             * @param songIndex - current index at playlist.
//             */
//            void onFragmentInteraction(String songIndex);
//        }
//    }
}
