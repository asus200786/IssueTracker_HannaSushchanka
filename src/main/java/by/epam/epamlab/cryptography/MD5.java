package by.epam.epamlab.cryptography;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5 {
	private final Logger logger = LoggerFactory.getLogger(MD5.class);
	
	private static final String CRYPTOGRAPHY_ERROR = "Cryptography Error.";
	private static final String MD52 = "md5";//

	public String getHash(String password) {
		MessageDigest md5;
		StringBuffer hexString = new StringBuffer();

		try {
			md5 = MessageDigest.getInstance(MD52);

			md5.reset();
			md5.update(password.getBytes());

			byte messageDigest[] = md5.digest();
			for (int i = 0; i < messageDigest.length; i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error(CRYPTOGRAPHY_ERROR, e);
		}
		return hexString.toString();
	}
}
