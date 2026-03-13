package com.revplay.main;

import com.revplay.dao.*;
import com.revplay.daoimpl.*;
import com.revplay.model.*;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserDao userDao = new UserDaoImpl();
        ArtistDao artistDao = new ArtistDaoImpl();
        SongDao songDao = new SongDaoImpl();
        PlaylistDao playlistDao = new PlaylistDaoImpl();

        int userId = -1;
        int artistId = -1;

        while (true) {

            /* ================= MAIN MENU ================= */
            while (userId == -1 && artistId == -1) {

                System.out.println("\n=== Welcome to RevPlay ===");
                System.out.println("1 User Register");
                System.out.println("2 User Login");
                System.out.println("3 Artist Register");
                System.out.println("4 Artist Login");
                System.out.println("0 Exit");

                int ch = sc.nextInt(); sc.nextLine();

                switch (ch) {

                    case 1:
                        System.out.print("Name: ");
                        String un = sc.nextLine();
                        System.out.print("Email: ");
                        String ue = sc.nextLine();
                        System.out.print("Password: ");
                        String up = sc.nextLine();
                        userDao.register(new User(un, ue, up));
                        break;

                    case 2:
                        System.out.print("Email: ");
                        ue = sc.nextLine();
                        System.out.print("Password: ");
                        up = sc.nextLine();
                        userId = userDao.login(ue, up);
                        System.out.println(userId > 0 ? "User Logged In" : "Invalid Login");
                        break;

                    case 3:
                        System.out.print("Name: ");
                        String an = sc.nextLine();
                        System.out.print("Email: ");
                        String ae = sc.nextLine();
                        System.out.print("Password: ");
                        String ap = sc.nextLine();
                        artistDao.register(new Artist(an, ae, ap));
                        break;

                    case 4:
                        System.out.print("Email: ");
                        ae = sc.nextLine();
                        System.out.print("Password: ");
                        ap = sc.nextLine();
                        artistId = artistDao.login(ae, ap);
                        System.out.println(artistId > 0 ? "Artist Logged In" : "Invalid Login");
                        break;

                    case 0:
                        System.exit(0);
                }
            }

            /* ================= USER MENU ================= */
            while (userId > 0) {

                System.out.println("\n=== USER MENU ===");
                System.out.println("1 View Songs");
                System.out.println("2 Search Songs");
                System.out.println("3 Browse by Genre");
                System.out.println("4 Play Song");
                System.out.println("5 Add Favorite");
                System.out.println("6 View Favorites");
                System.out.println("7 Create Playlist");
                System.out.println("8 Add Song to Playlist");
                System.out.println("9 View My Playlists");
                System.out.println("10 View History");
                System.out.println("0 Logout");

                int ch = sc.nextInt(); sc.nextLine();

                switch (ch) {

                    case 1:
                        songDao.viewSongs();
                        break;

                    case 2:
                        System.out.print("Keyword: ");
                        songDao.searchSongs(sc.nextLine());
                        break;

                    case 3:
                        System.out.print("Genre: ");
                        songDao.browseByGenre(sc.nextLine());
                        break;

                    case 4:
                        System.out.print("Song ID: ");
                        songDao.playSong(sc.nextInt(), userId);
                        sc.nextLine();
                        break;

                    case 5:
                        System.out.print("Song ID: ");
                        userDao.addFavorite(userId, sc.nextInt());
                        sc.nextLine();
                        break;

                    case 6:
                        userDao.viewFavorites(userId);
                        break;

                    case 7:
                        System.out.print("Playlist Name: ");
                        String pname = sc.nextLine();
                        System.out.print("Public? (1=yes,0=no): ");
                        int pub = sc.nextInt();
                        sc.nextLine();
                        playlistDao.createPlaylist(pname, pub, userId);
                        break;

                    case 8:
                        System.out.print("Playlist ID: ");
                        int pid = sc.nextInt();
                        System.out.print("Song ID: ");
                        int sid = sc.nextInt();
                        sc.nextLine();
                        playlistDao.addSongToPlaylist(pid, sid);
                        break;
                    case 9:
                        // Step 1: Show playlists
                        playlistDao.viewUserPlaylists(userId);

                        // Step 2: Ask which playlist to open
                        System.out.print("\nEnter Playlist ID to view songs (0 to cancel): ");
                        int viewPid = sc.nextInt();
                        sc.nextLine();

                        // Step 3: Show songs in playlist
                        if (viewPid != 0) {
                            playlistDao.viewPlaylistSongs(viewPid);
                        }
                        break;


                    case 10:
                        userDao.viewHistory(userId);
                        break;

                    case 0:
                        userId = -1;
                        System.out.println("User Logged Out");
                        break;
                }
            }

            /* ================= ARTIST MENU ================= */
            while (artistId > 0) {

                System.out.println("\n=== ARTIST MENU ===");
                System.out.println("1 Upload Song");
                System.out.println("2 View My Songs");
                System.out.println("3 Artist Stats");
                System.out.println("0 Logout");

                int ch = sc.nextInt(); sc.nextLine();

                switch (ch) {

                    case 1:
                        System.out.print("Title: ");
                        String t = sc.nextLine();
                        System.out.print("Genre: ");
                        String g = sc.nextLine();
                        songDao.uploadSong(t, g, artistId);
                        break;

                    case 2:
                        songDao.viewArtistSongs(artistId);
                        break;

                    case 3:
                        songDao.artistStats(artistId);
                        break;

                    case 0:
                        artistId = -1;
                        System.out.println("Artist Logged Out");
                        break;



                }
            }
        }
    }
}
