/**
 * 
 */
package cl.bch.motorpagos.command;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Comercio;
import cl.bch.motorpagos.persistencia.Convenio;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.RespuestaConveniosVO;

/**
 * @author boyanedel
 *
 */
public class ObtenerConveniosCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ObtenerConveniosCommand.class);

    /**
     * 
     * @param rutEmpresa
     * @param rutApoderado
     * @return
     */
    public RespuestaConveniosVO obtieneConveniosComercio(String rutEmpresa, String rutApoderado) {
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaConveniosVO respuesta = new RespuestaConveniosVO();
    	MotorPagosServicesUtils utilServices = new MotorPagosServicesUtils();
    	try{
    		respuesta.setRutEmpresa(rutEmpresa);
    		logger.debug("Se obtienen los datos del comercio [{}] apoderado [{}] desde la BD.", rutEmpresa, rutApoderado);
        	List<Comercio> comerciosDB = utilServices.findIdComercioByRutEmpresaApoderado(rutEmpresa, rutApoderado, em);
        	
        	if (comerciosDB==null || comerciosDB.isEmpty()){
        		logger.error("Error, No se encontraron comercios asociados a los rut's enviados.");
        		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
        	}else{   
        		logger.debug("Se obtiene el listado de convenios desde la BD.");
        		Comercio comercioDB = comerciosDB.get(0);
        		respuesta.setNombreEmpresa(comercioDB.getRazonSocialComercio());
        		
        		List<Convenio> conveniosDB = comercioDB.getConvenios();
        		
        		if(conveniosDB==null || conveniosDB.isEmpty()){
        			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Comercio sin convenios"));
        		}else{       			
        			respuesta.setListaConvenios(MapperVO.getListaConveniosVO(conveniosDB));
        			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Obtencion de Convenios Exitosa."));        		
        		}         	
        	}
    	}catch(Exception e){		
			logger.error("Error, No fue posible consultar el listado de convenios.", e);
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
		}finally{
    		em.close();
    	}
    	return respuesta;
    }
}
