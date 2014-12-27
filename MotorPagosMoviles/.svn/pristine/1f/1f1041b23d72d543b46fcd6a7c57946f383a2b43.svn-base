/**
 * 
 */
package cl.bch.motorpagos.command;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Cliente;
import cl.bch.motorpagos.persistencia.Cuenta;
import cl.bch.motorpagos.persistencia.Dispositivo;
import cl.bch.motorpagos.persistencia.DispositivosCliente;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperDB;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.ConsultaSaldoVO;
import cl.bch.motorpagos.vo.RespuestaConsultaCuentaVO;

/**
 * @author boyanedel
 *
 */
public class NumeroCuentaCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(NumeroCuentaCommand.class);
	
	/**
     * 
     * @param consultaVO
     * @return
     */
    public RespuestaConsultaCuentaVO obtenerNumeroCuenta(ConsultaSaldoVO consultaVO, RespuestaConsultaCuentaVO respuesta){
    	EntityManager em = this.factory.createEntityManager();
    	
    	try{
    		logger.debug("Se obtiene el numero de cuenta desde la BD para el cliente [{}].", consultaVO.getIdCliente());
        	
        	Cliente clienteDB = em.find(Cliente.class, consultaVO.getIdCliente());
        	if(clienteDB==null){
        		logger.error("Error, No se encontró el cliente [{}] en los registros del sistema.", consultaVO.getIdCliente());
        		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
        	}else{        		
    	    	Dispositivo dispositivoDB = em.find(Dispositivo.class, consultaVO.getIdDispositivo());
    	    	if(dispositivoDB==null){
    	    		logger.error("Error, No se encontró el dispositivo [{}] registros del sistema.", consultaVO.getIdDispositivo());
    	    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
    	    	}else{    	    		
    		    	DispositivosCliente dispClienteDB = em.find(DispositivosCliente.class, MapperDB.getDispositivoClientePK(consultaVO.getIdDispositivo(), consultaVO.getIdCliente()));
    		    	if(dispClienteDB==null){
    		    		logger.error("Error, No se encontró la relacion cliente-dispositivo registros del sistema.");
    		    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
    		    	}else{    		    		
    		    		if(dispClienteDB.getIdCuenta().equals(consultaVO.getIdCuenta())){
    		    			
    		    			Cuenta cuentaDB = em.find(Cuenta.class, consultaVO.getIdCuenta());
    		    			if(cuentaDB==null){
    		    				logger.error("Error, No se encontró la cuenta en los registros del sistema.");
    		    				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
    		    			}else{
    		    				respuesta.setNroCuenta(cuentaDB.getNroCuenta());
    		    				respuesta.setTipoCuenta(cuentaDB.getTipoCuenta());
    		    				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA));   		    			
    		    			}
    		    		}else{
    		    			logger.error("Error, La cuenta no está asociada al dispositivo.");
    		    			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
    		    		}
    		    	}
    	    	}
        	}
    	}catch(Exception e){		
			logger.error("Error, El precobro no pudo efectuarse.", e);
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
		}finally{
    		em.close();
    	} 
    	
    	return respuesta;
    }     

}
