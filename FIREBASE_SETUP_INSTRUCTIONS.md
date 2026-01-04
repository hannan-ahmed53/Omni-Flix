# Firebase Realtime Database Setup Instructions

## Critical: Set Firebase Database Rules

To fix the "Permission denied" errors, you **MUST** set the correct Firebase Realtime Database rules.

### Step 1: Open Firebase Console

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Select your project
3. Click on **Realtime Database** in the left menu
4. Click on the **Rules** tab

### Step 2: Set the Rules

Replace the existing rules with the following:

```json
{
  "rules": {
    "users": {
      "$uid": {
        ".read": "auth != null && auth.uid === $uid",
        ".write": "auth != null && auth.uid === $uid"
      }
    }
  }
}
```

### Step 3: Publish the Rules

1. Click **Publish** button
2. Wait for confirmation that rules are published

### What These Rules Do

- **`.read`**: Allows authenticated users to read only their own data (where `auth.uid === $uid`)
- **`.write`**: Allows authenticated users to write only their own data (where `auth.uid === $uid`)
- **`auth != null`**: Ensures the user is authenticated (logged in)

### Data Structure

The app stores user data in this structure:

```
users
 └── {user_uid}
     ├── firstName: "John"
     ├── lastName: "Doe"
     └── email: "john@example.com"
```

### Testing

After setting the rules:

1. **Clear app data** or uninstall/reinstall the app
2. **Sign up** a new account
3. **Open Settings** → Profile Information
4. Data should load without permission errors

### Troubleshooting

If you still get permission errors:

1. **Verify rules are published**: Check the Rules tab shows the correct rules
2. **Check user is authenticated**: Make sure the user is logged in
3. **Verify UID matches**: The app uses `FirebaseAuth.getInstance().getCurrentUser().getUid()` as the key
4. **Check Firebase project**: Ensure you're using the correct Firebase project

### Security Note

These rules ensure:
- ✅ Each user can only access their own data
- ✅ No user can read/write other users' data
- ✅ Only authenticated users can access the database
- ✅ Prevents unauthorized access

