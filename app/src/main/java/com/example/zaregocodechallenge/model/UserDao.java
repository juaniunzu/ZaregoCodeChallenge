package com.example.zaregocodechallenge.model;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserDao {

    private CollectionReference reference = FirebaseFirestore.getInstance().collection("PhoneBook");

    public UserDao() {
    }

    public void addUser(User user){
        reference.add(user);
    }

    public void modifyUser(String userId, User user){
        reference.document(userId).set(user);
    }

}
