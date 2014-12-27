/**
 * 
 */
package cl.bch.motorpagos.command;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.LimiteCuenta;
import cl.bch.motorpagos.persistencia.LimiteCuentaPK;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperDB;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.RespuestaVO;
import cl.bch.motorpagos.vo.TransferenciaVO;

/**
 * @author boyanedel
 *
 */
public class ActualizarLimiteCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ActualizarLimiteCommand.class);

    /**
     * 
     * @param tefVO
     * @return
     */
    public RespuestaVO actualizarLimiteCliente(TransferenciaVO tefVO){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaVO respuesta = null;
    	
    	try{
    		logger.debug("Se actualiza el limite de la cuenta asociada al dispositivo [{}].", tefVO.getIdDispositivoCliente());   		
    		LimiteCuentaPK limitePk = MapperDB.getLimiteCuentaPK(tefVO.getCtaOrigen(), tefVO.getIdCliente(), tefVO.getFechaHoraTrx());
    		LimiteCuenta limiteCuenta = em.find(LimiteCuenta.class, limitePk);
    		    		   		
    		em.getTransaction().begin();
    		
    		if(limiteCuenta==null){
    			limiteCuenta = MapperDB.getLimiteCuenta(limitePk, Integer.parseInt(tefVO.getMonto()));
    			em.persist(limiteCuenta);
    			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Limite OK.");
    		}else{
    			int montoAcumulado = limiteCuenta.getMontoAcumulado() + Integer.parseInt(tefVO.getMonto());
    				
    			if(montoAcumulado > tefVO.getLimiteCuentaOrigen()){
    				respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Ha superado el monto maximo permitido.");
    			}else{
    				limiteCuenta.setMontoAcumulado(montoAcumulado);
    				em.persist(limiteCuenta);
    				respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Limite OK.");
    			}   			
    		}   
    	    		
    		em.getTransaction().commit();
    		
    	}catch(Exception e){
    		logger.error("Error, No fue posible realizar la validacion de Limite.", e);
    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
    	}finally{
    		em.close();
    	}
    	
    	return respuesta;
    }
}
