package com.revplay.dao;

public interface SongDao {
    void uploadSong(String title,String genre,int artistId);
    void viewSongs();
    void playSong(int songId,int userId);
    void searchSongs(String key);
    void browseByGenre(String genre);
    void viewHistory(int userId);
//    void artistStats(int artistId);
    void viewArtistSongs(int artistId);
    void artistStats(int artistId);



}
