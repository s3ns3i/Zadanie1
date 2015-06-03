package com.example.s3ns3i.zadanie1;

import android.app.Activity;
import android.app.FragmentManager;
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


import com.example.s3ns3i.zadanie1.dummy.DummyContent;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

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
//    private static final String ARG_PARAM2 = "param2";

    private String mCurrentSong;
//    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mPlaylistListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mPlaylistAdapter;

    private File musicFolder;

    private File[] songs;

    private ArrayList<String> songsNames;

    public static PlaylistFragment newInstance() {
        return new PlaylistFragment();
//        PlaylistFragment fragment = new PlaylistFragment();
//        Bundle args = new Bundle();
//        args.putString(CURRENT_SONG, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
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

        if (getArguments() != null) {
            mCurrentSong = getArguments().getString(CURRENT_SONG);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //Reading files from music folder
//        musicFolder = new File(Environment.DIRECTORY_MUSIC);
        String path = Environment.getExternalStorageDirectory().toString()+"/Music";
        musicFolder = new File(path);
        songs = musicFolder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return (filename.endsWith(".mp3") || filename.endsWith(".MP3"));
            }
        });
        songsNames = new ArrayList<>();
        for(int i = 0; i < songs.length; i++) {
            songsNames.add(songs[i].getName());
        }

        // TODO: Change Adapter to display your content
        mPlaylistAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, songsNames);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        // Set the adapter
        mPlaylistListView = (AbsListView) view.findViewById(android.R.id.list);
        (mPlaylistListView).setAdapter(mPlaylistAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
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
//            mListener.onFragmentInteraction(songsNames.get(position), songs[position].getPath());
            FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.container, new ThirdApp.PlaceholderFragment()
                            .newInstance(songsNames.get(position), songs[position].getPath()))
                    .commit();
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
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id, String path);
    }

    private void getPlaylist(){
        songs = musicFolder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return (filename.endsWith(".mp3") || filename.endsWith(".MP3"));
            }
        });
        ArrayList<String> songsNames = new ArrayList<>();
        for (File song : songs) {
            songsNames.add(song.getName());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("songsNames", songsNames);
    }

}
