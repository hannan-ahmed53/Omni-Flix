# Omni Flix - Movie Streaming Application

## 📱 Overview

Omni Flix is a comprehensive Android movie streaming application that provides users with an immersive experience to browse, search, and watch movies. The app features user authentication, profile management, personalized recommendations, and a modern, intuitive user interface.

## ✨ Features

### 🔐 Authentication & User Management
- **Firebase Authentication**: Secure email/password-based authentication
- **Sign Up**: New user registration with first name, last name, email, and password
- **Sign In**: Existing user login with email and password
- **User Profile**: View and edit user profile information in Settings
- **Profile Management**: Multiple user profiles with password protection
- **User Data Isolation**: Each user's profiles are isolated per Firebase account
- **Profile Image Selection**: Choose profile pictures from device gallery or use default images

### 🎬 Movie Browsing & Discovery
- **Home Screen**: Curated movie sections including:
  - Continue Watching: Resume watching previously viewed movies (with remove option)
  - Trending Now: Most popular movies based on user clicks
  - Originals: Exclusive original content (15+ movies)
  - Bollywood: Indian cinema collection (15+ movies)
  - Hollywood: International blockbusters (15+ movies)
  - Korean: Korean drama and cinema (15+ movies)
  - Anime: Animated content collection (15+ movies)
- **News Section**: Movie news and upcoming releases (replaces Trending in bottom navigation)

### 🔍 Search & Filter
- **Advanced Search**: Search movies by title, genre, or category
- **Genre Filtering**: Browse movies by different genres (Action, Horror, Romance, etc.)
- **Real-time Search**: Instant results as you type

### 📋 Playlist & My List
- **My List**: Save favorite movies to watch later
- **Playlist Section**: Enhanced playlist with:
  - Top bar with "My Playlist" title
  - Search functionality to filter movies in playlist
  - Create custom playlists (foundation for future multi-playlist feature)
  - Persistent storage across app restarts
- **Add/Remove**: Easy management of your movie collection

### 👤 Profile System
- **Multiple Profiles**: Create multiple user profiles per user account
- **Profile Images**: 
  - Select profile pictures from device gallery
  - Default image (Guts) if no image selected
  - Support for both URI-based and drawable images
- **Password Protection**: Secure profiles with passwords
- **Profile Selection**: Choose profile before accessing the app
- **Profile Display**: Selected profile name displayed on home screen top bar
- **User Isolation**: Each Firebase account has its own isolated profile list

### 🎥 Movie Details
- **Detailed Information**: View movie title, overview, and poster
- **YouTube Integration**: Watch movie trailers directly from YouTube
- **Add to List**: Quick access to add movies to your list

