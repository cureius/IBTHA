package com.freelearners.ibtha.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.freelearners.ibtha.entity.User;
import com.freelearners.ibtha.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    public UserRepository repository;
    public LiveData<List<User>> getAllUser;

    public UserViewModel(Application application) {
        super(application);

        repository = new UserRepository(application);
        getAllUser = repository.getAllUsers;

    }

    public void insertUsers(User user) {
        repository.insertUser(user);
    }

    public void deleteUser(int id) {
        repository.deleteUser(id);
    }

    public void updateUser(User user) {
        repository.updateUser(user);
    }
}
