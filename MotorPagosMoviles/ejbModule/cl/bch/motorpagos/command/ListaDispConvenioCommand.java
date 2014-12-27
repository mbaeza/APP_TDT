/**
 * 
 */
package cl.bch.motorpagos.command;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Convenio;
import cl.bch.motorpagos.persistencia.Dispositivo;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.DispositivoRegistradoVO;
import cl.bch.motorpagos.vo.ListaDispositivosVO;
import cl.bch.motorpagos.vo.RespuestaDispRegistradosVO;

/**
 * @author boyanedel
 *
 */
public class ListaDispConvenioCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ListaDispConvenioCommand.class);

    /**
     * 
     * @param idConvenio
     * @return
     */
    public RespuestaDispRegistradosVO listaDispositivosConvenio(String idConvenio){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaDispRegistradosVO dispositivos = new RespuestaDispRegistradosVO();
    	MotorPagosServicesUtils utilServices = new MotorPagosServicesUtils();
    	try{
    		Convenio convenioDB = em.find(Convenio.class, idConvenio);
        	
        	if(convenioDB==null){
        		logger.error("Error, No se encontro el convenio [{}] en los registros del sistema.", idConvenio);
        		dispositivos.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
        	}else{
        		logger.debug("Se buscan los dispositivos del convenio [{}].", idConvenio);
        		List<Dispositivo> dispConvenios = utilServices.findDispositivosConvenio(idConvenio, em);
        		
        		if(dispConvenios==null || dispConvenios.isEmpty()){
        			logger.error("Error, Convenio [{}] sin dispositivos.", idConvenio);
        			dispositivos.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Convenio sin dispositivos."));
        		}else{
        			ArrayList<DispositivoRegistradoVO> listaDisp = new ArrayList<DispositivoRegistradoVO>();
    				
    				for(int i=0;i<dispConvenios.size();i++){
    					
    					Dispositivo dispositivoDB = dispConvenios.get(i);
    					
    					if(!dispositivoDB.getEstadoDispositivo().equals(ConstantesMotorPagos.ESTADO_DISP_ELIMINADO)){
    						listaDisp.add(MapperVO.getDispositivoRegistradoVO(dispositivoDB));
    					}
    				}
    				ListaDispositivosVO listaVO = new ListaDispositivosVO();
    				listaVO.setDispositivos(listaDisp);
    				
    				dispositivos.setListaDispositivos(listaVO);
    				if(dispositivos.getListaDispositivos().getDispositivos().size()>0){
    					dispositivos.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA));
    				}else{
    					dispositivos.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Convenio sin dispositivos habilitados."));
    				}    			
        		}
        	}
    	}catch(Exception e){		
			logger.error("Error, listaDispositivosConvenio.", e);
			dispositivos.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
		}finally{
    		em.close();
    	}
    	
    	return dispositivos;
    }
}
