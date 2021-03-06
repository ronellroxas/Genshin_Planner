package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.UnsupportedSchemeException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.CreatePlanItemsAdapter;
import com.mobdeve.s13.g26.genshinplanner.adapters.CreatePlanRoutesAdapter;
import com.mobdeve.s13.g26.genshinplanner.adapters.CustomSpinnerImageAdapter;
import com.mobdeve.s13.g26.genshinplanner.keys.PlanKeys;
import com.mobdeve.s13.g26.genshinplanner.keys.UserKeys;
import com.mobdeve.s13.g26.genshinplanner.models.Item;
import com.mobdeve.s13.g26.genshinplanner.models.Plan;
import com.mobdeve.s13.g26.genshinplanner.models.User;
import com.mobdeve.s13.g26.genshinplanner.utils.AssetsHelper;
import com.mobdeve.s13.g26.genshinplanner.utils.FirebasePlanDBHelper;
import com.mobdeve.s13.g26.genshinplanner.views.CreatePlanViewHolder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class CreatePlanActivity extends AppCompatActivity {

    //RecyclerView cycle variables
    private CreatePlanRoutesAdapter createPlanRoutesAdapter;
    private CreatePlanItemsAdapter createPlanItemsAdapter;
    private RecyclerView rvRoutes;
    private RecyclerView rvItems;
    private ArrayList<String> routes;
    private ArrayList<Item> items;

    //Create Plan components
    private Spinner spinnerAddRoute;
    private Spinner spinnerAddItem;
    private CreatePlanViewHolder createPlanViewHolder;

    private SharedPreferences sp;
    private FirebasePlanDBHelper planDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        this.sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.createPlanViewHolder = new CreatePlanViewHolder(this);
        initButtons();

        initRecyclerAdapter();
        initRouteSpinner();
        try {
            initItemsSpinner();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intent = getIntent();
        String plan_name = intent.getStringExtra(PlanKeys.PLAN_TITLE_KEY.name());
        String plan_description = intent.getStringExtra(PlanKeys.PLAN_DESCRIPTION_KEY.name());
        int plan_resin = intent.getIntExtra(PlanKeys.PLAN_RESIN_KEY.name(), -1);
        if(plan_name != null && plan_description != null && plan_resin != -1){
            this.createPlanViewHolder.setName(plan_name);
            this.createPlanViewHolder.setDescription(plan_description);
            this.createPlanViewHolder.setResin(String.valueOf(plan_resin));
        }
        planDBHelper = new FirebasePlanDBHelper();
        Query query = planDBHelper.getReference().limitToFirst(1).orderByChild("plan_id").equalTo(intent.getStringExtra(PlanKeys.PLAN_ID_KEY.name()));
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
            if(snapshot.exists()){
                Log.d("SNAPSHOT", snapshot.toString());
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Plan curr_plan = dataSnapshot.getValue(Plan.class);
                    items.addAll(curr_plan.getPlan_items());
                    routes.addAll(curr_plan.getPlan_route());
                }
                createPlanItemsAdapter.notifyDataSetChanged();
                createPlanRoutesAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull @NotNull DatabaseError error) {

        }
    };

    private void initRecyclerAdapter() {
        this.routes = new ArrayList<>();
        this.items = new ArrayList<>();

        this.rvRoutes = findViewById(R.id.rv_create_plan_routes);
        this.rvItems = findViewById(R.id.rv_create_plan_items);

        this.createPlanRoutesAdapter = new CreatePlanRoutesAdapter(routes, false);
        this.createPlanItemsAdapter = new CreatePlanItemsAdapter(items);

        this.rvRoutes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rvRoutes.setAdapter(this.createPlanRoutesAdapter);

        this.rvItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        this.rvItems.setAdapter(this.createPlanItemsAdapter);
    }

    /**
     * Currently temporary till Genshin API is used
     */
    private void initRouteSpinner() {
        this.spinnerAddRoute = findViewById(R.id.spinner_create_plan_routes);

        //load routes selection to spinner
        String[] arrSpinnerRoutes = {"", "Brightcrown Mountains", "Galesong Hill", "Starfell Valley", "Windwail Highland", "Dragonspine", "Mondstat",
                                    "Cider Lake", "Falcon Coast", "Cape Oath", "Dawn Winery", "Springvale", "Thousand Winds Temple", "Musk Reef", "Bishui PLain",
                                    "Minlin", "Qiongji Estuary", "Lisha", "Sea of Clouds", "Liyue Harbor", "Mt. Tianheng", "Dihua Marsh", "Wangshu Inn", "Qingce Village",
                                    "Jueyun Karst", "Narukami Island", "Kannazuka", "Yashiori Island", "Seirai Island", "Watatsumi Island", "Tsurumi Island", "Inazuma City",
                                    "Ritou", "Konda Village", "Chinju Forest", "Grand Narukami Shrine", "Tatarasuna", "Kujou Encampment", "Nazuchi Beach", "Musoujin Gorge",
                                    "Serpent's Head"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, arrSpinnerRoutes);
        spinnerAddRoute.setAdapter(adapter);

        //attach listener
        this.spinnerAddRoute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tvView = (TextView)view;
                tvView.setText(null);

                String selected = spinnerAddRoute.getSelectedItem().toString();
                if(!selected.equals("")) {
                    routes.add(selected);
                    createPlanRoutesAdapter.addRoute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initItemsSpinner() throws JSONException {
        this.spinnerAddItem = findViewById(R.id.spinner_create_plan_items);

        AssetsHelper assetsHelper = new AssetsHelper(CreatePlanActivity.this);
        ArrayList<Item> tempItemList = assetsHelper.getItemAssets();

        //get image ids
        Integer[] imageIds = new Integer[tempItemList.size()];
        //imageIds[0] = -1;
        for(int i = 0; i < tempItemList.size(); i++) {
            imageIds[i] = tempItemList.get(i).getItem_img();
        }

        CustomSpinnerImageAdapter adapter = new CustomSpinnerImageAdapter(this, imageIds);
        this.spinnerAddItem.setAdapter(adapter);

        //attach listener
        this.spinnerAddItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Boolean start = true;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImageView ivView = (ImageView) view;
                ivView.setVisibility(View.GONE);
                if (!start){
                    int selected = spinnerAddItem.getSelectedItemPosition();
                    if (selected != -1) {
                        items.add(tempItemList.get(selected));
                        createPlanItemsAdapter.addItem();
                    }
                }
                start = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initButtons() {
        createPlanViewHolder.setSubmitListener(v -> {
            String title = createPlanViewHolder.getName();
            String desc = createPlanViewHolder.getDescription();
            String resin = createPlanViewHolder.getResin();

            if(!title.isEmpty() && !desc.isEmpty() && !resin.isEmpty() && items.size() > 0 && routes.size() > 0) {

                //rebuild user
                String userId = sp.getString(UserKeys.ID_KEY.name(), null);
                String email = sp.getString(UserKeys.EMAIL_KEY.name(), null);
                String name = sp.getString(UserKeys.NAME_KEY.name(), null);
                String username = sp.getString(UserKeys.USERNAME_KEY.name(), null);
                String uid = sp.getString(UserKeys.UID_KEY.name(), null);
                String main = sp.getString(UserKeys.MAIN_KEY.name(), null);

                User user = new User(userId, email, name, username, uid, main);

                Intent intent = getIntent();
                FirebasePlanDBHelper dbHelper = new FirebasePlanDBHelper();

                if(intent.getStringExtra(PlanKeys.PLAN_TITLE_KEY.name()) != null){
                    dbHelper.addPlan(new Plan(dbHelper.getReference().getKey(), user, title, desc, items, routes, Integer.parseInt(resin)));
                }else{
                    dbHelper.addPlan(new Plan(dbHelper.getReference().push().getKey(), user, title, desc, items, routes, Integer.parseInt(resin)));
                }

                Intent new_intent = new Intent(CreatePlanActivity.this, PlanListActivity.class);
//                new_intent.putExtra(PlanKeys.PLAN_RATING_KEY.name(), 0);
//                new_intent.putExtra(PlanKeys.PLAN_RESIN_KEY.name(), Integer.parseInt(resin));
//                new_intent.putExtra(PlanKeys.PLAN_TITLE_KEY.name(), title);
//                new_intent.putExtra(PlanKeys.PLAN_OWNER_NAME.name(), username);
//                new_intent.putExtra(PlanKeys.PLAN_IMAGE.name(),getImageResources(main));
//                new_intent.putExtra(PlanKeys.PLAN_ID_KEY.name(), dbHelper.getReference().push().getKey());
//                new_intent.putExtra(PlanKeys.PLAN_OWNER_UID.name(), uid);
//                new_intent.putExtra(PlanKeys.PLAN_DESCRIPTION_KEY.name(), desc);
                finish();
                startActivity(new_intent);

            }
            else  {
                Toast.makeText(CreatePlanActivity.this, "Please fill-up empty fields.", Toast.LENGTH_SHORT).show();
            }
        });

        createPlanViewHolder.setCancelListener(v -> {
            finish();
        });
    }

    private int getImageResources(String main) {
        Field[] fields = R.drawable.class.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                String filename = fields[i].getName();

                if (filename.contains("characters_") && filename.contains(main.toLowerCase())) //remove filenames with _
                    return fields[i].getInt(null);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

}