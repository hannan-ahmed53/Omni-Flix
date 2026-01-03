package com.example.project67;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project67.adapter.ProfileAdapter;
import com.example.project67.data.AppDatabase;
import com.example.project67.model.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProfileSelectionActivity extends AppCompatActivity implements ProfileAdapter.OnProfileClickListener, ProfileAdapter.OnProfileDeleteListener {

    private AppDatabase db;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private ProfileAdapter adapter;
    private List<Profile> profiles;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView userNameText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selection);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        db = AppDatabase.getDatabase(this);

        userNameText = findViewById(R.id.user_name_text);
        loadUserName();

        RecyclerView recyclerView = findViewById(R.id.profiles_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        Button addProfileButton = findViewById(R.id.add_profile_button);
        addProfileButton.setOnClickListener(v -> showAddProfileDialog());

        loadProfiles(recyclerView);
    }
    
    private void loadUserName() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            mDatabase.child("users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String firstName = snapshot.child("firstName").getValue(String.class);
                        String lastName = snapshot.child("lastName").getValue(String.class);
                        if (firstName != null && lastName != null) {
                            userNameText.setText(firstName + " " + lastName);
                        } else if (firstName != null) {
                            userNameText.setText(firstName);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        }
    }

    private void loadProfiles(RecyclerView recyclerView) {
        executor.execute(() -> {
            profiles = db.profileDao().getAllProfiles();
            runOnUiThread(() -> {
                if (profiles != null) {
                    adapter = new ProfileAdapter(profiles, this, this);
                    recyclerView.setAdapter(adapter);
                }
            });
        });
    }

    private void showAddProfileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Profile");

        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_profile, null);
        EditText nameInput = dialogView.findViewById(R.id.profile_name_input);
        EditText passwordInput = dialogView.findViewById(R.id.profile_password_input);
        passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        builder.setView(dialogView);
        builder.setPositiveButton("Create", (dialog, which) -> {
            String name = nameInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            if (!name.isEmpty()) {
                Profile newProfile = new Profile(name, "guts", password);
                executor.execute(() -> {
                    db.profileDao().insert(newProfile);
                    loadProfiles(findViewById(R.id.profiles_recycler_view));
                });
            } else {
                Toast.makeText(this, "Please enter a profile name", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
    
    @Override
    public void onProfileDelete(Profile profile) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove Profile");
        builder.setMessage("Enter password to remove this profile");

        final EditText passwordInput = new EditText(this);
        passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordInput.setHint("Password");
        builder.setView(passwordInput);

        builder.setPositiveButton("Remove", (dialog, which) -> {
            String enteredPassword = passwordInput.getText().toString();
            if (profile.password != null && profile.password.equals(enteredPassword)) {
                executor.execute(() -> {
                    db.profileDao().delete(profile);
                    runOnUiThread(() -> {
                        loadProfiles(findViewById(R.id.profiles_recycler_view));
                        Toast.makeText(this, "Profile removed", Toast.LENGTH_SHORT).show();
                    });
                });
            } else {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    @Override
    public void onProfileClick(Profile profile) {
        // Check if profile has password
        if (profile.password != null && !profile.password.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter Profile Password");
            
            final EditText passwordInput = new EditText(this);
            passwordInput.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordInput.setHint("Password");
            builder.setView(passwordInput);
            
            builder.setPositiveButton("Enter", (dialog, which) -> {
                String enteredPassword = passwordInput.getText().toString();
                if (profile.password.equals(enteredPassword)) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    android.widget.Toast.makeText(this, "Incorrect password", android.widget.Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();
        } else {
            // No password, allow access
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
