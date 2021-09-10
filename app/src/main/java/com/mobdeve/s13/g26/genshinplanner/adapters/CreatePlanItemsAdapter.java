package com.mobdeve.s13.g26.genshinplanner.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.models.Item;
import com.mobdeve.s13.g26.genshinplanner.views.CreatePlanRoutesViewHolder;
import com.mobdeve.s13.g26.genshinplanner.views.ItemListViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CreatePlanItemsAdapter extends RecyclerView.Adapter<ItemListViewHolder> {

    private ArrayList<Item> items;
    private ItemListViewHolder itemListViewHolder;
    public CreatePlanItemsAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @NonNull
    @NotNull
    @Override
    public ItemListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.data_list_item, parent, false);
        itemListViewHolder = new ItemListViewHolder(itemView);

        itemListViewHolder.setListeners(v -> {
            int index = itemListViewHolder.getBindingAdapterPosition();
            removeItem(index);
        });

        return itemListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemListViewHolder holder, int position) {
        holder.setImageCharacter(items.get(position).getItem_img());
    }

    public void addItem() {
        notifyItemInserted(items.size());
        notifyItemRangeChanged(0, items.size());
    }

    private void removeItem(int index) {
        if(index >= 0)
            items.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, items.size());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
