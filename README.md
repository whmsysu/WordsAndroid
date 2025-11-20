# GRE Vocabulary Learning App

An Android application designed to help users master GRE vocabulary using the Ebbinghaus Forgetting Curve for efficient spaced repetition.

## Features

- **Flashcard Interface**: Clean and intuitive card-based UI for learning words.
- **Smart Swipe Gestures**:
  - **Swipe Left (Remember)**: Marks the word as remembered and schedules the next review based on the Ebbinghaus curve.
  - **Swipe Right (Forget)**: Marks the word as forgotten, resets its progress, and adds it to the "Vocabulary Book" for focused review.
- **Spaced Repetition System**: Automatically calculates the optimal next review time for each word to maximize retention.
- **Vocabulary Book**: A dedicated section to review words that you've struggled with (swiped right).
- **Progress Tracking**: Visual feedback on card rotation and animations.
- **Offline Support**: All data is stored locally using Room Database.

## Tech Stack

- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **Android Jetpack Components**:
  - **Room**: Local database for data persistence.
  - **ViewModel & LiveData**: UI state management.
  - **RecyclerView**: Efficient list display.
  - **ViewBinding**: Safer view interaction.
- **Concurrency**: Kotlin Coroutines & Flow.
- **Animations**: ObjectAnimator for card flips and custom ItemTouchHelper for swipe gestures.

## Getting Started

### Prerequisites

- Android Studio Iguana or newer.
- JDK 17 or newer.
- Android SDK API Level 24 (Min SDK) or higher.

### Installation

1.  Clone the repository:
    ```bash
    git clone https://github.com/yourusername/WordsAndroid.git
    ```
2.  Open the project in **Android Studio**.
3.  Sync the project with Gradle files.
4.  Run the app on an emulator or physical device.

## Usage

1.  **Home Screen**: You will see a stack of word cards.
2.  **Learning**:
    - Tap a card to flip it and see the Chinese definition.
    - Swipe **Left** if you know the word.
    - Swipe **Right** if you don't know the word.
3.  **Review**:
    - Tap the menu icon in the top right corner.
    - Select **Vocabulary Book** to see a list of words you need to review (words you swiped right on).

## Project Structure

```
com.example.wordsandroid
├── data/               # Room entities, DAOs, Repository, and Data Models
├── ui/                 # Adapters and UI-related classes
├── utils/              # Utility classes (e.g., Ebbinghaus logic)
├── MainActivity.kt     # Main flashcard screen
├── VocabularyBookActivity.kt # List of difficult words
├── MainViewModel.kt    # ViewModel for managing app data
└── WordsApplication.kt # Application class
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
