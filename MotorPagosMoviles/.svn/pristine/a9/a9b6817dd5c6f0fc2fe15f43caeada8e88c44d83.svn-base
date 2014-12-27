/**
 * 
 */
package cl.bch.motorpagos.srmclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.util.ConfigurationLoader;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.srm.SrmException;
import cl.bch.srm.SrmSender;

/**
 * @author boyanedel
 *
 */
public class CallSLGN {
	private static final Logger logger = LoggerFactory.getLogger(CallSDAF.class);
	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public FilialesRsp executeSLGN(String rutEmpresaMadre){
		
		FilialesRsp rsp = null;
		try{
			
			StringBuffer bf = new StringBuffer(30);
			bf.append("123456780060L000000");
			bf.append(MotorPagosHelper.formateaString(MotorPagosHelper.cleanRut(rutEmpresaMadre), "0", 10));
						
			logger.debug("SRM INPUT : {}", bf.toString());			
			String resp = SrmSender.send(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.NODO_SRM_SDAF), "SLGN", bf.toString());
			logger.debug("SRM OUTPUT : {}", resp);	
			rsp = FilialesRsp.parsear(resp);
		}catch (SrmException e) {
			logger.error("Error, se produjo un problema al invocar al SRM.", e);
		}
		return rsp;		
	}	
	
//	public static void main(String args[]){
//		CallSLGN clase = new CallSLGN();
//		clase.executeSLGN("93129000-2");
//	}
}
