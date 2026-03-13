package com.revplay.daoimpl;

import com.revplay.dao.SongDao;
import com.revplay.util.DBConnection;

import java.sql.*;

public class SongDaoImpl implements SongDao {

    public void uploadSong(String title,String genre,int artistId){

        try{
            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO songs(title,genre,artist_id) VALUES(?,?,?)");

            ps.setString(1,title);
            ps.setString(2,genre);
            ps.setInt(3,artistId);

            ps.executeUpdate();
            System.out.println("Song Uploaded");

        }catch(Exception e){ e.printStackTrace(); }
    }

    public void viewSongs(){

        try{
            Connection con = DBConnection.getConnection();

            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT song_id,title,genre FROM songs");

            while(rs.next()){
                System.out.println(rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getString(3));
            }

        }catch(Exception e){ e.printStackTrace(); }
    }

    public void playSong(int songId,int userId){

        try{
            Connection con = DBConnection.getConnection();

            PreparedStatement ps1=
                    con.prepareStatement("UPDATE songs SET play_count=play_count+1 WHERE song_id=?");
            ps1.setInt(1,songId);
            ps1.executeUpdate();

            PreparedStatement ps2=
                    con.prepareStatement("INSERT INTO history(user_id,song_id) VALUES(?,?)");
            ps2.setInt(1,userId);
            ps2.setInt(2,songId);
            ps2.executeUpdate();

            System.out.println("Playing Song...");

        }catch(Exception e){ e.printStackTrace(); }
    }

    public void searchSongs(String key){
        try(Connection con=DBConnection.getConnection()){
            PreparedStatement ps=con.prepareStatement(
                    "select song_id,title from songs where lower(title) like ?");
            ps.setString(1,"%"+key.toLowerCase()+"%");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
                System.out.println(rs.getInt(1)+" | "+rs.getString(2));
        }catch(Exception e){e.printStackTrace();}
    }

    public void browseByGenre(String genre){
        try(Connection con=DBConnection.getConnection()){
            PreparedStatement ps=con.prepareStatement(
                    "select song_id,title from songs where lower(genre)=?");
            ps.setString(1,genre.toLowerCase());
            ResultSet rs=ps.executeQuery();
            while(rs.next())
                System.out.println(rs.getInt(1)+" | "+rs.getString(2));
        }catch(Exception e){e.printStackTrace();}
    }

    public void viewHistory(int userId){
        try(Connection con=DBConnection.getConnection()){
            PreparedStatement ps=con.prepareStatement(
                    "select s.title,h.played_at from history h join songs s on h.song_id=s.song_id where h.user_id=?");
            ps.setInt(1,userId);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
                System.out.println(rs.getString(1)+" | "+rs.getTimestamp(2));
        }catch(Exception e){e.printStackTrace();}
    }

    @Override
    public void artistStats(int artistId) {

        String sql = "SELECT title, play_count FROM songs WHERE artist_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getString("title") + " | plays:" +
                                rs.getInt("play_count")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void viewArtistSongs(int artistId) {

        String sql = "SELECT song_id, title, genre FROM songs WHERE artist_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("song_id") + " | " +
                                rs.getString("title") + " | " +
                                rs.getString("genre")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
