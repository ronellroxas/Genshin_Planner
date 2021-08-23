package com.mobdeve.s13.g26.genshinplanner.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.activities.ViewCharacterActivity;
import com.mobdeve.s13.g26.genshinplanner.models.Character;
import com.mobdeve.s13.g26.genshinplanner.views.CharacterListViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListViewHolder> {

    public static final String KEY_PICTURE = "KEY_PICTURE";
    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_WEAP = "KEY_WEAP";
    public static final String KEY_ELEMENT = "KEY_ELEMENT";
    public static final String KEY_RARITY = "KEY_RARITY";

    public static final String KEY_ELE_MAT = "KEY_ELE_MAT";
    public static final String KEY_BOSS_MAT = "KEY_BOSS_MAT";
    public static final String KEY_WORLD_MAT = "KEY_WORLD_MAT";
    public static final String KEY_MOB_MAT = "KEY_MOB_MAT";

    public static final String KEY_TAL_MAT = "KEY_TAL_MAT";
    public static final String KEY_WEEK_MAT = "KEY_WEEK_MAT";
    public static final String KEY_CROWN_MAT = "KEY_CROWN_MAT";
    public static final String KEY_TAL_MOB_MAT = "KEY_TAL_MOB_MAT";

    private ArrayList<Character> charList;

    public CharacterListAdapter(ArrayList<Character> ar) {
        this.charList = ar;
    }

    @NonNull
    @NotNull
    @Override
    public CharacterListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.data_list_character, parent, false);
        CharacterListViewHolder clViewHolder = new CharacterListViewHolder(itemView);

        clViewHolder.setListeners(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewCharacterActivity.class);
                Character curr_char = charList.get(clViewHolder.getBindingAdapterPosition());
                String weap = processWeap(curr_char.getWeap_type());
                String element = processEle(curr_char.getElement());
                String rarity = curr_char.getRarity() + " Stars";

                intent.putExtra(KEY_PICTURE, curr_char.getChar_image());
                intent.putExtra(KEY_NAME, curr_char.getChar_name());
                intent.putExtra(KEY_WEAP, weap);
                intent.putExtra(KEY_ELEMENT, element);
                intent.putExtra(KEY_RARITY, rarity);

                intent.putExtra(KEY_ELE_MAT, curr_char.getAscension_mats().get(0));
                intent.putExtra(KEY_BOSS_MAT, curr_char.getAscension_mats().get(1));
                intent.putExtra(KEY_WORLD_MAT, curr_char.getAscension_mats().get(2));
                intent.putExtra(KEY_MOB_MAT, curr_char.getAscension_mats().get(3));

                intent.putExtra(KEY_TAL_MAT, curr_char.getTalent_mats().get(0));
                intent.putExtra(KEY_WEEK_MAT, curr_char.getTalent_mats().get(1));
                intent.putExtra(KEY_CROWN_MAT, curr_char.getTalent_mats().get(2));
                intent.putExtra(KEY_TAL_MOB_MAT, curr_char.getTalent_mats().get(3));

                v.getContext().startActivity(intent);
            }

        });
        return clViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CharacterListViewHolder holder, int position) {
        holder.setImageCharacter(charList.get(position).getChar_image());
    }

    @Override
    public int getItemCount() {
        return charList.size();
    }


    public String processWeap(int numbereqv){
        String res = "";
        switch(numbereqv){
            case 1: res = "Polearm";break;
            case 2: res = "Sword";break;
            case 3: res = "Great Sword";break;
            case 4: res = "Bow";break;
            case 5: res = "Catalyst";break;
        }

        return res;
    }

    public String processEle(int numbereqv){
        String res = "";
        switch(numbereqv){
            case 1: res = "Electro";break;
            case 2: res = "Pyro";break;
            case 3: res = "Hydro";break;
            case 4: res = "Dendro";break;
            case 5: res = "Geo";break;
            case 6: res = "Cryo";break;
        }

        return res;
    }
}
