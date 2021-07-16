package com.freelearners.ibtha.database.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.freelearners.ibtha.entity.User;
import com.freelearners.ibtha.dao.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public  abstract UserDao userDao();

    public static UserDatabase INSTANCE;

    public  static UserDatabase getDatabaseInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class,
                    "User_Database").build();
        }
        return  INSTANCE;

    }

}
