# My Kotlin GUI Application

This project is a simple GUI application developed in Kotlin. It serves as a demonstration of building a graphical user interface using Kotlin and the JavaFX framework.

## Project Structure

```
my-kotlin-gui-app
├── src
│   ├── main
│   │   ├── kotlin
│   │   │   └── App.kt          # Main entry point of the application
│   │   └── resources           # Resources for the application
│   └── test
│       └── kotlin
│           └── AppTest.kt      # Unit tests for the App class
├── build.gradle.kts            # Gradle build configuration
├── settings.gradle.kts         # Gradle project settings
└── README.md                   # Project documentation
```

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd my-kotlin-gui-app
   ```

2. **Build the project**:
   Make sure you have Gradle installed. Run the following command to build the project:
   ```bash
   ./gradlew build
   ```

3. **Run the application**:
   After building, you can run the application using:
   ```bash
   ./gradlew run
   ```

## Usage

- The application initializes a GUI window with various components.
- You can interact with the GUI elements as per the functionality defined in the `App.kt` file.

## Testing

To run the unit tests, use the following command:
```bash
./gradlew test
```

This will execute the tests defined in `AppTest.kt` and provide feedback on the functionality of the GUI components.