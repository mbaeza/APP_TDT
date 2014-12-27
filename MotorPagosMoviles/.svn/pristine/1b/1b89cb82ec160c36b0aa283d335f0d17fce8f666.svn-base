/**
 * 
 */
package cl.bch.motorpagos.command;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.AvatarConvenio;
import cl.bch.motorpagos.persistencia.Convenio;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.AvatarComercioVO;
import cl.bch.motorpagos.vo.RespuestaVO;

/**
 * @author boyanedel
 *
 */
public class CreateAvatarCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(CreateAvatarCommand.class);
	
    /**
     * 
     * @param avatarVO
     * @return
     */
    public RespuestaVO saveAvatar(AvatarComercioVO avatarVO) {
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaVO respuesta = new RespuestaVO();
    	logger.debug("Se almacena el avatar [{}] en la base de datos.", avatarVO.getIdConvenio());
    	
    	try{
	    	Convenio convenio = em.find(Convenio.class, avatarVO.getIdConvenio());
	    	
	    	if(convenio==null){
	    		respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
	    	}else{
	    		
	    		AvatarConvenio avatar = em.find(AvatarConvenio.class, avatarVO.getIdConvenio());
	    		
	    		if(avatar==null){
	    			logger.debug("Avatar [{}] no existe en la BD se procede a crear.", avatarVO.getIdConvenio());
	    			avatar = new AvatarConvenio();
	    			avatar.setAvatar(avatarVO.getImagenBase64());
		        	avatar.setIdconvenio(avatarVO.getIdConvenio());
	    		}else{
	    			logger.debug("Avatar [{}]  existe en la BD se procede a actualizar.", avatarVO.getIdConvenio());
	    			avatar.setAvatar(avatarVO.getImagenBase64());
	    		}	    		
	        	
	        	em.getTransaction().begin();
	        	em.persist(avatar);
	        	em.getTransaction().commit();
	        	
	        	respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA);
	    	}
    	}catch(Exception e){		
			logger.error("Error, al crear el avatar [{}].",avatarVO.getIdConvenio(), e);
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}finally{
    		em.close();
    	}    	
    	
    	return respuesta;
    }
}