### 🎨 User Interface
- **Light/Dark Mode**: Toggle between light and dark themes (defaults to Light Mode)
- **Modern Design**: CardViews, animations, and smooth transitions
- **Responsive Layout**: Optimized for various screen sizes
- **Top Navigation Bar**: 
  - App logo and name "Omni Flix"
  - Selected profile name (or user's first/last name)
  - Search icon for quick access
- **Bottom Navigation**: 
  - Glassy/blur effect for modern look
  - Easy navigation between Home, Playlist, News, and Settings
- **Enhanced Login/Signup**: CardView-based design with improved UI/UX

### 📊 Analytics & Recommendations
- **Click Tracking**: Tracks most clicked movies for trending section
- **Continue Watching**: Automatically tracks last watched movies
- **Personalized Content**: Movies sorted by popularity and user preferences

## 🛠️ Technologies Used

### Core Android Technologies
- **Java**: Primary programming language
- **Android SDK**: Target SDK 35, Minimum SDK 24
- **Material Design Components**: Modern UI components
- **AndroidX Libraries**: Latest Android support libraries

### Database & Storage
- **Room Database**: Local SQLite database for offline data storage
  - Movie data persistence
  - Profile management
  - My List storage
- **SharedPreferences**: Lightweight storage for:
  - Movie click tracking
  - Continue watching list
  - User preferences

### Backend Services
- **Firebase Authentication**: User authentication and management
- **Firebase Realtime Database**: Cloud-based user data storage
  - User profile information (first name, last name, email)
  - UID-based data isolation for security
  - Proper permission rules for authenticated users
  - See `FIREBASE_SETUP_INSTRUCTIONS.md` for database rules setup

### UI/UX Libraries
- **Glide**: Image loading and caching library
  - Movie poster loading
  - Profile image display
  - Efficient image management
- **Material Components**:
  - BottomNavigationView
  - CardView
  - SwitchMaterial
  - RecyclerView

### Architecture & Design Patterns
- **MVVM-like Structure**: Separation of concerns
- **Repository Pattern**: Centralized data management
- **Adapter Pattern**: RecyclerView adapters for lists
- **Singleton Pattern**: Database instance management

### Networking
- **NetworkUtils**: Network connectivity checking
- **YouTube Integration**: Direct YouTube trailer playback
- **Image Loading**: Remote image URLs support

## 📁 Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/project67/
│   │   │   ├── adapter/          # RecyclerView adapters
│   │   │   │   ├── MovieAdapter.java
│   │   │   │   ├── ProfileAdapter.java
│   │   │   │   └── ContinueWatchingAdapter.java
│   │   │   ├── data/              # Database layer
│   │   │   │   ├── AppDatabase.java
│   │   │   │   ├── MovieDao.java
│   │   │   │   ├── ProfileDao.java
│   │   │   │   └── DatabaseInitializer.java
│   │   │   ├── adapter/          # RecyclerView adapters
│   │   │   │   ├── MovieAdapter.java
│   │   │   │   ├── ProfileAdapter.java
│   │   │   │   ├── ContinueWatchingAdapter.java
│   │   │   │   └── NewsAdapter.java
│   │   │   ├── manager/           # Business logic managers
│   │   │   │   ├── MovieClickManager.java
│   │   │   │   └── ProfileManager.java
│   │   │   ├── model/             # Data models
│   │   │   │   ├── Movie.java
│   │   │   │   ├── Profile.java
│   │   │   │   └── NewsItem.java
│   │   │   ├── repository/       # Data repositories
│   │   │   │   └── MovieRepository.java
│   │   │   ├── ui/               # UI components
│   │   │   │   ├── home/
│   │   │   │   │   └── HomeFragment.java
│   │   │   │   ├── search/
│   │   │   │   │   └── SearchFragment.java
│   │   │   │   ├── news/
│   │   │   │   │   └── NewsFragment.java
│   │   │   │   └── settings/
│   │   │   │       └── SettingsFragment.java
│   │   │   ├── util/             # Utility classes
│   │   │   │   └── NetworkUtils.java
│   │   │   ├── LoginActivity.java
│   │   │   ├── SignUpActivity.java
│   │   │   ├── SplashScreenActivity.java
│   │   │   ├── ProfileSelectionActivity.java
│   │   │   ├── MainActivity.java
│   │   │   ├── DetailActivity.java
│   │   │   └── MyListActivity.java
│   │   └── res/                  # Resources
│   │       ├── drawable/         # Images and drawables
│   │       ├── layout/           # XML layouts
│   │       ├── menu/             # Menu definitions
│   │       ├── values/           # Strings, colors, themes
│   │       └── mipmap/           # App icons
│   └── AndroidManifest.xml
└── build.gradle
```

## 🚀 Getting Started

### Prerequisites
- Android Studio (latest version)
- JDK 8 or higher
- Android SDK 35
- Firebase project with Authentication and Realtime Database enabled

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd project67
   ```

2. **Firebase Setup**
   - Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
   - Enable Authentication (Email/Password)
   - Enable Realtime Database
   - **IMPORTANT**: Set Firebase Database Rules (see `FIREBASE_SETUP_INSTRUCTIONS.md`)
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
   - Download `google-services.json` and place it in `app/` directory

3. **Build the Project**
   ```bash
   ./gradlew build
   ```

4. **Run the App**
   - Connect an Android device or start an emulator
   - Run the app from Android Studio or use:
   ```bash
   ./gradlew installDebug
   ```

## 📱 App Flow

1. **Splash Screen**: Displays splash screen image for 3 seconds
2. **Login/Sign Up**: User authentication screen with CardView design
3. **Profile Selection**: 
   - Choose or create a profile (with optional password)
   - Select profile image from gallery or use default
   - Each user account has isolated profiles
4. **Home Screen**: 
   - Browse movies by categories
   - Top bar shows app logo, name, selected profile name, and search icon
   - Continue Watching section with remove option
5. **Movie Details**: View movie information and watch trailers
6. **Search**: Find movies by title, genre, or category
7. **Playlist**: Enhanced playlist with search and custom playlist creation
8. **News**: Movie news and upcoming releases
9. **Settings**: 
   - View and edit user profile (first name, last name, email)
   - Toggle dark mode
   - Logout

## 🎯 Key Functionalities

### Movie Management
- Movies are stored locally using Room database
- Movie data includes: title, poster (URL or drawable), YouTube URL, overview, category, genre
- Categories: trending, originals, bollywood, hollywood, korean, anime
- Genres: Action, Horror, Romance, Thriller, Fantasy, Drama, etc.

### User Experience
- **Continue Watching**: Tracks multiple movies being watched
- **Trending Algorithm**: Movies sorted by click count
- **Offline Support**: Local database allows offline browsing
- **Smooth Navigation**: Fragment-based navigation with bottom navigation bar

### Security Features
- Firebase Authentication for secure login
- Profile password protection
- Secure data storage

## 🎨 UI Components

- **CardViews**: Used in login, signup, and profile displays
- **RecyclerViews**: Horizontal and grid layouts for movie lists
- **Bottom Navigation**: Easy access to main sections
- **Top Bar**: Logo, app name, user name, and search icon
- **Material Design**: Modern, clean interface following Material Design guidelines

## 📦 Dependencies

```gradle
- androidx.appcompat:appcompat:1.6.1
- com.google.android.material:material:1.11.0
- androidx.constraintlayout:constraintlayout:2.1.4
- androidx.recyclerview:recyclerview:1.3.2
- androidx.cardview:cardview:1.0.0
- com.github.bumptech.glide:glide:4.16.0
- androidx.room:room-runtime:2.6.1
- com.google.firebase:firebase-auth:23.0.0
- com.google.firebase:firebase-database:22.0.1
```

## 🔧 Configuration

### Database Version
- Current Version: 7
- Entities: Movie, Profile
- Profile Entity: Includes `userId` field for user isolation
- Migration: Uses `fallbackToDestructiveMigration()` for development

### Theme Configuration
- Default Theme: Light Mode
- Supports Dark Mode toggle
- Material3 DayNight theme

## 📝 Notes

- The app uses Room database for local storage and Firebase for cloud-based user data
- Movie images can be loaded from URLs or local drawable resources
- Continue watching list persists across app sessions with remove functionality
- Profile passwords are stored locally in Room database
- All database operations are performed on background threads to maintain UI responsiveness
- Each user's profiles are isolated by Firebase UID for security
- Selected profile name is displayed on the home screen top bar
- News section uses mock data (ready for TMDB API integration)

## 🔒 Security & Data Privacy

- **User Data Isolation**: Each Firebase account has completely isolated profile data
- **UID-Based Storage**: All Firebase data is stored under `/users/{uid}` path
- **Permission Rules**: Firebase Database rules ensure users can only access their own data
- **Profile Passwords**: Stored locally and verified before profile access/deletion
- **Authentication Required**: All database operations require authenticated users

## 🆕 Recent Updates

### Version 2.0 Features
- ✅ Profile image selection from gallery
- ✅ Enhanced playlist with search functionality
- ✅ News section replacing Trending (with TMDB API ready)
- ✅ User data isolation per Firebase account
- ✅ Selected profile name display on home screen
- ✅ Improved error handling and user feedback
- ✅ Glassy effect on bottom navigation bar
- ✅ Expanded movie library (15+ movies per category)
- ✅ Enhanced Settings with proper profile information display
- ✅ Firebase Database rules setup and documentation

## 📚 Additional Documentation

- **Firebase Setup**: See `FIREBASE_SETUP_INSTRUCTIONS.md` for detailed Firebase configuration
- **Database Rules**: See `firebase_database_rules.json` for Firebase Realtime Database rules

## 👨‍💻 Development

### Building for Release
```bash
./gradlew assembleRelease
```

### Running Tests
```bash
./gradlew test
```

## 📄 License

This project is developed for educational purposes.

## 🤝 Contributing

This is a private project. For any issues or suggestions, please contact the development team.

---

**Omni Flix** - Your Ultimate Movie Streaming Experience 🎬
