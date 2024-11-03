import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import java.time.Duration;
import java.time.Instant;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MainGUI extends Application {
    private RSAEncryption rsa;
    private AESEncryption aes;

    @Override
    public void start(Stage primaryStage) {
        // Welcome Screen
        Stage welcomeStage = new Stage();
        welcomeStage.setTitle("Welcome to Encryption App");
        VBox welcomeLayout = new VBox(20);
        welcomeLayout.setPadding(new Insets(20));

        Label welcomeLabel = new Label("Welcome to the Encryption App");
        Label descriptionLabel = new Label("This application allows you to encrypt and decrypt messages using RSA and AES algorithms.\nYou can choose between RSA for secure key exchange or AES for fast symmetric encryption.");
        Button startButton = new Button("Start");

        try {
            Image logo = new Image("file:" + System.getProperty("user.dir") + "/src/resources/logo.png");
            ImageView logoView = new ImageView(logo);
            logoView.setFitHeight(100);
            logoView.setFitWidth(100);
            welcomeLayout.getChildren().add(logoView);
        } catch (Exception e) {
            System.out.println("Error loading logo image: " + e.getMessage());
        }

        welcomeLayout.getChildren().addAll(welcomeLabel, descriptionLabel, startButton);
        Scene welcomeScene = new Scene(welcomeLayout, 400, 300);
        welcomeStage.setScene(welcomeScene);
        welcomeStage.show();

        startButton.setOnAction(e -> {
            welcomeStage.close();
            showMainApp(primaryStage);
        });
    }

    private void showMainApp(Stage primaryStage) {
        rsa = new RSAEncryption();
        aes = new AESEncryption();
        try {
            rsa.generateKeys();
            aes.generateKey();
        } catch (Exception e) {
            showAlert("Error generating keys: " + e.getMessage(), Alert.AlertType.ERROR);
        }

        primaryStage.setTitle("Encryption App");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        Label messageLabel = new Label("Message:");
        TextField messageField = new TextField();
        grid.add(messageLabel, 0, 0);
        grid.add(messageField, 1, 0);

        ToggleGroup encryptionGroup = new ToggleGroup();
        RadioButton rsaButton = new RadioButton("RSA");
        rsaButton.setToggleGroup(encryptionGroup);
        rsaButton.setSelected(true);
        RadioButton aesButton = new RadioButton("AES");
        aesButton.setToggleGroup(encryptionGroup);
        grid.add(rsaButton, 0, 1);
        grid.add(aesButton, 1, 1);

        Button encryptButton = new Button("Encrypt");
        Button decryptButton = new Button("Decrypt");
        Button copyButton = new Button("Copy");
        Button clearButton = new Button("Clear");
        grid.add(encryptButton, 0, 2);
        grid.add(decryptButton, 1, 2);
        grid.add(copyButton, 1, 3);
        grid.add(clearButton, 0, 3);

        Label resultLabel = new Label("Result:");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setWrapText(true);
        grid.add(resultLabel, 0, 4);
        grid.add(resultArea, 1, 4);

        Label timeLabelMs = new Label("Execution Time (ms):");
        TextField timeFieldMs = new TextField();
        timeFieldMs.setEditable(false);
        grid.add(timeLabelMs, 0, 5);
        grid.add(timeFieldMs, 1, 5);

        Label timeLabelUs = new Label("Execution Time (µs):");
        TextField timeFieldUs = new TextField();
        timeFieldUs.setEditable(false);
        grid.add(timeLabelUs, 0, 6);
        grid.add(timeFieldUs, 1, 6);

        encryptButton.setOnAction(event -> {
            String message = messageField.getText();
            try {
                String encryptedMessage;
                Instant start = Instant.now();
                if (rsaButton.isSelected()) {
                    encryptedMessage = rsa.encrypt(message);
                } else {
                    encryptedMessage = aes.encrypt(message);
                }
                Instant end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);
                long timeInMs = timeElapsed.toMillis();
                long timeInUs = timeElapsed.toNanos() / 1000;
                resultArea.setText(encryptedMessage);
                timeFieldMs.setText(timeInMs + " ms");
                timeFieldUs.setText(timeInUs + " µs");
            } catch (Exception e) {
                showAlert("Error during encryption: " + e.getMessage() + "\nExample: Enter a text message to encrypt.", Alert.AlertType.ERROR);
            }
        });

        decryptButton.setOnAction(event -> {
            String encryptedMessage = messageField.getText();
            try {
                String decryptedMessage;
                Instant start = Instant.now();
                if (rsaButton.isSelected()) {
                    decryptedMessage = rsa.decrypt(encryptedMessage);
                } else {
                    decryptedMessage = aes.decrypt(encryptedMessage);
                }
                Instant end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);
                long timeInMs = timeElapsed.toMillis();
                long timeInUs = timeElapsed.toNanos() / 1000;
                resultArea.setText(decryptedMessage);
                timeFieldMs.setText(timeInMs + " ms");
                timeFieldUs.setText(timeInUs + " µs");
            } catch (Exception e) {
                showAlert("Error during decryption: " + e.getMessage() + "\nExample: Make sure the text is properly encrypted with the selected algorithm.", Alert.AlertType.ERROR);
            }
        });

        copyButton.setOnAction(event -> {
            String resultText = resultArea.getText();
            if (!resultText.isEmpty()) {
                ClipboardContent content = new ClipboardContent();
                content.putString(resultText);
                Clipboard.getSystemClipboard().setContent(content);
                showAlert("Result copied to clipboard.", Alert.AlertType.INFORMATION);
            } else {
                showAlert("No text to copy.", Alert.AlertType.WARNING);
            }
        });

        clearButton.setOnAction(event -> {
            messageField.clear();
            resultArea.clear();
            timeFieldMs.clear();
            timeFieldUs.clear();
        });

        Scene scene = new Scene(grid, 500, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}