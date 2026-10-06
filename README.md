# Kinnect

A social media app UI built in Android Studio with Kotlin and XML layouts.

**Student:** Abbas Raza · **Roll no:** 23I-0733

## Screens

| Area | Screens |
|---|---|
| Start | Splash, Login, Sign up |
| Feed | Home, Comments, Reaction picker |
| Stories | Story viewer, Camera, Story editor, Your story |
| Posting | Create post, Photo picker |
| Messaging | Chats, Chat, Voice call |
| Profile | Profile, Edit profile, Other profile, Search |
| Tabs | Friends, Marketplace, Notifications, Menu (log out) |

## Navigation notes

- The bottom tab bar is shared by Home, Friends, Marketplace, Notifications and Menu (`BottomNav.kt`).
  Home stays at the bottom of the back stack, so pressing Back from any tab returns to Home.
- Log out uses `FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK` so Back can't return into the app.
- Screens pass data with Intent extras (e.g. chat name) and `registerForActivityResult`
  (reaction picker → home, edit profile → profile).

## Running

Open the project in Android Studio, let Gradle sync, then run the `app` configuration.
UI tests are in `app/src/androidTest/.../NavigationTests.kt`.
