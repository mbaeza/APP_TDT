/**
 * 
 */
package cl.bch.motorpagos.command;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Dispositivo;
import cl.bch.motorpagos.security.GenBCH;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.CambioPinVO;
import cl.bch.motorpagos.vo.RespuestaVO;

/**
 * @author boyanedel
 *
 */
public class CambiarPinCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(CambiarPinCommand.class);

    /**
     * 
     * @param cambioVO
     * @param em
     * @return
     */
    public RespuestaVO cambiarPinDispositivo(CambioPinVO cambioVO){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaVO respuesta = null;
    	
    	logger.debug("Se cambia el pin del dispositivo [{}].", cambioVO.getIdDispositivo());
    	try{
    		Dispositivo dispositivoDB = em.find(Dispositivo.class, cambioVO.getIdDispositivo());
    		dispositivoDB.setClaveDispositivo(GenBCH.genClave(cambioVO.getNuevoPin()));
    		
    		em.getTransaction().begin();
    		em.persist(dispositivoDB);
    		em.getTransaction().commit();
    		
    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA);
    	}catch(Exception e){		
			logger.error("Error, cambiarPinDispositivo.", e);
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}finally{
    		em.close();
    	}
    	
    	return respuesta;    	
    }
}
