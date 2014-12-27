/**
 * 
 */
package cl.bch.motorpagos.command;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Cuenta;
import cl.bch.motorpagos.persistencia.LimiteCuenta;
import cl.bch.motorpagos.persistencia.LimiteCuentaPK;
import cl.bch.motorpagos.util.MapperDB;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.CuentaVO;

/**
 * @author boyanedel
 *
 */
public class CuentasClienteCommand extends IServiceCommand {
	private static final Logger logger = LoggerFactory.getLogger(CuentasClienteCommand.class);
	
	/**
     * 
     * @param idCliente
     * @return
     */
    public ArrayList<CuentaVO> obtenerCuentasCliente(String idCliente){
    	ArrayList<CuentaVO> cuentas = new ArrayList<CuentaVO>();    	
    	EntityManager em = this.factory.createEntityManager();
    	
    	try{
	    	TypedQuery<Cuenta> query = em.createQuery("Select c FROM Cuenta c, CuentasCliente cc WHERE cc.id.idCliente = ?1 AND cc.id.idCuentas = c.idCuenta", Cuenta.class);
	    	
	    	query.setParameter(1, idCliente);
	    	    	    
	    	List<Cuenta> resultadoQuery = query.getResultList();
	    	Date fechaActual = new Date();
	    	
	    	if(resultadoQuery==null || resultadoQuery.isEmpty()){
	    		logger.debug("No se encontraron cuentas del cliente [{}].", idCliente);
	    	}else{

	    		for(int i=0; i<resultadoQuery.size();i++){
	    			Cuenta cuentaDB = resultadoQuery.get(i);
	    			CuentaVO cuentaVO = MapperVO.getCuentaVO(cuentaDB);
	    			    			   		
	    			LimiteCuentaPK limitePk = MapperDB.getLimiteCuentaPK(cuentaDB.getNroCuenta(), idCliente, MotorPagosHelper.getFechaString(fechaActual));
	    			LimiteCuenta limiteCuenta = em.find(LimiteCuenta.class, limitePk);
	    			
	    			if(limiteCuenta!=null){
	    				cuentaVO.setUtilizadoCuenta(limiteCuenta.getMontoAcumulado());
	    			}
	    			cuentas.add(cuentaVO);
	    		}
	    	
	    	}
    	}catch(Exception e){
    		logger.error("Error, no fue posible leer los limites preestablecidos.", e);
    	}finally{
    		em.close();
    	}
    	
    	return cuentas;
    }
}
