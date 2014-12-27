/**
 * 
 */
package cl.bch.motorpagos.command;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.TrxsPago;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.RespuestaValidaPagoVO;
import cl.bch.motorpagos.vo.ValidarPagoVO;

/**
 * @author boyanedel
 *
 */
public class ValidarPagoCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ValidarPagoCommand.class);

    /**
     * 
     * @param validarPagoVO
     * @return 00 ok
     * 		   01 pendiente
     * 		   09 cancelada
     * 		   10 rechazada
     * 		   99 error
     */
    public RespuestaValidaPagoVO validarPago(ValidarPagoVO validarPagoVO){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaValidaPagoVO respuesta = new RespuestaValidaPagoVO();
    			
		try{
			TrxsPago trxPagoDB = em.find(TrxsPago.class, validarPagoVO.getIdTrxPago());
			
			logger.debug("Se busca la transaccion [{}] en los registros del sistema.", validarPagoVO.getIdTrxPago());
			if(trxPagoDB==null){
				logger.error("Error, La Transacción ["+validarPagoVO.getIdTrxPago()+"] no existe en los registros del sistema.");
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
			}else{				
	    		if(trxPagoDB.getConvenio().getIdConvenio().equals(validarPagoVO.getIdConvenio())){
	    			
	    			if(trxPagoDB.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_PENDIENTE)){    				
	    				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_PEND, "Confirmación de pago pendiente."));
	    				
	    				respuesta.setFechaHoraTrx(		MotorPagosHelper.getFechaString(trxPagoDB.getFechaHoraTrx()));
    					respuesta.setNombreComercio(	trxPagoDB.getConvenio().getComercio().getRazonSocialComercio());
    					respuesta.setRutComercio(		trxPagoDB.getConvenio().getComercio().getRutComercio());
    					respuesta.setNombreConvenio(	trxPagoDB.getConvenio().getNombreConveniio());
    					respuesta.setDireccionComercio(	trxPagoDB.getConvenio().getComercio().getDireccionComercio());
    					respuesta.setMontoTrx(			trxPagoDB.getMontoTrx());
    					respuesta.setPropinaTrx(		trxPagoDB.getPropinaTrx());
    					respuesta.setSubTotalTrx(		trxPagoDB.getSubTotalTrx());
    					respuesta.setIdTrx(				trxPagoDB.getIdTrxsPago());
    					respuesta.setIdVendedor(		trxPagoDB.getIdVendedor());
    					respuesta.setGlosaTrx(			trxPagoDB.getGlosaTrx());
	    			}else{
	    				if(trxPagoDB.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_CANCELADA)
	    					|| trxPagoDB.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_PAGADA)
	    					|| trxPagoDB.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_RECHAZO)){
	    					
	    					respuesta.setNombreCliente(		trxPagoDB.getCliente().getNombreCliente());
	    					respuesta.setRutCliente(		trxPagoDB.getCliente().getRutCliente());
	    				}
	    				
    					respuesta.setFechaHoraTrx(		MotorPagosHelper.getFechaString(trxPagoDB.getFechaHoraTrx()));
    					respuesta.setNombreComercio(	trxPagoDB.getConvenio().getComercio().getRazonSocialComercio());
    					respuesta.setRutComercio(		trxPagoDB.getConvenio().getComercio().getRutComercio());
    					respuesta.setNombreConvenio(	trxPagoDB.getConvenio().getNombreConveniio());
    					respuesta.setDireccionComercio(	trxPagoDB.getConvenio().getComercio().getDireccionComercio());
    					respuesta.setMontoTrx(			trxPagoDB.getMontoTrx());
    					respuesta.setPropinaTrx(		trxPagoDB.getPropinaTrx());
    					respuesta.setSubTotalTrx(		trxPagoDB.getSubTotalTrx());
    					respuesta.setIdTrx(				trxPagoDB.getIdTrxsPago());
    					respuesta.setIdVendedor(		trxPagoDB.getIdVendedor());
    					respuesta.setGlosaTrx(			trxPagoDB.getGlosaTrx());
    					    					
	    				if(trxPagoDB.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_CANCELADA)){    					
	    					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_CANC, "Transacción rechazada."));	    				
	    				}else if(trxPagoDB.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_PAGADA)){    				
	    					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Transacción pagada."));	    				
	    				}else if(trxPagoDB.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_RECHAZO)){
	    					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_RECH, "Transacción rechazada por recaudador."));
	    				}else if(trxPagoDB.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_ABORTADA)){
	    					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_ABORT, "Transacción rechazada por comercio."));
						}else{
	    					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Transacción con estado invalido."));
	    				}
	    			}
	    		}else{
	    			logger.error("Error, La Transacción no esta asociada al convenio enviado.");
	    			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
	    		}	    		
			}
		}catch(Exception e){		
			logger.error("Error, El precobro no pudo efectuarse.", e);
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
		}finally{
			em.close();
		}
    	
    	return respuesta;
    }
}
