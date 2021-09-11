package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.PlanListAdapter;
import com.mobdeve.s13.g26.genshinplanner.keys.PlanKeys;
import com.mobdeve.s13.g26.genshinplanner.keys.UserKeys;
import com.mobdeve.s13.g26.genshinplanner.models.Item;
import com.mobdeve.s13.g26.genshinplanner.models.Plan;
import com.mobdeve.s13.g26.genshinplanner.models.User;
import com.mobdeve.s13.g26.genshinplanner.utils.FirebasePlanDBHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PlanListActivity extends AppCompatActivity {

    private FirebasePlanDBHelper planDBHelper;

    private RecyclerView rvPlanList;
    private PlanListAdapter plAdapter;

    // move to viewholder next time
    private FloatingActionButton fab_button;
    private ArrayList<Plan> planArrayList;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);
        fab_button = findViewById(R.id.fab_plans_add);
        this.sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        fab_button.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CreatePlanActivity.class);

            v.getContext().startActivity(intent);
        });
        planArrayList = new ArrayList<>();
        loadData();
        initRecyclerAdapter();
    }

    private void initRecyclerAdapter() {
        this.rvPlanList = findViewById(R.id.rv_plan_list);

        this.plAdapter = new PlanListAdapter(this.planArrayList, PlanListActivity.this);
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
                Log.d("SNAPSHOT", snapshot.toString());
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
//        ArrayList<Item> itemList = new ArrayList<>();
//        ArrayList<String> famed_type = new ArrayList<>();
//        ArrayList<String> famed_obtain = new ArrayList<>();
//
//        famed_type.add("Character Ascension Material");
//        famed_type.add("Weapon Ascension Material");
//        famed_type.add("Talent Ascension Material");
//
//        famed_obtain.add("Dropped by Level 60 Nobushi");
//        famed_obtain.add("Forged through 3 Kageuchi Handguard");
//        famed_obtain.add("Buying through Paimon's Bargan");
//
//        itemList.add(new Item(R.drawable.item_common_ascension_famed_handguard, "Famed Handguard", famed_type, famed_obtain));
//
//        ArrayList<String> hoarfrost_type = new ArrayList<>();
//        ArrayList<String> hoarfrost_obtain = new ArrayList<>();
//
//        hoarfrost_type.add("Character Ascension Material");
//
//        hoarfrost_obtain.add("Dropped by Level 40 Cryo Regisvine");
//
//        itemList.add(new Item(R.drawable.item_boss_material_hoarfrost_core, "Hoarforst Core", hoarfrost_type, hoarfrost_obtain));
//
//        ArrayList<String> route = new ArrayList<>();
//        route.add("Bahay ni Howard");
//        route.add("Bahay ni A.");
//        planArrayList = new ArrayList<>();
//        planArrayList.add(new Plan(new User("LMAO", "howard_malakas@malakas.com", "Howard Malakas", "HowardMalakas", "09683948232", "Ganyu"),
//                    "Howard Lang Malakas", "Pinakamalakas si Howard", itemList, route, 69, 2));
}