/**
 * 
 */
package cl.bch.motorpagos.util;

import java.util.ArrayList;
import java.util.List;

import cl.bch.motorpagos.exceptions.MotorPagosException;
import cl.bch.motorpagos.persistencia.*;
import cl.bch.motorpagos.security.CryptData;
import cl.bch.motorpagos.vo.*;

/**
 * @author boyanedel
 *
 */
public final class MapperVO {
	
	/**
	 * 
	 */
	private MapperVO(){
		
	}

	/**
	 * 
	 * @param codigoRetorno
	 * @param glosaRetorno
	 * @param idDispositivo
	 * @param idCuenta
	 * @param String
	 * @return
	 */
	public static RespuestaEnrolamientoVO getRespuestaEnrolamientoVO(RespuestaVO retorno, String idDispositivo, String idCuenta, String idConvenio, String idCliente){
		RespuestaEnrolamientoVO respuestaVO = new RespuestaEnrolamientoVO();
		
		respuestaVO.setRetorno(			retorno);		
		respuestaVO.setIdDispositivo(	idDispositivo);
		respuestaVO.setIdConvenio(		idConvenio);
		respuestaVO.setIdCuenta(		idCuenta);
		respuestaVO.setIdCliente(		idCliente);
				
		return respuestaVO;
	}
	
	/**
	 * 
	 * @param codigoRetorno
	 * @param glosaRetorno
	 * @param idDispositivo
	 * @param idCuenta
	 * @param idConvenio
	 * @param razonSocial
	 * @param direccion
	 * @param nombreConvenio
	 * @return
	 */
	public static RespuestaDispVentaVO getRespuestaEnrolamientoDispVentaVO(String codigoRetorno, String glosaRetorno, String idDispositivo, String idCuenta, String idConvenio, String razonSocial, String direccion, String nombreConvenio){
		RespuestaDispVentaVO respuestaVO = new RespuestaDispVentaVO();
		
		respuestaVO.setRetorno(			MapperVO.getRespuestaVO(codigoRetorno, glosaRetorno));		
		respuestaVO.setIdDispositivo(	idDispositivo);
		respuestaVO.setIdConvenio(		idConvenio);
		respuestaVO.setIdCuenta(		idCuenta);
		respuestaVO.setRazonSocial(		razonSocial);
		respuestaVO.setDireccion(		direccion);
		respuestaVO.setNombreConvenio(	nombreConvenio);
						
		return respuestaVO;
	}
	
	/**
	 * 
	 * @param codigoRetorno
	 * @param glosaRetorno
	 * @return
	 */
	public static RespuestaVO getRespuestaVO(String codigoRetorno, String glosaRetorno){
		RespuestaVO retorno = new RespuestaVO();
		
		retorno.setCodigoRetorno(	codigoRetorno);
		retorno.setGlosaRetorno(	glosaRetorno);
		
		return retorno;
	}
	
	/**
	 * 
	 * @param codigoRetorno
	 * @param glosaRetorno
	 * @param glosaTrx
	 * @param idTrxPago
	 * @param idVendedor
	 * @param montoTrx
	 * @return
	 */
	public static RespuestaPagoVO getRespuestaPagoVO(String codigoRetorno, String glosaRetorno, TrxsPago pagoDB){
		RespuestaPagoVO respuestaVO = new RespuestaPagoVO();
			
		respuestaVO.setRetorno(		MapperVO.getRespuestaVO(codigoRetorno, glosaRetorno));
		if(pagoDB!=null){
			respuestaVO.setGlosaTrx(	pagoDB.getGlosaTrx());
			respuestaVO.setIdTrxPago(	pagoDB.getIdTrxsPago());
			respuestaVO.setIdVendedor(	pagoDB.getIdVendedor());
			respuestaVO.setMontoTrx(	pagoDB.getMontoTrx());
			respuestaVO.setFechaHoraTrx(MotorPagosHelper.getFechaString(pagoDB.getFechaHoraTrx()));
		}
		
		return respuestaVO;
	}
	
