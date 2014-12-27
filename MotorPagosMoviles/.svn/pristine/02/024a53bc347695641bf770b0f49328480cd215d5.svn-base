/**
 * 
 */
package cl.bch.motorpagos.command;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Cliente;
import cl.bch.motorpagos.persistencia.Convenio;
import cl.bch.motorpagos.persistencia.Dispositivo;
import cl.bch.motorpagos.persistencia.DispositivosCliente;
import cl.bch.motorpagos.persistencia.DispositivosConvenio;
import cl.bch.motorpagos.util.ConstantesMotorPagos;

/**
 * @author boyanedel
 *
 */
public class ObtenerRutDispositivoCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ObtenerRutDispositivoCommand.class);

    /**
     * 
     * @param String[3] - 0:rutCliente
     * 					  1:rutEmpresa
     * 					  2:idApp desde la cual se invoca el servicio
     * @return
     */
    public String[] obtenerRutAsociadoDispositivo(String idDispositivo){
    	EntityManager em = this.factory.createEntityManager();
    	MotorPagosServicesUtils utilServices = new MotorPagosServicesUtils();
    	String datosRespuesta[] = null;	
    	logger.debug("Se busca el cliente asociado al dispositivo [{}].", idDispositivo);
    	try{
    		Dispositivo dispositivoDB = em.find(Dispositivo.class, idDispositivo);
        	if(dispositivoDB==null){
        		logger.error("Error, No fue posible encontrar el rut asociado al dispositivo [{}].", idDispositivo);
        	}else{
        		if(dispositivoDB.getTipoDispositivo().equalsIgnoreCase(ConstantesMotorPagos.TIPO_DISP_ADMIN)
        				|| dispositivoDB.getTipoDispositivo().equalsIgnoreCase(ConstantesMotorPagos.TIPO_DISP_VENDEDOR)){
        			
        			List<DispositivosConvenio> dispvosConvenio = utilServices.findConvenioDispositivo(idDispositivo, em);
        			if(dispvosConvenio==null || dispvosConvenio.isEmpty()){
        				logger.error("Error, no se encontraron dispositivos para el convenio.");
        			}else{
        				DispositivosConvenio dispoConvenio = dispvosConvenio.get(0);
        				Convenio convenio = em.find(Convenio.class, dispoConvenio.getId().getIdConvenio());
        				
        				logger.debug("Convenio encontrado");
        				datosRespuesta = new String[3];
        				datosRespuesta[0] = convenio.getComercio().getRutApoderado();
        				datosRespuesta[1] = convenio.getComercio().getRutComercio();
        				if(convenio.getComercio().getTipoComercio().equals(ConstantesMotorPagos.COMERCIO_EMPRESA)){
        					datosRespuesta[2] = ConstantesMotorPagos.ID_APP_COBROS;
        				}else{
        					datosRespuesta[2] = ConstantesMotorPagos.ID_APP_PAGOS;
        				}
        			
        			}
        			
        			
        		}else if(dispositivoDB.getTipoDispositivo().equalsIgnoreCase(ConstantesMotorPagos.TIPO_DISP_CLIENTE)){
        			
        			List<DispositivosCliente> dispvosCliente = utilServices.findClienteDispositivo(idDispositivo, em);
        			if(dispvosCliente==null || dispvosCliente.isEmpty()){
        				logger.error("Error, no se encontraron dispositivos para el cliente.");
        			}else{
        				DispositivosCliente dispoCliente = dispvosCliente.get(0);
        				Cliente cliente = em.find(Cliente.class, dispoCliente.getId().getIdCliente());
        				
        				logger.debug("Cliente encontrado");
        				datosRespuesta = new String[3];
        				datosRespuesta[0] = cliente.getRutCliente();
        				datosRespuesta[1] = cliente.getRutCliente();
        				datosRespuesta[2] = ConstantesMotorPagos.ID_APP_PAGOS;
        			}
        		}
        	}
    	}catch(Exception e){		
			logger.error("Error, obtenerRutAsociadoDispositivo.", e);			
		}finally{
    		em.close();
    	}
    	return datosRespuesta;
    }
}
