package com.mobdeve.s13.g26.genshinplanner.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.models.Item;
import com.mobdeve.s13.g26.genshinplanner.views.CharacterListViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<CharacterListViewHolder> {

    public static final String KEY_PICTURE = "KEY_PICTURE";
    public static final String KEY_NAME = "KEY_NAME";


    private ArrayList<Item> itemList;

    public ItemListAdapter(ArrayList<Item> ar) {
        this.itemList = ar;
    }

    @NonNull
    @NotNull
    @Override
    public CharacterListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.data_list_character, parent, false);
        CharacterListViewHolder clViewHolder = new CharacterListViewHolder(itemView);



        return clViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CharacterListViewHolder holder, int position) {
        holder.setImageCharacter(itemList.get(position).getItem_img());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
