package cl.bch.motorpagos.ejb;

import javax.ejb.Local;

import cl.bch.motorpagos.vo.ActualizarMedioDePagoVO;
import cl.bch.motorpagos.vo.ClienteVO;
import cl.bch.motorpagos.vo.ConsultaMarcaClienteVO;
import cl.bch.motorpagos.vo.ConsultaSaldoVO;
import cl.bch.motorpagos.vo.LoginClienteVO;
import cl.bch.motorpagos.vo.ObtenerMediosDePagoVO;
import cl.bch.motorpagos.vo.ObtenerMovRecientesVO;
import cl.bch.motorpagos.vo.RespuestaConsultaSaldoVO;
import cl.bch.motorpagos.vo.RespuestaEnrolamientoVO;
import cl.bch.motorpagos.vo.RespuestaListadoMovimientosClienteVO;
import cl.bch.motorpagos.vo.RespuestaLoginClienteVO;
import cl.bch.motorpagos.vo.RespuestaMarcaClienteVO;
import cl.bch.motorpagos.vo.RespuestaMediosDePagoVO;

@Local
public interface ClienteBeanLocal {

	RespuestaLoginClienteVO loginCliente(LoginClienteVO loginVO);
	
	RespuestaEnrolamientoVO enrolarCliente(ClienteVO clienteVO);
	
//	RespuestaVO validarDispositivoSeguridadCliente(ValidarDispositivoSegVO dispositivoVO);
	
	RespuestaMediosDePagoVO obtenerMediosDePagoCliente(ObtenerMediosDePagoVO obtenerVO);
	
	RespuestaEnrolamientoVO actualizarMedioDePagoCliente(ActualizarMedioDePagoVO medioDePagoVO);
	
	RespuestaConsultaSaldoVO obtenerSaldoCuentaCliente(ConsultaSaldoVO consultaVO);
	
	RespuestaListadoMovimientosClienteVO obtieneMovimientosRecientesCliente(ObtenerMovRecientesVO obtenerVO);

	RespuestaMarcaClienteVO obtenerMarcaCliente(ConsultaMarcaClienteVO consultaVO);
	
//	RespuestaVO setAvatarCliente(String idCliente, String imagenBase64);
}
