# Encryption App Project - RSA and AES Encryption

## Project Overview

This project is an encryption and decryption application built using JavaFX. It allows users to encrypt and decrypt messages using two popular encryption algorithms: **RSA** (for secure key exchange) and **AES** (for fast symmetric encryption). The GUI is implemented with JavaFX, providing a user-friendly interface for interacting with the encryption functionalities.

## Features

- **Encryption and Decryption**: Encrypt messages using RSA or AES, and decrypt them as needed.
- **Execution Time Measurement**: Displays the execution time of encryption and decryption in both milliseconds (ms) and microseconds (µs).
- **Copy Results**: Easily copy encrypted or decrypted messages to the clipboard.
- **Clear Button**: Clear the message input and result fields for new operations.
- **Welcome Screen**: A welcome screen that provides a brief overview of the app with a logo.

## Tools and Technologies Used

- **Programming Language**: Java (Java SE 17 or later recommended).
- **GUI Framework**: JavaFX (version 23.0.1).
- **Cryptographic Libraries**: Java Security (for RSA) and Javax Crypto (for AES).
- **Build Tool**: Terminal with `javac` and `java` commands.
- **IDE**: Visual Studio Code (optional, but helpful for editing and debugging).

## Project Structure

The project directory is organized as follows:

```
RSA_AES_PROJECT/
  ├── .vscode/              # VS Code configuration files
  ├── bin/                  # Compiled classes directory
  ├── javafx-sdk-23.0.1/    # JavaFX SDK library
  ├── resources/            # Resources directory containing the logo image
  ├── src/                  # Source code directory
  ├── README.md             # Project README file
  └── launch.json           # VS Code launch configuration (optional)
```

## Libraries and Dependencies

- **JavaFX SDK (version 23.0.1)**: Required to provide the graphical interface for the application.
- **Java Cryptographic Extensions**: For RSA and AES encryption functionality.

## How to Compile and Run the Project

### Step 1: Clean the Build Directory

Before compiling, make sure the `bin` directory is empty:

```sh
macbpro@Meme RSA_AES_Project % rm -rf bin/*
```

You will be prompted to confirm the deletion of files in the `bin` directory. Press `y` to confirm.

### Step 2: Compile the Source Code

To compile the Java source files, use the following command:

```sh
macbpro@Meme RSA_AES_Project % javac -d bin --module-path javafx-sdk-23.0.1/lib --add-modules javafx.controls,javafx.fxml src/resources/*.java src/*.java
```

- `-d bin`: Specifies the output directory for compiled classes.
- `--module-path javafx-sdk-23.0.1/lib`: Points to the JavaFX SDK library.
- `--add-modules javafx.controls,javafx.fxml`: Specifies the JavaFX modules to include.

### Step 3: Run the Application

After compilation, use the following command to run the application:

```sh
macbpro@Meme RSA_AES_Project % java --module-path javafx-sdk-23.0.1/lib --add-modules javafx.controls,javafx.fxml -Dprism.order=sw -cp bin MainGUI
```

- `--module-path javafx-sdk-23.0.1/lib`: Specifies the JavaFX library path.
- `--add-modules javafx.controls,javafx.fxml`: Loads necessary JavaFX modules.
- `-Dprism.order=sw`: Ensures software rendering is used, which can be helpful for compatibility.
- `-cp bin MainGUI`: Runs the `MainGUI` class from the compiled classes.

## Important Notes

- **JavaFX Setup**: Make sure the JavaFX SDK is downloaded and correctly referenced in the command-line arguments.
- **Logo Resource**: Ensure `logo.png` is present in the `resources` directory, and the correct path is used in the code to load it.

## Running with an IDE (Optional)

If you prefer using Visual Studio Code or another IDE:

1. Import the project into your IDE.
2. Make sure to configure the JavaFX library in your IDE settings.
3. Use the provided `launch.json` file to run the project easily from VS Code.

## Troubleshooting

- **Image Not Found**: If you encounter errors loading the logo image, verify the path and ensure the `logo.png` file is in the correct directory.
- **JavaFX Errors**: Ensure that JavaFX is properly installed and referenced. You might need to adjust the module path to reflect the actual location of the JavaFX SDK.

## Acknowledgements

- **JavaFX Documentation**: For providing a rich UI framework to create an interactive user interface. ([JavaFX Documentation](https://openjfx.io/))
- **Oracle Java Documentation**: For RSA and AES cryptographic functionalities. ([Oracle Java Documentation](https://docs.oracle.com/en/java/))
- **Stack Overflow**: For troubleshooting various JavaFX and Java compilation issues. ([Stack Overflow](https://stackoverflow.com/))
- **GitHub Gists**: For providing examples on how to work with RSA and AES encryption in Java. ([GitHub Gists](https://gist.github.com/))

Feel free to reach out for more guidance or if you face any issues running the project. Enjoy exploring cryptographic encryption with Java and JavaFX!

