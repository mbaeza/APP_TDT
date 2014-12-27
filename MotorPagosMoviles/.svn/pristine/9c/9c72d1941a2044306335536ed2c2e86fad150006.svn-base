/**
 * 
 */
package cl.bch.motorpagos;

import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.exceptions.MotorPagosException;
import cl.bch.motorpagos.util.ConfigurationLoader;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.*;
import cl.bch.motorpagos.wsclient.*;

/**
 * @author boyanedel
 *
 */
public class MotorPagosServicesWS {
	private static final Logger logger = LoggerFactory.getLogger(MotorPagosServicesWS.class);
	/**
	 * 
	 * @param loginVO
	 * @return RespuestaLoginClienteVO
	 */
	public RespuestaLoginClienteVO loginCliente(LoginClienteVO loginVO){
		
		ClienteCS000152 client = new ClienteCS000152();		
		RespuestaLoginClienteVO respuesta = client.callCS000152(loginVO);
			
		return respuesta;
	}
	
	/**
	 * 
	 * @param rutCliente
	 * @return RespuestaFichaChicaVO
	 */
	public RespuestaFichaChicaVO consultaFichaChicaCliente(String rutCliente){
		ObtenerDatosFichaChicaVO obtenerDatosFichaChicaVO = new ObtenerDatosFichaChicaVO(); 
		
		obtenerDatosFichaChicaVO.setRut(rutCliente);
		ClienteCS000191_ObtenerDatosFichaChica client = new ClienteCS000191_ObtenerDatosFichaChica();		
		RespuestaFichaChicaVO fichaCliente = client.callCS000191(obtenerDatosFichaChicaVO);
		
		return fichaCliente;
	}
	
	/**
	 * 
	 * @param rutCliente
	 * @return MarcaClienteVO
	 */
	public RespuestaMarcaClienteVO consultaMarcaCliente(String rutCliente){
		ObtenerDatosFichaChicaVO obtenerDatosFichaChicaVO = new ObtenerDatosFichaChicaVO(); 
		RespuestaMarcaClienteVO respuesta = new RespuestaMarcaClienteVO();
		
		obtenerDatosFichaChicaVO.setRut(rutCliente);
		ClienteCS000191_ObtenerDatosFichaChica client = new ClienteCS000191_ObtenerDatosFichaChica();		
		RespuestaFichaChicaVO fichaCliente = client.callCS000191(obtenerDatosFichaChicaVO);
		
		respuesta.setRetorno(fichaCliente.getRetorno());  
		respuesta.setCodigoMarca(fichaCliente.getCodigoMarca());
		return respuesta;
	}
	
	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public DispositivosClienteVO obtenerDispositivosSeguridad(LoginClienteVO loginVO) throws MotorPagosException {
		ClienteCS000176_getDispositivos clienteCS000176 = new ClienteCS000176_getDispositivos();
		
		ArrayList<DispositivoSeguridadVO> dispositivosList = clienteCS000176.callCS000176(loginVO);	
		ArrayList<DispositivoSeguridadVO> resultado = new ArrayList<DispositivoSeguridadVO>();
		
		if(dispositivosList!=null && !dispositivosList.isEmpty()){
			boolean tieneDigipass = false;
			boolean tieneDigiCard = false;
			
			for(int i=0;i<dispositivosList.size();i++){
				DispositivoSeguridadVO disp = dispositivosList.get(i);
				
				if(disp.getTipo().equalsIgnoreCase(ConstantesMotorPagos.ID_DISP_DIGIPASS)){
					tieneDigipass = true;
					break;
				}else if(disp.getTipo().equalsIgnoreCase(ConstantesMotorPagos.ID_DISP_DIGICARD)){
					tieneDigiCard = true;
				}					
			}
			
			if(tieneDigipass){
				for(int i=0;i<dispositivosList.size();i++){
					DispositivoSeguridadVO disp = dispositivosList.get(i);
					 if(disp.getTipo().equalsIgnoreCase(ConstantesMotorPagos.ID_DISP_DIGIPASS)){
						resultado.add(disp);						
					}	
				}
			}else if(!tieneDigipass && tieneDigiCard){
				ClienteCS000176_obtenerCoordenadas clienteCS000176obtener = new ClienteCS000176_obtenerCoordenadas();
				CoordenadasDispositivoVO coordenadas = clienteCS000176obtener.callCS000176(loginVO);
				
				if(coordenadas==null){
					logger.error("No fue posible obtener las coordenadas del dispositivo.");
					throw new MotorPagosException();
				}else{
					for(int i=0;i<dispositivosList.size();i++){
						DispositivoSeguridadVO disp = dispositivosList.get(i);
						 if(disp.getTipo().equalsIgnoreCase(ConstantesMotorPagos.ID_DISP_DIGICARD)){
							disp.setCoordenadas(coordenadas);
							resultado.add(disp);
						}	
					}					
				}
			}
		}else{
			logger.error("No se encontraron dispositivos validos asociados.");
		}
		DispositivosClienteVO dispositivosVO = new DispositivosClienteVO();
		dispositivosVO.setDispSeguridad(resultado);
		
		return dispositivosVO;
	}
	
	/**
	 * 
	 * @param validarVO
	 *  @return
	 */
	public RespuestaVO validarDispositivoSeguridad(ValidarDispositivoSegVO validarVO){
		RespuestaVO respuesta = null;
		
		logger.debug(validarVO.toString());
		
		ClienteCS000176_validaDispositivo clienteCS000176 = new ClienteCS000176_validaDispositivo();
		respuesta = clienteCS000176.callCS000176(validarVO);
						
		return respuesta;		
	} 
	
