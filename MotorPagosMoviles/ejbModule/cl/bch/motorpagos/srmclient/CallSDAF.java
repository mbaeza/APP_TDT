/**
 * 
 */
package cl.bch.motorpagos.srmclient;

import java.security.GeneralSecurityException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.security.GenBCH;
import cl.bch.motorpagos.util.ConfigurationLoader;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.LoginComercioVO;
import cl.bch.srm.SrmException;
import cl.bch.srm.SrmSender;

/**
 * @author boyanedel
 *
 */
public class CallSDAF {
	private static final Logger logger = LoggerFactory.getLogger(CallSDAF.class);
	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public LoginRsp executeSDAF(LoginComercioVO loginVO){
		
		LoginRsp rsp = null;
		try{
			
			StringBuffer bf = new StringBuffer(30);
			bf.append("000000000011L0000D0");
			bf.append(MotorPagosHelper.formateaString(MotorPagosHelper.cleanRut(loginVO.getRutPersona()), "0", 10));
			bf.append(MotorPagosHelper.formateaString(MotorPagosHelper.cleanRut(loginVO.getRutEmpresa()), "0", 10));
			bf.append(GenBCH.genClave(loginVO.getClave()));
			bf.append("BCWA0");
			
			logger.debug("SRM INPUT : {}", bf.toString());
			String resp = SrmSender.send(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.NODO_SRM_SDAF), "SDAF", bf.toString());
			logger.debug("SRM OUTPUT : {}", resp);
			
			rsp = LoginRsp.parsear(resp);
		}catch (LoginException | GeneralSecurityException | SrmException e) {
			logger.error("Error, se produjo un problema al invocar al SRM.", e);
		}
		return rsp;
	
	}
}
