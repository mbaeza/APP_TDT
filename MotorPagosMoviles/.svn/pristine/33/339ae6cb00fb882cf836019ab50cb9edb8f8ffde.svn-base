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
import cl.bch.motorpagos.security.GenBCH;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.RespuestaValidaPinVO;
import cl.bch.motorpagos.vo.ValidarPinVO;

/**
 * @author boyanedel
 *
 */
public class ValidarPinCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ValidarPinCommand.class);

    /**
     * 
     * @param pinVO
     * @return
     */
    public RespuestaValidaPinVO validarPin(ValidarPinVO pinVO){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaValidaPinVO respuesta = new RespuestaValidaPinVO();
    	MotorPagosServicesUtils utilServices = new MotorPagosServicesUtils();
    	
    	try{    		
    		Dispositivo dispositivoDB = em.find(Dispositivo.class, pinVO.getIdDispositivo());
    		    	
    		if(dispositivoDB==null){
    			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_DISP_NOEXISTE, "Error, El Dispositivo no existe en registros."));
    		}else{
    			respuesta.setTipoDispositivo(dispositivoDB.getTipoDispositivo());
    			
    			List<DispositivosConvenio> conveniosDisp = utilServices.findConvenioDispositivo(pinVO.getIdDispositivo(), em);
    			
    			if(conveniosDisp==null || conveniosDisp.isEmpty()){
    				logger.debug("Dispositivo [{}] no asociado a convenios.", pinVO.getIdDispositivo());
    			}else{    				
    				DispositivosConvenio dispConv = conveniosDisp.get(0);
    				Convenio conv = em.find(Convenio.class, dispConv.getId().getIdConvenio());
    				logger.debug("Dispositivo [{}] asociado a convenio [{}].", pinVO.getIdDispositivo(), conv.getIdConvenio());
    				
    				respuesta.setRutDispositivo(conv.getComercio().getRutComercio());
    				
    				if(conv.getEstadoConvenio().equals(ConstantesMotorPagos.ESTADO_CONVENIO_BLOQUEADO)){
    					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Convenio temporalmente inhabilitado."));    					
    					return respuesta;
    				}else{
    					if(dispositivoDB.getEstadoDispositivo().equals(ConstantesMotorPagos.ESTADO_ACTIVO_DISP)){	    			
    		    			
    		    			if(dispositivoDB.getClaveDispositivo().equals(GenBCH.genClave(pinVO.getPinCinco()))){	
    		    				
    		    				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA));	    				
    			    			dispositivoDB.setIntentosFallidos(0);
    			    		}else{
    			    			
    			    			int reintentos = dispositivoDB.getIntentosFallidos() + 1;
    			    			dispositivoDB.setIntentosFallidos(reintentos);
    			    			if(reintentos==3){
    			    				dispositivoDB.setEstadoDispositivo(ConstantesMotorPagos.ESTADO_PIN_BLOQUEADO);
    			    				logger.error("Error, superó el numero de reintentos se procede a bloquear el pin.");
    			    				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Clave bloqueada por reintentos."));
    			    			}else{
    			    				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Clave inválida"));
    			    			}		    			
    			    		}
    		    			
    		    			em.getTransaction().begin();
    		    			em.persist(dispositivoDB);
    		    			em.getTransaction().commit();
    		    			
    		    		}else if(dispositivoDB.getEstadoDispositivo().equals(ConstantesMotorPagos.ESTADO_PIN_BLOQUEADO)){
    		    			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Clave bloqueada."));
    		    		}else if(dispositivoDB.getEstadoDispositivo().equals(ConstantesMotorPagos.ESTADO_DISP_BLOQUEADO)){
    		    			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Dispositivo bloqueado."));
    		    		}else if(dispositivoDB.getEstadoDispositivo().equals(ConstantesMotorPagos.ESTADO_DISP_ELIMINADO)){
    		    			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_DISP_DELETED, "Dispositivo inhabilitado."));
    		    		}
    				}
    			}
    			
	    		
    		}	
    	}catch(Exception e){		
			logger.error("Error, La validacion no pudo efectuarse.", e);
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
		}finally{
			em.close();
		}
    	return respuesta;
    }
}
