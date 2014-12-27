/**
 * 
 */
package cl.bch.motorpagos.command;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Comercio;
import cl.bch.motorpagos.persistencia.Convenio;
import cl.bch.motorpagos.persistencia.TrxsPago;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.RespuestaPreCobroVO;

/**
 * @author boyanedel
 *
 */
public class ObtienePreCobroCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ObtienePreCobroCommand.class);

    /**
     * 
     * @param idTrxPago
     * @return
     */
    public RespuestaPreCobroVO validarPreCobro(String idTrxPago){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaPreCobroVO respuesta = new RespuestaPreCobroVO();
    	
    	try{		
			TrxsPago trxPagoDB = em.find(TrxsPago.class, idTrxPago);
			
			logger.debug("Se busca la transaccion [{}] en los registros del sistema.", idTrxPago);
			if(trxPagoDB==null){
				logger.debug("La Transaccion ["+idTrxPago+"] no existe en los registros del sistema.");
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_PEND, "No hay cobro asociado."));
			}else{
				if(trxPagoDB.getEstadoTrxl().equals(ConstantesMotorPagos.TRX_PENDIENTE)){
					
					logger.debug("Se busca el convenio asociado al precobro [{}].", idTrxPago);
					Convenio convenioDB = em.find(Convenio.class, trxPagoDB.getConvenio().getIdConvenio());
					
					if(convenioDB==null){
						logger.error("Error, El convenio de la transaccion [{}] es invalido.", idTrxPago);
						respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
					}else{					
						logger.debug("Se busca el comercio asociado a la transaccion [{}].", idTrxPago);
						Comercio comercioDB = em.find(Comercio.class, convenioDB.getComercio().getIdComercio());
						
						if(comercioDB==null){
							logger.error("Error, El comercio de la transaccion [{}] es invalido.", idTrxPago);
							respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
						}else{
							respuesta.setRetorno(			MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Obtencion pre-cobro exitosa."));
							respuesta.setGlosaOperacion(	trxPagoDB.getGlosaTrx());
							respuesta.setDireccionComercio(	comercioDB.getDireccionComercio());
							respuesta.setMontoOperacion(	trxPagoDB.getMontoTrx());
							respuesta.setMontoPropina(		trxPagoDB.getPropinaTrx());
							respuesta.setSubTotalTrx(		trxPagoDB.getSubTotalTrx());
							respuesta.setNombreComercio(	comercioDB.getRazonSocialComercio());
							respuesta.setRutComercio(		comercioDB.getRutComercio());
							respuesta.setIdConvenio(		convenioDB.getIdConvenio());
							respuesta.setTipoComercio(		comercioDB.getTipoComercio());				
						}					
					}
				}else{
					logger.error("Error, La transaccion se encuentra en un estado invalido.");
					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
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
