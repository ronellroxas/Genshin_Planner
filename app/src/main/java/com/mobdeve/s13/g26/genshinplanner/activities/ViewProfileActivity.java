package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.CharacterListAdapter;
import com.mobdeve.s13.g26.genshinplanner.keys.UserKeys;
import com.mobdeve.s13.g26.genshinplanner.models.Character;
import com.mobdeve.s13.g26.genshinplanner.models.Plan;
import com.mobdeve.s13.g26.genshinplanner.utils.FirebasePlanDBHelper;
import com.mobdeve.s13.g26.genshinplanner.utils.FirebaseUserDBHelper;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewProfileActivity extends AppCompatActivity {


    private CircleImageView civ_picture;
    private TextView tv_name;
    private TextView tv_uid;
    private TextView tv_email;
    private Button btnEditProfile;
    private Button btnSignout;
    private TextView tv_created_plans;
    private TextView tv_shared_plans;
    private TextView tv_saved_plans;
    private TextView tv_plan_button;

    private int created_plans = 0;
    private int shared_plans = 0;
    private int saved_plans = 0;

    private SharedPreferences sp;
    private FirebasePlanDBHelper planDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        this.sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.civ_picture = findViewById(R.id.iv_view_prof_picture);
        this.tv_name = findViewById(R.id.tv_view_prof_username);
        this.tv_uid = findViewById(R.id.tv_view_prof_uid);
        this.tv_email = findViewById(R.id.tv_view_prof_email);
        this.tv_created_plans = findViewById(R.id.tv_view_prof_created_plans);
        this.tv_shared_plans = findViewById(R.id.tv_view_prof_shared_plans);
        this.tv_saved_plans = findViewById(R.id.tv_view_prof_saved_plans);
        this.tv_plan_button = findViewById(R.id.tv_view_prof_all_plans);

        initializeDetails();
        initializeButtons();
    }

    private void initializeDetails(){
        planDBHelper = new FirebasePlanDBHelper();

        if(getImageResources(sp.getString(UserKeys.MAIN_KEY.name(),null)) == -1){
            this.civ_picture.setImageResource(R.drawable.baal);
        }else{
            this.civ_picture.setImageResource(getImageResources(sp.getString(UserKeys.MAIN_KEY.name(), null)));
        }
        this.tv_name.setText(sp.getString(UserKeys.USERNAME_KEY.name(), null));
        this.tv_uid.setText(sp.getString(UserKeys.UID_KEY.name(), null));
        this.tv_email.setText(sp.getString(UserKeys.EMAIL_KEY.name(), null));

        DatabaseReference dbref = planDBHelper.getReference();
        dbref.addValueEventListener(valueEventListener);

        DatabaseReference sharedbref = planDBHelper.getSharedReference();
        sharedbref.addValueEventListener(sharedEventListener);

    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
            if(snapshot.exists()){
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Plan curr_plan  = dataSnapshot.getValue(Plan.class);
                    if(curr_plan.getPlan_owner().getUid().contains(sp.getString(UserKeys.UID_KEY.name(), "none"))){
                        created_plans++;
                        shared_plans++;
                        saved_plans++;
                    }
                }
                String created_text = "" + created_plans;
                String shared_text = "" + shared_plans;
                String saved_text = "" + saved_plans;
                tv_created_plans.setText(created_text);
                tv_shared_plans.setText(shared_text);
                tv_saved_plans.setText(saved_text);
            }
        }

        @Override
        public void onCancelled(@NonNull @NotNull DatabaseError error) {

        }
    };

    ValueEventListener sharedEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
            for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                Plan curr_plan  = dataSnapshot.getValue(Plan.class);
                if(curr_plan.getPlan_owner().getUid().contains(sp.getString(UserKeys.UID_KEY.name(), "none"))){
                    shared_plans--;
                }
            }
            String shared_text = "" + shared_plans;
            tv_shared_plans.setText(shared_text);
        }

        @Override
        public void onCancelled(@NonNull @NotNull DatabaseError error) {

        }
    };

    private void initializeButtons() {
        this.btnEditProfile = findViewById(R.id.btn_view_prof_edit_prof);
        this.btnSignout = findViewById(R.id.btn_view_prof_signout);

        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(ViewProfileActivity.this, RegisterActivity.class);

            startActivity(intent);
        });

        btnSignout.setOnClickListener(v -> {
            Intent intent = new Intent(ViewProfileActivity.this, MainActivity.class);
            sp.edit().clear().commit(); //clear preferences
            FirebaseUserDBHelper dbHelper = new FirebaseUserDBHelper();
            dbHelper.logout();
            finish();
            startActivity(intent);
        });

        tv_plan_button.setOnClickListener(v ->{
            Intent intent = new Intent(ViewProfileActivity.this, PlanListActivity.class);

            startActivity(intent);
        });
    }


    public int getImageResources(String main) {
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

}