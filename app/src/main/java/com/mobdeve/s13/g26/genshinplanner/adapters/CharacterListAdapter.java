package com.mobdeve.s13.g26.genshinplanner.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.activities.ViewCharacterActivity;
import com.mobdeve.s13.g26.genshinplanner.keys.CharacterKeys;
import com.mobdeve.s13.g26.genshinplanner.models.Character;
import com.mobdeve.s13.g26.genshinplanner.views.CharacterListViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListViewHolder> {


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

                intent.putExtra(CharacterKeys.KEY_PICTURE.name(), curr_char.getChar_image());
                intent.putExtra(CharacterKeys.KEY_NAME.name(), curr_char.getChar_name());
                intent.putExtra(CharacterKeys.KEY_WEAP.name(), weap);
                intent.putExtra(CharacterKeys.KEY_ELEMENT.name(), element);
                intent.putExtra(CharacterKeys.KEY_RARITY.name(), rarity);

                intent.putExtra(CharacterKeys.KEY_ELE_MAT.name(), curr_char.getAscension_mats().get(0));
                intent.putExtra(CharacterKeys.KEY_BOSS_MAT.name(), curr_char.getAscension_mats().get(1));
                intent.putExtra(CharacterKeys.KEY_WORLD_MAT.name(), curr_char.getAscension_mats().get(2));
                intent.putExtra(CharacterKeys.KEY_MOB_MAT.name(), curr_char.getAscension_mats().get(3));

                intent.putExtra(CharacterKeys.KEY_TAL_MAT.name(), curr_char.getTalent_mats().get(0));
                intent.putExtra(CharacterKeys.KEY_WEEK_MAT.name(), curr_char.getTalent_mats().get(1));
                intent.putExtra(CharacterKeys.KEY_CROWN_MAT.name(), curr_char.getTalent_mats().get(2));
                intent.putExtra(CharacterKeys.KEY_TAL_MOB_MAT.name(), curr_char.getTalent_mats().get(3));

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
