/**
 * 
 */
package cl.bch.motorpagos.command;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Cliente;
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
public class ListaDispClienteCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ListaDispClienteCommand.class);
	
	 /**
     * 
     * @param rutCliente
     * @return
     */
    public RespuestaDispRegistradosVO listaDispositivosCliente(String rutCliente){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaDispRegistradosVO dispositivos = new RespuestaDispRegistradosVO();
    	MotorPagosServicesUtils utilServices = new MotorPagosServicesUtils();
    	
    	try{
    		dispositivos.setRutCliente(rutCliente);
    		logger.debug("Se busca el cliente [{}] en los registros del sistema.", rutCliente);
    		List<Cliente> resultadoCliente = utilServices.findClienteByRut(rutCliente, em);
    		
    		if(resultadoCliente==null || resultadoCliente.isEmpty()){
    			logger.error("Error, No se encontraron registros para el cliente [{}].", rutCliente);
    			dispositivos.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
    		}else{
    			Cliente clienteBD = resultadoCliente.get(0);
    			dispositivos.setNombreCliente(clienteBD.getNombreCliente());
    			List<Dispositivo> resultadoDispositivos = utilServices.findDispositivosCliente(clienteBD.getIdCliente(), em);
    			
    			if(resultadoDispositivos==null || resultadoDispositivos.isEmpty()){
    				logger.error("Error, Cliente [{}] sin dispositivos.", rutCliente);
    				dispositivos.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Cliente sin dispositivos."));
    			}else{
    				ArrayList<DispositivoRegistradoVO> listaDisp = new ArrayList<DispositivoRegistradoVO>();
    				
    				for(int i=0;i<resultadoDispositivos.size();i++){
    					
    					Dispositivo dispositivoDB = resultadoDispositivos.get(i);
    					
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
    					dispositivos.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Cliente sin dispositivos habilitados."));
    				}
    			}
    		}
    	}catch(Exception e){		
			logger.error("Error, listaDispositivosCliente.", e);
			dispositivos.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
		}finally{
    		em.close();
    	}
    	return dispositivos;
    }
}
