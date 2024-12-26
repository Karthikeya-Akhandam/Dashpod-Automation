# Dashpod-Automation

This repository automates various activities within the Dashpod Android app using Appium, Maven, and Java. The automation includes functionalities for logging in, signing up, player profile management, search filters, device connection via Bluetooth, and activity automation.

## Prerequisites

Before running the automation scripts, ensure that the following tools and configurations are set up on your machine:

### 1. **JDK (Java Development Kit)**
   - Install the [latest JDK](https://www.oracle.com/in/java/technologies/downloads/).
   - Ensure that `JAVA_HOME` is set and `java` is available in your system's PATH.

### 2. **Node.js**
   - Install the latest LTS version of [Node.js](https://nodejs.org/en/).
   - Verify installation by running `node -v` and `npm -v` in the terminal.

### 3. **Android Studio**
   - Install the latest version of [Android Studio](https://developer.android.com/studio) for running Android emulators and setting up Android SDK.

### 4. **IntelliJ IDEA**
   - Install [IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=windows) for Java development.
   - Create a new Maven project and update the `pom.xml` file as needed.

### 5. **Appium**
   - Install Appium globally using npm:  
     ```bash
     npm install -g appium
     ```
   - Start the Appium server by running the following command:
     ```bash
     appium
     ```
   - The Appium server URL will differ when started from the Appium GUI or the command line. Ensure the correct URL is used in the code.

## Setting Up The Project

1. Clone the repository to your local machine.
2. Open IntelliJ IDEA and create a new Maven project.
3. Update the `pom.xml` file in your project with the dependencies provided in this repository.
4. Ensure that the path to the CSV files is correctly configured according to your system.

## Automation of Activities

The Activities page of the Dashpod app has been automated.

### Prerequisites:
- The app should be logged in and on the home screen.
- Desired capabilities are defined in the `AutomateActivityTest.java` file.
- Update the CSV file path based on your local system.

## Automation of Devices (Bluetooth Connection)

The Bluetooth connection page is automated to find and connect a specific pod via its MAC ID.

### Prerequisites:
- The Dashpod app opens automatically due to the desired capabilities.
- Ensure the correct MAC ID is entered, considering case and space sensitivity as the MAC ID is treated as a string in Java.

## Automation of Search Filters

This automation handles the search filter functionality within the Dashpod app's search page.

### Prerequisites:
- The Dashpod app opens automatically via the specified desired capabilities.
- The app will display the splash screen, and login will be completed (lines 43 and 44 of the code).
- Ensure the CSV file contains the correct path and format for search filters.
- Once logged in, the search filters will be applied according to the CSV file.

## Automation of Sign-Up

This script automates the sign-up process for new users on the Dashpod app and includes creating an owner profile.

### Prerequisites:
- Ensure the CSV file path is correct on your system.
- Once an account is created using the example CSV, the credentials (email, phone number, and username) cannot be reused.
- The Dashpod app will open automatically with the desired capabilities.

## Automation of Login

The login page of the Dashpod app is automated for testing different credentials.

### Prerequisites:
- Ensure the CSV file path is correct for login credentials.
- The credentials in the CSV file should be for testing and should not be reused once logged in.
- Once logged in, the app will automatically log out, so it can be tested with other credentials.

## Automation of Player Profile

This code automates the process of adding a new player profile in the Dashpod app, including selecting interests, email ID, phone number (with country code), date of birth (DOB), weight, height, etc.

### Prerequisites:
- The Dashpod app will automatically open due to the desired capabilities.
- The automation script will navigate to the settings and add the player profile.
- Ensure that the path to the CSV file is correct on your local system.

## Running the Tests

1. Start the Appium server by running `appium` in the terminal.
2. Open IntelliJ IDEA and run the desired test class (e.g., `AutomateActivityTest.java` or any other relevant test file).
3. Monitor the test execution in the terminal or IntelliJ console.
4. Verify that the automation performs the intended tasks in the Dashpod app.

## Notes
- Ensure that your Android device or emulator is connected and running before starting the automation.
- Modify the `desiredCapabilities` in the code as needed to match your local setup (e.g., device name, platform version).
- CSV files used for login, sign-up, and profile creation should be properly formatted to match the script's expectations.

## Contributing

Feel free to fork the repository, submit issues, or create pull requests if you have suggestions or improvements.
