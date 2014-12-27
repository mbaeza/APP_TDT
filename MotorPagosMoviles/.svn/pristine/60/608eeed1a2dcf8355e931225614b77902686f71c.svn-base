/**
 * 
 */
package cl.bch.motorpagos.ejb;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.logger.LogLine;
import cl.bch.logger.MotorPagosLogger;
import cl.bch.motorpagos.MotorPagosManager;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.CambioEstadoVO;
import cl.bch.motorpagos.vo.ConsultaListadoMovClienteVO;
import cl.bch.motorpagos.vo.ConsultaListadoMovVO;
import cl.bch.motorpagos.vo.LoginAdmWebVO;
import cl.bch.motorpagos.vo.LoginClienteVO;
import cl.bch.motorpagos.vo.ObtenerConveniosComercioVO;
import cl.bch.motorpagos.vo.ObtenerDispositivosSeguridadVO;
import cl.bch.motorpagos.vo.RespuestaConveniosVO;
import cl.bch.motorpagos.vo.RespuestaDispRegistradosVO;
import cl.bch.motorpagos.vo.RespuestaDispositivosSeguridadVO;
import cl.bch.motorpagos.vo.RespuestaListadoMovimientosVO;
import cl.bch.motorpagos.vo.RespuestaLoginClienteVO;
import cl.bch.motorpagos.vo.RespuestaVO;

/**
 * @author boyanedel
 *
 */
@Stateless
@WebService(serviceName="AdministracionWebServices")
public class AdministracionWebBean implements AdministracionWebBeanLocal{
	private static final Logger logger = LoggerFactory.getLogger(AdministracionWebBean.class);
	private static final String SEPARADOR_TEXTO = "*****************************************************************************************";
	
    /**
     * Default constructor. 
     */
    public AdministracionWebBean() {
    	super();
    }

    @Deprecated
	@Override
	@WebMethod(operationName="listaDispositivosCliente")
	public RespuestaDispRegistradosVO listaDispositivosCliente(@WebParam(name = "rutCliente") String rutCliente) {
		logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Inicio ListaDispositivosCliente Cliente : {}", rutCliente);
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	
    	RespuestaDispRegistradosVO respuesta = new RespuestaDispRegistradosVO();
    	respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_METODO_DEPRECADO));
