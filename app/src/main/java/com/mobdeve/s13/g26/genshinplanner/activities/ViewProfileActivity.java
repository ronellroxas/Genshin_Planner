package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.CharacterListAdapter;
import com.mobdeve.s13.g26.genshinplanner.keys.UserKeys;
import com.mobdeve.s13.g26.genshinplanner.models.Character;

import java.lang.reflect.Field;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewProfileActivity extends AppCompatActivity {

    private ArrayList<Character> charList;
    private ArrayList<String> userown_list;

    private CircleImageView civ_picture;
    private TextView tv_name;
    private TextView tv_uid;
    private TextView tv_email;

    private RecyclerView char_recycler_view;
    private CharacterListAdapter characterListAdapter;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        this.sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.civ_picture = findViewById(R.id.iv_view_prof_picture);
        this.tv_name = findViewById(R.id.tv_view_prof_username);
        this.tv_uid = findViewById(R.id.tv_view_prof_uid);
        this.tv_email = findViewById(R.id.tv_view_prof_email);

        charList = new ArrayList<Character>();
        userown_list = new ArrayList<String>();
        getUserOwnedCharacters();
        initializeDetails();
        //initRecyclerAdapter();
    }

    private void initializeDetails(){
        if(getImageResources(sp.getString(UserKeys.MAIN_KEY.name(),null)) == -1){
            this.civ_picture.setImageResource(R.drawable.baal);
        }else{
            this.civ_picture.setImageResource(getImageResources(sp.getString(UserKeys.MAIN_KEY.name(), null)));
        }
        this.tv_name.setText(sp.getString(UserKeys.USERNAME_KEY.name(), null));
        this.tv_uid.setText(sp.getString(UserKeys.UID_KEY.name(), null));
        this.tv_email.setText(sp.getString(UserKeys.EMAIL_KEY.name(), null));
    }

    private int getImageResources(String main) {
        Field[] fields = R.drawable.class.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                String filename = fields[i].getName();

                if (filename.contains("characters_") && filename.contains(main.toLowerCase())) //remove filenames with _
                    return fields[i].getInt(null);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
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