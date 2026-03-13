package com.revplay;

import com.revplay.daoimpl.*;
import com.revplay.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationSmokeTest {

    @Test
    void testFullFlow() {

        UserDaoImpl userDao = new UserDaoImpl();
        ArtistDaoImpl artistDao = new ArtistDaoImpl();
        SongDaoImpl songDao = new SongDaoImpl();

        String userEmail = "user_" + System.currentTimeMillis() + "@gmail.com";
        String artistEmail = "artist_" + System.currentTimeMillis() + "@gmail.com";

        userDao.register(new User("User", userEmail, "123"));
        int userId = userDao.login(userEmail, "123");

        artistDao.register(new Artist("Artist", artistEmail, "123"));
        int artistId = artistDao.login(artistEmail, "123");

        songDao.uploadSong("SmokeSong", "test", artistId);
        songDao.playSong(1, userId);

        assertTrue(userId > 0 && artistId > 0);
    }
}
