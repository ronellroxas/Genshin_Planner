package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.PlanListAdapter;
import com.mobdeve.s13.g26.genshinplanner.models.Item;
import com.mobdeve.s13.g26.genshinplanner.models.Plan;
import com.mobdeve.s13.g26.genshinplanner.models.User;
import com.mobdeve.s13.g26.genshinplanner.utils.FirebasePlanDBHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SearchPlanActivity extends AppCompatActivity {

    private FirebasePlanDBHelper planDBHelper;
    private RecyclerView rvPlanList;
    private PlanListAdapter plAdapter;
    private ArrayList<Plan> planArrayList;

    //ui components
    private ProgressBar pbLoading;
    private EditText etSearch;

    private String searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_plan);

        this.pbLoading = findViewById(R.id.pb_search_plan_progress);
        this.etSearch = findViewById(R.id.et_search_plan_name);

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
        AddEditTextListener();
        dbref.addValueEventListener(valueEventListener);
    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
            rvPlanList.setVisibility(View.GONE);
            pbLoading.setVisibility(View.VISIBLE);
            planArrayList.clear();
            if(snapshot.exists()){
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Plan curr_plan  = dataSnapshot.getValue(Plan.class);
                    if(searchText == null)
                        planArrayList.add(curr_plan);
                    else {
                        if(curr_plan.getPlan_title().contains(searchText))
                            planArrayList.add(curr_plan);
                    }
                }
                if(planArrayList.size() == 0)
                    Toast.makeText(SearchPlanActivity.this, "No plans found, try a different keyword.",
                            Toast.LENGTH_LONG).show();
                plAdapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(SearchPlanActivity.this, "No plans found, try a different keyword.",
                        Toast.LENGTH_LONG).show();
            }
            pbLoading.setVisibility(View.GONE);
            rvPlanList.setVisibility(View.VISIBLE);
        }

        @Override
        public void onCancelled(@NonNull @NotNull DatabaseError error) {

        }

    };

    private void AddEditTextListener() {
        this.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //show loading
                rvPlanList.setVisibility(View.GONE);
                pbLoading.setVisibility(View.VISIBLE);

                searchText = s.toString();  //save search
                FirebasePlanDBHelper dbHelper = new FirebasePlanDBHelper();
                Plan baitPlan = new Plan(new User(), "NULL", "NULL", null, null, 0, 0);
                dbHelper.addPlan(baitPlan);
                dbHelper.deletePlan(baitPlan.getPlan_id());

                //hide loading
                pbLoading.setVisibility(View.GONE);
                rvPlanList.setVisibility(View.VISIBLE);
            }
        });
    }
}