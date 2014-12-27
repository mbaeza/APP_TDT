package cl.bch.motorpagos.ejb;

import javax.ejb.Local;

import cl.bch.motorpagos.vo.*;

@Local
public interface ComercioBeanLocal {

	RespuestaLoginComercioVO loginComercio(LoginComercioVO loginComercioVO);
	
	RespuestaEnrolamientoVO enrolarComercio(ComercioVO comercioVO);
	
	RespuestaDispVentaVO enrolarDispositivoVenta(DispositivoVentaVO dispositivoVO);
	
	RespuestaVO validarPoderesApoderado(AtribucionesComercioVO atribucionesVO);
	
	RespuestaDispRegistradosVO listaDispositivosConvenio(String idConvenio);
	
	RespuestaVO desvincularDispositivoConvenio(DesvincularDispositivoVO dispositivoVO);
	
	RespuestaListadoMovimientosVO obtieneMovimientosRecientesComercio(ObtenerMovRecientesVO obtenerMovVO);
	
	RespuestaCierreDiarioVO cierreDiarioDispositivo(CierreDiarioVO cierreDiario);
	
	RespuestaVO setAvatarComercio(AvatarComercioVO avatarVO);
	
	RespuestaMediosDePagoVO obtenerCuentasFilial(ObtenerCuentasFilialVO obtenerVO);
}
