package com.revplay.model;

public class Album {

    private int albumId;
    private String albumName;
    private int artistId;

    public Album(String albumName, int artistId) {
        this.albumName = albumName;
        this.artistId = artistId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public int getArtistId() {
        return artistId;
    }
}
