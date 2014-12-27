/**
 * 
 */
package cl.bch.motorpagos.dummy;

import java.util.ArrayList;

import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.AtribucionesComercioVO;
import cl.bch.motorpagos.vo.ClienteVO;
import cl.bch.motorpagos.vo.ComercioVO;
import cl.bch.motorpagos.vo.CuentaVO;
import cl.bch.motorpagos.vo.CuentasClienteVO;
import cl.bch.motorpagos.vo.DispositivoSeguridadVO;
import cl.bch.motorpagos.vo.DispositivosClienteVO;
import cl.bch.motorpagos.vo.LoginClienteVO;
import cl.bch.motorpagos.vo.LoginComercioVO;
import cl.bch.motorpagos.vo.RespuestaEnrolamientoVO;
import cl.bch.motorpagos.vo.RespuestaLoginClienteVO;
import cl.bch.motorpagos.vo.RespuestaLoginComercioVO;
import cl.bch.motorpagos.vo.RespuestaVO;
import cl.bch.motorpagos.vo.ValidarPinVO;

/**
 * @author boyanedel
 *
 */
public class DummyServices {
	
	public RespuestaLoginComercioVO callSDAF(LoginComercioVO comercioVO){
		RespuestaLoginComercioVO respuesta = new RespuestaLoginComercioVO();
		
		RespuestaVO retorno = new RespuestaVO();
		retorno.setCodigoRetorno(	"00");
		retorno.setGlosaRetorno(	"Dummy Service.");
		respuesta.setRetorno(retorno);
		
		ArrayList<CuentaVO> cuentas = new ArrayList<CuentaVO>();
		CuentaVO cuenta = new CuentaVO();
		cuenta.setLlaveCuenta("1234567890CTD");	
		cuenta.setMascaraCuenta(MotorPagosHelper.enmascaraCuenta("1234567890", "CVICCH", "CTD"));
		cuentas.add(cuenta);
		
		cuenta = new CuentaVO();
		cuenta.setLlaveCuenta("1234567890CTD");
		cuenta.setMascaraCuenta(MotorPagosHelper.enmascaraCuenta("1234567890", "CCNMN1", "CTD"));
		cuentas.add(cuenta);
		
		CuentasClienteVO cuentasVO = new CuentasClienteVO();
		cuentasVO.setCuentasCliente(cuentas);
		respuesta.setCuentas(cuentasVO);
		
		respuesta.setDireccion("Dir Dummy");
		respuesta.setMail("email@dummy.cl");
		respuesta.setNombre("Nombre Empresa Dummy Ltda.");
				
		return respuesta;
	}
	
