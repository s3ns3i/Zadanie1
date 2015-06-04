package com.example.s3ns3i.zadanie1;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Timer;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class PlaylistFragment extends Fragment implements AbsListView.OnItemClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CURRENT_SONG = "currentSong";

    private String mCurrentSong;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mPlaylistListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with Views.
     */
    private ListAdapter mPlaylistAdapter;
    /**
     * Array of music files.
     */
    private File[] songs;
    /**
     * List with song names.
     */
    private ArrayList<String> songsNames;

    public static PlaylistFragment newInstance(String index) {
//        return new PlaylistFragment();
        PlaylistFragment fragment = new PlaylistFragment();
        Bundle args = new Bundle();
        args.putString(CURRENT_SONG, index);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlaylistFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * If there are arguments, then put them into fragment.
         * It will be used f.e. for marking currently playing song.
         */
        if (getArguments() != null) {
            mCurrentSong = getArguments().getString(CURRENT_SONG);
        }

        /**
         * Reading files from music folder
         */
        String path = Environment.getExternalStorageDirectory().toString()+"/Music";
        File musicFolder = new File(path);
        /**
         * Getting list of files in Music folder.
         */
        songs = musicFolder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return (filename.endsWith(".mp3") || filename.endsWith(".MP3"));
            }
        });
        /**
         * Making a list with songs names.
         */
        songsNames = new ArrayList<>();
        for (File song : songs) {
            songsNames.add(song.getName());
        }
        /**
         * Putting song names in our list view.
         */
        mPlaylistAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, songsNames);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        /**
         * Set the adapter
         */
        mPlaylistListView = (AbsListView) view.findViewById(android.R.id.list);
        (mPlaylistListView).setAdapter(mPlaylistAdapter);

        /**
         * Set OnItemClickListener so we can be notified on item clicks
         */
        mPlaylistListView.setOnItemClickListener(this);

        return view;
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(songsNames.get(position), songs[position].getPath(), Long.toString(position));
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mPlaylistListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        /**
         * It will send index of the song and path to the file.
         * @param songName - name of the current song.
         * @param songPath - path of the current file.
         * @param songIndex - current index at playlist.
         */
        void onFragmentInteraction(String songName, String songPath, String songIndex);
    }

}
