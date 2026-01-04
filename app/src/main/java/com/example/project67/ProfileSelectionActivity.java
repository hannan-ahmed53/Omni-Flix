package com.example.project67;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project67.adapter.ProfileAdapter;
import com.example.project67.data.AppDatabase;
import com.example.project67.manager.ProfileManager;
import com.example.project67.model.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    private ProfileManager profileManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selection);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        db = AppDatabase.getDatabase(this);
        profileManager = new ProfileManager(this);

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
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            // User not logged in, clear profiles
            runOnUiThread(() -> {
                if (adapter != null) {
                    adapter = new ProfileAdapter(new java.util.ArrayList<>(), this, this);
                    recyclerView.setAdapter(adapter);
                }
            });
            return;
        }
        
        String userId = user.getUid();
        executor.execute(() -> {
            profiles = db.profileDao().getProfilesByUserId(userId);
            runOnUiThread(() -> {
                if (profiles != null) {
                    adapter = new ProfileAdapter(profiles, this, this);
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter = new ProfileAdapter(new java.util.ArrayList<>(), this, this);
                    recyclerView.setAdapter(adapter);
                }
            });
        });
    }

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri = null;
    private String selectedImageName = "guts"; // Default image
    private AlertDialog currentDialog;

    private void showAddProfileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create New Profile");

        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_profile, null);
        EditText nameInput = dialogView.findViewById(R.id.profile_name_input);
        EditText passwordInput = dialogView.findViewById(R.id.profile_password_input);
        ImageView imagePreview = dialogView.findViewById(R.id.profile_image_preview);
        Button selectImageButton = dialogView.findViewById(R.id.select_image_button);
        
        // Load default image
        int defaultImageRes = getResources().getIdentifier("guts", "drawable", getPackageName());
        if (defaultImageRes != 0) {
            Glide.with(this).load(defaultImageRes).into(imagePreview);
        }
        
        selectedImageUri = null;
        selectedImageName = "guts";

        selectImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        builder.setView(dialogView);
        builder.setPositiveButton("Create", null); // Set to null to prevent auto-dismiss
        builder.setNegativeButton("Cancel", null);
        
        currentDialog = builder.create();
        currentDialog.show();
        
        // Override positive button to prevent auto-dismiss
        currentDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            if (!name.isEmpty()) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    String userId = user.getUid();
                    Profile newProfile = new Profile(name, selectedImageName, password, userId);
                    executor.execute(() -> {
                        db.profileDao().insert(newProfile);
                        runOnUiThread(() -> {
                            loadProfiles(findViewById(R.id.profiles_recycler_view));
                            currentDialog.dismiss();
                        });
                    });
                } else {
                    Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter a profile name", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            if (selectedImageUri != null && currentDialog != null) {
                ImageView imagePreview = currentDialog.findViewById(R.id.profile_image_preview);
                if (imagePreview != null) {
                    Glide.with(this).load(selectedImageUri).into(imagePreview);
                    // Store URI as string for later use, or use default name
                    selectedImageName = selectedImageUri.toString();
                }
            }
        }
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
                    // Save selected profile
                    profileManager.setSelectedProfile(profile.name, profile.id);
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    android.widget.Toast.makeText(this, "Incorrect password", android.widget.Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();
        } else {
            // No password, allow access
            // Save selected profile
            profileManager.setSelectedProfile(profile.name, profile.id);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
