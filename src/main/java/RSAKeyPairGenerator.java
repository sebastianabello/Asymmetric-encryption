import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class RSAKeyPairGenerator {
    // Generar y guardar claves públicas y privadas
    public static void generateKeys() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Guardar la clave pública
        PublicKey publicKey = keyPair.getPublic();
        String publicKeyContent = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        Files.write(Paths.get("src/main/resources/publicKey.txt"), publicKeyContent.getBytes());

        // Guardar la clave privada
        PrivateKey privateKey = keyPair.getPrivate();
        String privateKeyContent = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        Files.write(Paths.get("src/main/resources/privateKey.txt"), privateKeyContent.getBytes());
    }

    // Leer clave pública desde archivo
    public static PublicKey getPublicKey(String filePath) throws Exception {
        String keyContent = new String(Files.readAllBytes(Paths.get(filePath)));
        byte[] keyBytes = Base64.getDecoder().decode(keyContent);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    // Leer clave privada desde archivo
    public static PrivateKey getPrivateKey(String filePath) throws Exception {
        String keyContent = new String(Files.readAllBytes(Paths.get(filePath)));
        byte[] keyBytes = Base64.getDecoder().decode(keyContent);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }
}

