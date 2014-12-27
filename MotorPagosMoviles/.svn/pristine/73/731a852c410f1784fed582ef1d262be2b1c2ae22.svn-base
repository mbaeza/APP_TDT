/**
 * 
 */
package cl.bch.motorpagos.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import cl.bch.motorpagos.exceptions.MotorPagosException;
import cl.bch.motorpagos.util.MotorPagosHelper;

/**
 * @author boyanedel
 * 
 */
public final class CryptData {
	
	private static final byte bytesKeyE[] = {77, 69, 74, 84, 67, 45, 57, 48, 
											 77, 69, 74, 84, 67, 45, 57, 48, 
											 77, 69, 74, 84, 67, 45, 57, 48};
	/**
	 * 
	 */
	private CryptData(){
		
	}
	/**
	 * 
	 * @param cleartext
	 * @param key
	 * @return
	 * @throws MotorPagosException
	 */
	public static String encriptar(String data)	throws MotorPagosException {
		String dataNew = MotorPagosHelper.formateaString(data, "0", 24);
		return crypt(dataNew, Cipher.ENCRYPT_MODE);
	}

	/**
	 * 
	 * @param ciphertext
	 * @param key
	 * @return
	 * @throws MotorPagosException
	 */
	public static String desEncriptar(String data) throws MotorPagosException {
		String dataNew = MotorPagosHelper.formateaString(data, "0", 48);
		return crypt(dataNew, Cipher.DECRYPT_MODE);
	}

	/**
	 * 
	 * @param input
	 * @param key
	 * @param mode
	 * @return
	 * @throws Exception
	 */
	private static String crypt(String input, int mode)	throws MotorPagosException {
		Cipher ecipher = null;

		try{
			if (mode == Cipher.ENCRYPT_MODE) {
				javax.crypto.SecretKey keyE = new SecretKeySpec(bytesKeyE,"DESede");
				ecipher = Cipher.getInstance("DESede/ECB/NoPadding");
				ecipher.init(1, keyE);
				
				return new String(Hex.encodeHex(ecipher.doFinal(input.getBytes())));
				
			} else if (mode == Cipher.DECRYPT_MODE) {
				javax.crypto.SecretKey keyD = new SecretKeySpec(bytesKeyE,"DESede");
				ecipher = Cipher.getInstance("DESede/ECB/NoPadding");
				ecipher.init(2, keyD);
				
				return new String(ecipher.doFinal(Hex.decodeHex(input.toCharArray())));
				
			} else {
				throw new MotorPagosException();
			}
		}catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | DecoderException e ){
			throw new MotorPagosException(e);
		}
	}
	
	
//	public static void main(String args[]){
//		try {
//			System.out.println(CryptData.encriptar("000000000008510207309CTD"));
//			System.out.println(CryptData.desEncriptar(CryptData.encriptar("000000000008510207309CTD")));
//			System.out.println(CryptData.desEncriptar("f75c4d7bb583cf261bb8895fa313ded148b14bb2019037b1"));
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
