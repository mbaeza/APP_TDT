/**
 * 
 */
package cl.bch.motorpagos.util;

import java.text.ParseException;
import java.util.Date;

import cl.bch.motorpagos.exceptions.MotorPagosException;
import cl.bch.motorpagos.persistencia.*;
import cl.bch.motorpagos.security.CryptData;
import cl.bch.motorpagos.vo.ClienteVO;
import cl.bch.motorpagos.vo.ComercioVO;
import cl.bch.motorpagos.vo.PreCobroVO;
import cl.bch.motorpagos.vo.RespuestaFichaChicaVO;

/**
 * @author boyanedel
 *
 */
public final class MapperDB {
	
	/**
	 * 
	 */
	private MapperDB(){
		
	}

	/**
	 * 
	 * @param clienteVO
	 * @param idCliente
	 * @return
	 */
	public static Cliente getClienteDB(RespuestaFichaChicaVO clienteVO, String idCliente){
		Cliente clienteDB = new Cliente();
		
		clienteDB.setFechaHoraActivacion(	new Date());
		clienteDB.setIdCliente(			idCliente);
		clienteDB.setMailcliente(		MotorPagosHelper.formateaString(clienteVO.getMail(), 100));
		clienteDB.setNombreCliente(		MotorPagosHelper.formateaString(clienteVO.getNombre(), 100));		
		clienteDB.setRutCliente(		MotorPagosHelper.formateaString(clienteVO.getRutCliente(), 10));	
		clienteDB.setCodigoMarca(       MotorPagosHelper.formateaString(clienteVO.getMarca(), 1));	

		return clienteDB;
	}
	
	/**
	 * 
	 * @param clienteVO
	 * @param idCliente
	 * @return
	 */
	public static Cliente getClienteDB(ClienteVO clienteVO, String idCliente){
		Cliente clienteDB = new Cliente();
		
		clienteDB.setFechaHoraActivacion(	new Date());
		clienteDB.setIdCliente(			idCliente);
		clienteDB.setMailcliente(		MotorPagosHelper.formateaString(clienteVO.getMail(), 100));
		clienteDB.setNombreCliente(		MotorPagosHelper.formateaString(clienteVO.getNombre(), 100));		
		clienteDB.setRutCliente(		MotorPagosHelper.formateaString(clienteVO.getRut(), 10));	
		clienteDB.setCodigoMarca(       MotorPagosHelper.formateaString(clienteVO.getMarca(), 1));	

		return clienteDB;
	}
	
	/**
	 * 
	 * @param dispositivoId
	 * @param llaveEncode
	 * @param claveDispositivo
	 * @param aliasDispositivo
	 * @param modeloDispositivo
	 * @return
	 */
	public static Dispositivo getDispositivoDB(String dispositivoId, String claveDispositivo, String aliasDispositivo, String modeloDispositivo, String tipoDispositivo){
		Dispositivo dispositivoDB = new Dispositivo();
		
		dispositivoDB.setEstadoDispositivo(	ConstantesMotorPagos.ESTADO_ACTIVO_DISP);
		dispositivoDB.setIdDispositivo(		dispositivoId);
		dispositivoDB.setClaveDispositivo(	MotorPagosHelper.formateaString(claveDispositivo, 16));
		dispositivoDB.setAliasDispositivo(	MotorPagosHelper.formateaString(aliasDispositivo, 20));
		dispositivoDB.setModeloDispositivo(	MotorPagosHelper.formateaString(modeloDispositivo, 50));
		dispositivoDB.setIntentosFallidos(	0);
		dispositivoDB.setFechaHoraCreacion( new Date());
		dispositivoDB.setTipoDispositivo(	tipoDispositivo);
										
		return dispositivoDB;
	}
	
