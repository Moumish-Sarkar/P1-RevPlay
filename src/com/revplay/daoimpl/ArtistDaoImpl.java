package com.revplay.daoimpl;

import com.revplay.dao.ArtistDao;
import com.revplay.model.Artist;
import com.revplay.util.DBConnection;

import java.sql.*;

public class ArtistDaoImpl implements ArtistDao {

    public void register(Artist artist){

        try{
            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO artists(name,email,password) VALUES(?,?,?)");

            ps.setString(1,artist.getName());
            ps.setString(2,artist.getEmail());
            ps.setString(3,artist.getPassword());

            ps.executeUpdate();
            System.out.println("Artist Registered");

        }catch(Exception e){ e.printStackTrace(); }
    }

    public int login(String email,String password){

        int id=-1;

        try{
            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement("SELECT artist_id FROM artists WHERE email=? AND password=?");

            ps.setString(1,email);
            ps.setString(2,password);

            ResultSet rs=ps.executeQuery();

            if(rs.next()) id=rs.getInt(1);

        }catch(Exception e){ e.printStackTrace(); }

        return id;
    }
}
