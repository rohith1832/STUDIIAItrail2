# Studi AI Trail 2 - Android App

An educational Android application featuring search functionality, user authentication, and multiple study aids such as quizzes, flashcards, progress tracking, study planner, and exam date reminders.

---

## Features

- **User Authentication:**  
  Login and Signup using **SharedPreferences** for local credential storage.

- **Search Functionality:**  
  Search subjects or notes with results shown in a styled container below the search bar.

- **Feature Dashboard:**  
  Access multiple study tools from the home screen, including:
  - Quiz
  - Flashcards
  - Progress Tracker
  - Study Planner
  - Exam Date reminders

- **Clean UI:**  
  Modern Android UI using Material Design components, Navigation Drawer, and Jetpack Navigation component.

---

## Screenshots

*Add your app screenshots here (optional)*

---

## Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 21+
- Git

### Installation

1. Clone this repository:
https://github.com/rohith1832/STUDIIAItrail2
2. Open the project in Android Studio.
3. Let Gradle sync and download dependencies.
4. Run the app on an emulator or physical device.

---

## Project Structure Highlights

- `MainActivity.kt` – Hosts navigation and drawer setup.
- `LoginFragment.kt` – Handles user login and signup UI with SharedPreferences.
- `HomeFragment.kt` – Displays the search bar, results container, and dashboard of study tools.
- `QuizFragment.kt`, `FlashcardsFragment.kt`, `ProgressTrackerFragment.kt`, `StudyPlannerFragment.kt`, `ExamDateFragment.kt` – Individual feature pages.
- `fragment_home.xml`, `fragment_login.xml`, etc. – Corresponding XML layouts.
- Vector drawables for icons under `res/drawable/`.
- Navigation Graph in `res/navigation/nav_graph.xml`.

---

## How to Use

- On launch, the user lands on the **Login screen**.
- New users can **sign up**, and returning users can **log in**.
- After login, navigate to the **Home screen** with search and feature cards.
- Tap on each feature to access quiz, flashcards, and other study aids.

---

## Technologies & Libraries

- Kotlin
- Android Jetpack Components:
- Navigation
- View Binding
- Material Design Components
- SharedPreferences for simple local data storage

---

## Contribution

Contributions are welcome! Please fork the repository and submit pull requests for improvements or bug fixes.

---
## Contact

Rohith Kodali
Email: kodalil.rohith2023@vitstudent.ac.in  
GitHub: [rohith1832](https://github.com/rohith1832)

