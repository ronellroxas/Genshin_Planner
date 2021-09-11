package com.mobdeve.s13.g26.genshinplanner.utils;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mobdeve.s13.g26.genshinplanner.models.Plan;


import org.jetbrains.annotations.NotNull;

public class FirebasePlanDBHelper {

    private DatabaseReference planRef;
    private DatabaseReference sharedPlansRef;
    //private ArrayList<User> users = new ArrayList<>();

    public FirebasePlanDBHelper() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://genshin-planner-323108-default-rtdb.asia-southeast1.firebasedatabase.app/");
        planRef = database.getReference("plans");

    }

    public void addPlan(Plan plan) {
        planRef.child(plan.getPlan_id()).setValue(plan);
    }

    public void deletePlan(String id) {
        planRef.child(id).removeValue();
    }

    public DatabaseReference getReference() {
        return planRef;
    }

    public DatabaseReference getSharedReference() {
        return sharedPlansRef;
    }

    public void sharePlan(Plan plan) {
        sharedPlansRef.child(plan.getPlan_id()).setValue(plan);
    }

    public void deleteSharedPlan(String id) {
        sharedPlansRef.child(id).removeValue();
    }
}

