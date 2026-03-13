package com.revplay.daoimpl;

import com.revplay.dao.PlaylistDao;
import com.revplay.daoimpl.PlaylistDaoImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistDaoImplTest {

    @Test
    void testCreatePlaylistAndAddSong() {

        PlaylistDao playlistDao = new PlaylistDaoImpl();

        int userId = 21;  // existing user
        int songId = 1;   // existing song

        playlistDao.createPlaylist("JUnitPlaylist", 1, userId);

        playlistDao.addSongToPlaylist(1, songId);

        assertTrue(true); // if no exception → success
    }
}
