package com.example.project67;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project67.data.AppDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignUpActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button signUpButton;
    private TextView loginLink;
    
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private AppDatabase db;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        db = AppDatabase.getDatabase(this);

        firstNameEditText = findViewById(R.id.first_name);
        lastNameEditText = findViewById(R.id.last_name);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirm_password);
        signUpButton = findViewById(R.id.signup_button);
        loginLink = findViewById(R.id.login_link);

        signUpButton.setOnClickListener(v -> {
            String firstName = firstNameEditText.getText().toString().trim();
            String lastName = lastNameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            if (validateInput(firstName, lastName, email, password, confirmPassword)) {
                signUpUser(firstName, lastName, email, password);
            }
        });

        loginLink.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            finish();
        });
    }

    private boolean validateInput(String firstName, String lastName, String email, 
                                   String password, String confirmPassword) {
        if (TextUtils.isEmpty(firstName)) {
            firstNameEditText.setError("First name is required");
            return false;
        }
        if (TextUtils.isEmpty(lastName)) {
            lastNameEditText.setError("Last name is required");
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email is required");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Invalid email address");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required");
            return false;
        }
        if (password.length() < 6) {
            passwordEditText.setError("Password must be at least 6 characters");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    private void signUpUser(String firstName, String lastName, String email, String password) {
        signUpButton.setEnabled(false);
        Toast.makeText(this, "Creating account...", Toast.LENGTH_SHORT).show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    signUpButton.setEnabled(true);
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            String uid = user.getUid();
                            if (uid == null || uid.isEmpty()) {
                                Toast.makeText(SignUpActivity.this, 
                                        "Failed to get user ID", 
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }
                            
                            // Save user details to Firebase Realtime Database
                            // Path: /users/{uid}
                            Map<String, Object> userData = new HashMap<>();
                            userData.put("firstName", firstName);
                            userData.put("lastName", lastName);
                            userData.put("email", email);
                            
                            DatabaseReference userRef = mDatabase.child("users").child(uid);
                            userRef.setValue(userData)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(SignUpActivity.this, 
                                                    "Account created successfully!", 
                                                    Toast.LENGTH_SHORT).show();
                                            
                                            // Clear any old profile data for this user
                                            clearOldProfileData(uid);
                                            
                                            startActivity(new Intent(SignUpActivity.this, 
                                                    ProfileSelectionActivity.class));
                                            finish();
                                        } else {
                                            String errorMsg = task1.getException() != null ? 
                                                    task1.getException().getMessage() : "Failed to save user data";
                                            
                                            if (errorMsg.contains("Permission denied")) {
                                                Toast.makeText(SignUpActivity.this, 
                                                        "Permission denied. Please check Firebase Database rules. " +
                                                        "Rules should allow authenticated users to write to /users/{uid}", 
                                                        Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(SignUpActivity.this, 
                                                        "Failed to save user data: " + errorMsg, 
                                                        Toast.LENGTH_LONG).show();
                                            }
                                            
                                            // Delete the Firebase auth user if data save failed
                                            user.delete().addOnCompleteListener(task2 -> {
                                                if (task2.isSuccessful()) {
                                                    Toast.makeText(SignUpActivity.this, 
                                                            "Please try signing up again", 
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    });
                        }
                    } else {
                        String errorMessage = task.getException() != null ? 
                                task.getException().getMessage() : "Sign up failed";
                        Toast.makeText(SignUpActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    
    private void clearOldProfileData(String userId) {
        executor.execute(() -> {
            db.profileDao().deleteAllProfilesByUserId(userId);
        });
    }
}

