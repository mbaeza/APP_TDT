package cl.bch.motorpagos.ejb;

import javax.ejb.Local;

import cl.bch.motorpagos.vo.CambioPinVO;
import cl.bch.motorpagos.vo.CancelaCobroVO;
import cl.bch.motorpagos.vo.PagoVO;
import cl.bch.motorpagos.vo.PreCobroVO;
import cl.bch.motorpagos.vo.ResearPinVO;
import cl.bch.motorpagos.vo.RespuestaPagoVO;
import cl.bch.motorpagos.vo.RespuestaPreCobroVO;
import cl.bch.motorpagos.vo.RespuestaVO;
import cl.bch.motorpagos.vo.RespuestaValidaPagoVO;
import cl.bch.motorpagos.vo.RespuestaValidaPinVO;
import cl.bch.motorpagos.vo.ValidarPagoVO;
import cl.bch.motorpagos.vo.ValidarPinVO;
import cl.bch.motorpagos.vo.ObtenerPreCobroVO;

@Local
public interface PagoServicioBeanLocal {

	RespuestaPagoVO efectuarPago(PagoVO pagoVO);
	
	RespuestaValidaPagoVO obtenerPago(ValidarPagoVO validarPagoVO);
	
	RespuestaValidaPinVO validarPin(ValidarPinVO pinVO);
	
	RespuestaVO resetearPin(ResearPinVO reseteoVO);
	
	RespuestaVO cambiarPin(CambioPinVO cambioVO);
	
	RespuestaPreCobroVO obtenerPreCobro(ObtenerPreCobroVO preCobroVO);
	
	RespuestaValidaPagoVO registrarPreCobro(PreCobroVO preCobroVO);
	
	RespuestaVO cancelarCobro(CancelaCobroVO cancelaVO);
}
