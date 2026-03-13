package com.revplay.daoimpl;

import com.revplay.dao.UserDao;
import com.revplay.model.User;
import com.revplay.util.DBConnection;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    public void register(User user) {

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO users(name,email,password) VALUES(?,?,?)");

            ps.setString(1,user.getName());
            ps.setString(2,user.getEmail());
            ps.setString(3,user.getPassword());

            ps.executeUpdate();
            System.out.println("User Registered");

        } catch(Exception e){ e.printStackTrace(); }
    }

    public int login(String email,String password){

        int id = -1;

        try{
            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement("SELECT user_id FROM users WHERE email=? AND password=?");

            ps.setString(1,email);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) id = rs.getInt(1);

        }catch(Exception e){ e.printStackTrace(); }

        return id;
    }

    public void addFavorite(int userId,int songId){
        try(Connection con=DBConnection.getConnection()){
            PreparedStatement ps=con.prepareStatement(
                    "insert into favorites(user_id,song_id) values(?,?)");
            ps.setInt(1,userId);
            ps.setInt(2,songId);
            ps.executeUpdate();
            System.out.println("Added to favorites");
        }catch(Exception e){e.printStackTrace();}
    }

    public void viewFavorites(int userId){
        try(Connection con=DBConnection.getConnection()){
            PreparedStatement ps=con.prepareStatement(
                    "select s.song_id,s.title from favorites f join songs s on f.song_id=s.song_id where f.user_id=?");
            ps.setInt(1,userId);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
                System.out.println(rs.getInt(1)+" | "+rs.getString(2));
        }catch(Exception e){e.printStackTrace();}
    }
    @Override
    public void viewHistory(int userId) {

        String sql = """
        SELECT s.title, h.played_at
        FROM history h
        JOIN songs s ON h.song_id = s.song_id
        WHERE h.user_id = ?
        ORDER BY h.played_at DESC
    """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getString("title") + " | " +
                                rs.getTimestamp("played_at")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