	/**
	 * 
	 * @param idDispositivo
	 * @param rutCliente
	 * @return
	 */
	public static DispositivosCliente getDispositivosClienteDB(String idDispositivo, String idCliente, String idCuenta){
		DispositivosCliente dispositivoClienteDB = new DispositivosCliente();
		
		DispositivosClientePK pkDB = new DispositivosClientePK();		
		pkDB.setIdDispositivo(	idDispositivo);
		pkDB.setIdCliente(		idCliente);	
		
		dispositivoClienteDB.setId(			pkDB);
		dispositivoClienteDB.setIdCuenta(	idCuenta);
		
		return dispositivoClienteDB;
	}
	
	/**
	 * 
	 * @param idDispositivo
	 * @param idConvenio
	 * @return
	 */
	public static DispositivosConvenio getDispositivosConvenioDB(String idDispositivo, String idConvenio, String idCuenta){
		DispositivosConvenio dispositivoConvenioDB = new DispositivosConvenio();
		
		dispositivoConvenioDB.setId(MapperDB.getDispositivoConvenioPK(idDispositivo, idConvenio));
		dispositivoConvenioDB.setIdCuenta(idCuenta);
		
		return dispositivoConvenioDB;
	}
	
	/**
	 * 
	 * @param idCuenta
	 * @param rutCliente
	 * @param mascaraCuenta
	 * @param nroCuenta
	 * @param tipoCuenta
	 * @return
	 */
	public static Cuenta getCuentaDB(String idCuenta, String llaveCuenta, String limiteCuenta) throws MotorPagosException{
		Cuenta cuentaDB = new Cuenta();
				
		cuentaDB.setIdCuenta(			idCuenta);
		cuentaDB.setFechaActivacion(	new Date());
		cuentaDB.setEstadoCuenta(		ConstantesMotorPagos.ESTADO_CUENTA_ACTIVA);
		cuentaDB.setLimiteCuenta(		Integer.parseInt(limiteCuenta));		
			
		String keyCuenta = CryptData.desEncriptar(llaveCuenta);
		
		cuentaDB.setNroCuenta(		MotorPagosHelper.cleanCuenta(keyCuenta.substring(0, keyCuenta.length()-3)));
		cuentaDB.setTipoCuenta(		keyCuenta.substring(keyCuenta.length()-3));
		
		return cuentaDB;
	}	
	
	/**
	 * 
	 * @param cuentaDB
	 * @return
	 */
	public static AdmCuentasHist getAdmCuentaHistDB(Cuenta cuentaDB){
		AdmCuentasHist histCuenta = new AdmCuentasHist();
		
		histCuenta.setFechaActivacion(	cuentaDB.getFechaActivacion());
		histCuenta.setLimiteCuenta(		cuentaDB.getLimiteCuenta());
		histCuenta.setEstadoCuenta(		cuentaDB.getEstadoCuenta());
		histCuenta.setNroCuenta(		cuentaDB.getNroCuenta());
		histCuenta.setTipoCuenta(		cuentaDB.getTipoCuenta());
		
		AdmCuentasHistPK pk = new AdmCuentasHistPK();
		pk.setFechaUpdate(		new Date());
		pk.setIdCuenta(			cuentaDB.getIdCuenta());		
		
		histCuenta.setId(pk);
		
		return histCuenta;
	}
	
