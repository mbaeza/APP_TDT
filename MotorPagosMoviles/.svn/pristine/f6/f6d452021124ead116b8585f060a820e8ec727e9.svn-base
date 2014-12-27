package cl.bch.motorpagos.ejb;

import javax.ejb.Local;

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

@Local
public interface AdministracionWebBeanLocal {

	RespuestaDispRegistradosVO listaDispositivosCliente(String rutCliente);
	
	RespuestaDispositivosSeguridadVO listaDispositivosSeguridad(ObtenerDispositivosSeguridadVO dispositivosSeguridadVO);
	
	RespuestaVO cambiarEstadoDispositivo(CambioEstadoVO dispositivo);
	
	RespuestaConveniosVO obtieneConveniosComercio(ObtenerConveniosComercioVO conveniosComercioVO);
	
	RespuestaListadoMovimientosVO obtieneMovimientosConvenioComercio(ConsultaListadoMovVO consultaVO);
	
	RespuestaListadoMovimientosVO obtieneMovimientosCliente(ConsultaListadoMovClienteVO consultaVO);
	
	RespuestaLoginClienteVO loginClienteWeb(LoginClienteVO loginVO);
	
	RespuestaVO loginAdmWeb(LoginAdmWebVO loginVO);
	
}
