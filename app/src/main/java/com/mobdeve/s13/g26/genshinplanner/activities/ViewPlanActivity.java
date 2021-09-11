package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.CreatePlanRoutesAdapter;
import com.mobdeve.s13.g26.genshinplanner.adapters.ItemListAdapter;
import com.mobdeve.s13.g26.genshinplanner.keys.PlanKeys;
import com.mobdeve.s13.g26.genshinplanner.models.Item;
import com.mobdeve.s13.g26.genshinplanner.models.Plan;
import com.mobdeve.s13.g26.genshinplanner.utils.FirebasePlanDBHelper;
import com.squareup.okhttp.Route;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ViewPlanActivity extends AppCompatActivity {

    private FirebasePlanDBHelper planDBHelper;

    private ImageView iv_user_image;
    private TextView tv_plan_name;
    private TextView tv_plan_username;
    private TextView tv_plan_uid;
    private TextView tv_plan_description;
    private TextView tv_plan_resin;

    private RatingBar rb_rating;

    private RecyclerView rv_item;
    private ItemListAdapter itemListAdapter;

    private RecyclerView rv_routes;
    private CreatePlanRoutesAdapter planRoutesAdapter;

    private ArrayList<Item> item_list;
    private ArrayList<String> route_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plan);

        this.item_list = new ArrayList<>();
        this.route_list = new ArrayList<>();

        this.iv_user_image = findViewById(R.id.iv_user_image);
        this.tv_plan_name = findViewById(R.id.tv_plan_name);
        this.tv_plan_username = findViewById(R.id.tv_user_username);
        this.tv_plan_uid = findViewById(R.id.tv_user_uid);
        this.tv_plan_description = findViewById(R.id.tv_plan_description);
        this.tv_plan_resin = findViewById(R.id.tv_plan_resin);
        this.rb_rating = findViewById(R.id.rb_plan_view_rating);

        this.rv_item = findViewById(R.id.rv_item);
        this.itemListAdapter = new ItemListAdapter(this.item_list);
        this.rv_item.setLayoutManager(new  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        this.rv_item.setAdapter(this.itemListAdapter);

        this.rv_routes = findViewById(R.id.rv_routes);
        this.planRoutesAdapter = new CreatePlanRoutesAdapter(this.route_list, true);
        this.rv_routes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rv_routes.setAdapter(this.planRoutesAdapter);

        Intent intent = getIntent();
        this.iv_user_image.setImageResource(intent.getIntExtra(PlanKeys.PLAN_IMAGE.name(), 0));
        this.tv_plan_name.setText(intent.getStringExtra(PlanKeys.PLAN_TITLE_KEY.name()));
        this.tv_plan_username.setText(intent.getStringExtra(PlanKeys.PLAN_OWNER_NAME.name()));
        this.tv_plan_uid.setText(intent.getStringExtra(PlanKeys.PLAN_OWNER_UID.name()));
        this.tv_plan_description.setText(intent.getStringExtra(PlanKeys.PLAN_DESCRIPTION_KEY.name()));
        this.tv_plan_resin.setText(intent.getStringExtra(PlanKeys.PLAN_RESIN_KEY.name()));
        this.rb_rating.setRating(intent.getIntExtra(PlanKeys.PLAN_RATING_KEY.name(), 0));

        planDBHelper = new FirebasePlanDBHelper();
        Log.d("STRING", "" + intent.getStringExtra(PlanKeys.PLAN_ID_KEY.name()));
        Query query = planDBHelper.getReference().limitToFirst(1).orderByChild("plan_id").equalTo(intent.getStringExtra(PlanKeys.PLAN_ID_KEY.name()));
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
            if(snapshot.exists()){
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Plan curr_plan = dataSnapshot.getValue(Plan.class);
                    item_list.addAll(curr_plan.getPlan_items());
                    route_list.addAll(curr_plan.getPlan_route());
                }
                itemListAdapter.notifyDataSetChanged();
                planRoutesAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull @NotNull DatabaseError error) {

        }
    };
}