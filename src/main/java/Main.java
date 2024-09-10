import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Generar y guardar claves
        RSAKeyPairGenerator.generateKeys();
        System.out.println("Claves generadas y guardadas en 'publicKey.txt' y 'privateKey.txt'.");

        // Encriptar
        System.out.print("Introduce el mensaje que deseas encriptar: ");
        String message = scanner.nextLine();

        PublicKey publicKey = RSAKeyPairGenerator.getPublicKey("src/main/resources/publicKey.txt");
        String encryptedMessage = RSAEncryption.encrypt(message, publicKey);
        System.out.println("Mensaje encriptado: " + encryptedMessage);

        // Intentar desencriptar
        while (true) {
            System.out.print("Introduce el mensaje encriptado que deseas desencriptar: ");
            String encryptedInput = scanner.nextLine();

            PrivateKey privateKey = RSAKeyPairGenerator.getPrivateKey("src/main/resources/privateKey.txt");

            try {
                String decryptedMessage = RSADecryption.decrypt(encryptedInput, privateKey);
                System.out.println("Mensaje desencriptado: " + decryptedMessage);
                break;  // Salir del ciclo si el mensaje se desencripta correctamente
            } catch (Exception e) {
                System.out.println("Error al desencriptar el mensaje. Asegúrate de que el mensaje encriptado es correcto.");
                System.out.println("Inténtalo de nuevo.");
            }
        }

        scanner.close();
    }
}
