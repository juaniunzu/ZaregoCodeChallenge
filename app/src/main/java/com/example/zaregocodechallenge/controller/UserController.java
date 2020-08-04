package com.example.zaregocodechallenge.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.zaregocodechallenge.model.User;
import com.example.zaregocodechallenge.model.UserDao;

public class UserController {

    private UserDao userDao;

    public UserController() {
        this.userDao = new UserDao();
    }

    public void addUser(Context context, User user){
        userDao.addUser(user);
        Toast.makeText(context, "User added correctly", Toast.LENGTH_SHORT).show();
    }

    public void modifyUser(Context context, String userId, User user){
        userDao.modifyUser(userId, user);
        Toast.makeText(context, "User updated", Toast.LENGTH_SHORT).show();
    }

}
