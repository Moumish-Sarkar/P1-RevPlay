package com.revplay.daoimpl;

import com.revplay.dao.SongDao;
import com.revplay.daoimpl.SongDaoImpl;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SongDaoImplTest {

    private static SongDao songDao;

    @BeforeAll
    static void setup() {
        songDao = new SongDaoImpl();
    }

    @Test
    void testUploadSong() {

        songDao.uploadSong(
                "JUnit Song",
                "test",
                41   // existing artistId
        );

        assertTrue(true); // no exception = success
    }
}
