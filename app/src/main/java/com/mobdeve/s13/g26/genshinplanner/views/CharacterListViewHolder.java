package com.mobdeve.s13.g26.genshinplanner.views;

import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s13.g26.genshinplanner.R;

import org.jetbrains.annotations.NotNull;

public class CharacterListViewHolder extends RecyclerView.ViewHolder {

    private ImageButton ibCharacter;
    public CharacterListViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        this.ibCharacter = itemView.findViewById(R.id.ib_charlist_character);
    }

    public void setImageCharacter(int img) {
        this.ibCharacter.setImageResource(img);
    }
}
