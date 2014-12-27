/**
 * 
 */
package cl.bch.motorpagos.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author boyanedel
 *
 */
public final class ConfigurationLoader {
	
	public static final String NRO_REINTENTOS_VALIDACION = "NRO_REINTENTOS_VALIDACION";
	public static final String DELAY_REINTENTO_VALIDACION = "DELAY_REINTENTO_VALIDACION";	
	public static final String ALIAS_SERVER = "ALIAS_SERVER";
	public static final String PORT_SERVER = "PORT_SERVER";	
	public static final String PORT_SERVER_TELLER = "PORT_SERVER_TELLER";
	public static final String REGISTROS_X_PAGINA = "REGISTROS_X_PAGINA";	
	
	public static final String TEF_HEADER_ID_CANAL = "TEF_HEADER_ID_CANAL";
	public static final String TEF_RUT_OPERADOR = "TEF_RUT_OPERADOR";
	public static final String TEF_ESTACION = "TEF_ESTACION";
	public static final String TEF_SERVICIO = "TEF_SERVICIO";
	public static final String TEF_LOG = "TEF_LOG";
	public static final String TEF_CANAL = "TEF_CANAL";
	public static final String TEF_TOKEN = "TEF_TOKEN";
	public static final String TEF_TOKEN_TIPO_CLIENTE = "TEF_TOKEN_TIPO_CLIENTE";
	public static final String TEF_MIG_CTA_ORIGEN = "TEF_MIG_CTA_ORIGEN";
	public static final String TEF_COD_REL_ORIGEN = "TEF_COD_REL_ORIGEN";
	public static final String TEF_MIG_CTA_DESTINO = "TEF_MIG_CTA_DESTINO";
	public static final String TEF_COD_REL_DESTINO = "TEF_COD_REL_DESTINO";
	public static final String TEF_OFICINA_ORIGEN = "TEF_OFICINA_ORIGEN";	
	public static final String TEF_OFICINA_DESTINO = "TEF_OFICINA_DESTINO";
	public static final String TEF_AUTORIZACION = "TEF_AUTORIZACION";
	public static final String TEF_CAJERO = "TEF_CAJERO";
	public static final String TEF_INDICE_JOURNAL = "TEF_INDICE_JOURNAL";	
	public static final String TEF_REFERENCIA = "TEF_REFERENCIA";	
	public static final String TEF_SEGMENTO = "TEF_SEGMENTO";
	public static final String TEF_BANCA = "TEF_BANCA";
	public static final String TEF_BANCO = "TEF_BANCO";
	public static final String TEF_CODIGO_BANCO = "TEF_CODIGO_BANCO";
	
	public static final String NODO_SRM_SDAF = "NODO_SRM_SDAF";	
	public static final String TIPO_TRX_CIO = "TIPO_TRX_CIO";
	public static final String ID_CANAL_CIO = "ID_CANAL_CIO";
	public static final String MAX_NRO_REGISTROS_EXPORT = "MAX_NRO_REGISTROS_EXPORT";
	public static final String LIMITE_TEF = "LIMITE_TEF";
	public static final String LIMITE_MAXIMO_TEF = "LIMITE_MAXIMO_TEF";
	public static final String BUS_TIME_OUT = "BUS_TIME_OUT";
	public static final String DIAS_CACHE_VERSIONES = "DIAS_CACHE_VERSIONES";
	
	public static final String TEF_TMC = "TEF_TMC";
	public static final String TEF_TEC = "TEF_TEC";
	public static final String TEF_TMC_COD_PRODUCTO_CARGO = "TEF_TMC_COD_PRODUCTO_CARGO";
	public static final String TEF_TMC_COD_TRX_CARGO = "TEF_TMC_COD_TRX_CARGO";
	public static final String TEF_TMC_COD_EXT_CARGO = "TEF_TMC_COD_EXT_CARGO";
	public static final String TEF_TMC_COD_PRODUCTO_ABONO = "TEF_TMC_COD_PRODUCTO_ABONO";
	public static final String TEF_TMC_COD_TRX_ABONO = "TEF_TMC_COD_TRX_ABONO";
	public static final String TEF_TMC_COD_EXT_ABONO = "TEF_TMC_COD_EXT_ABONO";
	public static final String TEF_TEC_COD_PRODUCTO_CARGO = "TEF_TEC_COD_PRODUCTO_CARGO";
	public static final String TEF_TEC_COD_TRX_CARGO = "TEF_TEC_COD_TRX_CARGO";
	public static final String TEF_TEC_COD_EXT_CARGO = "TEF_TEC_COD_EXT_CARGO";
	public static final String TEF_TEC_COD_PRODUCTO_ABONO = "TEF_TEC_COD_PRODUCTO_ABONO";
	public static final String TEF_TEC_COD_TRX_ABONO = "TEF_TEC_COD_TRX_ABONO";
	public static final String TEF_TEC_COD_EXT_ABONO = "TEF_TEC_COD_EXT_ABONO";
	public static final String TEF_CORTE_FECHA_CONTABLE = "TEF_CORTE_FECHA_CONTABLE";	
	
	private final Properties properties = new Properties();
	private static final String CONFIG_FILE_NAME = "/MotorPagos/MotorPagosProps.properties";
	private static final String DEFAULT_CONFIG_FILE_NAME = "MotorPagosProps.properties";
	
	private static ConfigurationLoader instance;
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationLoader.class);

	/**
	 * 
	 */
	private ConfigurationLoader(){
		try{
			logger.info("Se procede a cargar propiedades del sistema.");
			String ruta = System.getenv("APPSERVER_PROPS")+ConfigurationLoader.CONFIG_FILE_NAME;
			logger.debug("la ruta predefinida es [{}].", ruta);
			FileInputStream fis = new FileInputStream(ruta);
			this.properties.load(fis);
			fis.close();
		} catch (IOException ex) {
			logger.error("Se produjo un error al cargar las propiedades del sistema, se setearan datos por defecto.");
			try{
				this.properties.load(MapperDB.class.getResourceAsStream(ConfigurationLoader.DEFAULT_CONFIG_FILE_NAME));
			}catch(IOException e){
				logger.error("Se produjo un error al cargar las propiedades por defecto.");
			}
	    }
		logger.info("La propiedades cargadas son:");
		Enumeration propiedades = this.properties.keys();
		while(propiedades.hasMoreElements()){
			String item = (String)propiedades.nextElement();
			logger.info(item + "=" + this.properties.getProperty(item));
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static ConfigurationLoader getInstance(){
		synchronized (ConfigurationLoader.class){
			if(instance==null){
				instance = new ConfigurationLoader();
			}
		}
		return instance;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
        return this.properties.getProperty(key);
    }
	
//	public static void main(String[] args){
//		try{			
//			Properties props = new Properties();
//			props.load(new MapperDB().getClass().getResourceAsStream("MotorPagosProps.properties"));
//			System.out.println(props.getProperty("ALIAS_SERVER"));
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
}
