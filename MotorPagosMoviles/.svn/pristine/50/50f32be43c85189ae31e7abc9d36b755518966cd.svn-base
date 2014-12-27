package cl.bch.motorpagos.ejb;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.MotorPagosManager;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.AtribucionesComercioVO;
import cl.bch.motorpagos.vo.AvatarComercioVO;
import cl.bch.motorpagos.vo.CierreDiarioVO;
import cl.bch.motorpagos.vo.ComercioVO;
import cl.bch.motorpagos.vo.DesvincularDispositivoVO;
import cl.bch.motorpagos.vo.DispositivoVentaVO;
import cl.bch.motorpagos.vo.LoginComercioVO;
import cl.bch.motorpagos.vo.ObtenerCuentasFilialVO;
import cl.bch.motorpagos.vo.ObtenerMovRecientesVO;
import cl.bch.motorpagos.vo.RespuestaCierreDiarioVO;
import cl.bch.motorpagos.vo.RespuestaDispRegistradosVO;
import cl.bch.motorpagos.vo.RespuestaDispVentaVO;
import cl.bch.motorpagos.vo.RespuestaEnrolamientoVO;
import cl.bch.motorpagos.vo.RespuestaListadoMovimientosVO;
import cl.bch.motorpagos.vo.RespuestaLoginComercioVO;
import cl.bch.motorpagos.vo.RespuestaMediosDePagoVO;
import cl.bch.motorpagos.vo.RespuestaVO;

/**
 * Session Bean implementation class ComercioBean
 */
@Stateless
@WebService(serviceName="ComercioServices")
public class ComercioBean implements ComercioBeanLocal{
	private static final Logger logger = LoggerFactory.getLogger(ComercioBean.class);
	private static final String SEPARADOR_TEXTO = "*******************************************************";
	
	/**
     * Default constructor. 
     */
    public ComercioBean() {
    	super();
    }

	@Override
	@WebMethod(operationName="loginComercio")
	public RespuestaLoginComercioVO loginComercio(LoginComercioVO loginComercioVO){
		
		logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio Login Comercio : Rut Empresa :{} Rut Persona :{}", loginComercioVO.getRutEmpresa(), loginComercioVO.getRutPersona());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	
		logger.debug(loginComercioVO.toString());
		
		RespuestaLoginComercioVO respuesta = null;
		if(loginComercioVO.validaParametros()){
			MotorPagosManager manager = new MotorPagosManager();
			respuesta = manager.loginComercio(loginComercioVO);
		}else{
			respuesta = new RespuestaLoginComercioVO();
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
		}
		
		logger.debug(respuesta.toString());
		
		logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Fin Login Comercio : Rut Empresa :{} Rut Persona :{}", loginComercioVO.getRutEmpresa(), loginComercioVO.getRutPersona());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	
		return respuesta;		
	}

	@Override
	@WebMethod(operationName="enrolarComercio")
	public RespuestaEnrolamientoVO enrolarComercio(ComercioVO comercioVO) {
		
		logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio Enrolamiento Comercio : Rut Empresa :{} Rut Persona :{}", comercioVO.getRutEmpresa(), comercioVO.getRutPersonas());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	
    	logger.debug(comercioVO.toString());
		
    	RespuestaEnrolamientoVO respuesta = null;
    	if(comercioVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
			respuesta = manager.enrolarComercio(comercioVO);
    	}else{
    		respuesta = new RespuestaEnrolamientoVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
    	
		logger.debug(respuesta.toString());
		
		logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Fin Enrolamiento Comercio : Rut Empresa :{} Rut Persona :{}", comercioVO.getRutEmpresa(), comercioVO.getRutPersonas());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	
		return respuesta;		
	}

	@Override
	@WebMethod(operationName="enrolarDispositivoVenta")
	public RespuestaDispVentaVO enrolarDispositivoVenta(DispositivoVentaVO dispositivoVO) {
		
		logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio Enrolamiento Disp. Venta Convenio : {}", dispositivoVO.getIdConvenio());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	
    	logger.debug(dispositivoVO.toString());

    	RespuestaDispVentaVO respuesta = null;
    	if(dispositivoVO.validaParametros()){
			MotorPagosManager manager = new MotorPagosManager();
			respuesta = manager.enrolarDispositivoVenta(dispositivoVO);
			
			if(respuesta.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
				respuesta.setAvatar(manager.getAvatar(respuesta.getIdConvenio()));
			}
    	}else{
    		respuesta = new RespuestaDispVentaVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
		
		logger.debug(respuesta.toString());
		
		logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Fin Enrolamiento Disp. Venta Convenio : {}", dispositivoVO.getIdConvenio());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	
		return respuesta;		
	}

	@Override
	@WebMethod(operationName="validarPoderesApoderado")
	public RespuestaVO validarPoderesApoderado(AtribucionesComercioVO atribucionesVO) {
		
		logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio Validacion poderes apoderado Rut Empresa :{} Rut Persona :{}", atribucionesVO.getRutEmpresa(), atribucionesVO.getRutPersona());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	
    	logger.debug(atribucionesVO.toString());

    	RespuestaVO respuesta = null;
    	if(atribucionesVO.validaParametros()){
    		//TODO Se comenta validacion de poderes apoderado.
    		//MotorPagosManager manager = new MotorPagosManager();
    		//respuesta = manager.validarPoderesApoderado(atribucionesVO);
    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA);
    	}else{
    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS);
    	}
		
		logger.debug(respuesta.toString());
		
		logger.info(ComercioBean.SEPARADOR_TEXTO);
		logger.info("Fin Validacion poderes apoderado Rut Empresa :{} Rut Persona :{}", atribucionesVO.getRutEmpresa(), atribucionesVO.getRutPersona());
		logger.info(ComercioBean.SEPARADOR_TEXTO);
    	
		return respuesta;		
	}

