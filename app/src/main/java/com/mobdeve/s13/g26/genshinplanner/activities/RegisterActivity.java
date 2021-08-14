package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.mobdeve.s13.g26.genshinplanner.R;

public class RegisterActivity extends AppCompatActivity {

    private Button btnSaveProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        this.btnSaveProfile = findViewById(R.id.btn_register_saveprofile);

        btnSaveProfile.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);

            startActivity(intent);
        });
    }
}