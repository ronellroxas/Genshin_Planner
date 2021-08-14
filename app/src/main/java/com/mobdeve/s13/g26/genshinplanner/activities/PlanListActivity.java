package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.CharacterListAdapter;
import com.mobdeve.s13.g26.genshinplanner.adapters.PlanListAdapter;

public class PlanListActivity extends AppCompatActivity {

    private RecyclerView rvPlanList;
    private PlanListAdapter plAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);

        initRecyclerAdapter();
    }

    private void initRecyclerAdapter() {
        this.rvPlanList = findViewById(R.id.rv_plan_list);

        this.plAdapter = new PlanListAdapter();
        this.rvPlanList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rvPlanList.setAdapter(this.plAdapter);
    }
}