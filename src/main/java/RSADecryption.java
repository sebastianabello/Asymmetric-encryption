import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.util.Base64;

public class RSADecryption {
    public static String decrypt(String encryptedMessage, PrivateKey privateKey) throws Exception {
        // RSA decryption
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessage = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        return new String(decryptedMessage);
    }
}