	/**
	 * 
	 * @param comercioVO
	 * @param idComercio
	 * @return
	 */
	public static Comercio getComercioDB(ComercioVO comercioVO, String idComercio){
		Comercio comercioDB = new Comercio();
		
		comercioDB.setFechaHoraActivacion(	new Date()); 
		comercioDB.setEmailComercio(		MotorPagosHelper.formateaString(comercioVO.getEmail(), 100));		
		comercioDB.setDireccionComercio(	MotorPagosHelper.formateaString(comercioVO.getDireccion(), 200));
		comercioDB.setIdComercio(			idComercio);
		
				
		if(comercioVO.getRutEmpresa()==null || comercioVO.getRutEmpresa().length() == 0){
			comercioDB.setRutComercio(		MotorPagosHelper.formateaString(comercioVO.getRutPersonas(), 10));
			comercioDB.setTipoComercio(		ConstantesMotorPagos.COMERCIO_PERSONA);
		}else{
			comercioDB.setRutComercio(		MotorPagosHelper.formateaString(comercioVO.getRutEmpresa(), 10));
			comercioDB.setTipoComercio(		ConstantesMotorPagos.COMERCIO_EMPRESA);
		}
		
		comercioDB.setRutApoderado(			MotorPagosHelper.formateaString(comercioVO.getRutPersonas(), 10));
		comercioDB.setRazonSocialComercio(	MotorPagosHelper.formateaString(comercioVO.getRazonSocial(), 100));

		return comercioDB;
	}	
	
	
	/**
	 * 
	 * @param comercioVO
	 * @param idComercio
	 * @return
	 */
	public static Comercio getComercioDB(ClienteVO clienteVO, String idComercio){
		Comercio comercioDB = new Comercio();
		
		comercioDB.setFechaHoraActivacion(	new Date()); 
		comercioDB.setEmailComercio(		MotorPagosHelper.formateaString(clienteVO.getMail(), 100));		
		comercioDB.setDireccionComercio(	MotorPagosHelper.formateaString(clienteVO.getDireccion(), 200));
		comercioDB.setIdComercio(			idComercio);
	
		comercioDB.setRutComercio(			MotorPagosHelper.formateaString(clienteVO.getRut(), 10));
		comercioDB.setTipoComercio(			ConstantesMotorPagos.COMERCIO_PERSONA);
				
		comercioDB.setRutApoderado(			MotorPagosHelper.formateaString(clienteVO.getRut(), 10));
		comercioDB.setRazonSocialComercio(	MotorPagosHelper.formateaString(clienteVO.getNombre(), 100));

		return comercioDB;
	}
	
	/**
	 * 
	 * @param pagoVO
	 * @return
	 */
	public static TrxsPago getTrxsPagoDB(PreCobroVO preCobroVO, Convenio convenioDB, Cuenta cuentaDB){
		TrxsPago pagoDB = new TrxsPago();
		
		pagoDB.setIdTrxsPago(				preCobroVO.getIdTrxPago());
		pagoDB.setConvenio(					convenioDB);
		pagoDB.setIdDispositivoComercio(	preCobroVO.getIdDispositivoComercio());
		pagoDB.setIdVendedor(				MotorPagosHelper.formateaString(preCobroVO.getIdVendedor(), 20));
		pagoDB.setNroCuentaComercio(		cuentaDB.getNroCuenta());
		pagoDB.setTipoCuentaComercio(		cuentaDB.getTipoCuenta());
		pagoDB.setMontoTrx(					preCobroVO.getMontoTrx());
		pagoDB.setPropinaTrx(				preCobroVO.getPropinaTrx());
		
		if(preCobroVO.getSubTotalTrx()==0){
			pagoDB.setSubTotalTrx(			preCobroVO.getMontoTrx());
		}else{
			pagoDB.setSubTotalTrx(			preCobroVO.getSubTotalTrx());
		}
		
		pagoDB.setGlosaTrx(					MotorPagosHelper.formateaString(preCobroVO.getGlosaTrx(), 45));
		pagoDB.setEstadoTrxl(				ConstantesMotorPagos.TRX_PENDIENTE);
		pagoDB.setHoraCobro(				preCobroVO.getHoraCobro());		
		pagoDB.setFechaHoraTrx(				new Date());
		pagoDB.setLlaveComercio(			preCobroVO.getLlaveComercio());
		
		return pagoDB;
	}
	
	/**
	 * 
	 * @param idConvenio
	 * @param nombreConvenio
	 * @return
	 */
	public static Convenio getConvenioDB(String idConvenio, String nombreConvenio, Comercio comercioDB, String pinAdmin){
		Convenio convenioDB = new Convenio();
		
		convenioDB.setIdConvenio(		idConvenio);
		convenioDB.setNombreConveniio(	MotorPagosHelper.formateaString(nombreConvenio, 45));
		convenioDB.setComercio(			comercioDB);
		convenioDB.setEstadoConvenio(	ConstantesMotorPagos.ESTADO_ACTIVO_DISP);
		convenioDB.setIntentosFallidos(	0);
						
		return convenioDB;
	}
	
