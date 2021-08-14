package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.mobdeve.s13.g26.genshinplanner.R;

public class MainActivity extends AppCompatActivity {

    private Button btnGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnGoogle =  findViewById(R.id.btn_main_google);

        btnGoogle.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);

            startActivity(intent);
        });
    }
}