package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.keys.PlanKeys;
import com.mobdeve.s13.g26.genshinplanner.models.Plan;

public class ViewPlanActivity extends AppCompatActivity {

    private ImageView iv_user_image;
    private TextView tv_plan_name;
    private TextView tv_plan_username;
    private TextView tv_plan_uid;
    private TextView tv_plan_description;
    private TextView tv_plan_resin;

    private RatingBar rb_rating;

    private RecyclerView rv_item;
    private RecyclerView rv_routes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plan);

        this.iv_user_image = findViewById(R.id.iv_user_image);
        this.tv_plan_name = findViewById(R.id.tv_plan_name);
        this.tv_plan_username = findViewById(R.id.tv_user_username);
        this.tv_plan_uid = findViewById(R.id.tv_user_uid);
        this.tv_plan_description = findViewById(R.id.tv_plan_description);
        this.tv_plan_resin = findViewById(R.id.tv_plan_resin);
        this.rb_rating = findViewById(R.id.rb_plan_view_rating);

        this.rv_item = findViewById(R.id.rv_item);
        this.rv_routes = findViewById(R.id.rv_routes);

        Intent intent = getIntent();
        this.iv_user_image.setImageResource(intent.getIntExtra(PlanKeys.PLAN_IMAGE.name(), 0));
        this.tv_plan_name.setText(intent.getStringExtra(PlanKeys.PLAN_TITLE_KEY.name()));
        this.tv_plan_username.setText(intent.getStringExtra(PlanKeys.PLAN_OWNER_NAME.name()));
        this.tv_plan_uid.setText(intent.getStringExtra(PlanKeys.PLAN_OWNER_UID.name()));
        this.tv_plan_description.setText(intent.getStringExtra(PlanKeys.PLAN_DESCRIPTION_KEY.name()));
        this.tv_plan_resin.setText(intent.getStringExtra(PlanKeys.PLAN_RESIN_KEY.name()));
        this.rb_rating.setRating(intent.getIntExtra(PlanKeys.PLAN_RATING_KEY.name(), 0));

    }
}