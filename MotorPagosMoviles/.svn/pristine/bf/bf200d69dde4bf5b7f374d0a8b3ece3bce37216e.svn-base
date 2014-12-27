/**
 * 
 */
package cl.bch.motorpagos.command;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Cliente;
import cl.bch.motorpagos.persistencia.Cuenta;
import cl.bch.motorpagos.persistencia.DispositivosCliente;
import cl.bch.motorpagos.persistencia.TrxsPago;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.PagoVO;
import cl.bch.motorpagos.vo.TransferenciaVO;

/**
 * @author boyanedel
 *
 */
public class ObtenerDatosPagoCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ObtenerDatosPagoCommand.class);

    /**
     * 
     * @param pagoVO
     * @return
     */
    public TransferenciaVO getDatosTrxPago(PagoVO pagoVO, TransferenciaVO transferencia){
    	EntityManager em = this.factory.createEntityManager();
    	TransferenciaVO respuesta = transferencia;
    	MotorPagosServicesUtils utilServices = new MotorPagosServicesUtils();
    	try{		
			TrxsPago trxDB = em.find(TrxsPago.class, pagoVO.getIdTrxPago());
			if(trxDB==null){
				logger.error("Error, la transaccion [{}] no existe en los registros.", pagoVO.getIdTrxPago());
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
			}else{			
				if(trxDB.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_PENDIENTE)){
					
					List<DispositivosCliente> dispositivos = utilServices.findClienteDispositivo(pagoVO.getIdDispositivoCliente(), em);
					if(dispositivos==null || dispositivos.isEmpty()){
						logger.error("Error, El dispositivo [{}] no esta asociado a un cliente.", pagoVO.getIdDispositivoCliente());
						respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
					}else{					
						DispositivosCliente dispClienteDB = dispositivos.get(0);
						Cliente clienteDB = em.find(Cliente.class, dispClienteDB.getId().getIdCliente());
						if(clienteDB==null){
							logger.error("Error, El cliente no existe en los registros.");
							respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
						}else{						
							Cuenta cuentaDB = em.find(Cuenta.class, dispClienteDB.getIdCuenta());
							if(cuentaDB==null){								
								logger.error("Error, La cuenta no existe en los registros.");
								respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
							}else{
								respuesta = MapperVO.getTransferenciaVO(clienteDB, trxDB, cuentaDB, pagoVO, respuesta);								
								respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Datos obtenidos con Exito."));								
							}						
						} 					
					}	    					
				}else{
					if(trxDB.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_CANCELADA)){    					
    					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_CANC, "Transacción rechazada por cliente."));	    				
    				}else if(trxDB.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_PAGADA)){    				
    					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Transacción pagada previamente."));	    				
    				}else if(trxDB.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_RECHAZO)){
    					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_RECH, "Transacción rechazada por recaudador."));
    				}else if(trxDB.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_ABORTADA)){
    					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_ABORT, "Transacción rechazada por comercio."));
					}else{
    					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Transacción con estado invalido."));
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
