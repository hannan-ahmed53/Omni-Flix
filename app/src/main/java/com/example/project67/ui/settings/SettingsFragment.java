package com.example.project67.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.project67.LoginActivity;
import com.example.project67.R;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsFragment extends Fragment {

    private TextView firstNameText;
    private TextView lastNameText;
    private TextView emailText;
    private Button editProfileButton;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        firstNameText = view.findViewById(R.id.first_name_text);
        lastNameText = view.findViewById(R.id.last_name_text);
        emailText = view.findViewById(R.id.email_text);
        editProfileButton = view.findViewById(R.id.edit_profile_button);

        loadUserProfile();

        editProfileButton.setOnClickListener(v -> showEditProfileDialog());

        SwitchMaterial darkModeSwitch = view.findViewById(R.id.dark_mode_switch);
        darkModeSwitch.setChecked(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES);
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        Button logoutButton = view.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(v -> {
            // Clear selected profile
            com.example.project67.manager.ProfileManager profileManager = 
                    new com.example.project67.manager.ProfileManager(requireContext());
            profileManager.clearSelectedProfile();
            
            mAuth.signOut();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            getActivity().finish();
        });

        return view;
    }

    private void loadUserProfile() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userEmail = user.getEmail();
            if (userEmail != null) {
                emailText.setText(userEmail);
            } else {
                emailText.setText("Email not available");
            }
            
            // Ensure we're using the authenticated user's UID
            String uid = user.getUid();
            if (uid == null || uid.isEmpty()) {
                Toast.makeText(getContext(), "User not authenticated properly", Toast.LENGTH_SHORT).show();
                firstNameText.setText("Not authenticated");
                lastNameText.setText("Not authenticated");
                return;
            }
            
            // Use the correct path: /users/{uid}
            DatabaseReference userRef = mDatabase.child("users").child(uid);
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String firstName = snapshot.child("firstName").getValue(String.class);
                        String lastName = snapshot.child("lastName").getValue(String.class);
                        
                        if (firstName != null && !firstName.isEmpty()) {
                            firstNameText.setText(firstName);
                        } else {
                            firstNameText.setText("Not set");
                        }
                        
                        if (lastName != null && !lastName.isEmpty()) {
                            lastNameText.setText(lastName);
                        } else {
                            lastNameText.setText("Not set");
                        }
                    } else {
                        // User data doesn't exist in database - this is normal for new users
                        firstNameText.setText("Not set");
                        lastNameText.setText("Not set");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    String errorMessage = error.getMessage();
                    if (errorMessage != null && errorMessage.contains("Permission denied")) {
                        Toast.makeText(getContext(), 
                                "Permission denied. Please check Firebase Database rules. " +
                                "Make sure rules allow authenticated users to access their own data.", 
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), 
                                "Failed to load profile: " + errorMessage, 
                                Toast.LENGTH_LONG).show();
                    }
                    firstNameText.setText("Error loading");
                    lastNameText.setText("Error loading");
                }
            });
        } else {
            // User not logged in
            firstNameText.setText("Not logged in");
            lastNameText.setText("Not logged in");
            emailText.setText("Not logged in");
        }
    }
    
    @Override
    public void onResume() {
        super.onResume();
        // Reload profile data when fragment becomes visible
        loadUserProfile();
    }

    private void showEditProfileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Edit Profile");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_profile, null);
        EditText firstNameEdit = dialogView.findViewById(R.id.edit_first_name);
        EditText lastNameEdit = dialogView.findViewById(R.id.edit_last_name);

        firstNameEdit.setText(firstNameText.getText().toString());
        lastNameEdit.setText(lastNameText.getText().toString());

        builder.setView(dialogView);
        builder.setPositiveButton("Save", (dialog, which) -> {
            String firstName = firstNameEdit.getText().toString().trim();
            String lastName = lastNameEdit.getText().toString().trim();

            if (!firstName.isEmpty() && !lastName.isEmpty()) {
                updateUserProfile(firstName, lastName);
            } else {
                Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void updateUserProfile(String firstName, String lastName) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            if (uid == null || uid.isEmpty()) {
                Toast.makeText(getContext(), "User not authenticated properly", Toast.LENGTH_SHORT).show();
                return;
            }
            
            // Use the correct path: /users/{uid}
            DatabaseReference userRef = mDatabase.child("users").child(uid);
            userRef.child("firstName").setValue(firstName);
            userRef.child("lastName").setValue(lastName)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            firstNameText.setText(firstName);
                            lastNameText.setText(lastName);
                            Toast.makeText(getContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            String errorMsg = task.getException() != null ? 
                                    task.getException().getMessage() : "Unknown error";
                            if (errorMsg.contains("Permission denied")) {
                                Toast.makeText(getContext(), 
                                        "Permission denied. Please check Firebase Database rules.", 
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getContext(), 
                                        "Failed to update profile: " + errorMsg, 
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(getContext(), "User not logged in", Toast.LENGTH_SHORT).show();
        }
    }
}
