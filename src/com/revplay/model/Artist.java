package com.revplay.model;

public class Artist {

    private int artistId;
    private String name;
    private String email;
    private String password;

    public Artist(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
