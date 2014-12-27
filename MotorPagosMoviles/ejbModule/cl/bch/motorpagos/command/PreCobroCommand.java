/**
 * 
 */
package cl.bch.motorpagos.command;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Convenio;
import cl.bch.motorpagos.persistencia.Cuenta;
import cl.bch.motorpagos.persistencia.Dispositivo;
import cl.bch.motorpagos.persistencia.DispositivosConvenio;
import cl.bch.motorpagos.persistencia.TrxsPago;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperDB;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.PreCobroVO;
import cl.bch.motorpagos.vo.RespuestaVO;

/**
 * @author boyanedel
 *
 */
public class PreCobroCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(PreCobroCommand.class);

    /**
     * 
     * @param preCobroVO
     * @return
     */
    public RespuestaVO registrarPreCobro(PreCobroVO preCobroVO){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaVO respuesta = null;
    	
    	try{
    		Dispositivo dispositivoDB = em.find(Dispositivo.class, preCobroVO.getIdDispositivoComercio());
    		
    		if(dispositivoDB==null){
    			logger.error("Error, No fue posible obtener el dispositivo [{}] desde la BD.", preCobroVO.getIdDispositivoComercio());
    			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
    		}else{
    			if(dispositivoDB.getEstadoDispositivo().equals(ConstantesMotorPagos.ESTADO_ACTIVO_DISP)){
    		   		
    	    		Convenio convenioDB = em.find(Convenio.class, preCobroVO.getIdConvenio());   		
    	    		
    	    		if(convenioDB==null){
    	    			logger.error("Error, el convenio [{}] no existe en los registros del sistema.", preCobroVO.getIdConvenio());
    	    			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
    	    		}else{
    	    			DispositivosConvenio dispConvenioDB = em.find(DispositivosConvenio.class, MapperDB.getDispositivoConvenioPK(dispositivoDB.getIdDispositivo(), convenioDB.getIdConvenio()));
    	    			Cuenta cuentaDB = em.find(Cuenta.class, dispConvenioDB.getIdCuenta());
	    					
	        			DispositivosConvenio dispositivosConvenioDB = em.find(DispositivosConvenio.class, MapperDB.getDispositivoConvenioPK(preCobroVO.getIdDispositivoComercio(), convenioDB.getIdConvenio()));
	    				
	    				if(dispositivosConvenioDB==null){
	    					logger.error("Error, el dispositivo [{}] no esta asociado al convenio [{}].", preCobroVO.getIdDispositivoComercio(), preCobroVO.getIdConvenio());    					
	    					respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
	    				}else{	    					
	    					TrxsPago trxAnterior = em.find(TrxsPago.class, preCobroVO.getIdTrxPago());
	    					if(trxAnterior==null){   					
	    						em.getTransaction().begin();
	    						TrxsPago pagoDB = MapperDB.getTrxsPagoDB(preCobroVO, convenioDB, cuentaDB);
	    						em.persist(pagoDB);
	    						respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA);
	    						em.getTransaction().commit(); 
	    					}else{
	    						logger.debug("Se encontro la trx [{}] previamente registrada, se procede a validar datos.", preCobroVO.getIdTrxPago());
	    						
	    						if(trxAnterior.getConvenio().getIdConvenio().equals(preCobroVO.getIdConvenio())
	    								&& trxAnterior.getIdDispositivoComercio().equals(preCobroVO.getIdDispositivoComercio())
	    								&& trxAnterior.getIdVendedor().equals(preCobroVO.getIdVendedor())
	    								&& trxAnterior.getHoraCobro().equals(preCobroVO.getHoraCobro())
	    								&& trxAnterior.getLlaveComercio().equals(preCobroVO.getLlaveComercio())){
	    							
	    							logger.warn("Corresponde a un reintento de insercion de Trx [{}].", preCobroVO.getIdTrxPago());    							
	    							respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Transaccion Existente.");
	    						}else{
	    							logger.warn("Corresponde a un intento de fraude.");    							
	    							respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Transaccion Rechazada, Codigo Cliente Invalido.");
	    						}    	    						
	    					}    	    				
	    				} 		    		
	    			
    	    		}    	    	
    			}else{
    				logger.debug("El dispositivo [{}] no se encuentra habilitado.", preCobroVO.getIdDispositivoComercio());
        			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Dispositivo no activo.");
    			}
    		}    				
    	}catch(Exception e){		
			logger.error("Error, El precobro no pudo efectuarse.", e);
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}finally{
			em.close();
		}
    	
    	return respuesta;
    }
}
