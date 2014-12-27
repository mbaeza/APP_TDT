/**
 * 
 */
package cl.bch.motorpagos.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import cl.bch.motorpagos.vo.RespuestaValidaPinVO;
import cl.bch.motorpagos.vo.TransferenciaVO;

/**
 * @author boyanedel
 *
 */
public final class MotorPagosHelper {
	public static final String FORMATO_FECHA = "dd/MM/yyyy";
	public static final String FORMATO_FECHA_CONTABLE = "yyyyMMdd";
	public static final String FORMATO_HORA = "hh:mm:ss";
	public static final String LOCALE_MAY = "ES";
	public static final String LOCALE_MIN = "es";
	
	/**
	 * 
	 */
	private MotorPagosHelper(){
		
	}
	
	/**
	 * 
	 * @param formato
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public static Date generaFecha(String formato, String fecha)throws ParseException{
		
		SimpleDateFormat sdf =  new SimpleDateFormat(formato, new Locale(MotorPagosHelper.LOCALE_MIN, MotorPagosHelper.LOCALE_MAY));
		Date date = null;
		date = sdf.parse(fecha);
		
		return date;
	}
	
	/**
	 * 
	 * @param formato
	 * @param hora
	 * @return
	 * @throws ParseException
	 */
	public static Time generaHora(String formato, String hora)throws ParseException{
		
		SimpleDateFormat sdfh = new SimpleDateFormat(formato, new Locale(MotorPagosHelper.LOCALE_MIN, MotorPagosHelper.LOCALE_MAY));
		Time time = new Time(sdfh.parse(hora).getTime());
		
		return time;
	}
	
	/**
	 * 
	 * @param nroCuenta
	 * @return
	 */
	public static String enmascaraCuenta(String nroCuenta, String claseCuenta, String tipoCuenta){
		String mascara;
		if(claseCuenta!=null && "CVICCH".equals(claseCuenta) || "JUV".equals(tipoCuenta)){  
			mascara = ConstantesMotorPagos.GLOSA_TIPO_CUENTA[1] + " ..." + nroCuenta.substring(nroCuenta.length()-4, nroCuenta.length());
		}else{
			mascara = ConstantesMotorPagos.GLOSA_TIPO_CUENTA[0] + " ..." + nroCuenta.substring(nroCuenta.length()-4, nroCuenta.length());
		}
		return mascara;
	}
	
	/**
	 * 
	 * @param rut
	 * @return
	 */
	public static String cleanRut(String rut){
		if(rut!=null){
			return rut.replace("-", "").replace(".", "");
		}else{
			return "";
		}
	}
	
	/**
	 * 
	 * @param transferencia
	 * @return
	 */
	public static TransferenciaVO formateaTransferenciaVO(TransferenciaVO transferencia){
		
		transferencia.setRutClienteOrigen(	MotorPagosHelper.cleanRut(transferencia.getRutClienteOrigen()));
		transferencia.setRutClienteOrigen(	MotorPagosHelper.formateaString(transferencia.getRutClienteOrigen(), "0", 10));
		transferencia.setCtaOrigen(			MotorPagosHelper.formateaString(transferencia.getCtaOrigen(), "0", 10));
		transferencia.setCtaDestino(		MotorPagosHelper.formateaString(transferencia.getCtaDestino(), "0", 10));
		transferencia.setMonto(				MotorPagosHelper.formateaString(transferencia.getMonto()+"00", "0", 13));
		
		return transferencia;
	}
	
	/**
	 * 
	 * @param original
	 * @param caracter
	 * @param largoDeseado
	 * @return
	 */
	public static String formateaString(String original, String caracter, int largoDeseado){		
		String newString;
		if(original != null && original.length()>largoDeseado){
			newString = original.substring(0, largoDeseado);
		}else{
			if(original==null){
				newString = "";
			}else{
				newString = original;
			}
			while(largoDeseado>newString.length()){
				newString = caracter + newString;			
			}
		}
		
		return newString;
	}
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public static boolean isMarcaClientePermitida(String token){
		for(int i=0;i<ConstantesMotorPagos.CODIGOS_MARCA_CLIENTE.length;i++){
			if(token.equalsIgnoreCase(ConstantesMotorPagos.CODIGOS_MARCA_CLIENTE[i])){
				return true;
			}
		}
		return false;
	}
		
