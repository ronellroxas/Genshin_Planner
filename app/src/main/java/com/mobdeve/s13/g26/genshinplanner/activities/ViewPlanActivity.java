package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.CreatePlanRoutesAdapter;
import com.mobdeve.s13.g26.genshinplanner.adapters.ItemListAdapter;
import com.mobdeve.s13.g26.genshinplanner.keys.PlanKeys;
import com.mobdeve.s13.g26.genshinplanner.keys.UserKeys;
import com.mobdeve.s13.g26.genshinplanner.models.Item;
import com.mobdeve.s13.g26.genshinplanner.models.Plan;
import com.mobdeve.s13.g26.genshinplanner.models.Rating;
import com.mobdeve.s13.g26.genshinplanner.models.User;
import com.mobdeve.s13.g26.genshinplanner.utils.FirebasePlanDBHelper;
import com.squareup.okhttp.Route;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewPlanActivity extends AppCompatActivity {

    private FirebasePlanDBHelper planDBHelper;

    private ImageView iv_user_image;
    private TextView tv_plan_name;
    private TextView tv_plan_username;
    private TextView tv_plan_uid;
    private TextView tv_plan_description;
    private TextView tv_plan_resin;
    private TextView tv_view_plan_rating;

    private RatingBar rb_rating;
    private FloatingActionButton fab_confirm;

    private RecyclerView rv_item;
    private ItemListAdapter itemListAdapter;

    private RecyclerView rv_routes;
    private CreatePlanRoutesAdapter planRoutesAdapter;

    private ArrayList<Item> item_list;
    private ArrayList<String> route_list;
    private ArrayList<Rating> rating_list;

    private Intent intent;
    private SharedPreferences sp;

    private Plan curr_plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plan);

        this.item_list = new ArrayList<>();
        this.route_list = new ArrayList<>();
        this.rating_list = new ArrayList<>();
        this.sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        initializeComponents();
        initRecyclerAdapter();
        initializeFields();
        initRatingBarListeners();
    }

    private void initializeComponents() {
        this.iv_user_image = findViewById(R.id.iv_user_image);
        this.tv_plan_name = findViewById(R.id.tv_plan_name);
        this.tv_plan_username = findViewById(R.id.tv_user_username);
        this.tv_plan_uid = findViewById(R.id.tv_user_uid);
        this.tv_plan_description = findViewById(R.id.tv_plan_description);
        this.tv_plan_resin = findViewById(R.id.tv_plan_resin);
        this.tv_view_plan_rating = findViewById(R.id.tv_view_plan_rating);
        this.rb_rating = findViewById(R.id.rb_plan_view_rating);
        this.fab_confirm = findViewById(R.id.fab_view_plan_confirm);

        fab_confirm.setOnClickListener(v -> {
            int rating = (int) rb_rating.getRating();

            addRating(rating);
        });
    }

    private void initRecyclerAdapter() {
        this.rv_item = findViewById(R.id.rv_item);
        this.itemListAdapter = new ItemListAdapter(this.item_list);
        this.rv_item.setLayoutManager(new  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        this.rv_item.setAdapter(this.itemListAdapter);

        this.rv_routes = findViewById(R.id.rv_routes);
        this.planRoutesAdapter = new CreatePlanRoutesAdapter(this.route_list, true);
        this.rv_routes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rv_routes.setAdapter(this.planRoutesAdapter);
    }

    private void initializeFields() {
        intent = getIntent();
        this.iv_user_image.setImageResource(intent.getIntExtra(PlanKeys.PLAN_IMAGE.name(), 0));
        this.tv_plan_name.setText(intent.getStringExtra(PlanKeys.PLAN_TITLE_KEY.name()));
        this.tv_plan_username.setText(intent.getStringExtra(PlanKeys.PLAN_OWNER_NAME.name()));
        this.tv_plan_uid.setText(intent.getStringExtra(PlanKeys.PLAN_OWNER_UID.name()));
        this.tv_plan_description.setText(intent.getStringExtra(PlanKeys.PLAN_DESCRIPTION_KEY.name()));
        this.tv_plan_resin.setText("Resin: " + String.valueOf(intent.getIntExtra(PlanKeys.PLAN_RESIN_KEY.name(), 0)));
        this.rb_rating.setRating(intent.getFloatExtra(PlanKeys.PLAN_RATING_KEY.name(), 0));

        planDBHelper = new FirebasePlanDBHelper();
        Query query = planDBHelper.getReference().limitToFirst(1).orderByChild("plan_id").equalTo(intent.getStringExtra(PlanKeys.PLAN_ID_KEY.name()));
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    private void initRatingBarListeners() {
        rb_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            Boolean start = true;
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                if(!start) {
                    float curr_rating = rb_rating.getRating();
                    if(curr_rating == intent.getFloatExtra(PlanKeys.PLAN_RATING_KEY.name(), 0))
                        fab_confirm.setVisibility(View.GONE);
                    else {
                        fab_confirm.setVisibility(View.VISIBLE);
                    }
                }
//                start = false;
//            }
        });
    }

    private void addRating(int rating) {
        FirebasePlanDBHelper dbHelper = new FirebasePlanDBHelper();

//        //rebuild user
//        User user = new User(   sp.getString(UserKeys.ID_KEY.name(), null),
//                                sp.getString(UserKeys.EMAIL_KEY.name(), null),
//                                sp.getString(UserKeys.NAME_KEY.name(), null),
//                                sp.getString(UserKeys.USERNAME_KEY.name(), null),
//                                sp.getString(UserKeys.UID_KEY.name(), null),
//                                sp.getString(UserKeys.MAIN_KEY.name(), null));

//        //rebuild plan
//        Plan currPlan = new Plan(   intent.getStringExtra(PlanKeys.PLAN_ID_KEY.name()),
//                                    user,
//                                    intent.getStringExtra(PlanKeys.PLAN_TITLE_KEY.name()),
//                                    intent.getStringExtra(PlanKeys.PLAN_DESCRIPTION_KEY.name()),
//                                    item_list, route_list,
//                                    intent.getIntExtra(PlanKeys.PLAN_RESIN_KEY.name(), 0));

        curr_plan.setPlan_rating(rating_list);

        Boolean found = false;
        float sum = 0;
        for(int i = 0; i < curr_plan.getPlan_rating().size(); i++) {
            Rating r = curr_plan.getPlan_rating().get(i);
            if(r.getUserId().equals(sp.getString(UserKeys.ID_KEY.name(), null))) {
                r.setRating(rating);
                found = true;
            }
            sum += r.getRating();
        }
        if(!found) {
            curr_plan.getPlan_rating().add(new Rating(sp.getString(UserKeys.ID_KEY.name(), null), rating));
        }
        curr_plan.setPlan_average_rating(sum/curr_plan.getPlan_rating().size());

        dbHelper.addPlan(curr_plan);
        dbHelper.sharePlan(curr_plan);
        fab_confirm.setVisibility(View.GONE);
        if(!found)
            Toast.makeText(this, "Plan rated!",  Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Plan rating updated!",  Toast.LENGTH_SHORT).show();
    }

    private void initializeRatingBar(Plan curr_plan) {
        if(curr_plan != null) {
            TextView tvRatingText = findViewById(R.id.tv_view_plan_rating);
            String userId = sp.getString(UserKeys.ID_KEY.name(), null);
            if(!userId.equals(curr_plan.getPlan_owner().getUserId())) {
                rb_rating.setIsIndicator(false);
                tvRatingText.setText("Rate this Plan");
            }
            else {
                rb_rating.setIsIndicator(true);
                tvRatingText.setText("Plan Rating");
            }
        }
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
            if(snapshot.exists()){
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    curr_plan = dataSnapshot.getValue(Plan.class);
                    item_list.addAll(curr_plan.getPlan_items());
                    route_list.addAll(curr_plan.getPlan_route());
                    rating_list.addAll(curr_plan.getPlan_rating());
                    initializeRatingBar(curr_plan);
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