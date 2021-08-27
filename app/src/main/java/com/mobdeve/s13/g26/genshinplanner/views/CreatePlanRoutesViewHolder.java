package com.mobdeve.s13.g26.genshinplanner.views;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s13.g26.genshinplanner.R;

import org.jetbrains.annotations.NotNull;

public class CreatePlanRoutesViewHolder extends RecyclerView.ViewHolder {

    private TextView tvRouteName;
    private ImageButton ibDeleteButton;

    public CreatePlanRoutesViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        this.tvRouteName = itemView.findViewById(R.id.tv_create_plan_route_name);
        this.ibDeleteButton = itemView.findViewById(R.id.ib_create_plan_routes_delete);

    }

    public void addDeleteListener(View.OnClickListener listener) {
        if(this.ibDeleteButton != null) {
            this.ibDeleteButton.setOnClickListener(listener);
        }
    }

    public String getRouteName() {
        return this.tvRouteName.getText().toString();
    }

    public void setRouteName(String routeName) {
        this.tvRouteName.setText(routeName);
    }

}
