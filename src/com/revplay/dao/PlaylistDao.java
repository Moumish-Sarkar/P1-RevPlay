package com.revplay.dao;

public interface PlaylistDao {
    void createPlaylist(String name, int isPublic, int userId);
    void addSongToPlaylist(int playlistId, int songId);
    void viewMyPlaylists(int userId);
    void viewUserPlaylists(int userId);
    void viewPlaylistSongs(int playlistId);


}
