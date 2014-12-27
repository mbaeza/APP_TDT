/**
 * 
 */
package cl.bch.motorpagos.command;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Cliente;
import cl.bch.motorpagos.persistencia.Dispositivo;
import cl.bch.motorpagos.persistencia.DispositivosCliente;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.RespuestaVO;


/**
 * @author boyanedel
 *
 */
public class ActualizarPersonaCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ActualizarPersonaCommand.class);
	
	 /**
     * 
     * @param clienteVO
     * @return RespuestaEnrolamientoV
     */
    public RespuestaVO actializarMarcaCliente(String idDispositivo, String codigoMarca) {
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaVO respuesta = new RespuestaVO();
    	MotorPagosServicesUtils utilServices = new MotorPagosServicesUtils();
    	try{
    		Dispositivo dispositivoDB = em.find(Dispositivo.class, idDispositivo);
        	
        	logger.debug("Se busca el dispositivo [{}] en los registros del sistema.", idDispositivo);
        	if(dispositivoDB==null){
        		logger.error("Error, El dispositivo [{}] no existe en los registros.", idDispositivo);
        		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
        	}else{        		
    			List<DispositivosCliente> listaDispVO = utilServices.findClienteDispositivo(idDispositivo, em);
    			if(listaDispVO==null || listaDispVO.isEmpty()){
					logger.error("Error, no se encontró la relación dispositivo-cliente.");
					respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO); 
				}else{        				
    				DispositivosCliente dispCliDB = listaDispVO.get(0);
    				Cliente clienteDB = em.find(Cliente.class, dispCliDB.getId().getIdCliente());
    				
        			logger.debug("Se procede a actualizar la marca del cliente [{}] en el sistema con el valor [{}].", clienteDB.getRutCliente(), codigoMarca);
    				clienteDB.setCodigoMarca(codigoMarca);
	        		
	        		em.getTransaction().begin();
	        		em.persist(clienteDB);
	        		em.getTransaction().commit();
	        		
	        		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Cambio de Estado Exitoso."); 
				}        		
        	}
    	}catch(Exception e){		
			logger.error("Error, La actualización del cliente no pudo efectuarse.", e);
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}finally{
    		em.close();
    	}
    	
    	return respuesta;
    }
}
