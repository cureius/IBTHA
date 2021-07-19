package com.freelearners.ibtha.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.freelearners.ibtha.entity.User;

import java.util.List;

@androidx.room.Dao
public interface UserDao {

    @Query("SELECT * FROM User_Database")
    LiveData<List<User>> getUsers();

    @Insert
    void insertUser(User... users);

    @Query("DELETE FROM User_Database WHERE id = :id")
    void deleteUser(int id);

    @Query("DELETE FROM User_Database")
    void deleteAllUser();

    @Update
    void updateUser(User users);

}
