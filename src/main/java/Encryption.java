import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class Encryption {

    private static final String algo = "AES";

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        SecretKey key = generate();

        System.out.print("Enter a string to encryBpt: ");
        String text = in.nextLine();

        String encrypted = encrypt(text, key);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);

        in.close();
    }

    public static SecretKey generate() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance(algo);
        SecureRandom secure = new SecureRandom();
        generator.init(256, secure); // Use a 256-bit key size
        return generator.generateKey();
    }

    public static String encrypt(String text, SecretKey key) throws Exception {
        Cipher ci = Cipher.getInstance(algo);
        ci.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryB = ci.doFinal(text.getBytes());
        return Base64.getEncoder().encodeToString(encryB);
    }

    public static String decrypt(String encrypted, SecretKey key) throws Exception {
        Cipher ci = Cipher.getInstance(algo);
        ci.init(Cipher.DECRYPT_MODE, key);
        byte[] encryB = Base64.getDecoder().decode(encrypted);
        byte[] decryB = ci.doFinal(encryB);
        return new String(decryB);
    }
}
