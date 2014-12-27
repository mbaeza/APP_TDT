/**
 * 
 */
package cl.bch.motorpagos.command;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Cliente;

/**
 * @author boyanedel
 *
 */
public class RutClienteCommand extends IServiceCommand {
	private static final Logger logger = LoggerFactory.getLogger(RutClienteCommand.class);
	
	/**
     * 
     * @param idCliente
     * @return
     */
    public String obtenerRutCliente(String idCliente){
    	EntityManager em = this.factory.createEntityManager();
    	String rutCliente = null;
    	
    	try{
    		logger.debug("Se busca al cliente [{}] en los registros de la base de datos.", idCliente);
        	Cliente clienteDB = em.find(Cliente.class, idCliente);
        	if(clienteDB == null){
        		logger.error("Error, el cliente [{}] no existe en los registros.", idCliente);        		
        	}else{
        		logger.debug("El cliente [{}] existe en la base de datos.", idCliente);
        		rutCliente = clienteDB.getRutCliente();        	
        	}
    	}catch(Exception e){		
			logger.error("Error, obtenerRutCliente.", e);			
		}finally{
    		em.close();
    	}
    	return rutCliente;
    }
}
