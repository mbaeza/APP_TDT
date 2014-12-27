/**
 * 
 */
package cl.bch.motorpagos.command;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.AdmCuentasHist;
import cl.bch.motorpagos.persistencia.Cuenta;
import cl.bch.motorpagos.persistencia.CuentasCliente;
import cl.bch.motorpagos.persistencia.CuentasConvenio;
import cl.bch.motorpagos.persistencia.DispositivosCliente;
import cl.bch.motorpagos.persistencia.DispositivosConvenio;
import cl.bch.motorpagos.security.CryptData;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperDB;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.ActualizarMedioDePagoVO;
import cl.bch.motorpagos.vo.RespuestaEnrolamientoVO;

/**
 * @author boyanedel
 *
 */
public class ActualizarMedioPagoCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ActualizarMedioPagoCommand.class);

    /**
     * 
     * @param medioDePagoVO
     * @return
     */
    public RespuestaEnrolamientoVO actualizarMedioDePagoCliente(ActualizarMedioDePagoVO medioDePagoVO){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaEnrolamientoVO respuesta = new RespuestaEnrolamientoVO();
    	MotorPagosServicesUtils utilServices = new MotorPagosServicesUtils();
    	try{   		
    		logger.debug("Se buscan las cuentas asociadas al dispositivo [{}].", medioDePagoVO.getIdDispositivo());
	    	List<DispositivosCliente> dispositivosDB = utilServices.findDispositivosCliente(medioDePagoVO.getIdCliente(), medioDePagoVO.getIdDispositivo(), em);
	    	if(dispositivosDB==null || dispositivosDB.isEmpty()){
	    		logger.error("Error, No se pudo encontrar la relacion cliente-dispositivo.");
	    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
	    	}else{	    		
	    		String idCuenta = null;
	    		em.getTransaction().begin();
	    		    		
    			DispositivosCliente dispositivoDB = dispositivosDB.get(0);
    			Cuenta cuentaDB = em.find(Cuenta.class, dispositivoDB.getIdCuenta());
    			
    			String keyCuenta = CryptData.desEncriptar(medioDePagoVO.getLlaveCuenta());
            	
            	String nroCuenta = MotorPagosHelper.cleanCuenta(keyCuenta.substring(0, keyCuenta.length()-3));
            	String tipoCuenta = keyCuenta.substring(keyCuenta.length()-3);
            	    			
    			if(cuentaDB.getNroCuenta().equals(nroCuenta) && cuentaDB.getTipoCuenta().equals(tipoCuenta)){
    				
    				logger.debug("Se registra cambio en la cuenta [{}].", cuentaDB.getIdCuenta());
					AdmCuentasHist admCuentaHist = MapperDB.getAdmCuentaHistDB(cuentaDB);	
					em.persist(admCuentaHist);
					idCuenta = cuentaDB.getIdCuenta();
					
    				if(cuentaDB.getEstadoCuenta().equals(ConstantesMotorPagos.ESTADO_CUENTA_ACTIVA)){
    					   					
        				cuentaDB.setLimiteCuenta(medioDePagoVO.getLimiteCuenta());        				
        				em.persist(cuentaDB);        				
        				
        			}else{
        				cuentaDB.setLimiteCuenta(medioDePagoVO.getLimiteCuenta());  
        				cuentaDB.setEstadoCuenta(ConstantesMotorPagos.ESTADO_CUENTA_ACTIVA);
        				em.persist(cuentaDB);
        			}
    			}else{    				
    				logger.debug("Se registra cambio en la cuenta [{}].", cuentaDB.getIdCuenta());
					AdmCuentasHist admCuentaHist = MapperDB.getAdmCuentaHistDB(cuentaDB);	
					em.persist(admCuentaHist);
					
    				cuentaDB.setEstadoCuenta(ConstantesMotorPagos.ESTADO_CUENTA_ACTIVA);
    				em.persist(cuentaDB);    				
					
    				logger.debug("Se valida la existencia de la cuenta [{}] en la BD.", cuentaDB.getIdCuenta());            		                	
                	Cuenta cuentaDBExistente = utilServices.findCuentaByNroTipo(nroCuenta, tipoCuenta, em);
                	
                	if(cuentaDBExistente==null){
                		logger.debug("Se crea nueva cuenta del cliente en la BD.");	 
                		idCuenta = MotorPagosHelper.getCadenaAlfanumAleatoria(ConstantesMotorPagos.LARGO_ID_CUENTA);
        	    		Cuenta newCuentaDB = MapperDB.getCuentaDB(idCuenta, medioDePagoVO.getLlaveCuenta(), medioDePagoVO.getLimiteCuenta()+"");
        	    		
        	    		logger.debug("Se asocia la cuenta al dispositivo del cliente [{}].", medioDePagoVO.getIdDispositivo());
        	    		dispositivoDB.setIdCuenta(idCuenta);	
        	    		CuentasCliente cuentaCliente = MapperDB.getCuentaCliente(medioDePagoVO.getIdCliente(), idCuenta);
        	    		
        	    		em.persist(newCuentaDB);
        	    		em.persist(dispositivoDB);
        	    		em.persist(cuentaCliente);
        	    		
        	    		logger.debug("Se asocia la cuenta al dispositivo del convenio [{}].", medioDePagoVO.getIdConvenio());
        	    		List<DispositivosConvenio> dispConv = utilServices.findDispositivosConvenio(medioDePagoVO.getIdConvenio(), medioDePagoVO.getIdDispositivo(), em);
        	    		if(dispConv!=null && !dispConv.isEmpty()){
        	    			DispositivosConvenio dispConvenioDB = dispConv.get(0);
        	    			dispConvenioDB.setIdCuenta(idCuenta);
        	    			CuentasConvenio cuentaConvenio = MapperDB.getCuentaConvenio(medioDePagoVO.getIdConvenio(), idCuenta);
        	    			
        	    			em.persist(dispConvenioDB);
        	    			em.persist(cuentaConvenio);
        	    		}  		
        	    		
                	}else{               		
                		logger.debug("Se registra cambio en la cuenta [{}].", cuentaDBExistente.getIdCuenta());
    					admCuentaHist = MapperDB.getAdmCuentaHistDB(cuentaDB);	
    					em.persist(cuentaDBExistente);
                		
    					logger.debug("Se asocia la cuenta al dispositivo del cliente [{}].", medioDePagoVO.getIdDispositivo());
                		cuentaDBExistente.setEstadoCuenta(ConstantesMotorPagos.ESTADO_CUENTA_ACTIVA);
                		cuentaDBExistente.setLimiteCuenta(medioDePagoVO.getLimiteCuenta());
                		dispositivoDB.setIdCuenta(cuentaDBExistente.getIdCuenta());
                		
                		em.persist(cuentaDBExistente);
                		em.persist(dispositivoDB);
                		
                		logger.debug("Se asocia la cuenta al dispositivo del convenio [{}].", medioDePagoVO.getIdConvenio());
        	    		List<DispositivosConvenio> dispConv = utilServices.findDispositivosConvenio(medioDePagoVO.getIdConvenio(), medioDePagoVO.getIdDispositivo(), em);
        	    		if(dispConv!=null && !dispConv.isEmpty()){
        	    			DispositivosConvenio dispConvenioDB = dispConv.get(0);
        	    			dispConvenioDB.setIdCuenta(cuentaDBExistente.getIdCuenta());
        	    			
        	    			em.persist(dispConvenioDB);        	    			
        	    		}
                		
                		idCuenta = cuentaDBExistente.getIdCuenta();                	
                	}
    			}    		
	    		
	    		em.getTransaction().commit();
	    		
	    		respuesta.setIdConvenio(	medioDePagoVO.getIdCliente());
	    		respuesta.setIdCuenta(		idCuenta);
	    		respuesta.setRetorno(		MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA));    	
	    	}
    	}catch(Exception e){	
    		logger.error("Error, El cambio de cuenta no pudo ser efectuado.", e);
			respuesta = MapperVO.getRespuestaEnrolamientoVO(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO), null, null, null, null);
		}finally{
			em.close();
		}
    	return respuesta;
    }
}