	/**
	 * 
	 * @param clienteVO
	 * @return
	 */
	public RespuestaVO validarDispositivoSeguridad(ClienteVO clienteVO){
		RespuestaVO respuesta = new RespuestaVO();
		
		respuesta.setCodigoRetorno("00");
		respuesta.setGlosaRetorno("Respuesta OK Dummy.");
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public RespuestaLoginClienteVO loginCliente(LoginClienteVO loginVO){
		RespuestaLoginClienteVO respuesta = new RespuestaLoginClienteVO();
		
		ArrayList<CuentaVO> cuentas = new ArrayList<CuentaVO>();
		CuentaVO cuenta = new CuentaVO();
		cuenta.setLlaveCuenta("1234567890CTD");	
		cuenta.setMascaraCuenta(MotorPagosHelper.enmascaraCuenta("1234567890", "CVICCH", "CTD"));
		cuentas.add(cuenta);
		
		cuenta = new CuentaVO();
		cuenta.setLlaveCuenta("1234567890CTD");
		cuenta.setMascaraCuenta(MotorPagosHelper.enmascaraCuenta("1234567890", "CCNMN1", "CTD"));
		cuentas.add(cuenta);
		
		CuentasClienteVO cuentasVO = new CuentasClienteVO();
		cuentasVO.setCuentasCliente(cuentas);
		respuesta.setCuentas(cuentasVO);
		
		RespuestaVO retorno = new RespuestaVO();
		retorno.setCodigoRetorno("00");
		retorno.setGlosaRetorno("Dummy Service.");
		respuesta.setRetorno(retorno);
		
		respuesta.setNombre("Nombre Persona Dummy");
		respuesta.setDireccion("Direccion Dummy 123");
		respuesta.setMail("email@dummy.cl");
		
		respuesta.setDireccion("Direccion Dummy #123");
		respuesta.setMail("dummy@gmail.com");
		respuesta.setNombre("Dummy Dummy Dummy Dummy");		
				
		return respuesta;
	}
	
	/**
	 * 
	 * @param loginVO
	 * @return
	 * @throws Exception
	 */
	public DispositivosClienteVO obtenerDispositivosSeguridad(LoginClienteVO loginVO) throws Exception {
		ArrayList<DispositivoSeguridadVO> listaDispositivos = new ArrayList<DispositivoSeguridadVO>();
		
		DispositivoSeguridadVO disp1 = new DispositivoSeguridadVO();
		disp1.setSerie("12345678");
		disp1.setTipo("8");
		listaDispositivos.add(disp1);
		
		DispositivoSeguridadVO disp2 = new DispositivoSeguridadVO();
		disp2.setSerie("87654321");
		disp2.setTipo("8");
		listaDispositivos.add(disp2);
		
		DispositivosClienteVO dispositivoVO = new DispositivosClienteVO();		
		dispositivoVO.setDispSeguridad(listaDispositivos);
		
		return dispositivoVO;
	}
	
	/**
	 * 
	 * @param clienteVO
	 * @return
	 */
	public RespuestaEnrolamientoVO enrolarCliente(ClienteVO clienteVO){
		RespuestaEnrolamientoVO respuesta = new RespuestaEnrolamientoVO();
		respuesta.setIdConvenio("");
		respuesta.setIdCuenta("idCuenta");
		respuesta.setIdDispositivo("idDispositivo");
		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Dummy"));
		return respuesta;
	}

	/**
     * 
     * @param comercioVO
     * @return
     */
    public RespuestaEnrolamientoVO enrolarComercio(ComercioVO comercioVO){
    	RespuestaEnrolamientoVO respuesta = new RespuestaEnrolamientoVO();
		respuesta.setIdConvenio("idConvenio");
		respuesta.setIdCuenta("idCuenta");
		respuesta.setIdDispositivo("idDispositivo");
		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Dummy"));
		return respuesta;
    }
    
	/**
	 * 
	 * @param loginVO
	 * @return RespuestaLoginComercioVO
	 */
	public RespuestaLoginComercioVO getInfoEmpresa(LoginComercioVO loginVO){
		RespuestaLoginComercioVO respuesta = new RespuestaLoginComercioVO();
		
		RespuestaVO retorno = new RespuestaVO();
		retorno.setCodigoRetorno(	"00");
		retorno.setGlosaRetorno(	"Dummy Service.");
		respuesta.setRetorno(retorno);
		
		respuesta.setDireccion("Dir Dummy");
		respuesta.setMail("email@dummy.cl");
		respuesta.setNombre("Nombre Empresa Dummy Ltda.");
				
		return respuesta;
	}
	
	/**
	 * 
	 * @param atribucionesVO
	 * @return
	 */
	public RespuestaVO validarPoderesApoderado(AtribucionesComercioVO atribucionesVO){
		RespuestaVO respuesta = new RespuestaVO();
		
		respuesta.setCodigoRetorno("00");
		respuesta.setGlosaRetorno("Respuesta OK Dummy.");
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param pinVO
	 * @return
	 */
	public RespuestaVO validarPin(ValidarPinVO pinVO){
		RespuestaVO respuesta = new RespuestaVO();
		
		respuesta.setCodigoRetorno("00");
		respuesta.setGlosaRetorno("Respuesta OK Dummy.");
		
		return respuesta;
	}
}
