package by.epam.epamlab.cryptography;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	public String getHash(String password) {
		MessageDigest md5;
		StringBuffer hexString = new StringBuffer();

		try {
			md5 = MessageDigest.getInstance("md5");

			md5.reset();
			md5.update(password.getBytes());

			byte messageDigest[] = md5.digest();
			for (int i = 0; i < messageDigest.length; i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hexString.toString();
	}
}
