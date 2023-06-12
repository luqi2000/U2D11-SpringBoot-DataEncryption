package luqmanmohammad.U2D11SpringBootDataEncryption.entities;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;
public class CreditCardConverter implements AttributeConverter<String, String> {
	private static final String ALGORITHM = "AES/ECB/PKCS5Padding"; //name of algorithm used
	private static final String secret = "mysup3rs3cr3tttt"; //be attention key need to be long String
	
	@Override
	public String convertToDatabaseColumn(String creditCardNumber) {

		try {
			Key key = new SecretKeySpec(secret.getBytes(), "AES");
			
			Cipher c = Cipher.getInstance(ALGORITHM); //we need this for use algorithm so i create an instance of the alghorithm

			c.init(Cipher.ENCRYPT_MODE, key); //for Encrypt = decifrare we need method and key

			return Base64.getEncoder().encodeToString(c.doFinal(creditCardNumber.getBytes()));// for return a String 
			//with limited set of characters 
			//c.doFinal is doing Encrypt

			} catch (RuntimeException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
					| IllegalBlockSizeException | BadPaddingException e) {
				System.out.println(e);
				throw new RuntimeException();
			}

	}
	
	
	//here is reversing 
	@Override
	public String convertToEntityAttribute(String encryptedCreditCard) {
		Key key = new SecretKeySpec(secret.getBytes(), "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key); //initialize algorithm

			return new String(c.doFinal(Base64.getDecoder().decode(encryptedCreditCard)));

		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException();
		}

	}

}
