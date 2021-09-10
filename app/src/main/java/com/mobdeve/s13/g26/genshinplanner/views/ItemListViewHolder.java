package com.mobdeve.s13.g26.genshinplanner.views;

import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s13.g26.genshinplanner.R;

import org.jetbrains.annotations.NotNull;

public class ItemListViewHolder extends RecyclerView.ViewHolder {

    private ImageButton ibItem;
    public ItemListViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        this.ibItem = itemView.findViewById(R.id.ib_item);
    }

    public void setImageCharacter(int img) {
        this.ibItem.setImageResource(img);
    }

    public void setListeners(View.OnClickListener e){ this.ibItem.setOnClickListener(e);}
}
