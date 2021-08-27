package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.PlanListAdapter;
import com.mobdeve.s13.g26.genshinplanner.models.Item;
import com.mobdeve.s13.g26.genshinplanner.models.Plan;
import com.mobdeve.s13.g26.genshinplanner.models.User;

import java.util.ArrayList;

public class PlanListActivity extends AppCompatActivity {

    private RecyclerView rvPlanList;
    private PlanListAdapter plAdapter;

    // move to viewholder next time
    private FloatingActionButton fab_button;
    private ArrayList<Plan> planArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);
        fab_button = findViewById(R.id.fab_plans_add);

        fab_button.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CreatePlanActivity.class);

            v.getContext().startActivity(intent);
        });
        loadData();
        initRecyclerAdapter();
    }

    private void initRecyclerAdapter() {
        this.rvPlanList = findViewById(R.id.rv_plan_list);

        this.plAdapter = new PlanListAdapter(this.planArrayList, PlanListActivity.this);
        this.rvPlanList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rvPlanList.setAdapter(this.plAdapter);
    }

    public void loadData(){
        ArrayList<Item> itemList = new ArrayList<>();
        ArrayList<String> famed_type = new ArrayList<>();
        ArrayList<String> famed_obtain = new ArrayList<>();

        famed_type.add("Character Ascension Material");
        famed_type.add("Weapon Ascension Material");
        famed_type.add("Talent Ascension Material");

        famed_obtain.add("Dropped by Level 60 Nobushi");
        famed_obtain.add("Forged through 3 Kageuchi Handguard");
        famed_obtain.add("Buying through Paimon's Bargan");

        itemList.add(new Item(R.drawable.item_mob_famed_handguard, "Famed Handguard", famed_type, famed_obtain));

        ArrayList<String> hoarfrost_type = new ArrayList<>();
        ArrayList<String> hoarfrost_obtain = new ArrayList<>();

        hoarfrost_type.add("Character Ascension Material");

        hoarfrost_obtain.add("Dropped by Level 40 Cryo Regisvine");

        itemList.add(new Item(R.drawable.item_char_ascen_hoarfrost_core, "Hoarforst Core", hoarfrost_type, hoarfrost_obtain));

        ArrayList<String> route = new ArrayList<>();
        route.add("Bahay ni Howard");
        route.add("Bahay ni A.");
        planArrayList = new ArrayList<>();
        planArrayList.add(new Plan(1, new User("LMAO", "howard_malakas@malakas.com", "Howard Malakas", "HowardMalakas", "09683948232", "Ganyu"),
                    "Howard Lang Malakas", "Pinakamalakas si Howard", itemList, route, 69, 2));
    }
}