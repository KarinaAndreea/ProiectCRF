package encryption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import javax.crypto.spec.SecretKeySpec;

import com.sun.mail.util.BASE64DecoderStream;

public class DecryptData {

	private static Cipher ecipher;
	private static Cipher dcipher;

	public static String decrypt(String input, String key) {

		try {

			byte[] decodedKey = Base64.getDecoder().decode(key);
			SecretKey originalKey = new SecretKeySpec(decodedKey, 0,
					decodedKey.length, "DES");
			ecipher = Cipher.getInstance("DES");
			dcipher = Cipher.getInstance("DES");
			ecipher.init(Cipher.ENCRYPT_MODE, originalKey);
			dcipher.init(Cipher.DECRYPT_MODE, originalKey);
			String decrypted = decrypt(input);
			return decrypted;
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm:" + e.getMessage());
			return "error";
		} catch (NoSuchPaddingException e) {
			System.out.println("No Such Padding:" + e.getMessage());
			return "error";
		} catch (InvalidKeyException e) {
			System.out.println("Invalid Key:" + e.getMessage());
			return "error";
		}
	}

	public static String decrypt(String str) {

		try {
			byte[] dec = BASE64DecoderStream.decode(str.getBytes());
			byte[] utf8 = dcipher.doFinal(dec);
			return new String(utf8, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}