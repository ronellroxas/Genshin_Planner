package com.mobdeve.s13.g26.genshinplanner.utils;

import android.renderscript.Sampler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mobdeve.s13.g26.genshinplanner.models.Plan;
import com.mobdeve.s13.g26.genshinplanner.models.User;

import org.jetbrains.annotations.NotNull;

public class FirebasePlanDBHelper {

    private DatabaseReference planRef;
    //private ArrayList<User> users = new ArrayList<>();

    public FirebasePlanDBHelper() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://genshin-planner-323108-default-rtdb.asia-southeast1.firebasedatabase.app/");
        planRef = database.getReference("plans");

    }

    public void addPlan(Plan plan) {
        String key = planRef.push().getKey();
        planRef.child(key).setValue(plan);
        plan.setPlan_id(key);

    }

    public void deletePlan(String id) {
        planRef.child(id).removeValue();
    }

    public DatabaseReference getReference() {
        return planRef;
    }
}

