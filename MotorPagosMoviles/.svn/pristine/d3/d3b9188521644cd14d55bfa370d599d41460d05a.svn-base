/**
 * 
 */
package cl.bch.motorpagos.command;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Dispositivo;
import cl.bch.motorpagos.persistencia.DispositivosConvenio;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperDB;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.DesvincularDispositivoVO;
import cl.bch.motorpagos.vo.RespuestaVO;

/**
 * @author boyanedel
 *
 */
public class DeleteDispConvenioCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(DeleteDispConvenioCommand.class);

    /**
     * 
     * @param dispositivoVO
     * @return
     */
    public RespuestaVO desvincularDispositivoConvenio(DesvincularDispositivoVO dispositivoVO){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaVO respuesta = new RespuestaVO();
    	
    	try{
    		logger.debug("Se busca la relacion dispositivo [{}] convenio [{}] en la base de dato.", dispositivoVO.getIdDispositivo(), dispositivoVO.getIdConvenio());
        	DispositivosConvenio dispConvDB = em.find(DispositivosConvenio.class, MapperDB.getDispositivoConvenioPK(dispositivoVO.getIdDispositivo(), dispositivoVO.getIdConvenio()));
        	
        	if(dispConvDB==null){
        		logger.error("Error, El dispositivo [{}] no está asociado al convenio [{}].", dispositivoVO.getIdDispositivo(), dispositivoVO.getIdConvenio());
        		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
        	}else{        		
        		logger.debug("Se busca el dispositivo en la base de dato.");
        		Dispositivo dispDB = em.find(Dispositivo.class, dispConvDB.getId().getIdDispositivo());
        		if(dispDB==null){
        			logger.error("Error, El dispositivo [{}] no fue encontrado en los registros del sistema.", dispositivoVO.getIdDispositivo());
        			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
        		}else{        			
        			dispDB.setEstadoDispositivo(ConstantesMotorPagos.ESTADO_DISP_ELIMINADO);
        			
        			em.getTransaction().begin();
        			em.persist(dispDB);
        			em.getTransaction().commit();
        			
        			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Dispositivo desvinculado.");
        		}
        	}
    	}catch(Exception e){		
			logger.error("Error, desvincularDispositivoConvenio.", e);
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}finally{
    		em.close();
    	}
    	
    	return respuesta;
    }
}
