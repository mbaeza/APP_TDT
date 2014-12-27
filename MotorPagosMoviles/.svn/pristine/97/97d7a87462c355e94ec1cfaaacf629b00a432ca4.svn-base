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
import cl.bch.motorpagos.vo.ResearPinVO;
import cl.bch.motorpagos.vo.RespuestaVO;

/**
 * @author boyanedel
 *
 */
public class ResetearPinCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ResetearPinCommand.class);

    /**
     * 
     * @param reseteoVO
     * @return
     */
    public RespuestaVO reseteoPinDispositivoCliente(ResearPinVO reseteoVO) {
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaVO respuesta = new RespuestaVO();
    	
    	try{
    		logger.debug("Se busca el dispositivo [{}] en los registros del sistema.", reseteoVO.getIdDispositivo());
    		Dispositivo dispDB = em.find(Dispositivo.class, reseteoVO.getIdDispositivo());
    		if(dispDB==null){
    			logger.error("Error, El dispositivo [{}] no existe en los registros del sistema.", reseteoVO.getIdDispositivo());
    			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
    		}else{    			
    			if(dispDB.getEstadoDispositivo().equals(ConstantesMotorPagos.ESTADO_ACTIVO_DISP) 
    					|| dispDB.getEstadoDispositivo().equals(ConstantesMotorPagos.ESTADO_PIN_BLOQUEADO)){
    				
    				dispDB.setClaveDispositivo(	GenBCH.genClave(reseteoVO.getPin()));
    				dispDB.setIntentosFallidos(	0);
    				dispDB.setEstadoDispositivo(ConstantesMotorPagos.ESTADO_ACTIVO_DISP);
    				
    				em.getTransaction().begin();
    				em.persist(dispDB);
    				em.getTransaction().commit();
    				
    				respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Reseteo Exitoso.");
    				
    			}else if(dispDB.getEstadoDispositivo().equals(ConstantesMotorPagos.ESTADO_DISP_BLOQUEADO)){
    				respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Dispositivo bloqueado.");
    			}else if(dispDB.getEstadoDispositivo().equals(ConstantesMotorPagos.ESTADO_DISP_ELIMINADO)){
    				respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Dispositivo inhabilitado.");
    			}
    		}
    	}catch(Exception e){		
			logger.error("Error, reseteoPinDispositivoCliente.", e);
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}finally{
    		em.close();
    	}
    	
    	return respuesta;
    }
}
