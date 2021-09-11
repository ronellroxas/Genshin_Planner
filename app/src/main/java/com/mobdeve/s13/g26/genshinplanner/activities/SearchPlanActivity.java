package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.PlanListAdapter;
import com.mobdeve.s13.g26.genshinplanner.models.Item;
import com.mobdeve.s13.g26.genshinplanner.models.Plan;
import com.mobdeve.s13.g26.genshinplanner.utils.FirebasePlanDBHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SearchPlanActivity extends AppCompatActivity {

    private FirebasePlanDBHelper planDBHelper;
    private RecyclerView rvPlanList;
    private PlanListAdapter plAdapter;
    private ArrayList<Plan> planArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_plan);

        planArrayList = new ArrayList<>();
        loadData();
        initRecyclerAdapter();
    }

    private void initRecyclerAdapter() {
        this.rvPlanList = findViewById(R.id.rv_search_plan);

        this.plAdapter = new PlanListAdapter(this.planArrayList, SearchPlanActivity.this);
        this.rvPlanList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rvPlanList.setAdapter(this.plAdapter);
    }

    public void loadData() {
        planDBHelper = new FirebasePlanDBHelper();

        DatabaseReference dbref = planDBHelper.getReference();
        dbref.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
            planArrayList.clear();
            if(snapshot.exists()){
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Plan curr_plan  = dataSnapshot.getValue(Plan.class);
                    planArrayList.add(curr_plan);
                }
                plAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull @NotNull DatabaseError error) {

        }
    };
}