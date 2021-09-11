package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.keys.UserKeys;
import com.mobdeve.s13.g26.genshinplanner.utils.AssetsHelper;
import com.mobdeve.s13.g26.genshinplanner.utils.FirebaseUserDBHelper;

public class HomeActivity extends AppCompatActivity {

    private SharedPreferences sp;

    //texts
    private TextView tvUsername;
    private TextView tvUid;

    //buttons
    private ImageView  imageView;
    private ImageButton btnViewSavedPlans;
    private ImageButton btnSearchPlans;
    private ImageButton btnViewCharacters;
    private ImageButton btnViewItems;
    private ImageButton btnViewProfile;
    private ImageButton btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.imageView = findViewById(R.id.imageView);
        this.btnViewSavedPlans = findViewById(R.id.btn_home_svdplans);
        this.btnSearchPlans = findViewById(R.id.btn_home_srhplans);
        this.btnViewCharacters = findViewById(R.id.btn_home_vwchar);
        this.btnViewItems = findViewById(R.id.btn_home_vwitems);
        this.btnViewProfile = findViewById(R.id.btn_home_vwprof);
        this.btnLogout = findViewById(R.id.btn_home_logout);

        this.sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        initializeTextViews();
        initializeButtons();

        AssetsHelper assetsHelper = new AssetsHelper(HomeActivity.this);
    }

    private void initializeTextViews() {
        this.tvUsername = findViewById(R.id.tv_home_name);
        this.tvUid = findViewById(R.id.tv_home_uid);

        this.tvUsername.setText(sp.getString(UserKeys.USERNAME_KEY.name(), "Error"));
        this.tvUid.setText(sp.getString(UserKeys.UID_KEY.name(), "Error"));

    }

    private void initializeButtons() {
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ViewProfileActivity.class);

            startActivity(intent);
        });

        this.btnViewSavedPlans.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PlanListActivity.class);

            startActivity(intent);
        });

        this.btnSearchPlans.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SearchPlanActivity.class);

            startActivity(intent);
        });

        this.btnViewCharacters.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CharacterListActivity.class);

            startActivity(intent);
        });

        this.btnViewItems.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ItemListActivity.class);

            startActivity(intent);
        });

        this.btnViewProfile.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ViewProfileActivity.class);

            startActivity(intent);
        });

        this.btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            sp.edit().clear().commit(); //clear preferences
            FirebaseUserDBHelper dbHelper = new FirebaseUserDBHelper();
            dbHelper.logout();
            finish();
            startActivity(intent);
        });
    }
}