package com.mobdeve.s13.g26.genshinplanner.utils;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobdeve.s13.g26.genshinplanner.models.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FirebaseUserDBHelper {

    private DatabaseReference userRef;
    //private ArrayList<User> users = new ArrayList<>();

    public FirebaseUserDBHelper() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://genshin-planner-323108-default-rtdb.asia-southeast1.firebasedatabase.app/");
        userRef = database.getReference("users");
    }


    public void addUser(User user) {
        userRef.child(user.getUserId()).setValue(user);
    }

    public DatabaseReference getReference() {
        return userRef;
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
    }
}
