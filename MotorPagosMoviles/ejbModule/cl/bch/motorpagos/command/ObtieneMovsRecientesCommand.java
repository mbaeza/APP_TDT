/**
 * 
 */
package cl.bch.motorpagos.command;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Dispositivo;
import cl.bch.motorpagos.persistencia.TrxsPago;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.RespuestaListadoMovimientosVO;

/**
 * @author boyanedel
 *
 */
public class ObtieneMovsRecientesCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ObtieneMovsRecientesCommand.class);

    /**
     * 
     * @param idDispositivo
     * @param tipoConsulta
     * @return
     */
    public RespuestaListadoMovimientosVO obtieneMovimientosRecientes(String idDispositivo, String tipoConsulta){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaListadoMovimientosVO respuestaVO = new RespuestaListadoMovimientosVO();
    	MotorPagosServicesUtils utilServices = new MotorPagosServicesUtils();
    	try{
    		Dispositivo dispositivoDB = em.find(Dispositivo.class, idDispositivo);
    		
    		if(dispositivoDB==null){
    			logger.error("Error, No fue posible obtener los datos del dispositivo [{}].", idDispositivo);
    			respuestaVO.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
    		}else{   			
    			List<TrxsPago> pagosDB = utilServices.findTrxRecientesByDispositivo(idDispositivo, tipoConsulta, em);
    			logger.debug("Se obtienen movimientos recientes tipo [{}] del dispositivo [{}].", tipoConsulta, idDispositivo);
    			if(pagosDB==null || pagosDB.isEmpty()){
    				respuestaVO.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "No existen movimientos."));
    			}else{   	    		
    				respuestaVO.setListadoMovimientos(MapperVO.getListaMovimientosVO(pagosDB));
    				respuestaVO.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA));       			
        		}    			
    		}
    	}catch(Exception e){
    		logger.error("Error, No fue posible obtener los movimientos recientes.", e);
    		respuestaVO.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
    	}finally{
    		em.close();
    	}
    	
    	return respuestaVO;
    }
}
