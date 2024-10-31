import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainGUI extends Application {

    private RSAEncryption rsaEncryption = new RSAEncryption();
    private AESEncryption aesEncryption = new AESEncryption();

    @Override
    public void start(Stage primaryStage) throws Exception {
        rsaEncryption.generateKeys();
        aesEncryption.generateKey();

        Label messageLabel = new Label("Message:");
        TextField messageField = new TextField();
        Label resultLabel = new Label("Résultat:");

        ToggleGroup encryptionType = new ToggleGroup();
        RadioButton rsaButton = new RadioButton("RSA");
        rsaButton.setToggleGroup(encryptionType);
        rsaButton.setSelected(true);
        RadioButton aesButton = new RadioButton("AES");
        aesButton.setToggleGroup(encryptionType);

        Button encryptButton = new Button("Chiffrer");
        Button decryptButton = new Button("Déchiffrer");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        encryptButton.setOnAction(e -> {
            String message = messageField.getText();
            String encryptedMessage = "";
            try {
                if (rsaButton.isSelected()) {
                    encryptedMessage = rsaEncryption.encrypt(message);
                } else if (aesButton.isSelected()) {
                    encryptedMessage = aesEncryption.encrypt(message);
                }
                resultArea.setText("Encrypted: " + encryptedMessage);
            } catch (Exception ex) {
                resultArea.setText("Erreur: " + ex.getMessage());
            }
        });

        decryptButton.setOnAction(e -> {
            String encryptedMessage = messageField.getText();
            String decryptedMessage = "";
            try {
                if (rsaButton.isSelected()) {
                    decryptedMessage = rsaEncryption.decrypt(encryptedMessage);
                } else if (aesButton.isSelected()) {
                    decryptedMessage = aesEncryption.decrypt(encryptedMessage);
                }
                resultArea.setText("Decrypted: " + decryptedMessage);
            } catch (Exception ex) {
                resultArea.setText("Erreur: " + ex.getMessage());
            }
        });

        VBox root = new VBox(10, messageLabel, messageField, rsaButton, aesButton, encryptButton, decryptButton, resultLabel, resultArea);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Encryption App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
