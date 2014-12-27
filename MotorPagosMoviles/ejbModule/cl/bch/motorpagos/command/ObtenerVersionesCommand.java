/**
 * 
 */
package cl.bch.motorpagos.command;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.VersionesApp;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.vo.VersionVO;

/**
 * @author boyanedel
 *
 */
public class ObtenerVersionesCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(ObtenerVersionesCommand.class);

	/**
	 * 
	 * @return
	 */
	public ArrayList<VersionVO> getVersionesApps(){
		ArrayList<VersionVO> listaVersion = new ArrayList<VersionVO>();
		EntityManager em = this.factory.createEntityManager();
		try{
			logger.debug("Se obtiene versiones desde la Base de Datos.");
			TypedQuery<VersionesApp> query = em.createQuery("SELECT v FROM VersionesApp v", VersionesApp.class);
			
			List<VersionesApp> resultadoQuery = query.getResultList();
			
			if(resultadoQuery!=null){
				logger.debug("Se encontraron [{}] registros de versiones en la base de datos.", resultadoQuery.size());
				listaVersion = MapperVO.getListaVersiones(resultadoQuery);
			}else{
				logger.error("No se encontraron registros en la tabla versiones.");
			}
		}catch(Exception e){		
			logger.error("Error, getVersionesApps", e);			
		}finally{
    		em.close();
    	}
		
		return listaVersion;
	}
}
