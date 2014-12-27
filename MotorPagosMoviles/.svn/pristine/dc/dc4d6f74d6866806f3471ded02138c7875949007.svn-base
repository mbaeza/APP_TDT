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
import cl.bch.motorpagos.vo.CancelaCobroVO;
import cl.bch.motorpagos.vo.RespuestaVO;

/**
 * @author boyanedel
 *
 */
public class CancelarCobroCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(CancelarCobroCommand.class);

    /**
     * 
     * @param cancelaVO
     * @return
     */
    public RespuestaVO cancelarCobro(CancelaCobroVO cancelaVO){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaVO respuesta = new RespuestaVO();
    	logger.debug("Se procede a cancelar la transaccion [{}].", cancelaVO.getIdTrx());
    	try{
    		TrxsPago trx = em.find(TrxsPago.class, cancelaVO.getIdTrx());
    		
    		if(trx==null){
    			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Transacción no existe en Sistema.");
    		}else{
    			if(trx.getIdDispositivoComercio().equals(cancelaVO.getIdDispositivo())){
    				if(trx.getConvenio().getIdConvenio().equals(cancelaVO.getIdConvenio())){
    					
    					if(trx.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_PENDIENTE)){
    						logger.debug("Se realiza la cancelación del cobro [{}].", cancelaVO.getIdTrx());
    						trx.setEstadoTrxl(ConstantesMotorPagos.TRX_ABORTADA);
    						em.getTransaction().begin();
    						em.persist(trx);
    						em.getTransaction().commit();
    						respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "El cobro fue abortado.");
    					}else{
    						if(trx.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_CANCELADA)){    					
    							respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_CANC, "Transacción rechazada por cliente.");	    				
    	    				}else if(trx.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_PAGADA)){    				
    	    					respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_PAGADA, "Transacción pagada por cliente.");	    				
    	    				}else if(trx.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_RECHAZO)){
    	    					respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_RECH, "Transacción rechazada por recaudador.");
    	    				}else if(trx.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_ABORTADA)){
    	    					respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_ABORT, "Transacción rechazada por comercio.");
    						}else{
    							respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Transacción con estado invalido.");
    	    				}    						
    					}    					
    				}else{
    					respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Transacción no asociada al Convenio.");
    				}
    			}else{
    				respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Transacción no asociada al Dispositivo.");
    			}
    		}    		
    	}catch(Exception e){		
			logger.error("Error, cancelarCobro.", e);
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}finally{
    		em.close();
    	}
    	return respuesta;
    }
}