	/**
	 * 
	 * @param idConvenio
	 * @param idCuenta
	 * @return
	 */
	public static CuentasConvenio getCuentaConvenio(String idConvenio, String idCuenta){
		CuentasConvenio cuenta = new CuentasConvenio();
		CuentasConvenioPK pk = new CuentasConvenioPK();
		
		pk.setIdConvenio(idConvenio);
		pk.setIdCuentas(idCuenta);
		cuenta.setId(pk);
		
		return cuenta;
	}
	
	/**
	 * 
	 * @param idDispositivo
	 * @param idConvenio
	 * @return
	 */
	public static DispositivosConvenioPK getDispositivoConvenioPK(String idDispositivo, String idConvenio){
		DispositivosConvenioPK pk = new DispositivosConvenioPK();
		
		pk.setIdConvenio(idConvenio);
		pk.setIdDispositivo(idDispositivo);
		
		return pk;
	}
	
	/**
	 * 
	 * @param idCuenta
	 * @param idConvenio
	 * @return
	 */
	public static CuentasConvenioPK getCuentaConvenioPK(String idCuenta, String idConvenio){
		CuentasConvenioPK pk = new CuentasConvenioPK();
		
		pk.setIdCuentas(idCuenta);
		pk.setIdConvenio(idConvenio);
		
		return pk;
	} 
	
	/**
	 * 
	 * @param idDispositivo
	 * @param idCliente
	 * @return
	 */
	public static DispositivosClientePK getDispositivoClientePK(String idDispositivo, String idCliente){
		DispositivosClientePK pk = new DispositivosClientePK();
		
		pk.setIdCliente(idCliente);
		pk.setIdDispositivo(idDispositivo);
		
		return pk;
	}	
	
	/**
	 * 
	 * @param nroCuenta
	 * @param idCliente
	 * @param fecha
	 * @return
	 */
	public static LimiteCuentaPK getLimiteCuentaPK(String nroCuenta, String idCliente, String fechaTrx){
		LimiteCuentaPK limitePk = new LimiteCuentaPK();
		
		limitePk.setNroCuenta(	nroCuenta);
		try{
			limitePk.setFechaTrx(	MotorPagosHelper.getFechaDateddMMyyyy(fechaTrx));
		}catch(ParseException e){
			limitePk.setFechaTrx(	new Date());
		}
		limitePk.setIdCliente(	idCliente);
		
		return limitePk;
	}
	
	/**
	 * 
	 * @param limitePk
	 * @param montoAcumulado
	 * @return
	 */
	public static LimiteCuenta getLimiteCuenta(LimiteCuentaPK limitePk, int montoAcumulado){
		LimiteCuenta limiteCuenta = new LimiteCuenta();
		
		limiteCuenta.setMontoAcumulado(	montoAcumulado);
		limiteCuenta.setId(				limitePk);
		
		return limiteCuenta;
	}
	
	/**
	 * 
	 * @param idCliente
	 * @param idCuenta
	 * @return
	 */
	public static CuentasCliente getCuentaCliente(String idCliente, String idCuenta){
		CuentasCliente cuentaCli = new CuentasCliente();
		CuentasClientePK pk = new CuentasClientePK();
		
		pk.setIdCliente(idCliente);
		pk.setIdCuentas(idCuenta);
		
		cuentaCli.setId(pk);
		
		return cuentaCli;
	}
	
	/**
	 * 
	 * @param idApp
	 * @param version
	 * @return
	 */
	public static VersionesAppPK getVersionesAppPK(String idApp, String version){
		VersionesAppPK pk = new VersionesAppPK();
		
		pk.setIdapp(		idApp);
		pk.setNroversion(	version);
		
		return pk;
	}

}
