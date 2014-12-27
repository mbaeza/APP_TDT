/**
 * 
 */
package cl.bch.motorpagos.command;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Cliente;
import cl.bch.motorpagos.persistencia.TrxsPago;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.RespuestaPagoVO;
import cl.bch.motorpagos.vo.TransferenciaVO;

/**
 * @author boyanedel
 *
 */
public class RegistrarPagoCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(RegistrarPagoCommand.class);
	
    /**
     * 
     * @param transferenciaVO
     * @param idTrxPago
     * @param estadoTrx
     * @param idTrxInterno
     * @return
     */
    public RespuestaPagoVO registrarPago(TransferenciaVO transferenciaVO, String idTrxPago, String estadoTrx, String idTrxInterno){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaPagoVO respuesta = null;
    	
    	try{
    		logger.debug("Se registra el pago de la transaccion [{}] idCio [{}] en la BD.", idTrxPago, idTrxInterno);
			TrxsPago pagoDB = em.find(TrxsPago.class, idTrxPago);	 	
					
    		pagoDB.setCliente(				em.find(Cliente.class, transferenciaVO.getIdCliente()));
    		pagoDB.setIdDispositivoCliente(	transferenciaVO.getIdDispositivoCliente());
    		pagoDB.setNroCuentaCliente(		transferenciaVO.getCtaOrigen());
	    	pagoDB.setTipoCuentaCliente(	transferenciaVO.getTokenCtaOrigen());
	    	pagoDB.setEstadoTrxl(			estadoTrx);
	    	pagoDB.setIdTrxInterno(			idTrxInterno);
    		
	    	em.getTransaction().begin();
	    	em.persist(pagoDB);
	    	em.getTransaction().commit();
	    	
	    	respuesta = MapperVO.getRespuestaPagoVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, null, pagoDB);
	    	
	    	if(estadoTrx.equals(ConstantesMotorPagos.TRX_PAGADA)){
	    		respuesta.getRetorno().setGlosaRetorno("Pago Exitoso.");
	    	}else if(estadoTrx.equals(ConstantesMotorPagos.TRX_CANCELADA)){
	    		respuesta.getRetorno().setGlosaRetorno("Cobro Rechazado.");
	    	}else if(estadoTrx.equals(ConstantesMotorPagos.TRX_RECHAZO)){
	    		respuesta.getRetorno().setGlosaRetorno("Rechazo por Recaudador.");
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
