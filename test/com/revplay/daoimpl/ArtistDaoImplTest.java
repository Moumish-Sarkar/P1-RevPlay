package com.revplay.daoimpl;

import com.revplay.dao.ArtistDao;
import com.revplay.daoimpl.ArtistDaoImpl;
import com.revplay.model.Artist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtistDaoImplTest {

    @Test
    void testArtistRegisterAndLogin() {

        ArtistDao artistDao = new ArtistDaoImpl();

        String email = "artist_" + System.currentTimeMillis() + "@gmail.com";

        Artist artist = new Artist(
                "JUnitArtist",
                email,
                "123"
        );

        artistDao.register(artist);

        int artistId = artistDao.login(email, "123");

        assertTrue(artistId > 0, "Artist login failed");
    }
}
