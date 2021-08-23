package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.CharacterListAdapter;
import com.mobdeve.s13.g26.genshinplanner.adapters.ItemListAdapter;
import com.mobdeve.s13.g26.genshinplanner.models.Item;

import android.os.Bundle;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity {

    private ArrayList<Item> itemList;

    private RecyclerView rvList;
    private ItemListAdapter clAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);
        itemList = new ArrayList<>();
        initRecyclerAdapter();
    }

    private void initRecyclerAdapter() {
        //getImageResources();    //get all img filenames in res folder (self-declared)
        this.rvList = findViewById(R.id.rv_list);
        this.clAdapter = new ItemListAdapter(this.itemList);

        this.rvList.setLayoutManager(new GridLayoutManager(this, 4));
        this.rvList.setAdapter(this.clAdapter);
    }

//    private void getImageResources() {
//        Field[] fields = R.drawable.class.getDeclaredFields();
//
//        try {
//            for (int i = 0; i < fields.length; i++) {
//                String filename = fields[i].getName();
//
//                if (filename.contains("item_")) //remove filenames with _
//                    this.itemList.add(fields[i].getInt(null));
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}