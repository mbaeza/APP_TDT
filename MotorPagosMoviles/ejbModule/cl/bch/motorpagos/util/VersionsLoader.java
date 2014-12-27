/**
 * 
 */
package cl.bch.motorpagos.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.MotorPagosManager;
import cl.bch.motorpagos.vo.VersionVO;

/**
 * @author boyanedel
 *
 */
public class VersionsLoader {
	
	private static final Logger logger = LoggerFactory.getLogger(VersionsLoader.class);
	private static VersionsLoader instance;
	private Calendar consultaCalendar;
	private Hashtable<String, String> versionesCobros;
	private Hashtable<String, String> versionesPagos;
	
	/**
	 * 
	 */
	private VersionsLoader(){
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static VersionsLoader getInstance(){
		synchronized (ConfigurationLoader.class){
			if(instance==null){
				instance = new VersionsLoader();
			}
		}
		return instance;
	}
	
	/**
	 * 
	 * @param idApp
	 * @param version
	 * @return
	 */
	public boolean validaVersion(String idApp, String version){
		this.actualizaData();
		
		if(ConstantesMotorPagos.ID_APP_COBROS.equals(idApp)){
			if(this.versionesCobros.containsKey(version)){
				if(this.versionesCobros.get(version).equals(ConstantesMotorPagos.ESTADO_VERSION_ACTIVA)){					
					logger.debug("Version [{}] de la aplicacion [{}] Activa.", version, idApp);
					return true;
				}else{
					logger.debug("Version [{}] de la aplicacion [{}] no Activa.", version, idApp);					
					return false;
				}
			}else{
				logger.error("Version [{}] de la aplicacion [{}] no registrada.", version, idApp);
				return true;
			}
		}else if(ConstantesMotorPagos.ID_APP_PAGOS.equals(idApp)){
			if(this.versionesPagos.containsKey(version)){
				if(this.versionesPagos.get(version).equals(ConstantesMotorPagos.ESTADO_VERSION_ACTIVA)){
					logger.debug("Version [{}] de la aplicacion [{}] Activa.", version, idApp);
					return true;
				}else{
					logger.debug("Version [{}] de la aplicacion [{}] no Activa.", version, idApp);	
					return false;
				}
			}else{
				logger.error("Version [{}] de la aplicacion [{}] no registrada.", version, idApp);
				return true;
			}
		}else{
			logger.error("Version [{}] de la aplicacion [{}] inválida.", version, idApp);
			return false;
		}		
	}
	
	/**
	 * 
	 */
	private void actualizaData(){
		Calendar intervalBefore  = Calendar.getInstance();
		intervalBefore.add(Calendar.DAY_OF_YEAR, -Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.DIAS_CACHE_VERSIONES)));
		
		if(this.consultaCalendar==null || this.consultaCalendar.before(intervalBefore)){
			logger.debug("Se actualiza CACHE de versiones de las aplicaciones.");
			synchronized (ConfigurationLoader.class) {
				MotorPagosManager manager = new MotorPagosManager();
				ArrayList<VersionVO> versiones = manager.getVersionesApps();
				
				this.versionesCobros = new Hashtable<String, String>();
				this.versionesPagos = new Hashtable<String, String>();
				
				for(VersionVO version:versiones){
					logger.debug("App [{}], version [{}], estado [{}]", version.getIdApp(), version.getVersion(), version.getEstado());
					if(ConstantesMotorPagos.ID_APP_COBROS.equals(version.getIdApp())){
						this.versionesCobros.put(version.getVersion(), version.getEstado());
					}else{
						this.versionesPagos.put(version.getVersion(), version.getEstado());
					}
				}
			}
			this.consultaCalendar = Calendar.getInstance();
		}
	}

}
