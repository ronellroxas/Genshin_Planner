package com.mobdeve.s13.g26.genshinplanner.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.activities.CreatePlanActivity;
import com.mobdeve.s13.g26.genshinplanner.activities.PlanListActivity;
import com.mobdeve.s13.g26.genshinplanner.activities.SearchPlanActivity;
import com.mobdeve.s13.g26.genshinplanner.activities.ViewPlanActivity;
import com.mobdeve.s13.g26.genshinplanner.activities.ViewProfileActivity;
import com.mobdeve.s13.g26.genshinplanner.keys.PlanKeys;
import com.mobdeve.s13.g26.genshinplanner.models.Plan;
import com.mobdeve.s13.g26.genshinplanner.utils.FirebasePlanDBHelper;
import com.mobdeve.s13.g26.genshinplanner.views.ItemListViewHolder;
import com.mobdeve.s13.g26.genshinplanner.views.PlanListViewHolder;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class PlanListAdapter extends RecyclerView.Adapter<PlanListViewHolder> {

    private ArrayList<Plan> planArrayList;
    public Context cxt;
    private FirebasePlanDBHelper dbHelper;

    public PlanListAdapter(ArrayList<Plan> pl, Context cxt){
        this.planArrayList = pl;
        this.cxt = cxt;
        dbHelper = new FirebasePlanDBHelper();
    }

    @NonNull
    @NotNull
    @Override
    public PlanListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.data_list_plan, parent, false);
        PlanListViewHolder holder = new PlanListViewHolder(itemView);
        holder.setListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewPlanActivity.class);
                Plan curr_plan = planArrayList.get(holder.getBindingAdapterPosition());
                String resin = "Resin: " + curr_plan.getPlan_resin_spent();

                intent.putExtra(PlanKeys.PLAN_ID_KEY.name(), curr_plan.getPlan_id());
                intent.putExtra(PlanKeys.PLAN_IMAGE.name(),getImageResources(curr_plan.getPlan_owner().getMain()));
                intent.putExtra(PlanKeys.PLAN_TITLE_KEY.name(), curr_plan.getPlan_title());
                intent.putExtra(PlanKeys.PLAN_OWNER_NAME.name(), curr_plan.getPlan_owner().getUsername());
                intent.putExtra(PlanKeys.PLAN_OWNER_UID.name(), curr_plan.getPlan_owner().getUid());
                intent.putExtra(PlanKeys.PLAN_RESIN_KEY.name(), resin);
                intent.putExtra(PlanKeys.PLAN_DESCRIPTION_KEY.name(), curr_plan.getPlan_description());
                intent.putExtra(PlanKeys.PLAN_RATING_KEY.name(), curr_plan.getPlan_rating());

                v.getContext().startActivity(intent);
            }
        });

        initSpinnerOptions(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PlanListViewHolder holder, int position) {
        Plan curr_plan = planArrayList.get(position);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(cxt ,LinearLayoutManager.HORIZONTAL, false);
        holder.setDataListLayoutManager(layoutManager);
        if(getImageResources(curr_plan.getPlan_owner().getMain()) == -1){
            holder.setImagePlan(R.drawable.baal);
        }else{
            holder.setImagePlan(getImageResources(curr_plan.getPlan_owner().getMain()));
        }
        holder.setTitlePlan(curr_plan.getPlan_title());
        holder.setUIDPlan(curr_plan.getPlan_owner().getUid());
        holder.setRatingPlan(curr_plan.getPlan_rating());
        holder.setResinPlan("" + curr_plan.getPlan_resin_spent());
        holder.setCreatorPlan(curr_plan.getPlan_owner().getUsername());


        ItemListAdapter itemListAdapter = new ItemListAdapter(curr_plan.getPlan_items());
        holder.setDataListAdapter(itemListAdapter);
    }

    @Override
    public int getItemCount() {
        return planArrayList.size();
    }

    private int getImageResources(String main) {
        Field[] fields = R.drawable.class.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                String filename = fields[i].getName();

                if (filename.contains("characters_") && filename.contains(main.toLowerCase())) //remove filenames with _
                    return fields[i].getInt(null);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    private void initSpinnerOptions(PlanListViewHolder holder) {
        Spinner spinnerOptions = holder.getSpinnerOptions();

        String[] arrOptions = {"", "Edit Plan", "Share Plan", "Remove Plan"};

        if(holder.getBindingAdapterPosition() >= 0)
            isPlanShared(arrOptions, holder.getBindingAdapterPosition());

        //change options if viewing from searchPlan
        if(cxt instanceof SearchPlanActivity) {
            arrOptions = new String[]{"", "Save Plan"};
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(cxt, R.layout.support_simple_spinner_dropdown_item, arrOptions);
        spinnerOptions.setAdapter(adapter);

        //attach listener
        spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Boolean start = true;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tvView = (TextView)view;
                tvView.setText(null);

                if(!start) {
                    Plan curr_plan = planArrayList.get(holder.getBindingAdapterPosition());
                    Log.d("TAG", curr_plan.getPlan_id());
                    if(spinnerOptions.getSelectedItem().toString().equalsIgnoreCase("Edit Plan")) {
                        Intent intent = new Intent(cxt, CreatePlanActivity.class);

                        intent.putExtra(PlanKeys.PLAN_ID_KEY.name(), curr_plan.getPlan_id());
                        intent.putExtra(PlanKeys.PLAN_IMAGE.name(),getImageResources(curr_plan.getPlan_owner().getMain()));
                        intent.putExtra(PlanKeys.PLAN_TITLE_KEY.name(), curr_plan.getPlan_title());
                        intent.putExtra(PlanKeys.PLAN_OWNER_NAME.name(), curr_plan.getPlan_owner().getUsername());
                        intent.putExtra(PlanKeys.PLAN_OWNER_UID.name(), curr_plan.getPlan_owner().getUid());
                        intent.putExtra(PlanKeys.PLAN_RESIN_KEY.name(), curr_plan.getPlan_resin_spent());
                        intent.putExtra(PlanKeys.PLAN_DESCRIPTION_KEY.name(), curr_plan.getPlan_description());
                        intent.putExtra(PlanKeys.PLAN_RATING_KEY.name(), curr_plan.getPlan_rating());

                        cxt.startActivity(intent);
                    }
                    if(spinnerOptions.getSelectedItem().toString().equalsIgnoreCase("share plan")) {
                        dbHelper.sharePlan(curr_plan);
                    }
                    if(spinnerOptions.getSelectedItem().toString().equalsIgnoreCase("remove plan")) {
                        dbHelper.deleteSharedPlan(curr_plan.getPlan_id());
                    }
                    if(spinnerOptions.getSelectedItem().toString().equalsIgnoreCase("save plan")) {
                        dbHelper.addPlan(curr_plan);
                    }
                }
                start = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void isPlanShared(String[] arrOptions, int position) {
        dbHelper.getSharedReference().orderByValue().equalTo(planArrayList.get(position).getPlan_id()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Log.d("TEST:", snapshot.toString());
                arrOptions[2] = "Unshare Plan";
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}
