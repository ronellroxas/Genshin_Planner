package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.mobdeve.s13.g26.genshinplanner.R;

public class HomeActivity extends AppCompatActivity {

    private ImageButton btnViewCharacters;
    private ImageView  imageView;
    private ImageButton btnViewSavedPlans;
    private ImageButton btnCreatePlans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.imageView = findViewById(R.id.imageView);
        this.btnViewCharacters = findViewById(R.id.btn_home_vwchar);
        this.btnViewSavedPlans = findViewById(R.id.btn_home_svdplans);

        this.btnViewCharacters.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ListActivity.class);

            startActivity(intent);
        });

        this.btnViewSavedPlans.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PlanListActivity.class);

            startActivity(intent);
        });

    }

}