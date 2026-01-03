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

### 🎬 Movie Browsing & Discovery
- **Home Screen**: Curated movie sections including:
  - Continue Watching: Resume watching previously viewed movies
  - Trending Now: Most popular movies based on user clicks
  - Originals: Exclusive original content
  - Bollywood: Indian cinema collection
  - Hollywood: International blockbusters
  - Korean: Korean drama and cinema
  - Anime: Animated content collection

### 🔍 Search & Filter
- **Advanced Search**: Search movies by title, genre, or category
- **Genre Filtering**: Browse movies by different genres (Action, Horror, Romance, etc.)
- **Real-time Search**: Instant results as you type

### 📋 Playlist & My List
- **My List**: Save favorite movies to watch later
- **Playlist Section**: Access all saved movies in one place
- **Add/Remove**: Easy management of your movie collection

### 👤 Profile System
- **Multiple Profiles**: Create multiple user profiles
- **Profile Images**: Each profile has a custom image (default: Guts)
- **Password Protection**: Secure profiles with passwords
- **Profile Selection**: Choose profile before accessing the app

### 🎥 Movie Details
- **Detailed Information**: View movie title, overview, and poster
- **YouTube Integration**: Watch movie trailers directly from YouTube
- **Add to List**: Quick access to add movies to your list

### 🎨 User Interface
- **Light/Dark Mode**: Toggle between light and dark themes
- **Modern Design**: CardViews, animations, and smooth transitions
- **Responsive Layout**: Optimized for various screen sizes
- **Top Navigation Bar**: Quick access to search and user information
- **Bottom Navigation**: Easy navigation between Home, Playlist, Trending, and Settings

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
  - User profile information
  - First name, last name, email storage

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
│   │   │   ├── manager/           # Business logic managers
│   │   │   │   └── MovieClickManager.java
│   │   │   ├── model/             # Data models
│   │   │   │   ├── Movie.java
│   │   │   │   └── Profile.java
│   │   │   ├── repository/       # Data repositories
│   │   │   │   └── MovieRepository.java
│   │   │   ├── ui/               # UI components
│   │   │   │   ├── home/
│   │   │   │   │   └── HomeFragment.java
│   │   │   │   ├── search/
│   │   │   │   │   └── SearchFragment.java
│   │   │   │   ├── trending/
│   │   │   │   │   └── TrendingFragment.java
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
2. **Login/Sign Up**: User authentication screen
3. **Profile Selection**: Choose or create a profile (with optional password)
4. **Home Screen**: Browse movies by categories
5. **Movie Details**: View movie information and watch trailers
6. **Search**: Find movies by title, genre, or category
7. **Settings**: Manage profile, toggle dark mode, logout

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
- Current Version: 6
- Entities: Movie, Profile
- Migration: Uses `fallbackToDestructiveMigration()` for development

### Theme Configuration
- Default Theme: Light Mode
- Supports Dark Mode toggle
- Material3 DayNight theme

## 📝 Notes

- The app uses Room database for local storage and Firebase for cloud-based user data
- Movie images can be loaded from URLs or local drawable resources
- Continue watching list persists across app sessions
- Profile passwords are stored locally in Room database
- All database operations are performed on background threads to maintain UI responsiveness

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
