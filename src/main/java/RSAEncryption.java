import javax.crypto.Cipher;
import java.security.PublicKey;
import java.util.Base64;

public class RSAEncryption {
    public static String encrypt(String message, PublicKey publicKey) throws Exception {
        // RSA encryption
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMessage = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedMessage);
    }
}
