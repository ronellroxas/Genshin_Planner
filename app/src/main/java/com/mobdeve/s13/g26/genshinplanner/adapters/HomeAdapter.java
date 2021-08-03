package com.mobdeve.s13.g26.genshinplanner.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.views.HomeViewHolder;

import org.jetbrains.annotations.NotNull;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {


    @NonNull
    @NotNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.activity_home, parent, false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(itemView);

        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
