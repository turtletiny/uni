# JavaFX Exercises (Weeks 7–10)

This project is set up using **Gradle** to make it fully cross-platform and self-contained. It works seamlessly on **Linux (CachyOS/Arch)** and **Windows 11** without requiring you to manually download or install the JavaFX SDK. Gradle will automatically fetch the correct OS-specific JavaFX libraries at build time.

---

## Project Structure

```text
weeks_7_10/
├── build.gradle           # Build and JavaFX configurations
├── settings.gradle        # Project settings
├── README.md              # This guide
├── gradlew & gradlew.bat  # Gradle wrappers (Linux/Windows)
└── src/main/java/
    └── week07/
        ├── HelloJavaFX.java         # A modern sample JavaFX application
        └── HelloJavaFXLauncher.java # Wrapper launcher (helps IDE runs)
```

To create exercises for subsequent weeks:
1. Create a new package directory under `src/main/java/` (e.g., `week08/`, `week09/`, `week10/`).
2. Put your JavaFX classes inside.
3. (Recommended) Create a matching launcher class for each GUI class so you can run it directly from your IDE without VM argument issues.

---

## How to Run the Exercises

### 1. Running from Terminal

#### 🐧 On Linux (CachyOS)
Because your codebase resides on a FUSE-mounted folder (`pCloudDrive`), direct execution of scripts might be blocked by `noexec` settings. To run, invoke the wrapper explicitly with `bash`:

*   **Run the Default Exercise:**
    ```bash
    bash gradlew run
    ```
*   **Run a Specific Exercise:**
    ```bash
    bash gradlew runExercise -PclassName=week07.HelloJavaFXLauncher
    ```

#### 🪟 On Windows 11
Open Command Prompt (`cmd`) or PowerShell in the `weeks_7_10` directory and run:

*   **Run the Default Exercise:**
    ```cmd
    gradlew.bat run
    ```
*   **Run a Specific Exercise:**
    ```cmd
    gradlew.bat runExercise -PclassName=week07.HelloJavaFXLauncher
    ```

---

## 💡 Running Directly in VS Code or IntelliJ IDEA

Modern IDEs automatically detect Gradle projects, configure the project classpath, and provide a **"Run" / "Debug"** button directly in the editor above your Java classes.

### The JavaFX Runtime Workaround (Why we use Launchers)
If you try to run a class that `extends Application` directly in an IDE, Java will check for JavaFX modules on the startup module path and will likely throw this error:
> `Error: JavaFX runtime components are missing, and are required to run this application`

To solve this easily:
1. Create a standard Java class (e.g. `HelloJavaFXLauncher`) that does **not** inherit from `Application`.
2. Inside its `main` method, call the `main` method of your actual JavaFX application.
3. Click the **"Run"** button on this launcher class instead. The IDE will boot the JavaFX application perfectly!

Example Launcher code:
```java
package week07;

public class HelloJavaFXLauncher {
    public static void main(String[] args) {
        HelloJavaFX.main(args);
    }
}
```
