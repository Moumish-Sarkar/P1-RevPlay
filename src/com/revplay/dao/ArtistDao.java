package com.revplay.dao;

import com.revplay.model.Artist;

public interface ArtistDao {
    void register(Artist artist);
    int login(String email,String password);
}
