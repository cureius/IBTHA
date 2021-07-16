package com.freelearners.ibtha.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.freelearners.ibtha.dao.UserDao;
import com.freelearners.ibtha.database.local.UserDatabase;
import com.freelearners.ibtha.entity.User;

import java.util.List;

public class UserRepository {
    private static final String TAG = "UserRepository";
    public UserDao userDao;
    public LiveData<List<User>> getAllUsers;

    public UserRepository(Application application) {
        UserDatabase database = UserDatabase.getDatabaseInstance(application);
        userDao = database.userDao();
        getAllUsers = userDao.getUsers();
    }

    public void insertUser(User user) {
        userDao.insertUser(user);
        Log.d(TAG, "insertUser: " + user.toString());
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

}
