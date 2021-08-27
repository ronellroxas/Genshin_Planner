package com.mobdeve.s13.g26.genshinplanner.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.activities.ViewItemActivity;
import com.mobdeve.s13.g26.genshinplanner.keys.ItemKeys;
import com.mobdeve.s13.g26.genshinplanner.models.Item;
import com.mobdeve.s13.g26.genshinplanner.views.CharacterListViewHolder;
import com.mobdeve.s13.g26.genshinplanner.views.ItemListViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListViewHolder> {

    private ArrayList<Item> itemList;

    public ItemListAdapter(ArrayList<Item> ar) {
        this.itemList = ar;
    }

    @NonNull
    @NotNull
    @Override
    public ItemListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.data_list_item, parent, false);
        ItemListViewHolder ilViewHolder = new ItemListViewHolder(itemView);

        ilViewHolder.setListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewItemActivity.class);
                Item curr_item = itemList.get(ilViewHolder.getBindingAdapterPosition());
                String type = "Type: ";
                String obtain = "";
                for(int i = 0; i < curr_item.getItem_types().size(); i++){
                    type = type + curr_item.getItem_types().get(i)+ ", ";
                }

                for(int j = 0; j < curr_item.getItem_obtain_ways().size(); j++){
                    obtain = obtain + "\u2022 " + curr_item.getItem_obtain_ways().get(j) + "\n";
                }

                intent.putExtra(ItemKeys.KEY_PICTURE.name(), curr_item.getItem_img());
                intent.putExtra(ItemKeys.KEY_NAME.name(), curr_item.getItem_name());
                intent.putExtra(ItemKeys.KEY_TYPE.name(), type);
                intent.putExtra(ItemKeys.KEY_OBTAIN.name(), obtain);

                v.getContext().startActivity(intent);
            }
        });

        return ilViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemListViewHolder holder, int position) {
        holder.setImageCharacter(itemList.get(position).getItem_img());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
