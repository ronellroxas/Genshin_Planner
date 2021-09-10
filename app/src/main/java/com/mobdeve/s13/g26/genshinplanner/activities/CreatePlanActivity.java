package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.UnsupportedSchemeException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.CreatePlanItemsAdapter;
import com.mobdeve.s13.g26.genshinplanner.adapters.CreatePlanRoutesAdapter;
import com.mobdeve.s13.g26.genshinplanner.adapters.CustomSpinnerImageAdapter;
import com.mobdeve.s13.g26.genshinplanner.keys.UserKeys;
import com.mobdeve.s13.g26.genshinplanner.models.Item;
import com.mobdeve.s13.g26.genshinplanner.models.Plan;
import com.mobdeve.s13.g26.genshinplanner.models.User;
import com.mobdeve.s13.g26.genshinplanner.utils.AssetsHelper;
import com.mobdeve.s13.g26.genshinplanner.utils.FirebasePlanDBHelper;
import com.mobdeve.s13.g26.genshinplanner.views.CreatePlanViewHolder;

import org.json.JSONException;

import java.util.ArrayList;

public class CreatePlanActivity extends AppCompatActivity {

    //RecyclerView cycle variables
    private CreatePlanRoutesAdapter createPlanRoutesAdapter;
    private CreatePlanItemsAdapter createPlanItemsAdapter;
    private RecyclerView rvRoutes;
    private RecyclerView rvItems;
    private ArrayList<String> routes;
    private ArrayList<Item> items;

    //Create Plan components
    private Spinner spinnerAddRoute;
    private Spinner spinnerAddItem;
    private CreatePlanViewHolder createPlanViewHolder;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        this.sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.createPlanViewHolder = new CreatePlanViewHolder(this);
        initButtons();

        initRecyclerAdapter();
        initRouteSpinner();
        try {
            initItemsSpinner();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initRecyclerAdapter() {
        this.routes = new ArrayList<>();
        this.items = new ArrayList<>();

        this.rvRoutes = findViewById(R.id.rv_create_plan_routes);
        this.rvItems = findViewById(R.id.rv_create_plan_items);

        this.createPlanRoutesAdapter = new CreatePlanRoutesAdapter(routes);
        this.createPlanItemsAdapter = new CreatePlanItemsAdapter(items);

        this.rvRoutes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rvRoutes.setAdapter(this.createPlanRoutesAdapter);

        this.rvItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        this.rvItems.setAdapter(this.createPlanItemsAdapter);
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


    private void initItemsSpinner() throws JSONException {
        this.spinnerAddItem = findViewById(R.id.spinner_create_plan_items);

        AssetsHelper assetsHelper = new AssetsHelper(CreatePlanActivity.this);
        ArrayList<Item> tempItemList = assetsHelper.getItemAssets();

        //get image ids
        Integer[] imageIds = new Integer[tempItemList.size()];
        //imageIds[0] = -1;
        for(int i = 0; i < tempItemList.size(); i++) {
            imageIds[i] = tempItemList.get(i).getItem_img();
        }

        CustomSpinnerImageAdapter adapter = new CustomSpinnerImageAdapter(this, imageIds);
        this.spinnerAddItem.setAdapter(adapter);

        //attach listener
        this.spinnerAddItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Boolean start = true;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!start){
                    ImageView ivView = (ImageView) view;
                    ivView.setVisibility(View.GONE);


                    int selected = spinnerAddItem.getSelectedItemPosition();
                    if (selected != -1) {
                        items.add(tempItemList.get(selected));
                        createPlanItemsAdapter.addItem();
                    }
                }
                start = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initButtons() {
        createPlanViewHolder.setSubmitListener(v -> {
            String title = createPlanViewHolder.getName();
            String desc = createPlanViewHolder.getDescription();
            String resin = createPlanViewHolder.getResin();

            if(!title.isEmpty() && !desc.isEmpty() && !resin.isEmpty() && items.size() > 0 && routes.size() > 0) {

                //rebuild user
                String userId = sp.getString(UserKeys.ID_KEY.name(), "Error");
                String email = sp.getString(UserKeys.EMAIL_KEY.name(), "Error");
                String name = sp.getString(UserKeys.NAME_KEY.name(), "Error");
                String username = sp.getString(UserKeys.USERNAME_KEY.name(), "Error");
                String uid = sp.getString(UserKeys.UID_KEY.name(), "Error");
                String main = sp.getString(UserKeys.MAIN_KEY.name(), "Error");

                User user = new User(userId, email, name, username, uid, main);

                FirebasePlanDBHelper dbHelper = new FirebasePlanDBHelper();
                dbHelper.addPlan(new Plan(user, title, desc, items, routes, Integer.parseInt(resin), 0));

                Intent intent = new Intent(CreatePlanActivity.this, ViewPlanActivity.class);
                finish();
                startActivity(intent);

            }
            else  {
                Toast.makeText(CreatePlanActivity.this, "Please fill-up empty fields.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}