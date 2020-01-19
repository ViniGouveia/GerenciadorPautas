package com.vinigouveia.gerenciadorpautas.Room.DBDao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vinigouveia.gerenciadorpautas.Room.DBEntities.UserEntity;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users WHERE user_Email LIKE :userEmail LIMIT 1")
    UserEntity getUser(String userEmail);

    @Query("SELECT user_password from users WHERE user_email LIKE :userEmail")
    String getUserPassword(String userEmail);

    @Insert
    void insertUser(UserEntity user);
}