	/**
	 * 
	 * @param clienteVO
	 * @return boolean
	 */
	public RespuestaVO validarDatosCliente(ClienteVO clienteVO){
		
		logger.debug(clienteVO.toString());

		ObtenerDatosFichaChicaVO obtenerDatosFichaChicaVO = new ObtenerDatosFichaChicaVO(); 
		
		obtenerDatosFichaChicaVO.setRut(clienteVO.getRut());
		ClienteCS000191_ObtenerDatosFichaChica client = new ClienteCS000191_ObtenerDatosFichaChica();		
		RespuestaFichaChicaVO fichaChicaVO = client.callCS000191(obtenerDatosFichaChicaVO);
		
		if (fichaChicaVO.getRetorno().getCodigoRetorno().equalsIgnoreCase(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			clienteVO.setMarca(fichaChicaVO.getCodigoMarca());
			clienteVO.setNombre(fichaChicaVO.getNombre());
			clienteVO.setDireccion(fichaChicaVO.getDireccion());
			
			return MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA);
		}
		else {
			return MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}
	}
	/**
	 * 
	 * @param loginVO
	 * @return RespuestaLoginComercioVO
	 */
	public RespuestaLoginComercioVO getInfoEmpresa(LoginComercioVO loginVO){
		RespuestaLoginComercioVO respuesta;				
		if(MotorPagosHelper.getValorNumericoRut(loginVO.getRutEmpresa())<=50000000){
			ClienteCS000028 cliente = new ClienteCS000028();
			respuesta = cliente.callCS000028(loginVO);
		}else{
			ClienteCS000029 cliente = new ClienteCS000029();
			respuesta = cliente.callCS000029(loginVO);
		}
				
		return respuesta;
	}
	
	/**
	 * 
	 * @param atribucionesVO
	 * @return
	 */
	public RespuestaVO validarPoderesApoderado(AtribucionesComercioVO atribucionesVO){
		
		ClienteCS000384 cliente = new ClienteCS000384();
		RespuestaVO respuesta = null;
				
		for(int i=0;i<ConstantesMotorPagos.ATRIBUCIONES_APODERADO.length;i++){
			cliente = new ClienteCS000384();
			
			cliente.setIdPoder(ConstantesMotorPagos.ATRIBUCIONES_APODERADO[i]);
			respuesta = cliente.callCS000384(atribucionesVO);
			
			if(respuesta.getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_NOOK)){
				break;
			}
		}
				
		return respuesta;
	}
	
	/**
	 * 
	 * @param pagoVO
	 * @return RespuestaVO
	 */
	public RespuestaVO transferirMonto(TransferenciaVO transferenciaVO){

		ClienteCS003006 cliente = new ClienteCS003006();
		RespuestaVO respuesta = cliente.callCS003006(transferenciaVO);
			
		return respuesta;
	}
	
	/**
	 * 
	 * @param idCliente
	 * @return
	 */
	public RespuestaMediosDePagoVO obtenerMediosDePagoCliente(String rutCliente) {
		
		ClienteCS000026 cliente = new ClienteCS000026();
		
		LoginClienteVO loginVO = new LoginClienteVO();
		loginVO.setRut(rutCliente);
		
		RespuestaMediosDePagoVO respuesta = cliente.callCS000026(loginVO);
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param consultaVO
	 * @return
	 */
	public RespuestaConsultaSaldoVO consultaSaldoCta(String nroCuenta){
		
		ClienteCS000069 cliente = new ClienteCS000069();		
		RespuestaConsultaSaldoVO respuesta = cliente.callCS000069(nroCuenta);
		
		return respuesta;
	}
	
	
	/**
	 * 
	 * @param transferencia
	 * @return
	 */
	public RespuestaVO sendMailCliente(TransferenciaVO transferencia){
		
		ClienteCS000177 cliente = new ClienteCS000177();
		RespuestaVO respuesta = cliente.callCS000177(transferencia);
		
		return respuesta;
	}

	/**
	 * 
	 * @return 
	 */
	public String obtieneFechaContable(String rutCliente){
		String fechaContable = null;
		
		Calendar fechaHora = Calendar.getInstance();
		int hora = fechaHora.get(Calendar.HOUR_OF_DAY);
		int corteFecha = Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_CORTE_FECHA_CONTABLE));
		
		FechaContableVO fechaContableVO = new FechaContableVO();
		fechaContableVO.setFechaActual(MotorPagosHelper.getFechayyyyMMdd());
		fechaContableVO.setRutCliente(rutCliente);
		
		if (hora < corteFecha){
			ClienteCS002028_calculoFechaHabil cliente = new ClienteCS002028_calculoFechaHabil();
			
			fechaContable = cliente.callCS002028(fechaContableVO);
		}else{
			ClienteCS002028_calculoFechaHabilVariable cliente = new ClienteCS002028_calculoFechaHabilVariable();
			fechaContable = cliente.callCS002028(fechaContableVO);
		}
		
		if(fechaContable!=null){
			fechaContable = fechaContable.substring(0, 4) + "-" + fechaContable.substring(4, 6) + "-" + fechaContable.substring(6);
		}
		
		return fechaContable;
	}
	
//	/**
//	 * 
//	 * @param args
//	 */
//	public static void main(String args[]){
//		MotorPagosServicesWS services = new MotorPagosServicesWS();
//		System.out.println(services.obtieneFechaContable());
//	}

}
