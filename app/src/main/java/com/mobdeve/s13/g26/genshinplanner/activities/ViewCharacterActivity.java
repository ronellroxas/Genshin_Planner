package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.CharacterListAdapter;
import com.mobdeve.s13.g26.genshinplanner.models.Character;

public class ViewCharacterActivity extends AppCompatActivity {
    private ImageView iv_Character_img;
    private TextView tv_Character_name;
    private TextView tv_Character_weap;
    private TextView tv_Character_element;
    private TextView tv_Character_rarity;
    private ImageView iv_elemat, iv_bossmat, iv_worldmat, iv_mobmat;
    private ImageView iv_talmat, iv_weekmat, iv_crown, iv_tal_mobmat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_character);

        this.iv_Character_img = findViewById(R.id.iv_char_picture);
        this.tv_Character_name = findViewById(R.id.tv_view_char_name);
        this.tv_Character_weap = findViewById(R.id.tv_view_char_weap);
        this.tv_Character_element = findViewById(R.id.tv_view_char_element);
        this.tv_Character_rarity = findViewById(R.id.tv_view_char_rarity);

        this.iv_elemat = findViewById(R.id.iv_view_char_ele_mat);
        this.iv_bossmat = findViewById(R.id.iv_view_char_boss_mat);
        this.iv_worldmat = findViewById(R.id.iv_view_char_world_mat);
        this.iv_mobmat =findViewById(R.id.iv_view_char_mob_mat);

        this.iv_talmat = findViewById(R.id.iv_view_char_tal_mat);
        this.iv_weekmat = findViewById(R.id.iv_view_char_week_mat);
        this.iv_crown = findViewById(R.id.iv_view_char_crown);
        this.iv_tal_mobmat = findViewById(R.id.iv_view_char_tal_mob_mat);

        Intent intent = getIntent();
        this.iv_Character_img.setImageResource(intent.getIntExtra(CharacterListAdapter.KEY_PICTURE, 0));
        this.tv_Character_name.setText(intent.getStringExtra(CharacterListAdapter.KEY_NAME));
        this.tv_Character_weap.setText(intent.getStringExtra(CharacterListAdapter.KEY_WEAP));
        this.tv_Character_element.setText(intent.getStringExtra(CharacterListAdapter.KEY_ELEMENT));
        this.tv_Character_rarity.setText(intent.getStringExtra(CharacterListAdapter.KEY_RARITY));

        this.iv_elemat.setImageResource(intent.getIntExtra(CharacterListAdapter.KEY_ELE_MAT, 0));
        this.iv_bossmat.setImageResource(intent.getIntExtra(CharacterListAdapter.KEY_BOSS_MAT, 0));
        this.iv_worldmat.setImageResource(intent.getIntExtra(CharacterListAdapter.KEY_WORLD_MAT, 0));
        this.iv_mobmat.setImageResource(intent.getIntExtra(CharacterListAdapter.KEY_MOB_MAT, 0));

        this.iv_talmat.setImageResource(intent.getIntExtra(CharacterListAdapter.KEY_TAL_MAT, 0));
        this.iv_weekmat.setImageResource(intent.getIntExtra(CharacterListAdapter.KEY_WEEK_MAT, 0));
        this.iv_crown.setImageResource(intent.getIntExtra(CharacterListAdapter.KEY_CROWN_MAT, 0));
        this.iv_tal_mobmat.setImageResource(intent.getIntExtra(CharacterListAdapter.KEY_TAL_MOB_MAT, 0));
    }
}