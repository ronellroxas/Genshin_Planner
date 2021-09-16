package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.keys.UserKeys;
import com.mobdeve.s13.g26.genshinplanner.models.User;
import com.mobdeve.s13.g26.genshinplanner.utils.FirebaseUserDBHelper;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseUserDBHelper userDBHelper;
    private Button btnSaveProfile;
    private Button btnDeleteProfile;
    private SharedPreferences sp;

    private EditText etEmail;
    private EditText etName;
    private EditText etUsername;
    private EditText etUid;
    private Spinner spinnerMain;

    //spinner adapter
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        this.userDBHelper = new FirebaseUserDBHelper();
        this.sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        initializeFields();
        initializeButtons();
    }

    private void initializeFields() {
        this.etEmail = findViewById(R.id.et_register_emailaddress);
        this.etName = findViewById(R.id.et_register_fullname);
        this.etUsername = findViewById(R.id.et_register_username);
        this.etUid = findViewById(R.id.et_register_uid);
        this.spinnerMain = findViewById(R.id.spinner_register_main);

        //initialize fields based from login
        etEmail.setText(sp.getString(UserKeys.EMAIL_KEY.name(), null));
        etName.setText(sp.getString(UserKeys.NAME_KEY.name(), null));

        //if editing profile only, can initialize these fields
        String username = sp.getString(UserKeys.USERNAME_KEY.name(), null);
        String uid = sp.getString(UserKeys.UID_KEY.name(), null);
        String main = sp.getString(UserKeys.MAIN_KEY.name(), null);

        //Disable editing of email
        etEmail.setFocusable(false);
        etEmail.setClickable(false);

        //initialize spinner content
        initSpinnerContent();

        if(username != null && uid != null && main != null) {
            etUsername.setText(username);
            etUid.setText(uid);
            spinnerMain.setSelection(adapter.getPosition(main));
        }
    }

    private void initializeButtons() {
        this.btnSaveProfile = findViewById(R.id.btn_register_saveprofile);
        this.btnDeleteProfile = findViewById(R.id.btn_register_delete);
        btnSaveProfile.setOnClickListener(v -> {
            if (isFormFilled()) {
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);

                saveUser();
                finish();
                startActivity(intent);
            } else {
                Toast.makeText(RegisterActivity.this, "Please fill-up empty fields.", Toast.LENGTH_SHORT).show();
            }
        });

        //if editing user only
        if(sp.getString(UserKeys.USERNAME_KEY.name(), null) != null) {
            btnDeleteProfile.setVisibility(View.VISIBLE);

            btnDeleteProfile.setOnClickListener(v -> {
               new AlertDialog.Builder(RegisterActivity.this)
                       .setTitle("Delete Account")
                       .setMessage("Are you sure you want to PERMANENTLY delete your account?")
                       .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               FirebaseUserDBHelper userDBHelper = new FirebaseUserDBHelper();
                               userDBHelper.deleteAccount(sp.getString(UserKeys.EMAIL_KEY.name(), null),
                                                          sp.getString(UserKeys.ID_KEY.name(), null));

                               Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                               sp.edit().clear().commit(); //clear preferences
                               Toast.makeText(RegisterActivity.this,
                                       "Account deleted!",
                                       Toast.LENGTH_SHORT).show();
                               startActivity(intent);
                           }
                       })
                       .setNegativeButton(android.R.string.no, null)
                       .setIcon(android.R.drawable.stat_sys_warning)
                       .show();
            });
        }

    }

    private Boolean isFormFilled() {
        return  !this.etEmail.getText().toString().isEmpty() &&
                !this.etName.getText().toString().isEmpty() &&
                !this.etUsername.getText().toString().isEmpty() &&
                !this.etUid.getText().toString().isEmpty() &&
                !this.spinnerMain.getSelectedItem().toString().equalsIgnoreCase("select character");
    }

    /**
     * Get current list of characters based on images
     */
    private void initSpinnerContent() {
        Field[] fields = R.drawable.class.getDeclaredFields();  //get filenames
        ArrayList<String> characters = new ArrayList<>();

        characters.add("Select Character");
        try {
            for (int i = 0; i < fields.length; i++) {
                String filename = fields[i].getName();

                if (filename.contains("characters_")) {     //filter characters then add name
                    String charName = filename.substring(filename.indexOf("_") + 1);
                    if(charName.contains("_")) {            //for traveler_geo and traveler_anemo
                        charName =  charName.replace('_', ' ');
                        charName =  charName.substring(0, charName.indexOf(' ')) + " " +
                                    charName.substring(charName.indexOf(' ') + 1, charName.indexOf(' ') + 2).toUpperCase() +
                                    charName.substring(charName.indexOf(' ') + 2);
                    }

                    characters.add(charName.substring(0, 1).toUpperCase() + charName.substring(1)); //save as uppercase first letter
                }
            }

            String[] charList = new String[characters.size()];
            charList = characters.toArray(charList);
            adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, charList);
            spinnerMain.setAdapter(adapter);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveUser() {
        //fix optional uid
        String uid = etUid.getText().toString();
        if (uid.isEmpty())
            uid = null;

        User user = new User(sp.getString(UserKeys.ID_KEY.name(), null),
                            etEmail.getText().toString(),
                            etName.getText().toString(),
                            etUsername.getText().toString(),
                            uid,
                            spinnerMain.getSelectedItem().toString());

        if(user.getUserId() == null) {
            Toast.makeText(this, "Error creating user.",Toast.LENGTH_SHORT).show();
        }

        //add user to db
        userDBHelper.addUser(user);

        //add user to SharedPreferences
        SharedPreferences.Editor spEditor = this.sp.edit();
        spEditor.putString(UserKeys.USERNAME_KEY.name(), user.getUsername());
        spEditor.putString(UserKeys.UID_KEY.name(), user.getUid());
        spEditor.putString(UserKeys.MAIN_KEY.name(), user.getMain());
        spEditor.apply();
    }
}