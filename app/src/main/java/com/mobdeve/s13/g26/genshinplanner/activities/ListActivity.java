package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.CharacterListAdapter;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class ListActivity extends AppCompatActivity {

    private ArrayList<Integer> charList;

    private RecyclerView rvList;
    private CharacterListAdapter clAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        charList = new ArrayList<Integer>();
        initRecyclerAdapter();
    }

    private void initRecyclerAdapter() {
        getImageResources();    //get all img filenames in res folder (self-declared)
        this.rvList = findViewById(R.id.rv_list);
        this.clAdapter = new CharacterListAdapter(this.charList);

        this.rvList.setLayoutManager(new GridLayoutManager(this, 4));
        this.rvList.setAdapter(this.clAdapter);
    }

    private void getImageResources() {
        Field[] fields = R.drawable.class.getDeclaredFields();

        try {
            for (int i = 0; i < fields.length; i++) {
                String filename = fields[i].getName();

                if (!filename.contains("_") && !filename.contains("background")) //remove filenames with _
                    this.charList.add(fields[i].getInt(null));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}