	/**
	 * 
	 * @param token
	 * @return
	 */
	public static boolean isTokenPermitido(String token){
		for(int i=0;i<ConstantesMotorPagos.PRODUCTOS_PERMITIDOS.length;i++){
			if(token.equalsIgnoreCase(ConstantesMotorPagos.PRODUCTOS_PERMITIDOS[i])){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getFechaActual(){
		Calendar c=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", new Locale(MotorPagosHelper.LOCALE_MIN, MotorPagosHelper.LOCALE_MAY));
		return sdf.format(c.getTime());
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getFechaString(Date fechaHoraCreacion){		
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", new Locale(MotorPagosHelper.LOCALE_MIN, MotorPagosHelper.LOCALE_MAY));
		return sdf.format(fechaHoraCreacion);
	}
	
	/**
	 * 
	 * @param fechaHoraCreacion
	 * @return
	 */
	public static String getHoraString(Date fechaHoraCreacion){
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss", new Locale(MotorPagosHelper.LOCALE_MIN, MotorPagosHelper.LOCALE_MAY));
		return sdf.format(fechaHoraCreacion);
	}
	
	/**
	 * 
	 * @return
	 * @throws ParseException 
	 */
	public static Date getFechaDate(String fechaHora) throws ParseException{		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale(MotorPagosHelper.LOCALE_MIN, MotorPagosHelper.LOCALE_MAY));		
		return sdf.parse(fechaHora);
	}
	
	/**
	 * 
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public static Date getFechaDateddMMyyyy(String fecha) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", new Locale(MotorPagosHelper.LOCALE_MIN, MotorPagosHelper.LOCALE_MAY));		
		return sdf.parse(fecha);
	}
	
	/**
	 * 
	 * @param longitud
	 * @return
	 */
	public static String getCadenaAlfanumAleatoria(int longitud){
		String cadenaAleatoria = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random ra = new Random();
		Random r = new Random(milis + ra.nextInt());
		int i = 0;
		while ( i < longitud){
			char c = (char)r.nextInt(255);
			if (c >= '0' && c <='9' || c >='A' && c <='Z' || c >='a' && c <='z'){
				cadenaAleatoria += c;
				i ++;
			}
		}
		return cadenaAleatoria;
	}
	
	/**
	 * 
	 * @param llaveCuenta
	 * @return
	 */
	public static String cleanCuenta(String llaveCuenta){
		return Long.parseLong(llaveCuenta)+"";
	}	
	
	/**
	 * 
	 * @param estado
	 * @return
	 */
	public static boolean isActivaVigente(String estado){
		for(int i=0; i<ConstantesMotorPagos.ESTADOS_CUENTAS.length;i++){
			if(estado.equalsIgnoreCase(ConstantesMotorPagos.ESTADOS_CUENTAS[i])){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param respValDisp
	 * @return
	 */
	public static boolean esDispAdmin(RespuestaValidaPinVO respValDisp){
		return respValDisp.getTipoDispositivo().equals(ConstantesMotorPagos.TIPO_DISP_ADMIN);		
	}
	
	/**
	 * 
	 * @param respValDisp
	 * @return
	 */
	public static boolean esDispCli(RespuestaValidaPinVO respValDisp){
		return respValDisp.getTipoDispositivo().equals(ConstantesMotorPagos.TIPO_DISP_CLIENTE);		
	}
	
	/**
	 * 
	 * @param numeroRegistros
	 * @return
	 */
	public static boolean esCantidadPermitida(int numeroRegistros){
		return numeroRegistros <= Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.MAX_NRO_REGISTROS_EXPORT));				
	}
	
	/**
	 * 
	 * @param rut
	 * @return
	 */
	public static String formatearRutPuntosGuion(String rut){
        int cont=0;
        String format;
        String rutNew = rut.replace(".", "");
        rutNew = rutNew.replace("-", "");
        format = "-"+rutNew.substring(rutNew.length()-1);
        for(int i = rutNew.length()-2;i>=0;i--){
            format = rutNew.substring(i, i+1)+format;
            cont++;
            if(cont == 3 && i != 0){
                format = "."+format;
                cont = 0;
            }
        }
        return format;
    }
	
	/**
	 * 
	 * @param rut
	 * @return
	 */
	public static String formatearRutGuion(String rut){
        String format;
        String rutNew = rut.replace(".", "");
        rutNew = rutNew.replace("-", "");
        
        String digito = rutNew.substring(rutNew.length()-1);
        int numero = Integer.parseInt(rutNew.substring(0, rutNew.length()-1));
        format = numero+"-"+digito;
       
        return format;
    }
	/**
	 * 
	 * @param monto
	 * @return
	 */
	public static String formatearMonto(String monto){
		String format = "";
		try{
			String montoNew = String.valueOf(Integer.parseInt(monto));
			int cont=0;	        
	        for(int i = montoNew.length()-1;i>=0;i--){
	            format = montoNew.substring(i, i+1)+format;
	            cont++;
	            if(cont == 3 && i != 0){
	                format = "."+format;
	                cont = 0;
	            }
	        }
		}catch(NumberFormatException e){
			format = "No disponible.";
		}		
        return format;
	}
	
	/**
	 * 
	 * @param idTrx
	 * @return
	 */
	public static String formatearIdTrx(String idTrx){
		if(idTrx==null){
			return "No disponible.";
		}else if(idTrx.length()<=11){
			return idTrx;
		}else{
			return idTrx.substring(idTrx.length()-11);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static boolean isValid(String valor){
		if(valor==null || valor.length()==0 || "null".equalsIgnoreCase(valor.trim())){
			return false;
		}else{
			return true;
		}
	}	
	
	/**
	 * 
	 * 
	 * @param rut
	 * @return
	 */
	public static int getValorNumericoRut(String rut){
		String nuevoRut = MotorPagosHelper.cleanRut(rut);
		nuevoRut = nuevoRut.substring(0, nuevoRut.length()-1);
		return Integer.parseInt(nuevoRut);
	}
	
	/**
	 * 
	 * @param original
	 * @param largo
	 * @return
	 */
	public static String formateaString(String original, int largo){
		String respuesta;
		if(original==null || original.isEmpty()){
			respuesta = "";
		}else if(original.length()<=largo){
			respuesta = original;
		}else{
			respuesta = original.substring(0, largo);
		}
		return respuesta;
	}
	
	/**
	 * 
	 * @return
	 */
	public static boolean isEmpresa(String idApp){
		if(ConstantesMotorPagos.ID_APP_PAGOS.equals(idApp)){
			return false;
		}else{
			if(ConstantesMotorPagos.ID_APP_COBROS.equals(idApp)){
				return true;
			}else{
				return false;
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getFechayyyyMMdd(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", new Locale(MotorPagosHelper.LOCALE_MIN, MotorPagosHelper.LOCALE_MAY));
		return sdf.format(c.getTime());
	}
	
//	/**
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args){
//		System.out.println(MotorPagosHelper.getFechayyyyMMdd());		
//	}

}
