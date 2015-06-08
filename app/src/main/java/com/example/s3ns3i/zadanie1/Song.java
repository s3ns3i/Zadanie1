package com.example.s3ns3i.zadanie1;

/**
 * Created by s3ns3i on 2015-06-08.
 */
public class Song {
    private String name;
    private String path;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Song(){}
    public Song(String name, String path, int index){
        this.name = name;
        this.path = path;
        this.index = index;
    }
}
