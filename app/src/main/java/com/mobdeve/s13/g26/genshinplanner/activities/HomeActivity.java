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
    private ImageButton btnViewCharacters;
    private ImageView  imageView;
    private ImageButton btnViewSavedPlans;
    private ImageButton btnCreatePlans;
    private ImageButton btnViewItems;
    private ImageButton btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.imageView = findViewById(R.id.imageView);
        this.btnViewCharacters = findViewById(R.id.btn_home_vwchar);
        this.btnViewSavedPlans = findViewById(R.id.btn_home_svdplans);
        this.btnViewItems = findViewById(R.id.btn_home_vwitems);
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
        this.btnViewCharacters.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CharacterListActivity.class);

            startActivity(intent);
        });

        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ViewProfileActivity.class);

            startActivity(intent);
        });

        this.btnViewSavedPlans.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PlanListActivity.class);

            startActivity(intent);
        });

        this.btnViewItems.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ItemListActivity.class);

            startActivity(intent);
        });

        this.btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            FirebaseUserDBHelper userDBHelper = new FirebaseUserDBHelper();
            userDBHelper.logout();

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }
}