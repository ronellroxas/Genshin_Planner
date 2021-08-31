package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.CreatePlanRoutesAdapter;
import com.mobdeve.s13.g26.genshinplanner.models.Item;

import java.util.ArrayList;

public class CreatePlanActivity extends AppCompatActivity {

    //RecyclerView cycle variables
    private CreatePlanRoutesAdapter createPlanRoutesAdapter;
    private RecyclerView rvRoutes;
    private ArrayList<String> routes;
    private ArrayList<Item> items;

    //Create Plan components
    private Spinner spinnerAddRoute;
    private Spinner spinnerAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        initRecyclerAdapter();
        initRouteSpinner();
    }

    private void initRecyclerAdapter() {
        this.routes = new ArrayList<>();

        this.rvRoutes = findViewById(R.id.rv_create_plan_routes);
        this.createPlanRoutesAdapter = new CreatePlanRoutesAdapter(routes);


        this.rvRoutes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rvRoutes.setAdapter(this.createPlanRoutesAdapter);
    }

    /**
     * Currently temporary till Genshin API is used
     */
    private void initRouteSpinner() {
        this.spinnerAddRoute = findViewById(R.id.spinner_create_plan_routes);

        //load routes selection to spinner
        String[] arrSpinnerRoutes = {"", "Inazuma", "Liyue", "Monstadt", "Bahay ni Howard", "Bahay ni A.."};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, arrSpinnerRoutes);
        spinnerAddRoute.setAdapter(adapter);

        //attach listener
        this.spinnerAddRoute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tvView = (TextView)view;
                tvView.setText(null);

                String selected = spinnerAddRoute.getSelectedItem().toString();
                if(!selected.equals("")) {
                    routes.add(selected);
                    createPlanRoutesAdapter.addRoute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}