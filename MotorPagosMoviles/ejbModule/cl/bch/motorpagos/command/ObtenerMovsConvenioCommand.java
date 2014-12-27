/**
 * 
 */
package cl.bch.motorpagos.command;

import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Convenio;
import cl.bch.motorpagos.persistencia.TrxsPago;
import cl.bch.motorpagos.util.ConfigurationLoader;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.ConsultaListadoMovVO;
import cl.bch.motorpagos.vo.RespuestaListadoMovimientosVO;

/**
 * @author boyanedel
 *
 */
public class ObtenerMovsConvenioCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ObtenerMovsConvenioCommand.class);

	   /**
	    * 
	    * @param consultaVO
	    * @return
	    */
	    public RespuestaListadoMovimientosVO obtieneMovimientosConvenioComercio(ConsultaListadoMovVO consultaVO){
	    	EntityManager em = this.factory.createEntityManager();
	    	RespuestaListadoMovimientosVO respuesta = new RespuestaListadoMovimientosVO();
	    	MotorPagosServicesUtils utilServices = new MotorPagosServicesUtils();
	    	try{
	    		logger.debug("Se obtiene el convenio [{}] desde la BD.", consultaVO.getIdConvenio());
	        	Convenio convenioDB = em.find(Convenio.class, consultaVO.getIdConvenio());
	        	
	        	if(convenioDB==null){
	        		logger.error("Error, No fue posible obtener el convenio [{}] desde la BD.", consultaVO.getIdConvenio());
	        		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
	        	}else{        		
	        		logger.debug("Se obtiene el listado de movimientos del convenio [{}] desde la BD.", consultaVO.getIdConvenio());
	        		try{
	        			//TODO podria mejorar
	        			if(consultaVO.getNumeroPagina() <= 1){
	        				int totalRegistros = utilServices.countTrxByIdConvenioFechas(consultaVO.getIdConvenio(), consultaVO.getFechaDesde(), consultaVO.getFechaHasta(), em);
	        				int numeroPaginas;
	        				if(consultaVO.getNumeroPagina()==0){
	        					numeroPaginas = 1;
	        				}else{
	        					float nroRegistros = totalRegistros;
	        					numeroPaginas = Double.valueOf(Math.ceil(nroRegistros/Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.REGISTROS_X_PAGINA)))).intValue();
	        				} 
	        				
	        				respuesta.setTotalRegistros(totalRegistros);
	        				respuesta.setNumeroPaginas(numeroPaginas);
	        			}
	        			
	        			if(MotorPagosHelper.esCantidadPermitida(respuesta.getTotalRegistros())){        			
		           			respuesta.setPaginaActual(consultaVO.getNumeroPagina());
		           			
		        			List<TrxsPago> pagosDB = utilServices.findTrxByIdConvenioFechas(consultaVO.getIdConvenio(), consultaVO.getFechaDesde(), consultaVO.getFechaHasta(), consultaVO.getNumeroPagina(), em);
		    	    		if(pagosDB==null || pagosDB.isEmpty()){
		    	    			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "No se encontrarón movimientos"));
		    	    		}else{	    	    		
		    	    			respuesta.setListadoMovimientos(MapperVO.getListaMovimientosVO(pagosDB));
		    	    			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA));	    	    		
		    	    		}
	        			}else{
	        				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "El número de registros supera el permitido."));
	        			}	
	        		}catch(ParseException e){
	        			logger.error("Error, Se produjo al parsear las fechas de busqueda.", e);
	        			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
	        		}         	
	        	}
	    	}catch(Exception e){
	    		logger.error("Error, No fue posible obtener los movimientos.", e);
	    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
	    	}finally{
	    		em.close();
	    	}
	    	
	    	return respuesta;
	    }
}
