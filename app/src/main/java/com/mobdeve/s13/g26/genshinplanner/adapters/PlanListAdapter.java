package com.mobdeve.s13.g26.genshinplanner.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.views.PlanListViewHolder;

import org.jetbrains.annotations.NotNull;

public class PlanListAdapter extends RecyclerView.Adapter<PlanListViewHolder> {

    @NonNull
    @NotNull
    @Override
    public PlanListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.data_list_plan, parent, false);
        PlanListViewHolder holder = new PlanListViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PlanListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
