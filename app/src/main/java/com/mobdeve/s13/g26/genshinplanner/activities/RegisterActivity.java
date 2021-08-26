package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.keys.UserKeys;

public class RegisterActivity extends AppCompatActivity {

    private Button btnSaveProfile;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        this.btnSaveProfile = findViewById(R.id.btn_register_saveprofile);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        initializeFields();
        btnSaveProfile.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);

            startActivity(intent);
        });
    }

    private void initializeFields() {

    }
}