package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.mobdeve.s13.g26.genshinplanner.adapters.CharacterListAdapter;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.models.Character;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class CharacterListActivity extends AppCompatActivity {

    private ArrayList<Character> charList;

    private RecyclerView rvList;
    private CharacterListAdapter clAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);
        charList = new ArrayList<Character>();
        initRecyclerAdapter();
    }

    private void initRecyclerAdapter() {
        //getImageResources();    //get all img filenames in res folder (self-declared)
        loadData();
        this.rvList = findViewById(R.id.rv_list);
        this.clAdapter = new CharacterListAdapter(this.charList);

        this.rvList.setLayoutManager(new GridLayoutManager(this, 4));
        this.rvList.setAdapter(this.clAdapter);
    }

    private void loadData(){
        ArrayList<Integer> ascen_mats = new ArrayList<>();
        ascen_mats.add(R.drawable.item_char_ascen_cryo_gemstone);
        ascen_mats.add(R.drawable.item_char_ascen_perpetual_heart);
        ascen_mats.add(R.drawable.item_world_sakura_bloom);
        ascen_mats.add(R.drawable.item_mob_famed_handguard);

        ArrayList<Integer> talent_mats = new ArrayList<>();
        talent_mats.add(R.drawable.item_tal_philo_elegance);
        talent_mats.add(R.drawable.item_week_frog_branch);
        talent_mats.add(R.drawable.item_tal_crown);
        talent_mats.add(R.drawable.item_mob_famed_handguard);

        charList.add(new Character(R.drawable.characters_ayaka, "Kamisato Ayaka", 2, 6, 5, ascen_mats, talent_mats));
        ascen_mats.clear();
        talent_mats.clear();

        ascen_mats.add(R.drawable.item_char_ascen_pyro_gemstone);
        ascen_mats.add(R.drawable.item_char_ascen_everflame_seed);
        ascen_mats.add(R.drawable.item_world_lamp_grass);
        ascen_mats.add(R.drawable.item_mob_weathered_arrow);

        talent_mats.add(R.drawable.item_tal_philo_freedom);
        talent_mats.add(R.drawable.item_week_dragon_sigh);
        talent_mats.add(R.drawable.item_tal_crown);
        talent_mats.add(R.drawable.item_mob_weathered_arrow);
        charList.add(new Character(R.drawable.characters_amber, "Amber", 4, 2, 4, ascen_mats, talent_mats));
        ascen_mats.clear();
        talent_mats.clear();

        ascen_mats.add(R.drawable.item_char_ascen_pyro_gemstone);
        ascen_mats.add(R.drawable.item_char_ascen_everflame_seed);
        ascen_mats.add(R.drawable.item_world_philanemo_mushroom);
        ascen_mats.add(R.drawable.item_mob_forbidden_curse_scroll);

        talent_mats.add(R.drawable.item_tal_philo_freedom);
        talent_mats.add(R.drawable.item_week_wolf_ring);
        talent_mats.add(R.drawable.item_tal_crown);
        talent_mats.add(R.drawable.item_mob_forbidden_curse_scroll);
        charList.add(new Character(R.drawable.characters_klee, "Klee", 5, 2, 5, ascen_mats, talent_mats));

    }

//    private void getImageResources() {
//        Field[] fields = R.drawable.class.getDeclaredFields();
//
//        try {
//            for (int i = 0; i < fields.length; i++) {
//                String filename = fields[i].getName();
//
//                if (filename.contains("characters_")) //remove filenames with _
//                    this.charList.add(fields[i].getInt(null));
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}