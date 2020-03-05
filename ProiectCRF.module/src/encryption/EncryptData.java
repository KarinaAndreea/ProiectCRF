package encryption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import com.sun.mail.util.*;

public class EncryptData {

	private static Cipher ecipher;

	private static SecretKey key;

	public static String enc(String input) throws IllegalBlockSizeException,
			BadPaddingException {
		try {
			key = KeyGenerator.getInstance("DES").generateKey();

			ecipher = Cipher.getInstance("DES");

			ecipher.init(Cipher.ENCRYPT_MODE, key);

			byte encoded[] = key.getEncoded();
			String encodedKey = Base64.getEncoder().encodeToString(encoded);

			String encrypted = encrypt(input);


			encodedKey = encodedKey.concat("_");
			encodedKey = encodedKey.concat(encrypted);

			return encodedKey;
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm:" + e.getMessage());
			return "Erorr";
		} catch (NoSuchPaddingException e) {
			System.out.println("No Such Padding:" + e.getMessage());
			return "Erorr";
		} catch (InvalidKeyException e) {
			System.out.println("Invalid Key:" + e.getMessage());
			return "Erorr";
		}
	}

	public static String encrypt(String str) {
		try {
			byte[] utf8 = str.getBytes("UTF8");
			byte[] enc = ecipher.doFinal(utf8);

			enc = BASE64EncoderStream.encode(enc);
			return new String(enc);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}