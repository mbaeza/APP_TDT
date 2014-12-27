/**
 * 
 */
package cl.bch.motorpagos.command;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Convenio;
import cl.bch.motorpagos.persistencia.Dispositivo;
import cl.bch.motorpagos.persistencia.DispositivosConvenio;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.CambioEstadoVO;
import cl.bch.motorpagos.vo.RespuestaVO;

/**
 * @author boyanedel
 *
 */
public class ActualizarEstDispConvenioCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ActualizarEstDispConvenioCommand.class);

    /**
     * 
     * @param dispositivo
     * @return
     */
    public RespuestaVO cambiarEstadoDispositivoConvenio(CambioEstadoVO dispositivo) {
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
        			
        			List<DispositivosConvenio> listaDispVO = utilServices.findConvenioDispositivo(dispositivo.getId(), em);
        			if(listaDispVO==null || listaDispVO.isEmpty()){
    					logger.error("Error, no se encontró la relación dispositivo-convenio.");
    					respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO); 
    				}else{        				
        				DispositivosConvenio dispConvDB = listaDispVO.get(0);
        				Convenio convenioDB = em.find(Convenio.class, dispConvDB.getId().getIdConvenio());
        				        				
        				if(convenioDB.getComercio().getRutApoderado().equals(dispositivo.getRutApoderado())
        						&& convenioDB.getComercio().getRutComercio().equals(dispositivo.getRutEmpresa())) {
        					
        					dispositivoDB.setEstadoDispositivo(dispositivo.getEstado());
                    		
                    		em.getTransaction().begin();
                    		em.persist(dispositivoDB);
                    		em.getTransaction().commit();
                    		
                    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Cambio de Estado Exitoso."); 
        				}else{
        					logger.error("Error, el dispositivo enviado no pertenece al convenio.");
        					respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "El dispositivo no pertenece al cliente."); 
        				}    				
    				}        		
        		}else{
        			logger.error("Error, El estado [{}] enviado no es permitido.", dispositivo.getEstado());
        			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
        		}        	
        	}
    	}catch(Exception e){		
			logger.error("Error, cambiarEstadoDispositivoConvenio.", e);
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}finally{
    		em.close();
    	}
    	
    	return respuesta;
    }
}
