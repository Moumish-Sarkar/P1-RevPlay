package com.revplay.model;

public class Song {

    private int songId;
    private String title;
    private String genre;
    private int artistId;
    private int albumId;

    public Song(String title, String genre, int artistId, int albumId) {
        this.title = title;
        this.genre = genre;
        this.artistId = artistId;
        this.albumId = albumId;
    }

    public int getSongId() {
        return songId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getAlbumId() {
        return albumId;
    }
}