	@Override
	@WebMethod(operationName="listaDispositivosConvenio")
	public RespuestaDispRegistradosVO listaDispositivosConvenio(@WebParam(name = "idConvenio") String idConvenio) {
		logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio listaDispositivosComercio Cliente : {}", idConvenio);
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	
    	RespuestaDispRegistradosVO respuesta = null;
    	if(MotorPagosHelper.isValid(idConvenio)){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.listaDispositivosConvenio(idConvenio);
    	}else{
    		respuesta = new RespuestaDispRegistradosVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
    	
    	logger.debug(respuesta.toString());
    	
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Fin listaDispositivosComercio Cliente : {}", idConvenio);
    	logger.info(ComercioBean.SEPARADOR_TEXTO);	
    	
    	return respuesta;
	}
	
	@Override
	@WebMethod(operationName="desvincularDispositivoConvenio")
	public RespuestaVO desvincularDispositivoConvenio(DesvincularDispositivoVO dispositivoVO) {
		logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio cambiarEstadoDispositivo idDispositivo : {}", dispositivoVO.getIdDispositivo());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	
    	logger.debug(dispositivoVO.toString());
    	
    	RespuestaVO respuesta = null;
    	if(dispositivoVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.desvincularDispositivoConvenio(dispositivoVO);
    	}else{
    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS);
    	}
    	
    	logger.debug(respuesta.toString());
    	
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Fin cambiarEstadoDispositivo idDispositivo : {}", dispositivoVO.getIdDispositivo());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);		
    	
    	return respuesta;
	}
	
	
	@Override
	@WebMethod(operationName="obtieneMovimientosRecientesComercio")
	public RespuestaListadoMovimientosVO obtieneMovimientosRecientesComercio(ObtenerMovRecientesVO obtenerMovVO) {
		logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio obtieneMovimientosRecientesComercio idDispositivo : {}", obtenerMovVO.getIdDispositivo());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	
    	logger.debug(obtenerMovVO.toString());
    	
    	RespuestaListadoMovimientosVO respuesta = null;
    	if(obtenerMovVO.validaParametros()){
	    	MotorPagosManager manager = new MotorPagosManager();
	    	respuesta = manager.obtieneMovimientosRecientes(obtenerMovVO, ConstantesMotorPagos.COMERCIO);
    	}else{
    		respuesta = new RespuestaListadoMovimientosVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
    	
    	logger.debug(respuesta.toString());
    	
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Fin obtieneMovimientosRecientesComercio idDispositivo : {}", obtenerMovVO.getIdDispositivo());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);	
    	
    	return respuesta;
	}

	@Override
	@WebMethod(operationName="cierreDiarioDispositivo")
	public RespuestaCierreDiarioVO cierreDiarioDispositivo(CierreDiarioVO cierreDiario) {
		logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio cierreDiarioDispositivo idDispositivo : {}", cierreDiario.getIdDispositivo());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	
    	logger.debug(cierreDiario.toString());
    	
    	RespuestaCierreDiarioVO respuesta = null;
    	if(cierreDiario.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.cierreDiarioDispositivo(cierreDiario);
    	}else{
    		respuesta = new RespuestaCierreDiarioVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
    	
    	logger.debug(respuesta.toString());
    	
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Fin cierreDiarioDispositivo idDispositivo : {}", cierreDiario.getIdDispositivo());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);	
    	
    	return respuesta;
	}

	@Override
	@WebMethod(operationName="setAvatarComercio")
	public RespuestaVO setAvatarComercio(AvatarComercioVO avatarVO) {
		logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio setAvatarComercio idConvenio : {}", avatarVO.getIdConvenio());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	
    	logger.debug(avatarVO.toString());
    	
    	RespuestaVO respuesta = null;
    	if(avatarVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.createAvatar(avatarVO);
    	}else{
    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS);
    	}
    	
    	logger.debug(respuesta.toString());
    	
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Fin setAvatarComercio idConvenio : {}", avatarVO.getIdConvenio());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);	
    	
    	return respuesta;
	}	
	
	@Override
	@WebMethod(operationName="obtenerCuentasFilial")
	public RespuestaMediosDePagoVO obtenerCuentasFilial(ObtenerCuentasFilialVO obtenerVO) {
		
		logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Inicio obtenerCuentasFilial rutEmpresa : {} rutFilial: {}", obtenerVO.getRutEmpresa(), obtenerVO.getRut());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	
    	logger.debug(obtenerVO.toString());
    	
    	RespuestaMediosDePagoVO respuesta =  null;
    	if(obtenerVO.validaParametros()){
    		MotorPagosManager manager = new MotorPagosManager();
    		respuesta = manager.obtenerCuentasFilial(obtenerVO);
    	}else{
    		respuesta = new RespuestaMediosDePagoVO();
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_PARAMETROS_INVALIDOS));
    	}
		
		logger.debug(respuesta.toString());
		
		logger.info(ComercioBean.SEPARADOR_TEXTO);
    	logger.info("Fin obtenerCuentasFilial rutEmpresa : {} rutFilial: {}", obtenerVO.getRutEmpresa(), obtenerVO.getRut());
    	logger.info(ComercioBean.SEPARADOR_TEXTO);
    	
		return respuesta;		
	}
}
