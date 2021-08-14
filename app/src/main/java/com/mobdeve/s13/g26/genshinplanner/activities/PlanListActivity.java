package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.CharacterListAdapter;
import com.mobdeve.s13.g26.genshinplanner.adapters.PlanListAdapter;

public class PlanListActivity extends AppCompatActivity {

    private RecyclerView rvPlanList;
    private PlanListAdapter plAdapter;

    // move to viewholder next time
    private FloatingActionButton fabAddPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);

        this.fabAddPlan = findViewById(R.id.fab_plans_add);

        fabAddPlan.setOnClickListener(v -> {
            Intent intent = new Intent(PlanListActivity.this, CreatePlanActivity.class);

            startActivity(intent);
        });

        initRecyclerAdapter();
    }

    private void initRecyclerAdapter() {
        this.rvPlanList = findViewById(R.id.rv_plan_list);

        this.plAdapter = new PlanListAdapter();
        this.rvPlanList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rvPlanList.setAdapter(this.plAdapter);
    }
}