package cl.bch.motorpagos.ejb;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.logger.LogLine;
import cl.bch.logger.MotorPagosLogger;
import cl.bch.motorpagos.MotorPagosManager;
import cl.bch.motorpagos.util.*;
import cl.bch.motorpagos.vo.*;

/**
 * Session Bean implementation class PagoServicioBean
 */
@Stateless
@WebService(serviceName="PagoServicioServices")
public class PagoServicioBean implements PagoServicioBeanLocal{
	private static final Logger logger = LoggerFactory.getLogger(PagoServicioBean.class);
	private static final String SEPARADOR_TEXTO = "*******************************************************";
	

    /**
	 * Default constructor. 
	 */
	public PagoServicioBean() {
		super();
	}

	@Override
	@WebMethod(operationName="efectuarPago")
	public RespuestaPagoVO efectuarPago(PagoVO pagoVO) {
		
		/**********************LOG STANDARD ******************************/
		LogLine linea = new LogLine("nodo",	ConstantesMotorPagos.LOG_CANAL, ConstantesMotorPagos.LOG_IDPROGRAMA, ConstantesMotorPagos.LOG_OPCION_PAGAR);
		/**********************LOG STANDARD ******************************/
		
		logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio Pago Operación : {}", pagoVO.getIdTrxPago());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
		
		logger.debug(pagoVO.toString());   	
    	
		RespuestaPagoVO respuesta = null;
		TransferenciaVO datosPago = new TransferenciaVO();
		if(pagoVO.validaParametros()){
			MotorPagosManager manager = new MotorPagosManager();
			respuesta = manager.efectuarPago(pagoVO, datosPago);			
		}else{
			respuesta = new RespuestaPagoVO();
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
		}
    	
    	logger.debug(respuesta.toString());
    	
    	/**********************LOG STANDARD ******************************/		
		String dataVariable = MotorPagosHelper.formateaString(respuesta.getRetorno().getCodigoRetorno(), "0", 4)
				  			+ MotorPagosHelper.formateaString(respuesta.getRetorno().getGlosaRetorno(), " ", 30)
				  			+ MotorPagosHelper.formateaString(pagoVO.getIdTrxPago(), " ", 29)
				  			+ MotorPagosHelper.formateaString(datosPago.getIdVendedor(), " ", 20)				  			
				  			+ MotorPagosHelper.formateaString(String.valueOf(datosPago.getMonto()), " ", 38)
							+ MotorPagosHelper.formateaString(MotorPagosHelper.cleanRut(String.valueOf(datosPago.getRutClienteOrigen())), " ", 10)
							+ MotorPagosHelper.formateaString(datosPago.getTokenCtaOrigen(), " ", 3)
							+ MotorPagosHelper.formateaString(datosPago.getCtaOrigen(), " ", 20)
							+ MotorPagosHelper.formateaString(MotorPagosHelper.cleanRut(String.valueOf(datosPago.getRutComercio())), " ", 10)
							+ MotorPagosHelper.formateaString(datosPago.getTokenCtaDestino(), " ", 3)
							+ MotorPagosHelper.formateaString(datosPago.getCtaDestino(), " ", 20)
							+ MotorPagosHelper.formateaString(datosPago.getTipoComercio(), " ", 7)
							+ MotorPagosHelper.formateaString(datosPago.getNombreComercio(), " ", 50);

		linea.setValues("", "", MotorPagosHelper.cleanRut(String.valueOf(datosPago.getRutClienteOrigen())), MotorPagosHelper.cleanRut(String.valueOf(datosPago.getRutClienteOrigen())), "", 
					respuesta.getRetorno().getCodigoRetorno(), respuesta.getRetorno().getCodigoRetorno(), "", "244", 
					dataVariable);
		
		MotorPagosLogger.logAppendLine(linea);		
		/**********************LOG STANDARD ******************************/
		
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Fin Pago Operación : {}", pagoVO.getIdTrxPago());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	
    	return respuesta;
	}

