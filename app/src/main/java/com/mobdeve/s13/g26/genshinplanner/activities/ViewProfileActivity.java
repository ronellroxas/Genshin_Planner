package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.CharacterListAdapter;
import com.mobdeve.s13.g26.genshinplanner.models.Character;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ViewProfileActivity extends AppCompatActivity {

    private ArrayList<Character> charList;
    private ArrayList<String> userown_list;

    private RecyclerView char_recycler_view;
    private CharacterListAdapter characterListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        charList = new ArrayList<Character>();
        userown_list = new ArrayList<String>();
        getUserOwnedCharacters();
        initRecyclerAdapter();
    }

    private void initRecyclerAdapter() {
        //getImageResources();
        this.char_recycler_view = findViewById(R.id.rv_view_profile_charlist);
        this.characterListAdapter = new CharacterListAdapter(this.charList);

        this.char_recycler_view.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        this.char_recycler_view.setAdapter(this.characterListAdapter);
    }

//    private void getImageResources() {
//        Field[] fields = R.drawable.class.getDeclaredFields();
//
//        try {
//            for (int i = 0; i < fields.length; i++) {
//                String filename = fields[i].getName();
//                for (int j = 0; j < this.userown_list.size(); j++){
//                    if (!filename.contains("_") && !filename.contains("background") && filename.contains(userown_list.get(j))) //remove filenames with _
//                        this.charList.add(fields[i].getInt(null));
//                }
//
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void getUserOwnedCharacters(){
        userown_list.add("ayaka");
        userown_list.add("childe");
        userown_list.add("fischl");
        userown_list.add("keqing");
        userown_list.add("noelle");
        userown_list.add("barbara");
        userown_list.add("zhongli");
    }
}