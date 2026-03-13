package com.revplay.model;

public class Playlist {

    private int playlistId;
    private String playlistName;
    private int isPublic;
    private int userId;

    public Playlist(String playlistName, int isPublic, int userId) {
        this.playlistName = playlistName;
        this.isPublic = isPublic;
        this.userId = userId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public int getUserId() {
        return userId;
    }
}