	@Override
	@WebMethod(operationName="obtenerPago")
	public RespuestaValidaPagoVO obtenerPago(ValidarPagoVO validarPagoVO){
		
		/**********************LOG STANDARD ******************************/
		LogLine linea = new LogLine("nodo",	ConstantesMotorPagos.LOG_CANAL, ConstantesMotorPagos.LOG_IDPROGRAMA, ConstantesMotorPagos.LOG_OPCION_GETPAGO);
		/**********************LOG STANDARD ******************************/
		
		logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio Validación Operación : {}", validarPagoVO.getIdTrxPago());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
		
		logger.debug(validarPagoVO.toString());

		RespuestaValidaPagoVO respuesta = null;
		if(validarPagoVO.validaParametros()){
			MotorPagosManager manager = new MotorPagosManager();
			respuesta = manager.validarPago(validarPagoVO);
		}else{
			respuesta = new RespuestaValidaPagoVO();
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
		}
		
		logger.debug(respuesta.toString());
		
		/**********************LOG STANDARD ******************************/
		String dataVariable = MotorPagosHelper.formateaString(respuesta.getRetorno().getCodigoRetorno(), "0", 4)
	  			+ MotorPagosHelper.formateaString(respuesta.getRetorno().getGlosaRetorno(), " ", 30)
	  			+ MotorPagosHelper.formateaString(validarPagoVO.getIdTrxPago(), " ", 29)
	  			+ MotorPagosHelper.formateaString(respuesta.getIdVendedor(), " ", 20)	
	  			+ MotorPagosHelper.formateaString(respuesta.getGlosaTrx(), " ", 50)
	  			+ MotorPagosHelper.formateaString(String.valueOf(respuesta.getMontoTrx()), " ", 38)
				+ MotorPagosHelper.formateaString(MotorPagosHelper.cleanRut(respuesta.getRutComercio()), " ", 10)
				+ MotorPagosHelper.formateaString(respuesta.getNombreComercio(), " ", 50);
				
		
		linea.setValues("", "", MotorPagosHelper.cleanRut(respuesta.getRutComercio()), MotorPagosHelper.cleanRut(respuesta.getRutComercio()), "", 
						respuesta.getRetorno().getCodigoRetorno(), respuesta.getRetorno().getCodigoRetorno(), "", "231", 
						dataVariable);
		
		MotorPagosLogger.logAppendLine(linea);		
		/**********************LOG STANDARD ******************************/
		
		logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Fin Validación Operación : {}", validarPagoVO.getIdTrxPago());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	
		return respuesta;
	}
	
	@Override
	@WebMethod(operationName="validarPin")
	public RespuestaValidaPinVO validarPin(ValidarPinVO pinVO){
		
		/**********************LOG STANDARD ******************************/
		LogLine linea = null;
		if(pinVO.getIdApp() !=null && pinVO.getIdApp().equals(ConstantesMotorPagos.ID_APP_COBROS)){
			linea = new LogLine("nodo",	ConstantesMotorPagos.LOG_CANAL, ConstantesMotorPagos.LOG_IDPROGRAMA, ConstantesMotorPagos.LOG_OPCION_VAL_PIN_CBS);
		}else{
			linea = new LogLine("nodo",	ConstantesMotorPagos.LOG_CANAL, ConstantesMotorPagos.LOG_IDPROGRAMA, ConstantesMotorPagos.LOG_OPCION_VAL_PIN_PGS);
		}
		/**********************LOG STANDARD ******************************/

		logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio Validación Pin Dispositivo : {}", pinVO.getIdDispositivo());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
		
		logger.debug(pinVO.toString());

		RespuestaValidaPinVO respuesta = null;
		if(pinVO.validaParametros()){
			MotorPagosManager manager = new MotorPagosManager();
			respuesta = manager.validarPin(pinVO, "REMOTE_CALL");
		}else{
			respuesta = new RespuestaValidaPinVO();
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
		}
						
		/**********************LOG STANDARD ******************************/
		String dataVariable = MotorPagosHelper.formateaString(respuesta.getRetorno().getCodigoRetorno(), "0", 4)
	  						+ MotorPagosHelper.formateaString(respuesta.getRetorno().getGlosaRetorno(), " ", 30);
				
		
		linea.setValues("", "", MotorPagosHelper.cleanRut(respuesta.getRutDispositivo()), MotorPagosHelper.cleanRut(respuesta.getRutDispositivo()), "", 
						respuesta.getRetorno().getCodigoRetorno(), respuesta.getRetorno().getCodigoRetorno(), "", "34", 
						dataVariable);
		
		MotorPagosLogger.logAppendLine(linea);		
		/**********************LOG STANDARD ******************************/
		
		respuesta.setRutDispositivo(null);
		logger.debug(respuesta.toString());
		
		logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Fin Validación Pin Dispositivo : {}", pinVO.getIdDispositivo());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	
		return respuesta;
	}
	