//    	if(MotorPagosHelper.isValid(rutCliente)){
//    		MotorPagosManager manager = new MotorPagosManager();
//    		respuesta = manager.listaDispositivosCliente(rutCliente.toUpperCase());
//    	}else{
//    		respuesta = new RespuestaDispRegistradosVO();
//    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
//    	}
//    	
//    	logger.debug(respuesta.toString());
    	
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Fin ListaDispositivosCliente Cliente : {}", rutCliente);
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);	
    	
    	return respuesta;
	}

	@Override
	@WebMethod(operationName="listaDispositivosSeguridad")
	public RespuestaDispositivosSeguridadVO listaDispositivosSeguridad(ObtenerDispositivosSeguridadVO dispositivosSeguridadVO) {
		logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Inicio listaDispositivosSeguridad Cliente : {}", dispositivosSeguridadVO.getRutCliente());
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	
    	RespuestaDispositivosSeguridadVO respuesta = null;
    	if(dispositivosSeguridadVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.listaDispositivosSeguridad(dispositivosSeguridadVO);
    	}else{
    		respuesta = new RespuestaDispositivosSeguridadVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
    	
    	logger.debug(respuesta.toString());
    	
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Fin listaDispositivosSeguridad Cliente : {}", dispositivosSeguridadVO.getRutCliente());
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);	
    	
    	return respuesta;
	}

	@Override
	@WebMethod(operationName="cambiarEstadoDispositivo")
	public RespuestaVO cambiarEstadoDispositivo(CambioEstadoVO dispositivo) {
		
		/**********************LOG STANDARD ******************************/
		LogLine linea = new LogLine("nodo",	ConstantesMotorPagos.LOG_CANAL, ConstantesMotorPagos.LOG_IDPROGRAMA, ConstantesMotorPagos.LOG_OPCION_ADM_DISP);
		/**********************LOG STANDARD ******************************/
		
		logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Inicio cambiarEstadoDispositivo idDispositivo : {}", dispositivo.getId());
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	
    	logger.debug(dispositivo.toString());
    	
    	RespuestaVO respuesta = null;
    	if(dispositivo.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.cambiarEstadoDispositivo(dispositivo);
    	}else{
    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS);
    	}
    	
    	logger.debug(respuesta.toString());
    	
    	/**********************LOG STANDARD ******************************/
		String dataVariable = MotorPagosHelper.formateaString(respuesta.getCodigoRetorno(), "0", 4)
							  + MotorPagosHelper.formateaString(respuesta.getGlosaRetorno(), " ", 30)
							  + MotorPagosHelper.formateaString(dispositivo.getEstado(), " ", 1)
							  + MotorPagosHelper.formateaString(dispositivo.getId()	, " ", 30);
		
		linea.setValues("", "", MotorPagosHelper.cleanRut(dispositivo.getRutApoderado()), MotorPagosHelper.cleanRut(dispositivo.getRutApoderado()), "", 
						respuesta.getCodigoRetorno(), respuesta.getCodigoRetorno(), "", "65", 
						dataVariable);
		
		MotorPagosLogger.logAppendLine(linea);		
		/**********************LOG STANDARD ******************************/
    	
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Fin cambiarEstadoDispositivo idDispositivo : {}", dispositivo.getId());
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);	
    	
    	return respuesta;
	}

	@Override
	@WebMethod(operationName="obtieneConveniosComercio")
	public RespuestaConveniosVO obtieneConveniosComercio(ObtenerConveniosComercioVO conveniosComercioVO) {
		logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Inicio obtieneConveniosComercio rutEmpresa : {}, rutApoderado : {}", conveniosComercioVO.getRutEmpresa(), conveniosComercioVO.getRutApoderado());
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	
    	RespuestaConveniosVO respuesta = null;
    	if(conveniosComercioVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.obtieneConveniosComercio(conveniosComercioVO);
    	}else{
    		respuesta = new RespuestaConveniosVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
    	
    	logger.debug(respuesta.toString());
    	
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Fin obtieneConveniosComercio rutEmpresa : {}, rutApoderado : {}", conveniosComercioVO.getRutEmpresa(), conveniosComercioVO.getRutApoderado());
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);	
    	
    	return respuesta;
	}

	@Override
	@WebMethod(operationName="obtieneMovimientosConvenioComercio")
	public RespuestaListadoMovimientosVO obtieneMovimientosConvenioComercio(ConsultaListadoMovVO consultaVO) {
		logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Inicio obtieneMovimientosConvenioComercio idConvenio : {}, rutEmpresa : {}, rutApoderado : {}", consultaVO.getIdConvenio(), consultaVO.getRutEmpresa(), consultaVO.getRutApoderado());
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	
    	logger.debug(consultaVO.toString());
    	
    	RespuestaListadoMovimientosVO respuesta = null;
    	if(consultaVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.obtieneMovimientosConvenioComercio(consultaVO);
    	}else{
    		respuesta = new RespuestaListadoMovimientosVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
    	
    	logger.debug(respuesta.toString());
    	
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Fin obtieneMovimientosConvenioComercio idConvenio : {}, rutEmpresa : {}, rutApoderado : {}", consultaVO.getIdConvenio(), consultaVO.getRutEmpresa(), consultaVO.getRutApoderado());
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);	
    	
    	return respuesta;
	}

	@Override
	@WebMethod(operationName="obtieneMovimientosCliente")
	public RespuestaListadoMovimientosVO obtieneMovimientosCliente(ConsultaListadoMovClienteVO consultaVO) {
		logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Inicio obtieneMovimientosCliente rutCliente : {}", consultaVO.getRutCliente());
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	
    	logger.debug(consultaVO.toString());
    	
    	RespuestaListadoMovimientosVO respuesta = null;
    	if(consultaVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.obtieneMovimientosCliente(consultaVO);
    	}else{
    		respuesta = new RespuestaListadoMovimientosVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
    	
    	logger.debug(respuesta.toString());
    	
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Fin obtieneMovimientosCliente rutCliente : {}", consultaVO.getRutCliente());
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);	
    	
    	return respuesta;
	}
	
	@Override
	@WebMethod(operationName="loginClienteWeb")
	public RespuestaLoginClienteVO loginClienteWeb(LoginClienteVO loginVO) {
		
		/**********************LOG STANDARD ******************************/
		LogLine linea = new LogLine("nodo",	ConstantesMotorPagos.LOG_CANAL, ConstantesMotorPagos.LOG_IDPROGRAMA, ConstantesMotorPagos.LOG_OPCION_LOGIN_WEB);
		/**********************LOG STANDARD ******************************/
		
		logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Inicio loginClienteWeb : {}", loginVO.getRut());
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	
    	logger.debug(loginVO.toString());
		
    	RespuestaLoginClienteVO respuesta = null;
    	if(loginVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.loginClienteWeb(loginVO);
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
		
		logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Fin loginClienteWeb : {}", loginVO.getRut());
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	
		return respuesta;		
	}
	
	@Override
	@WebMethod(operationName="loginAdmWeb")
	public RespuestaVO loginAdmWeb(LoginAdmWebVO loginVO) {
		
		logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Inicio loginAdmWeb Usuario: {}, Operario : {}", loginVO.getIdUsuario(), loginVO.getAliasOperario());
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	
    	logger.debug(loginVO.toString());
		
    	RespuestaVO respuesta = null;
    	if(loginVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.loginAdministracionWeb(loginVO);
    	}else{
    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS);
    	}
		
		logger.debug(respuesta.toString());
				
		logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	logger.info("Fin loginAdmWeb Usuario: {}, Operario : {}", loginVO.getIdUsuario(), loginVO.getAliasOperario());
    	logger.info(AdministracionWebBean.SEPARADOR_TEXTO);
    	
		return respuesta;		
	}
}