	/**
	 * 
	 * @param rutClienteOrigen
	 * @param monto
	 * @param ctaDestino
	 * @param ctaOrigen
	 * @param tokenCtaDestino
	 * @param tokenCtaOrigen
	 * @return
	 */
	public static TransferenciaVO getTransferenciaVO(Cliente clienteDB, TrxsPago trxDB, Cuenta cuentaDB, PagoVO pagoVO, TransferenciaVO transferenciaVO){
		
		transferenciaVO.setRutClienteOrigen( 	clienteDB.getRutCliente());
		transferenciaVO.setMonto(				String.valueOf(trxDB.getMontoTrx()));
		transferenciaVO.setCtaDestino(			trxDB.getNroCuentaComercio());
		transferenciaVO.setCtaOrigen(			cuentaDB.getNroCuenta());
		transferenciaVO.setTokenCtaDestino(		trxDB.getTipoCuentaComercio());
		transferenciaVO.setTokenCtaOrigen(		cuentaDB.getTipoCuenta());
		transferenciaVO.setClaveOperacion(		pagoVO.getIdTrxPago());
		transferenciaVO.setIdCliente(			clienteDB.getIdCliente());
		transferenciaVO.setIdDispositivoCliente(pagoVO.getIdDispositivoCliente());
		transferenciaVO.setHoraCobro(			trxDB.getHoraCobro());
		transferenciaVO.setLimiteCuentaOrigen(	cuentaDB.getLimiteCuenta());
		
		transferenciaVO.setMailCliente(			clienteDB.getMailcliente());	
		transferenciaVO.setMailComercio(		trxDB.getConvenio().getComercio().getEmailComercio());
		transferenciaVO.setNombreCliente(		clienteDB.getNombreCliente());
		transferenciaVO.setNombreComercio(		trxDB.getConvenio().getComercio().getRazonSocialComercio());
		transferenciaVO.setRutComercio(			trxDB.getConvenio().getComercio().getRutComercio());
		transferenciaVO.setNombreConvenio(		trxDB.getConvenio().getNombreConveniio());
		transferenciaVO.setIdVendedor(			trxDB.getIdVendedor());
		
		if(trxDB.getGlosaTrx()==null || trxDB.getGlosaTrx().isEmpty() || "null".equals(trxDB.getGlosaTrx())){
			transferenciaVO.setGlosaTrx(			" ");			
		}else{
			transferenciaVO.setGlosaTrx(			trxDB.getGlosaTrx());
		}
		
		transferenciaVO.setFechaHoraTrx(		MotorPagosHelper.getFechaString(trxDB.getFechaHoraTrx()));
		transferenciaVO.setTipoComercio(		trxDB.getConvenio().getComercio().getTipoComercio());
		
		//TODO pasar logica de taller de productos BD
		if(transferenciaVO.getRutClienteOrigen().equals(transferenciaVO.getRutComercio())){
			transferenciaVO.setTipoTransferencia(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TMC));
			
			TallerDeProductoVO taller = new TallerDeProductoVO();
			
			taller.setCodProductoCargo(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TMC_COD_PRODUCTO_CARGO));
			taller.setCodTrxCargo(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TMC_COD_TRX_CARGO));
			taller.setCodExtCargo(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TMC_COD_EXT_CARGO));
			taller.setCodProductoAbono(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TMC_COD_PRODUCTO_ABONO));
			taller.setCodTrxAbono(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TMC_COD_TRX_ABONO));
			taller.setCodExtAbono(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TMC_COD_EXT_ABONO));
			
			transferenciaVO.setTaller(taller);
		}else{
			transferenciaVO.setTipoTransferencia(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TEC));
			
			TallerDeProductoVO taller = new TallerDeProductoVO();
			
			taller.setCodProductoCargo(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TEC_COD_PRODUCTO_CARGO));
			taller.setCodTrxCargo(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TEC_COD_TRX_CARGO));
			taller.setCodExtCargo(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TEC_COD_EXT_CARGO));
			taller.setCodProductoAbono(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TEC_COD_PRODUCTO_ABONO));
			taller.setCodTrxAbono(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TEC_COD_TRX_ABONO));
			taller.setCodExtAbono(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TEC_COD_EXT_ABONO));
			
			transferenciaVO.setTaller(taller);
		}
		return transferenciaVO;
	}
	
	/**
	 * 
	 * @param comercioVO
	 * @return
	 */
	public static ClienteVO getClienteVO(ComercioVO comercioVO){
		ClienteVO clienteVO = new ClienteVO();
		
		clienteVO.setClaveSeguridad(	comercioVO.getClaveSeguridad());
		clienteVO.setCoordenada1(		comercioVO.getCoordenada1());
		clienteVO.setCoordenada2(		comercioVO.getCoordenada2());
		clienteVO.setCoordenada3(		comercioVO.getCoordenada3());
		clienteVO.setIdApp(				comercioVO.getIdApp());
		clienteVO.setRut(				comercioVO.getRutPersonas());
		clienteVO.setSerieDispSeguridad(comercioVO.getSerieDispSeguridad());
		clienteVO.setTipoDispSeguridad( comercioVO.getTipoDispSeguridad());
		
		return clienteVO;
	}
	
	/**
	 * 
	 * @param dispositivoDB
	 * @return
	 */
	public static DispositivoRegistradoVO getDispositivoRegistradoVO(Dispositivo dispositivoDB){
		DispositivoRegistradoVO dispVO = new DispositivoRegistradoVO();
		
		dispVO.setAlias(			dispositivoDB.getAliasDispositivo());
		dispVO.setId(				dispositivoDB.getIdDispositivo());
		dispVO.setModelo(			dispositivoDB.getModeloDispositivo());
		dispVO.setEstado(			dispositivoDB.getEstadoDispositivo());
		dispVO.setTipo(				dispositivoDB.getTipoDispositivo());
		dispVO.setFechaHoraCreacion(MotorPagosHelper.getFechaString(dispositivoDB.getFechaHoraCreacion()));
				
		return dispVO;
	}
	
	/**
	 * 
	 * @param conveniosDB
	 * @return ConveniosComercioVO
	 */
	public static ConveniosComercioVO getListaConveniosVO(List<Convenio> conveniosDB){
		ArrayList<ConvenioVO> listaConveniosVO = new ArrayList<ConvenioVO>();
		ConveniosComercioVO convenios = new ConveniosComercioVO();
		
		for(Convenio convenio:conveniosDB){
			ConvenioVO convenioVO = new ConvenioVO();
			convenioVO.setIdConvenio(		convenio.getIdConvenio());
			convenioVO.setNombreConvenio(	convenio.getNombreConveniio());
			
			listaConveniosVO.add(convenioVO);
		}
		convenios.setConvenios(listaConveniosVO);
		
		return convenios;
	}
	
	/**
	 * 
	 * @param listaTrxs
	 * @return MovimientosConvenioVO
	 */
	public static MovimientosConvenioVO getListaMovimientosVO(List<TrxsPago> listaTrxs){
		ArrayList<MovimientoVO> listaMovimientos = new ArrayList<MovimientoVO>();
		MovimientosConvenioVO movimientos = new MovimientosConvenioVO();
		
		for(TrxsPago trxPago:listaTrxs){
		
			MovimientoVO movimientoVO = new MovimientoVO();
			
			movimientoVO.setCuentaComercio(		trxPago.getNroCuentaComercio());
			movimientoVO.setFechaHoraTrx(		MotorPagosHelper.getFechaString(trxPago.getFechaHoraTrx()));
			movimientoVO.setGlosaTrx(			trxPago.getGlosaTrx());
			movimientoVO.setIdTrxPago(			trxPago.getIdTrxsPago());
			movimientoVO.setIdVendedor(			trxPago.getIdVendedor());
			movimientoVO.setMontoTrx(			trxPago.getMontoTrx());
			movimientoVO.setPropinaTrx(     	trxPago.getPropinaTrx());
			movimientoVO.setSubTotalTrx(		trxPago.getSubTotalTrx());
						
			if(trxPago.getCliente()==null){
				movimientoVO.setNombreCliente(		"Sin información");
				movimientoVO.setRutCliente(			"Sin información");
				movimientoVO.setCuentaCliente(		"Sin información");
			}else{
				movimientoVO.setNombreCliente(		trxPago.getCliente().getNombreCliente());
				movimientoVO.setRutCliente(			trxPago.getCliente().getRutCliente());
				movimientoVO.setCuentaCliente(		trxPago.getNroCuentaCliente());				
			}
			
			movimientoVO.setNombreConvenio(		trxPago.getConvenio().getNombreConveniio());			
			movimientoVO.setRutComercio(		trxPago.getConvenio().getComercio().getRutComercio());
			movimientoVO.setNombreComercio(		trxPago.getConvenio().getComercio().getRazonSocialComercio());
			movimientoVO.setEstadoTrx(			trxPago.getEstadoTrxl());
			
									
			listaMovimientos.add(movimientoVO);
		}
		movimientos.setMovimiento(listaMovimientos);
		
		return movimientos;
	}
	
	/**
	 * 
	 * @param idDispositivo
	 * @param pinCinco
	 * @return
	 */
	public static ValidarPinVO getValidarPinVO(String idDispositivo, String pinCinco){
		
		ValidarPinVO validarPin = new ValidarPinVO();
		validarPin.setIdDispositivo(	idDispositivo);
		validarPin.setPinCinco(			pinCinco);
		
		return validarPin;
	}
	
	/**
	 * 
	 * @param cuentaDB
	 * @return
	 */
	public static CuentaVO getCuentaVO(Cuenta cuentaDB) throws MotorPagosException{
		CuentaVO cuentaVO = new CuentaVO();
		
		cuentaVO.setLimiteCuenta(		cuentaDB.getLimiteCuenta());
		cuentaVO.setLlaveCuenta(CryptData.encriptar(cuentaDB.getNroCuenta()+cuentaDB.getTipoCuenta()));
		
		return cuentaVO;
	}
	
	/**
	 * 
	 * @param claveSeguridad
	 * @param coordenada1
	 * @param coordenada2
	 * @param coordenada3
	 * @param rutCliente
	 * @param rutEmpresa
	 * @param serieDisp
	 * @param tipoDisp
	 * @return
	 */
	public static ValidarDispositivoSegVO getValidarDispositivoSegVO(String claveSeguridad, String coordenada1, String coordenada2, String coordenada3, String rutCliente, String rutEmpresa, String serieDisp, String tipoDisp, String idApp){
		ValidarDispositivoSegVO dispositivo = new ValidarDispositivoSegVO();
		
		dispositivo.setClaveSeguridad(		claveSeguridad);
		dispositivo.setCoordenada1(			coordenada1);
		dispositivo.setCoordenada2(			coordenada2);
		dispositivo.setCoordenada3(			coordenada3);
		dispositivo.setRutCliente(			rutCliente);
		dispositivo.setRutEmpresa(			rutEmpresa);
		dispositivo.setSerieDispSeguridad(	serieDisp);
		dispositivo.setTipoDispSeguridad(	tipoDisp);
		dispositivo.setIdApp(				idApp);
		
		return dispositivo;
	}
	
	/**
	 * 
	 * @param trx
	 * @return
	 */
	public static MovimientoCierreVO getMovimientoCierreVO(TrxsPago trx){
		MovimientoCierreVO movimiento = new MovimientoCierreVO();
		
		movimiento.setFechaHoraTrx(	MotorPagosHelper.getHoraString(trx.getFechaHoraTrx()));
		movimiento.setGlosaTrx(		trx.getGlosaTrx());
		movimiento.setMontoTrx(		trx.getMontoTrx());
		
		return movimiento;
	}
	
	/**
	 * 
	 * @param clienteVO
	 * @return
	 */
	public static ComercioVO getComercioVO(ClienteVO clienteVO){
		ComercioVO comercioVO = new ComercioVO();

		comercioVO.setDireccion(		clienteVO.getDireccion());
		comercioVO.setEmail(			clienteVO.getMail());
		comercioVO.setNombreConvenio(	"Personal");
		comercioVO.setRazonSocial(		clienteVO.getNombre());
		comercioVO.setRutPersonas(		clienteVO.getRut());
		
		return comercioVO;
	}	
	
	/**
	 * 
	 * @param versionesDB
	 * @return
	 */
	public static ArrayList<VersionVO> getListaVersiones(List<VersionesApp> versionesDB){
		ArrayList<VersionVO> listaVersion = new ArrayList<VersionVO>();
		
		for(VersionesApp version:versionesDB){
			VersionVO ver = new VersionVO();
			
			ver.setEstado(	version.getEstado());
			ver.setIdApp(	version.getId().getIdapp());
			ver.setVersion(	version.getId().getNroversion());
			
			listaVersion.add(ver);
		}
		
		return listaVersion;
	}
}
