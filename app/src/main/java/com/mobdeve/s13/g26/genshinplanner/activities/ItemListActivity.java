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
        loadData();
        this.rvList = findViewById(R.id.rv_list);
        this.clAdapter = new ItemListAdapter(this.itemList);

        this.rvList.setLayoutManager(new GridLayoutManager(this, 4));
        this.rvList.setAdapter(this.clAdapter);
    }

    private void loadData(){
        ArrayList<String> famed_type = new ArrayList<>();
        ArrayList<String> famed_obtain = new ArrayList<>();

        famed_type.add("Character Ascension Material");
        famed_type.add("Weapon Ascension Material");
        famed_type.add("Talent Ascension Material");

        famed_obtain.add("Dropped by Level 60 Nobushi");
        famed_obtain.add("Forged through 3 Kageuchi Handguard");
        famed_obtain.add("Buying through Paimon's Bargan");

        itemList.add(new Item(R.drawable.item_mob_famed_handguard, "Famed Handguard", famed_type, famed_obtain));

        ArrayList<String> hoarfrost_type = new ArrayList<>();
        ArrayList<String> hoarfrost_obtain = new ArrayList<>();

        hoarfrost_type.add("Character Ascension Material");

        hoarfrost_obtain.add("Dropped by Level 40 Cryo Regisvine");

        itemList.add(new Item(R.drawable.item_char_ascen_hoarfrost_core, "Hoarforst Core", hoarfrost_type, hoarfrost_obtain));
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