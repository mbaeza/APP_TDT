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
import cl.bch.motorpagos.vo.CambioEstadoVO;
import cl.bch.motorpagos.vo.RespuestaVO;

/**
 * @author boyanedel
 *
 */
public class ActualizarEstDispClienteCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ActualizarEstDispClienteCommand.class);

	 /**
     * 
     * @param dispositivo
     * @return
     */
    public RespuestaVO cambiarEstadoDispositivo(CambioEstadoVO dispositivo) {
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaVO respuesta = new RespuestaVO();
    	MotorPagosServicesUtils utilServices = new MotorPagosServicesUtils();
    	try{
    		Dispositivo dispositivoDB = em.find(Dispositivo.class, dispositivo.getId());
        	
        	logger.debug("Se busca el dispositivo [{}] en los registros del sistema.", dispositivo.getId());
        	if(dispositivoDB==null){
        		logger.error("Error, El dispositivo [{}] no existe en los registros.", dispositivo.getId());
        		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
        	}else{        		
        		if(dispositivo.getEstado().equals(ConstantesMotorPagos.ESTADO_ACTIVO_DISP) 
        				|| dispositivo.getEstado().equals(ConstantesMotorPagos.ESTADO_DISP_BLOQUEADO)
        				|| dispositivo.getEstado().equals(ConstantesMotorPagos.ESTADO_DISP_ELIMINADO)){
        			
        			List<DispositivosCliente> listaDispVO = utilServices.findClienteDispositivo(dispositivo.getId(), em);
        			if(listaDispVO==null || listaDispVO.isEmpty()){
    					logger.error("Error, no se encontró la relación dispositivo-cliente.");
    					respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO); 
    				}else{        				
        				DispositivosCliente dispCliDB = listaDispVO.get(0);
        				Cliente clienteDB = em.find(Cliente.class, dispCliDB.getId().getIdCliente());
        				
        				if(clienteDB.getRutCliente().equals(dispositivo.getRutCliente())){
        					dispositivoDB.setEstadoDispositivo(dispositivo.getEstado());
                    		
                    		em.getTransaction().begin();
                    		em.persist(dispositivoDB);
                    		em.getTransaction().commit();
                    		
                    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Cambio de Estado Exitoso."); 
        				}else{
        					logger.error("Error, el dispositivo enviado no pertenece al cliente.");
        					respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "El dispositivo no pertenece al cliente."); 
        				}    				
    				}        		
        		}else{
        			logger.error("Error, El estado enviado [{}] no es permitido.", dispositivo.getEstado());
        			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
        		}
        	}
    	}catch(Exception e){		
			logger.error("Error, cambiarEstadoDispositivo.", e);
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}finally{
    		em.close();
    	}
    	
    	return respuesta;
    }
}
