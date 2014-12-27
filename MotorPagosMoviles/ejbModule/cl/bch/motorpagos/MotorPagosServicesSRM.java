/**

 * 
 */
package cl.bch.motorpagos;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.exceptions.MotorPagosException;
import cl.bch.motorpagos.security.CryptData;
import cl.bch.motorpagos.srmclient.CallSDAF;
import cl.bch.motorpagos.srmclient.CallSLGN;
import cl.bch.motorpagos.srmclient.FilialSRM;
import cl.bch.motorpagos.srmclient.FilialesRsp;
import cl.bch.motorpagos.srmclient.LoginRsp;
import cl.bch.motorpagos.srmclient.Producto;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.CuentaVO;
import cl.bch.motorpagos.vo.CuentasClienteVO;
import cl.bch.motorpagos.vo.FilialVO;
import cl.bch.motorpagos.vo.FilialesEmpresaVO;
import cl.bch.motorpagos.vo.LoginComercioVO;
import cl.bch.motorpagos.vo.RespuestaLoginComercioVO;

/**
 * @author boyanedel
 *
 */
public class MotorPagosServicesSRM {
	private static final Logger logger = LoggerFactory.getLogger(MotorPagosServicesSRM.class);
	/**
	 * 
	 * @param comercioVO
	 * @return
	 */
	public RespuestaLoginComercioVO callSDAF(LoginComercioVO comercioVO){
		RespuestaLoginComercioVO respuesta = null;
		
		CallSDAF callSRM = new CallSDAF();		
		LoginRsp resp = callSRM.executeSDAF(comercioVO);
		logger.debug(resp.toString());
		
		respuesta = new RespuestaLoginComercioVO();
		
		if(resp.getRetorno().equalsIgnoreCase("00") && resp.getProductosCTDoJUV().size() > 0){
			try{
			
				CuentasClienteVO cuentas = new CuentasClienteVO();
				ArrayList<CuentaVO> listCuentas = new ArrayList<CuentaVO>();
				
				List<Producto> productos = resp.getProductosCTDoJUV();
				for(int i=0;i<productos.size();i++){
					Producto producto = productos.get(i);
					
					CuentaVO cuenta = new CuentaVO();
					cuenta.setLlaveCuenta(		CryptData.encriptar(producto.getLlave()+producto.getIdProducto()));
					cuenta.setMascaraCuenta(	MotorPagosHelper.enmascaraCuenta(producto.getLlave(), null, producto.getIdProducto()));
					
					listCuentas.add(cuenta);
				}
				cuentas.setCuentasCliente(listCuentas);
				respuesta.setCuentas(cuentas);
				
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Login Exitoso."));
			}catch(MotorPagosException e){
				logger.error("Error al encriptar las llaves de los productos.", e);
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
			}			
		}else{
			respuesta.setRetorno(MapperVO.getRespuestaVO(resp.getRetorno(), "Clave Inválida."));
		}
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param rutEmpresa
	 * @return
	 */
	public FilialesEmpresaVO callSLGN(String rutEmpresa){
		CallSLGN srm = new CallSLGN();
		FilialesRsp filiales = srm.executeSLGN(rutEmpresa);
		FilialesEmpresaVO respuesta = null;
		
		if (filiales.getNumeroFiliales()>0){
			respuesta = new FilialesEmpresaVO();
			ArrayList<FilialVO> filialesVO = new ArrayList<FilialVO>();
			
			for(FilialSRM filial:filiales.getFiliales()){
				FilialVO filialVO = new FilialVO();
				
				filialVO.setRutFilial(MotorPagosHelper.formatearRutGuion(filial.getRutFilial()));
				filialVO.setNombreFilial(filial.getNombreFilial());
				
				filialesVO.add(filialVO);
			}
			respuesta.setFilial(filialesVO);
		}
		return respuesta;
	}
	
//	public static void main(String args[]){
//		MotorPagosServicesSRM srm = new MotorPagosServicesSRM();
//		
//		LoginComercioVO comercio = new LoginComercioVO();
//		comercio.setRutEmpresa("93129000-2");
//		comercio.setRutPersona("1-9");
//		comercio.setClave("530c0feebb1f9bb8");//"c9463c9de9ee1d75");
//		
//		srm.callSDAF(comercio);
//	}
	

}
