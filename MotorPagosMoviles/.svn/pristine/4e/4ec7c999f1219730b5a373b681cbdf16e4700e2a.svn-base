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
import cl.bch.motorpagos.security.*;

/**
 * Session Bean implementation class ClienteBean
 */
@Stateless
@WebService(serviceName="ClienteServices")
public class ClienteBean implements ClienteBeanLocal{
	private static final Logger logger = LoggerFactory.getLogger(ClienteBean.class);
	private static final String SEPARADOR_TEXTO = "*****************************************************************************************";
	
    /**
     * Default constructor. 
     */
    public ClienteBean() {
    	super();
    }

	@Override
	@WebMethod(operationName="loginCliente")
	public RespuestaLoginClienteVO loginCliente(LoginClienteVO loginVO) {
		
		/**********************LOG STANDARD ******************************/
		LogLine linea = new LogLine("nodo",	ConstantesMotorPagos.LOG_CANAL, ConstantesMotorPagos.LOG_IDPROGRAMA, ConstantesMotorPagos.LOG_OPCION_LOGIN);
		/**********************LOG STANDARD ******************************/
		
		logger.info(ClienteBean.SEPARADOR_TEXTO);
    	logger.info("Inicio Login Cliente : {}", loginVO.getRut());
    	logger.info(ClienteBean.SEPARADOR_TEXTO);
    	
    	logger.debug(loginVO.toString());
		
    	RespuestaLoginClienteVO respuesta = null;
    	if(loginVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.loginCliente(loginVO);
    	}else{
    		respuesta = new RespuestaLoginClienteVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
		
		logger.debug(respuesta.toString());
		
		/**********************LOG STANDARD ******************************/
		String dataVariable = MotorPagosHelper.formateaString(respuesta.getRetorno().getCodigoRetorno(), "0", 4)
							  + MotorPagosHelper.formateaString(respuesta.getRetorno().getGlosaRetorno(), " ", 30);
		
		linea.setValues("", "", MotorPagosHelper.cleanRut(loginVO.getRut()), MotorPagosHelper.cleanRut(loginVO.getRut()), "", 
						respuesta.getRetorno().getCodigoRetorno(), respuesta.getRetorno().getCodigoRetorno(), "", "34", 
						dataVariable);
		
		MotorPagosLogger.logAppendLine(linea);		
		/**********************LOG STANDARD ******************************/
		
		logger.info(ClienteBean.SEPARADOR_TEXTO);
    	logger.info("Fin Login Cliente : {}", loginVO.getRut());
    	logger.info(ClienteBean.SEPARADOR_TEXTO);
    	
		return respuesta;		
	}

	@Override
	@WebMethod(operationName="enrolarCliente")
	public RespuestaEnrolamientoVO enrolarCliente(ClienteVO clienteVO) {
		
		/**********************LOG STANDARD ******************************/
		LogLine linea = new LogLine("nodo",	ConstantesMotorPagos.LOG_CANAL, ConstantesMotorPagos.LOG_IDPROGRAMA, ConstantesMotorPagos.LOG_OPCION_ENROLAR_CLI);
		/**********************LOG STANDARD ******************************/
		
		logger.info(ClienteBean.SEPARADOR_TEXTO);
    	logger.info("Inicio Enrolamiento Cliente : {}", clienteVO.getRut());
    	logger.info(ClienteBean.SEPARADOR_TEXTO);
    	
    	logger.debug(clienteVO.toString());
		
    	RespuestaEnrolamientoVO respuesta = null;
    	if(clienteVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.enrolarCliente(clienteVO);
    	}else{
    		respuesta = new RespuestaEnrolamientoVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
		
		logger.debug(respuesta.toString());
		
		/**********************LOG STANDARD ******************************/
		String llaveCuenta = "";
		try{
			llaveCuenta = CryptData.desEncriptar(clienteVO.getLlaveCuenta());
		}catch (Exception e) {
			logger.error("Error al intentar desencriptar la cuenta.");
		}
		
		String dataVariable = MotorPagosHelper.formateaString(respuesta.getRetorno().getCodigoRetorno(), "0", 4)
				  			+ MotorPagosHelper.formateaString(respuesta.getRetorno().getGlosaRetorno(), " ", 30)
				  			+ MotorPagosHelper.formateaString(respuesta.getIdDispositivo(), " ", 30)
				  			+ MotorPagosHelper.formateaString(clienteVO.getAliasDispositivo(), " ", 20)
				  			+ MotorPagosHelper.formateaString(clienteVO.getModeloDispositivo(), " ", 50)
				  			+ MotorPagosHelper.formateaString(respuesta.getIdConvenio(), " ", 30)
				  			+ MotorPagosHelper.formateaString(respuesta.getIdCliente(), " ", 30)
				  			+ MotorPagosHelper.formateaString(respuesta.getIdCuenta(), " ", 30)
				  			+ MotorPagosHelper.formateaString(llaveCuenta, " ", 24);

		linea.setValues("", "", MotorPagosHelper.cleanRut(clienteVO.getRut()), MotorPagosHelper.cleanRut(clienteVO.getRut()), "", 
					respuesta.getRetorno().getCodigoRetorno(), respuesta.getRetorno().getCodigoRetorno(), "", "248", 
					dataVariable);
		
		MotorPagosLogger.logAppendLine(linea);		
		/**********************LOG STANDARD ******************************/
		
		logger.info(ClienteBean.SEPARADOR_TEXTO);
    	logger.info("Fin Enrolamiento Cliente : {}", clienteVO.getRut());
    	logger.info(ClienteBean.SEPARADOR_TEXTO);
    	
		return respuesta;		
	}

//	@Override
//	//@WebMethod(operationName="validarDispositivoSeguridadCliente")
//	public RespuestaVO validarDispositivoSeguridadCliente(ValidarDispositivoSegVO dispositivoVO) {
//		
//		logger.info(ClienteBean.SEPARADOR_TEXTO);
//    	logger.info("Inicio validarDispositivoSeguridadCliente cliente : {}", dispositivoVO.getRutCliente());
//    	logger.info(ClienteBean.SEPARADOR_TEXTO);
//    	
//    	logger.debug(dispositivoVO.toString());
//    	
//    	RespuestaVO respuesta = null;
//    	if(dispositivoVO.validaParametros()){
//    		MotorPagosManager manager = new MotorPagosManager();
//			respuesta = manager.validarDispositivoSeguridadCliente(dispositivoVO);
//		}else{
//			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS);
//		}
//		
//		logger.debug(respuesta.toString());
//		
//		logger.info(ClienteBean.SEPARADOR_TEXTO);
//    	logger.info("Fin validarDispositivoSeguridadCliente cliente : {}", dispositivoVO.getRutCliente());
//    	logger.info(ClienteBean.SEPARADOR_TEXTO);
//    	
//		return respuesta;		
//	}

	@Override
	@WebMethod(operationName="obtenerMediosDePagoCliente")
	public RespuestaMediosDePagoVO obtenerMediosDePagoCliente(ObtenerMediosDePagoVO obtenerVO) {
		
		logger.info(ClienteBean.SEPARADOR_TEXTO);
    	logger.info("Inicio obtenerMediosDePagoCliente idCliente : {}", obtenerVO.getIdCliente());
    	logger.info(ClienteBean.SEPARADOR_TEXTO);
    	
    	logger.debug(obtenerVO.toString());
    	
    	RespuestaMediosDePagoVO respuesta =  null;
    	if(obtenerVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.obtenerMediosDePagoCliente(obtenerVO);
    	}else{
    		respuesta = new RespuestaMediosDePagoVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
		
		logger.debug(respuesta.toString());
		
		logger.info(ClienteBean.SEPARADOR_TEXTO);
    	logger.info("Fin obtenerMediosDePagoCliente idCliente : {}", obtenerVO.getIdCliente());
    	logger.info(ClienteBean.SEPARADOR_TEXTO);
    	
		return respuesta;		
	}

	@Override
	@WebMethod(operationName="actualizarMedioDePagoCliente")
	public RespuestaEnrolamientoVO actualizarMedioDePagoCliente(ActualizarMedioDePagoVO medioDePagoVO) {
		
		/**********************LOG STANDARD ******************************/
		LogLine linea = new LogLine("nodo",	ConstantesMotorPagos.LOG_CANAL, ConstantesMotorPagos.LOG_IDPROGRAMA, ConstantesMotorPagos.LOG_OPCION_CAMBIO_CTA);
		/**********************LOG STANDARD ******************************/
		
		logger.info(ClienteBean.SEPARADOR_TEXTO);
    	logger.info("Inicio actualizarMedioDePagoCliente idCliente : {}", medioDePagoVO.getIdCliente());
    	logger.info(ClienteBean.SEPARADOR_TEXTO);
    	
    	logger.debug(medioDePagoVO.toString());
    	
    	RespuestaEnrolamientoVO respuesta = null;
    	String rutCliente = "";
    	if(medioDePagoVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.actualizarMedioDePagoCliente(medioDePagoVO);
    		rutCliente = MotorPagosHelper.cleanRut(manager.obtenerRutCliente(medioDePagoVO.getIdCliente()));
    	}else{
    		respuesta = new RespuestaEnrolamientoVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
		
		logger.debug(respuesta.toString());
		
		/**********************LOG STANDARD ******************************/		
		String llaveCuenta = "";		
		try{
			llaveCuenta = CryptData.desEncriptar(medioDePagoVO.getLlaveCuenta());
		}catch (Exception e) {
			logger.error("Error al intentar desencriptar la cuenta.");
		}
		
		String dataVariable = MotorPagosHelper.formateaString(respuesta.getRetorno().getCodigoRetorno(), "0", 4)
				  			+ MotorPagosHelper.formateaString(respuesta.getRetorno().getGlosaRetorno(), " ", 30)
				  			+ MotorPagosHelper.formateaString(medioDePagoVO.getIdCliente(), " ", 30)
				  			+ MotorPagosHelper.formateaString(respuesta.getIdCuenta(), " ", 30)				  			
				  			+ MotorPagosHelper.formateaString(llaveCuenta, " ", 24)
				  			+ MotorPagosHelper.formateaString(String.valueOf(medioDePagoVO.getLimiteCuenta()), "0", 24);

		linea.setValues("", "", rutCliente, rutCliente, "", 
					respuesta.getRetorno().getCodigoRetorno(), respuesta.getRetorno().getCodigoRetorno(), "", "142", 
					dataVariable);
		
		MotorPagosLogger.logAppendLine(linea);		
		/**********************LOG STANDARD ******************************/
		
		logger.info(ClienteBean.SEPARADOR_TEXTO);
    	logger.info("Fin actualizarMedioDePagoCliente idCliente {}", medioDePagoVO.getIdCliente());
    	logger.info(ClienteBean.SEPARADOR_TEXTO);
    	
		return respuesta;		
	}

	@Override
	@WebMethod(operationName="obtenerSaldoCuentaCliente")
	public RespuestaConsultaSaldoVO obtenerSaldoCuentaCliente(ConsultaSaldoVO consultaVO) {
		
		/**********************LOG STANDARD ******************************/
		LogLine linea = new LogLine("nodo",	ConstantesMotorPagos.LOG_CANAL, ConstantesMotorPagos.LOG_IDPROGRAMA, ConstantesMotorPagos.LOG_OPCION_SALDO);
		/**********************LOG STANDARD ******************************/
		
		logger.info(ClienteBean.SEPARADOR_TEXTO);
    	logger.info("Inicio obtenerSaldoCuentaCliente idCuenta : {}", consultaVO.getIdCuenta());
    	logger.info(ClienteBean.SEPARADOR_TEXTO);
    	
    	logger.debug(consultaVO.toString());
    	
    	RespuestaConsultaSaldoVO respuesta = null;
    	RespuestaConsultaCuentaVO respCuenta = new RespuestaConsultaCuentaVO();
    	String rutCliente = "";
    	if(consultaVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.obtenerSaldoCuentaCliente(consultaVO, respCuenta);
    		rutCliente = MotorPagosHelper.cleanRut(manager.obtenerRutCliente(consultaVO.getIdCliente()));
    	}else{
    		respuesta = new RespuestaConsultaSaldoVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
    	
		logger.debug(respuesta.toString());
		
		/**********************LOG STANDARD ******************************/		
		String dataVariable = MotorPagosHelper.formateaString(respuesta.getRetorno().getCodigoRetorno(), "0", 4)
				  			+ MotorPagosHelper.formateaString(respuesta.getRetorno().getGlosaRetorno(), " ", 30)
				  			+ MotorPagosHelper.formateaString(respCuenta.getTipoCuenta(), " ", 3)
				  			+ MotorPagosHelper.formateaString(respCuenta.getNroCuenta(), " ", 20)				  			
				  			+ MotorPagosHelper.formateaString(String.valueOf(respuesta.getSaldoDisponible()), "0", 20)
				  			+ MotorPagosHelper.formateaString(respuesta.getMoneda(), " ", 5);

		linea.setValues("", "", rutCliente, rutCliente, "", 
					respuesta.getRetorno().getCodigoRetorno(), respuesta.getRetorno().getCodigoRetorno(), "", "82", 
					dataVariable);
		
		MotorPagosLogger.logAppendLine(linea);		
		/**********************LOG STANDARD ******************************/
		
		logger.info(ClienteBean.SEPARADOR_TEXTO);
    	logger.info("Fin obtenerSaldoCuentaCliente idCuenta : {}", consultaVO.getIdCuenta());
    	logger.info(ClienteBean.SEPARADOR_TEXTO);
    	
		return respuesta;		
	}

	@Override
	@WebMethod(operationName="obtieneMovimientosRecientesCliente")
	public RespuestaListadoMovimientosClienteVO obtieneMovimientosRecientesCliente(ObtenerMovRecientesVO obtenerVO) {
		logger.info(ClienteBean.SEPARADOR_TEXTO);
    	logger.info("Inicio obtieneMovimientosRecientesCliente idDispositivo : {}", obtenerVO.getIdDispositivo());
    	logger.info(ClienteBean.SEPARADOR_TEXTO);
    	
    	logger.debug(obtenerVO.toString());
    	
    	RespuestaListadoMovimientosClienteVO respuesta = new RespuestaListadoMovimientosClienteVO();
    	if(obtenerVO.validaParametros()){
	    	MotorPagosManager manager = new MotorPagosManager();
	    	RespuestaListadoMovimientosVO repPagos = manager.obtieneMovimientosRecientes(obtenerVO, ConstantesMotorPagos.CLIENTE);
	    	RespuestaListadoMovimientosVO repCobros = manager.obtieneMovimientosRecientes(obtenerVO, ConstantesMotorPagos.COMERCIO);
	    	
	    	if(repPagos.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK) || repCobros.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
	    		respuesta.setListadoPagos(repPagos.getListadoMovimientos());	    	
	    		respuesta.setListadoCobros(repCobros.getListadoMovimientos());
	    		
	    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA));
	    	}else{
	    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "No se encontraron movimientos."));
	    	}
    	}else{
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
    	
    	logger.debug(respuesta.toString());
    	
    	logger.info(ClienteBean.SEPARADOR_TEXTO);
    	logger.info("Fin obtieneMovimientosRecientesCliente idDispositivo : {}", obtenerVO.getIdDispositivo());
    	logger.info(ClienteBean.SEPARADOR_TEXTO);	
    	
    	return respuesta;
	}
	@Override
	@WebMethod(operationName="obtenerMarcaCliente")
	public RespuestaMarcaClienteVO obtenerMarcaCliente(ConsultaMarcaClienteVO consultaVO) {
		
		/**********************LOG STANDARD ******************************/
		LogLine linea = new LogLine("nodo",	ConstantesMotorPagos.LOG_CANAL, ConstantesMotorPagos.LOG_IDPROGRAMA, ConstantesMotorPagos.LOG_OPCION_SALDO);
		/**********************LOG STANDARD ******************************/
		
		logger.info(ClienteBean.SEPARADOR_TEXTO);
    	logger.info("Inicio obtenerMarcaCliente idDispositivo : {}", consultaVO.getIdDispositivo());
    	logger.info(ClienteBean.SEPARADOR_TEXTO);
    	
    	logger.debug(consultaVO.toString());
    	
    	RespuestaMarcaClienteVO respuesta = null;
    	if(consultaVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.obtenerMarcaCliente(consultaVO);
    	}else{
    		respuesta = new RespuestaMarcaClienteVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
    	
		logger.debug(respuesta.toString());
		
		/**********************LOG STANDARD ******************************/		
		String dataVariable = MotorPagosHelper.formateaString(respuesta.getRetorno().getCodigoRetorno(), "0", 4)
				  			+ MotorPagosHelper.formateaString(respuesta.getRetorno().getGlosaRetorno(), " ", 30)
				  			+ MotorPagosHelper.formateaString(respuesta.getCodigoMarca(), " ", 1);

		linea.setValues( "", "", "", "", "", 
					respuesta.getRetorno().getCodigoRetorno(), respuesta.getRetorno().getCodigoRetorno(), "", "35", 
					dataVariable);
		
		MotorPagosLogger.logAppendLine(linea);		
		/**********************LOG STANDARD ******************************/
		
		logger.info(ClienteBean.SEPARADOR_TEXTO);
    	logger.info("Fin obtenerMarcaCliente idDispositivo : {}", consultaVO.getIdDispositivo());
    	logger.info(ClienteBean.SEPARADOR_TEXTO);
    	
		return respuesta;		
	}


//	@Override
//	@WebMethod(operationName="setAvatarCliente")
//	public RespuestaVO setAvatarCliente(@WebParam(name = "idDispCliente") String idDispCliente, @WebParam(name = "imagenBase64") String imagenBase64) {
//		logger.info(ClienteBean.SEPARADOR_TEXTO);
//    	logger.info("Inicio setAvatarCliente idDispCliente : {}", idDispCliente);
//    	logger.info(ClienteBean.SEPARADOR_TEXTO);
//    	
//    	RespuestaVO respuesta = null;
//    	if(MotorPagosHelper.isValid(idDispCliente) && MotorPagosHelper.isValid(imagenBase64)){
//    		MotorPagosManager manager = new MotorPagosManager();
//    		respuesta = manager.createAvatar(idDispCliente, imagenBase64, ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.RUTA_IMAGENES_CLIENTE));
//    	}else{
//    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS);
//    	}
//    	
//    	logger.debug(respuesta.toString());
//    	
//    	logger.info(ClienteBean.SEPARADOR_TEXTO);
//    	logger.info("Fin setAvatarCliente idDispCliente : {}", idDispCliente);
//    	logger.info(ClienteBean.SEPARADOR_TEXTO);	
//    	
//    	return respuesta;
//	}	
	
}
