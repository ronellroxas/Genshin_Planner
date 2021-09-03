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
import java.util.Arrays;

public class CreatePlanRoutesAdapter extends RecyclerView.Adapter<CreatePlanRoutesViewHolder> {

    private CreatePlanRoutesViewHolder routesViewHolder;
    private ArrayList<String> routes;

    public CreatePlanRoutesAdapter(ArrayList<String> routes) {
        this.routes = routes;
    }

    @NonNull
    @NotNull
    @Override
    public CreatePlanRoutesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.data_create_plan_route, parent, false);
        routesViewHolder = new CreatePlanRoutesViewHolder(itemView);

        routesViewHolder.addDeleteListener(v -> {
            int index = routesViewHolder.getBindingAdapterPosition();
            removeRoute(index);
        });

        return routesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CreatePlanRoutesViewHolder holder, int position) {
        holder.setRouteName(this.routes.get(position));
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }


    public void addRoute() {
        notifyItemInserted(routes.size());
        notifyItemRangeChanged(routes.size(), routes.size());
    }

    private void removeRoute(int index) {
        if(index >= 0)
            routes.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, routes.size());
    }
}