	@Override
	@WebMethod(operationName="resetearPin")
	public RespuestaVO resetearPin(ResearPinVO reseteoVO) {
		
		/**********************LOG STANDARD ******************************/
		LogLine linea = new LogLine("nodo",	ConstantesMotorPagos.LOG_CANAL, ConstantesMotorPagos.LOG_IDPROGRAMA, ConstantesMotorPagos.LOG_OPCION_RESET_CLAVE);
		/**********************LOG STANDARD ******************************/

		logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio resetearPin Dispositivo : {}", reseteoVO.getIdDispositivo());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	
    	logger.debug(reseteoVO.toString());
		
    	RespuestaVO respuesta = null;
    	ClienteVO clienteVO = new ClienteVO();
    	if(reseteoVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.resetearPin(reseteoVO, clienteVO);
    	}else{
    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS);
    	}
    	
		logger.debug(respuesta.toString());
		
		/**********************LOG STANDARD ******************************/		
		String dataVariable = MotorPagosHelper.formateaString(respuesta.getCodigoRetorno(), "0", 4)
				  			+ MotorPagosHelper.formateaString(respuesta.getGlosaRetorno(), " ", 30);
		
		linea.setValues("", "", MotorPagosHelper.cleanRut(clienteVO.getRut()), MotorPagosHelper.cleanRut(clienteVO.getRut()), "", 
					respuesta.getCodigoRetorno(), respuesta.getCodigoRetorno(), "", "34", 
					dataVariable);
		
		MotorPagosLogger.logAppendLine(linea);		
		/**********************LOG STANDARD ******************************/
		
		logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Fin resetearPin Dispositivo : {}", reseteoVO.getIdDispositivo());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	
		return respuesta;		
	}
	
	@Override
	@WebMethod(operationName="registrarPreCobro")
	public RespuestaValidaPagoVO registrarPreCobro(PreCobroVO preCobroVO) {
		
		/**********************LOG STANDARD ******************************/
		LogLine linea = new LogLine("nodo",	ConstantesMotorPagos.LOG_CANAL, ConstantesMotorPagos.LOG_IDPROGRAMA, ConstantesMotorPagos.LOG_OPCION_PRECOBRO);
		/**********************LOG STANDARD ******************************/

		logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio Registro Pre-Cobro  idOperacion : {}", preCobroVO.getIdTrxPago());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
		
		logger.debug(preCobroVO.toString());

		RespuestaValidaPagoVO respuesta = null;
		if(preCobroVO.validaParametros()){
			MotorPagosManager manager = new MotorPagosManager();
			respuesta = manager.registrarPreCobro(preCobroVO);
		}else{
			respuesta = new RespuestaValidaPagoVO();
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
		}
					
		logger.debug(respuesta.toString());
				
		/**********************LOG STANDARD ******************************/
		String dataVariable = MotorPagosHelper.formateaString(respuesta.getRetorno().getCodigoRetorno(), "0", 4)
	  			+ MotorPagosHelper.formateaString(respuesta.getRetorno().getGlosaRetorno(), " ", 30)
	  			+ MotorPagosHelper.formateaString(preCobroVO.getIdTrxPago(), " ", 29)
	  			+ MotorPagosHelper.formateaString(preCobroVO.getIdVendedor(), " ", 20)	
	  			+ MotorPagosHelper.formateaString(preCobroVO.getGlosaTrx(), " ", 50)
	  			+ MotorPagosHelper.formateaString(String.valueOf(preCobroVO.getMontoTrx()), " ", 38)
				+ MotorPagosHelper.formateaString(MotorPagosHelper.cleanRut(respuesta.getRutComercio()), " ", 10)
				+ MotorPagosHelper.formateaString(respuesta.getNombreComercio(), " ", 50);
				
		
		linea.setValues("", "", MotorPagosHelper.cleanRut(respuesta.getRutComercio()), MotorPagosHelper.cleanRut(respuesta.getRutComercio()), "", 
						respuesta.getRetorno().getCodigoRetorno(), respuesta.getRetorno().getCodigoRetorno(), "", "231", 
						dataVariable);
		
		MotorPagosLogger.logAppendLine(linea);		
		/**********************LOG STANDARD ******************************/
		
		logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Fin Registro Pre-Cobro  idOperacion : {}", preCobroVO.getIdTrxPago());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	
		return respuesta;
	}

	@Override
	@WebMethod(operationName="obtenerPreCobro")
	public RespuestaPreCobroVO obtenerPreCobro(ObtenerPreCobroVO preCobroVO) {
		
		logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio obtenerPreCobro  idTrxPago : {}", preCobroVO.getIdTrxPago());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
		
    	logger.debug(preCobroVO.toString());
    	
    	RespuestaPreCobroVO respuesta = null;
    	if(preCobroVO.validaParametros()){
			MotorPagosManager manager = new MotorPagosManager();
			respuesta = manager.validarPreCobro(preCobroVO);
			
			if(respuesta.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
				respuesta.setAvatar(manager.getAvatar(respuesta.getIdConvenio()));
			}
    	}else{
    		respuesta = new RespuestaPreCobroVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
		
		logger.debug(respuesta.toString());
				
		logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Fin obtenerPreCobro  idTrxPago : {}", preCobroVO.getIdTrxPago());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	
		return respuesta;
	}

	@Override
	@WebMethod(operationName="cambiarPin")
	public RespuestaVO cambiarPin(CambioPinVO cambioVO) {

		/**********************LOG STANDARD ******************************/
		LogLine linea = new LogLine("nodo",	ConstantesMotorPagos.LOG_CANAL, ConstantesMotorPagos.LOG_IDPROGRAMA, ConstantesMotorPagos.LOG_OPCION_CAMBIO_CLAVE);
		/**********************LOG STANDARD ******************************/
		
		logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio cambiarPin  idTrxPago : {}", cambioVO.getIdDispositivo());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
		
    	logger.debug(cambioVO.toString());
    	
    	RespuestaVO respuesta = null;
    	ClienteVO clienteVO = new ClienteVO();
    	if(cambioVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.cambiarPin(cambioVO, clienteVO);
    	}else{
    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS);
    	}
    	
		logger.debug(respuesta.toString());
				
		/**********************LOG STANDARD ******************************/		
		String dataVariable = MotorPagosHelper.formateaString(respuesta.getCodigoRetorno(), "0", 4)
				  			+ MotorPagosHelper.formateaString(respuesta.getGlosaRetorno(), " ", 30);
		
		linea.setValues("", "",  MotorPagosHelper.cleanRut(clienteVO.getRut()),  MotorPagosHelper.cleanRut(clienteVO.getRut()), "", 
					respuesta.getCodigoRetorno(), respuesta.getCodigoRetorno(), "", "34", 
					dataVariable);
		
		MotorPagosLogger.logAppendLine(linea);		
		/**********************LOG STANDARD ******************************/
		
		logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Fin cambiarPin  idTrxPago : {}", cambioVO.getIdDispositivo());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	
		return respuesta;
	}

	@Override
	@WebMethod(operationName="cancelarCobro")
	public RespuestaVO cancelarCobro(CancelaCobroVO cancelaVO) {
		
		logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio cancelaCobro  idTrxPago : {}", cancelaVO.getIdTrx());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
		
    	logger.debug(cancelaVO.toString());
    	
    	RespuestaVO respuesta = null;
    	if(cancelaVO.validaParametros()){
			MotorPagosManager manager = new MotorPagosManager();
			respuesta = manager.cancelarCobro(cancelaVO);
    	}else{
    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS);
    	}
    	
		logger.debug(respuesta.toString());
				
		logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	logger.info("Fin cancelaCobro  idTrxPago : {}", cancelaVO.getIdTrx());
    	logger.info(PagoServicioBean.SEPARADOR_TEXTO);
    	
		return respuesta;
	}

}
