package com.revplay.dao;

import com.revplay.model.User;

public interface UserDao {
    void register(User user);
    int login(String email, String password);
    void addFavorite(int userId,int songId);
    void viewFavorites(int userId);
    void viewHistory(int userId);


}
