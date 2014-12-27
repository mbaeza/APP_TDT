/**
 * 
 */
package cl.bch.motorpagos.command;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.AvatarConvenio;

/**
 * @author boyanedel
 *
 */
public class ObtenerAvatarCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ObtenerAvatarCommand.class);

   /**
    * 
    * @param idAvatar
    * @return
    */
    public String obtenerAvatar(String idAvatar) {
    	EntityManager em = this.factory.createEntityManager();
    	String respuesta = "SININFO";
    	logger.debug("Se ontiene el avatar [{}] desde la base de datos.", idAvatar);
    	
    	try{
	    	AvatarConvenio avatar = em.find(AvatarConvenio.class, idAvatar);
	    	
	    	if(avatar==null){
	    		logger.warn("El no existe avatar para el id [{}].", idAvatar);
	    	}else{
	    		respuesta = avatar.getAvatar();
	    	}
    	}catch(Exception e){		
			logger.error("Error, al obtener el avatar [{}].",idAvatar, e);			
		}finally{
    		em.close();
    	}    	
    	
    	return respuesta;
    }   
}
