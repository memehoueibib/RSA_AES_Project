


public class Main {
    public static void main(String[] args) {
        try {
            // Test RSA
            System.out.println("===== RSA Encryption =====");
            RSAEncryption rsa = new RSAEncryption();
            rsa.generateKeys();
            String rsaMessage = "Hello, RSA!";
            String rsaEncrypted = rsa.encrypt(rsaMessage);
            System.out.println("RSA Encrypted: " + rsaEncrypted);
            String rsaDecrypted = rsa.decrypt(rsaEncrypted);
            System.out.println("RSA Decrypted: " + rsaDecrypted);

            // Test AES
            System.out.println("\n===== AES Encryption =====");
            AESEncryption aes = new AESEncryption();
            aes.generateKey();
            String aesMessage = "Hello, AES!";
            String aesEncrypted = aes.encrypt(aesMessage);
            System.out.println("AES Encrypted: " + aesEncrypted);
            String aesDecrypted = aes.decrypt(aesEncrypted);
            System.out.println("AES Decrypted: " + aesDecrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
