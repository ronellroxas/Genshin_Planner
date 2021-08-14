package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.mobdeve.s13.g26.genshinplanner.R;

public class HomeActivity extends AppCompatActivity {

    private ImageButton btnViewCharacters;
    private ImageButton btnViewSavedPlans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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