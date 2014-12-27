package cl.bch.motorpagos.security;

import cl.bch.motorpagos.util.ConstantesMotorPagos;


public final class Seguridad {

	/**
	 * 
	 */
	private Seguridad(){
		
	}
	
	/**
	 * 
	 * @param clave
	 * @param pin
	 * @param hora
	 * @param numeroDispositivo
	 * @throws ClaveNoValidaException
	 */
	public static void validarClaveDinamica(String clave, String pin, String hora, String numeroDispositivo) throws ClaveNoValidaException{
		if (pin.length()!=5){ 
			throw new ClaveNoValidaException("Pin no tiene 5 digitos");
		}
		if (hora.length()!=5){
			throw new ClaveNoValidaException("Hora debe estar en formato HHMMSSS");
		}	
		if (numeroDispositivo.length()!=ConstantesMotorPagos.LARGO_ID_DISPOSITIVO){
			throw new ClaveNoValidaException("id dispositivo debe tener largo 30");
		}
		if (clave.length()!=11){
			throw new ClaveNoValidaException("La Clave dinamica debe ser de largo 11");
		}
		
		StringBuffer buf=new StringBuffer();
		int l=hora.length();
		for(int i=0;i<5;i++){
			int aux=(pin.charAt(i)-'0') * (hora.charAt(l-i-1)-'0');
			aux=aux%ConstantesMotorPagos.LARGO_ID_DISPOSITIVO;
			buf.append(numeroDispositivo.charAt(aux));
		}
		if (!buf.toString().equals(clave.substring(6))){
			throw new ClaveNoValidaException("Clave no coincide");
		}
	}
	
//	public static void main(String[] args) throws Exception{
//		Calendar c=Calendar.getInstance();
//		SimpleDateFormat sdf=new SimpleDateFormat("mmssSSS");
//		validarClaveDinamica("1234568e1o1", "12345", "06077", "1234567890abcdefghijklmnopqrst");
//	}
}
