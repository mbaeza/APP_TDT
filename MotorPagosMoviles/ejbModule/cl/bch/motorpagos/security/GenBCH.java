/**
 * 
 */
package cl.bch.motorpagos.security;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import cl.bch.motorpagos.util.ConfigurationLoader;

/**
 * @author boyanedel
 *
 */
public final class GenBCH {

	private static Cipher ecipher = null;
	private static Cipher dcipher = null;
	private static final byte bytesKeyE[] = {
		77, 69, 74, 84, 67, 45, 57, 48
	};
	
	private static final byte bytesKeyD[] = {
		49, 50, 35, 67, 65, 66, 64, 37
	};
	
	/**
	 * 
	 */
	private GenBCH(){
		
	}
	
	/**
	 * 
	 * @param clave
	 * @return
	 * @throws GeneralSecurityException
	 */
	public static String genClave(String clave) throws GeneralSecurityException {
		synchronized (ConfigurationLoader.class){
			if (ecipher == null) {
				javax.crypto.SecretKey keyE = new SecretKeySpec(bytesKeyE, "DES");
				ecipher = Cipher.getInstance("DES/ECB/NoPadding");
				ecipher.init(1, keyE);
			}
			if (dcipher == null) {
				javax.crypto.SecretKey keyD = new SecretKeySpec(bytesKeyD, "DES");
				dcipher = Cipher.getInstance("DES/ECB/NoPadding");
				dcipher.init(2, keyD);
			}
		}
		String data = "00000000" + clave;
		data = data.substring(data.length() - 8);
		
		return new String(Hex.encodeHex(dcipher.doFinal(ecipher.doFinal(data.getBytes()))));
	}
	
//	public static void main(String args[])
//	{
//		try
//		{
//			System.out.println(genClave("111111"));
//			System.out.println(reverseClave(genClave("111111")));
//		}
//		catch(Exception e)
//		{
//			System.out.println("Error");
//		}
//	}
}
