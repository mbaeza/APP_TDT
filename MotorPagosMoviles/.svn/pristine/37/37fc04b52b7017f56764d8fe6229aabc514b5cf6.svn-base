/**
 * 
 */
package cl.bch.motorpagos.command;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Comercio;
import cl.bch.motorpagos.persistencia.Convenio;
import cl.bch.motorpagos.persistencia.Dispositivo;
import cl.bch.motorpagos.persistencia.DispositivosConvenio;
import cl.bch.motorpagos.security.GenBCH;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperDB;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.DispositivoVentaVO;
import cl.bch.motorpagos.vo.RespuestaDispVentaVO;

/**
 * @author boyanedel
 *
 */
public class EnrolarVendedorCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(EnrolarVendedorCommand.class);
	
	/**
     * 
     * @param dispositivoVO
     * @return
     */
    public RespuestaDispVentaVO enrolarDispositivoVenta(DispositivoVentaVO dispositivoVO){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaDispVentaVO respuesta = new RespuestaDispVentaVO();
    	
    	try{
    		Convenio convenioDB = em.find(Convenio.class, dispositivoVO.getIdConvenio());
    		
    		if(convenioDB==null){    			
    			logger.error("Error, el convenio no existe en los registros del sistema.");
    			respuesta = MapperVO.getRespuestaEnrolamientoDispVentaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO, null, null ,dispositivoVO.getIdConvenio(), null, null, null);				
    		}else{
    			String idDispositivo = MotorPagosHelper.getCadenaAlfanumAleatoria(ConstantesMotorPagos.LARGO_ID_DISPOSITIVO);
    			Dispositivo dispositivoDB = MapperDB.getDispositivoDB(idDispositivo, GenBCH.genClave(dispositivoVO.getPin()), dispositivoVO.getAliasDispositivo(), dispositivoVO.getModeloDispositivo(), ConstantesMotorPagos.TIPO_DISP_VENDEDOR);
    			DispositivosConvenio dispConvenioDB = MapperDB.getDispositivosConvenioDB(idDispositivo, dispositivoVO.getIdConvenio(), dispositivoVO.getIdCuenta());
    	    	    			
				em.getTransaction().begin();
				
				Comercio comercioDB = convenioDB.getComercio();
				
				logger.debug("Almacenando datos del dispositivo en la BD.");
				em.persist(dispositivoDB);
				
				logger.debug("Almacenando datos de la relacion convenio-dispositivo en la BD.");
				em.persist(dispConvenioDB);
				
				em.getTransaction().commit();
				
				respuesta = MapperVO.getRespuestaEnrolamientoDispVentaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA, idDispositivo, null ,dispositivoVO.getIdConvenio(), comercioDB.getRazonSocialComercio(), comercioDB.getDireccionComercio(), convenioDB.getNombreConveniio());
    		}
    	}catch(Exception e){		
			logger.error("Error, El Enrolamiento no pudo efectuarse.", e);
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
		}finally{
			em.close();
		}    	
    	return respuesta;
    }

}
