package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.*;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.keys.UserKeys;
import com.mobdeve.s13.g26.genshinplanner.models.User;
import com.mobdeve.s13.g26.genshinplanner.utils.FirebaseUserDBHelper;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton btnGoogle;
    private FirebaseAuth mAuth;
    private SharedPreferences sp;
    private FirebaseUserDBHelper userdbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userdbHelper = new FirebaseUserDBHelper();

        initializeGoogleAuth();
        mAuth = FirebaseAuth.getInstance();

        this.btnGoogle = findViewById(R.id.btn_main_google);
        btnGoogle.setOnClickListener(v -> {
            SignIn();
            //authSuccess();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            authSuccess(currentUser);
        }

    }

    /**
     * Initializes google auth stuff
     */
    private void initializeGoogleAuth() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    /**
     * Initializes google one tap sign in
     */
    private void SignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 1);
    }

    private void authSuccess(FirebaseUser user) {
        userdbHelper.getReference().orderByChild("email").equalTo(user.getEmail()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                Intent intent = null;

                if(snapshot.exists()) {
                    User user = snapshot.getValue(User.class);

                    //add user data to SharedPreferences
                    SharedPreferences.Editor spEditor = sp.edit();
                    spEditor.putString(UserKeys.USERNAME_KEY.name(), user.getUsername());
                    spEditor.putString(UserKeys.UID_KEY.name(), user.getUid());
                    spEditor.putString(UserKeys.MAIN_KEY.name(), user.getMain());
                    spEditor.apply();

                    intent = new Intent(MainActivity.this, HomeActivity.class);
                }
                else {
                    intent = new Intent(MainActivity.this, RegisterActivity.class);
                }
                startActivity(intent);
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEditor = sp.edit();
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);

        spEditor.putString(UserKeys.ID_KEY.name(), user.getUid());
        spEditor.putString(UserKeys.EMAIL_KEY.name(), user.getEmail());
        spEditor.putString(UserKeys.NAME_KEY.name(), user.getDisplayName());
        spEditor.apply();

        startActivity(intent);
    }

    private void createUser(FirebaseUser user) {
        String email = user.getEmail();
        mAuth.createUserWithEmailAndPassword(email, "NONE")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("AUTH", "createUserWithEmail:success");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FAIL", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("AUTH:", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately

                Toast.makeText(MainActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("GAUTH:", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            authSuccess(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("GAUTH:", "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }
}