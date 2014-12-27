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
public class ValidarLimiteCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ValidarLimiteCommand.class);
	
    /**
     * 
     * @param tefVO
     * @return
     */
    public RespuestaVO validarLimiteCliente(TransferenciaVO tefVO){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaVO respuesta = null;
    	
    	try{
    		logger.debug("Se valida el limite de la cuenta asociada al dispositivo [{}].", tefVO.getIdDispositivoCliente() );   		
    		LimiteCuentaPK limitePk = MapperDB.getLimiteCuentaPK(tefVO.getCtaOrigen(), tefVO.getIdCliente(), tefVO.getFechaHoraTrx());
    		LimiteCuenta limiteCuenta = em.find(LimiteCuenta.class, limitePk);
    		
    		if(Integer.parseInt(tefVO.getMonto()) > tefVO.getLimiteCuentaOrigen()){
    			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Ha superado el monto maximo permitido.");
    		}else{
    			if(limiteCuenta==null){
    				respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Limite OK.");
    			}else{
    				int montoAcumulado = limiteCuenta.getMontoAcumulado() + Integer.parseInt(tefVO.getMonto());
    				
    				if(montoAcumulado > tefVO.getLimiteCuentaOrigen()){
    					respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Ha superado el monto maximo permitido.");
    				}else{    					
    					respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Limite OK.");
    				}   			
    			}   
    		}    		
    		
    	}catch(Exception e){
    		logger.error("Error, No fue posible realizar la validacion de Limite.", e);
    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
    	}finally{
    		em.close();
    	}
    	
    	return respuesta;
    }
}
