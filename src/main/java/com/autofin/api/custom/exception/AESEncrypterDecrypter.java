package com.autofin.api.custom.exception;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.autofin.api.bean.MobileNumberEncrptDecrptBean;

/**
 * Encrypt/Decrypt data using symmetric encryption and key
 * @author Madhura Oak
 * 
 */
public class AESEncrypterDecrypter {
	private static final byte[] IV = "1234567890123456".getBytes();
	private static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5Padding";
	private static final String AES = "AES";
	
	/**
	 * Encrypt data using AES/CBC/PKCS5Padding. IV appears first in the returned data array followed with encrypted request. 
	 * @param string to be encrypted
	 * @param key used for encryption
	 * @return encrypted result
	 */
	public static byte[] encrypt(String string, byte[] key) {
		byte[] encryptedValue = null;
		try {
			SecretKeySpec secretKeySpec=new SecretKeySpec(key,AES);
			Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING); 
			cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec, new IvParameterSpec(IV));
			byte[] dataArr = string.getBytes();
			byte[] ivAndData = new byte[IV.length + dataArr.length];
			System.arraycopy(IV, 0, ivAndData, 0, IV.length);
			System.arraycopy(dataArr, 0, ivAndData, IV.length,dataArr.length);			
			encryptedValue = cipher.doFinal(ivAndData);
		}
		catch(NoSuchAlgorithmException exp) {
			exp.printStackTrace();
		}
		catch(NoSuchPaddingException exp) {
			exp.printStackTrace();
		}
		catch(IllegalBlockSizeException exp) {
			exp.printStackTrace();
		}
		catch(BadPaddingException exp) {
			exp.printStackTrace();
		}
		catch(InvalidKeyException exp) {
			exp.printStackTrace();
		}
		catch(InvalidAlgorithmParameterException exp) {
			exp.printStackTrace();
		}
		return encryptedValue;
	}
	
	/**
	 * Decrypt encrypted data using AES/CBC/PKCS5Padding and a secret key. IV should appear first in the byte array argument followed with encrypted value of response 
	 * @param data to be decrypted
	 * @param key
	 * @return decrypted value
	 */
	public static byte[] decrypt(byte[] encrypted, byte[] key) {
		byte[] decryptedData = null;
		try {
			SecretKeySpec secretKeySpec=new SecretKeySpec(key,AES);
			Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
			cipher.init(Cipher.DECRYPT_MODE,secretKeySpec, new IvParameterSpec(IV));
			byte[] decryptedDataWithIV = cipher.doFinal(encrypted);			
			decryptedData = new byte[decryptedDataWithIV.length-IV.length];			
			System.arraycopy(decryptedDataWithIV, IV.length, decryptedData, 0, decryptedData.length);
		}
		catch(NoSuchAlgorithmException exp) {
			exp.printStackTrace();
		}
		catch(NoSuchPaddingException exp) {
			exp.printStackTrace();
		}
		catch(IllegalBlockSizeException exp) {
			exp.printStackTrace();
		}
		catch(BadPaddingException exp) {
			exp.printStackTrace();
		}
		catch(InvalidKeyException exp) {
			exp.printStackTrace();
		}
		catch(InvalidAlgorithmParameterException exp) {
			exp.printStackTrace();
		}		
		return decryptedData;
	}
}
