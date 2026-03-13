package com.revplay.daoimpl;

import com.revplay.dao.PlaylistDao;
import com.revplay.util.DBConnection;

import java.sql.*;

public class PlaylistDaoImpl implements PlaylistDao {

    public void createPlaylist(String name,int isPublic,int userId){
        try(Connection con=DBConnection.getConnection()){
            PreparedStatement ps=con.prepareStatement(
                    "insert into playlists(playlist_name,is_public,user_id) values(?,?,?)");
            ps.setString(1,name);
            ps.setInt(2,isPublic);
            ps.setInt(3,userId);
            ps.executeUpdate();
            System.out.println("Playlist created");
        }catch(Exception e){e.printStackTrace();}
    }

    public void addSongToPlaylist(int pid,int sid){
        try(Connection con=DBConnection.getConnection()){
            PreparedStatement ps=con.prepareStatement(
                    "insert into playlist_songs(playlist_id,song_id) values(?,?)");
            ps.setInt(1,pid);
            ps.setInt(2,sid);
            ps.executeUpdate();
            System.out.println("Song added");
        }catch(Exception e){e.printStackTrace();}
    }

    public void viewMyPlaylists(int userId){
        try(Connection con=DBConnection.getConnection()){
            PreparedStatement ps=con.prepareStatement(
                    "select playlist_id,playlist_name from playlists where user_id=?");
            ps.setInt(1,userId);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
                System.out.println(rs.getInt(1)+" | "+rs.getString(2));
        }catch(Exception e){e.printStackTrace();}
    }
    @Override
    public void viewUserPlaylists(int userId) {

        String sql = "SELECT playlist_id, playlist_name, is_public FROM playlists WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("playlist_id") + " | " +
                                rs.getString("playlist_name") + " | " +
                                (rs.getInt("is_public") == 1 ? "Public" : "Private")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void viewPlaylistSongs(int playlistId) {

        String sql = """
        SELECT s.song_id, s.title, s.genre
        FROM playlist_songs ps
        JOIN songs s ON ps.song_id = s.song_id
        WHERE ps.playlist_id = ?
    """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Songs in Playlist ---");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getInt("song_id") + " | " +
                                rs.getString("title") + " | " +
                                rs.getString("genre")
                );
            }

            if (!found) {
                System.out.println("No songs in this playlist.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
