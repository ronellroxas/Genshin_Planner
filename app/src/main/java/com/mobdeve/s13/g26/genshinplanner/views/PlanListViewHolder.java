package com.mobdeve.s13.g26.genshinplanner.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.ItemListAdapter;

import org.jetbrains.annotations.NotNull;

public class PlanListViewHolder extends RecyclerView.ViewHolder {
    private ConstraintLayout cl_plan_template;
    private ImageView iv_plan_img;
    private TextView tv_plan_title;
    private TextView tv_plan_creator;
    private TextView tv_plan_uid;
    private TextView tv_plan_resin;
    private RatingBar rb_start_rate;
    private RecyclerView rv_data_list_plan;
    public PlanListViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        this.cl_plan_template = itemView.findViewById(R.id.cl_plan_template);
        this.iv_plan_img = itemView.findViewById(R.id.iv_list_plan_character);
        this.tv_plan_title = itemView.findViewById(R.id.tv_list_plan_title);
        this.tv_plan_creator = itemView.findViewById(R.id.tv_list_plan_user);
        this.tv_plan_uid = itemView.findViewById(R.id.tv_list_plan_uid);
        this.tv_plan_resin = itemView.findViewById(R.id.tv_list_plan_resin);
        this.rb_start_rate = itemView.findViewById(R.id.rb_list_plan_rating);
        this.rv_data_list_plan = itemView.findViewById(R.id.rv_data_list_plan);
    }

    public void setImagePlan(int image){
        iv_plan_img.setImageResource(image);
    }

    public void setTitlePlan(String text){
        tv_plan_title.setText(text);
    }

    public void setCreatorPlan(String text){
        tv_plan_creator.setText(text);
    }

    public void setUIDPlan(String text){
        tv_plan_uid.setText(text);
    }

    public void setResinPlan(String text){
        tv_plan_resin.setText(text);
    }

    public void setRatingPlan(float rating){
        rb_start_rate.setRating(rating);
    }

    public void setDataListLayoutManager(RecyclerView.LayoutManager lm){
        rv_data_list_plan.setLayoutManager(lm);
        rv_data_list_plan.setHasFixedSize(true);
    }

    public void setDataListAdapter(ItemListAdapter ila){
        rv_data_list_plan.setAdapter(ila);
    }

    public void setListeners(View.OnClickListener ocl){
        cl_plan_template.setOnClickListener(ocl);
    }
}
