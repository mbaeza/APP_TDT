package cl.bch.motorpagos.command;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Comercio;
import cl.bch.motorpagos.persistencia.Convenio;
import cl.bch.motorpagos.persistencia.Cuenta;
import cl.bch.motorpagos.persistencia.CuentasConvenio;
import cl.bch.motorpagos.persistencia.Dispositivo;
import cl.bch.motorpagos.persistencia.DispositivosConvenio;
import cl.bch.motorpagos.security.CryptData;
import cl.bch.motorpagos.security.GenBCH;
import cl.bch.motorpagos.util.ConfigurationLoader;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperDB;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.ComercioVO;
import cl.bch.motorpagos.vo.RespuestaEnrolamientoVO;

/**
 * 
 * @author boyanedel
 *
 */
public class EnrolarComercioCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(EnrolarComercioCommand.class);

    /**
     * 
     * @param comercioVO
     * @return
     */
    public RespuestaEnrolamientoVO enrolarComercio(ComercioVO comercioVO){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaEnrolamientoVO respuesta = null;
    	MotorPagosServicesUtils utilServices = new MotorPagosServicesUtils();
    	try{
    		em.getTransaction().begin();    		
    		
    		Comercio comercioDB;
    		List<Comercio> listCom;
    		if(comercioVO.getRutEmpresa()==null || comercioVO.getRutEmpresa().length()==0){
    			logger.debug("Se busca el comercio [{}] en los registros del sistema.", comercioVO.getRutPersonas());
    			listCom = utilServices.findIdComercioByRutEmpresaApoderado(comercioVO.getRutPersonas(), comercioVO.getRutPersonas(), em);    			
    		}else{
    			logger.debug("Se busca el comercio [{}] apoderado [{}] en los registros del sistema.", comercioVO.getRutEmpresa(), comercioVO.getRutPersonas());
    			listCom = utilServices.findIdComercioByRutEmpresaApoderado(comercioVO.getRutEmpresa(), comercioVO.getRutPersonas(), em);    			
    		}   		
    		
    		Convenio convenioDB;
    		if(listCom==null || listCom.isEmpty()){
    			logger.debug("El comercio no existe en los registros, será creado.");
    			String idComercio = MotorPagosHelper.getCadenaAlfanumAleatoria(ConstantesMotorPagos.LARGO_ID_COMERCIO);
    			comercioDB = MapperDB.getComercioDB(comercioVO, idComercio);
    			em.persist(comercioDB);   
    			    			
    			logger.debug("Almacenando datos del convenio en la BD.");
    			String idConvenio = MotorPagosHelper.getCadenaAlfanumAleatoria(ConstantesMotorPagos.LARGO_ID_CONVENIO);
        		convenioDB = MapperDB.getConvenioDB(idConvenio, comercioVO.getNombreConvenio(), comercioDB, comercioVO.getPin());
    			em.persist(convenioDB);
    			
    		}else{
    			logger.debug("El comercio existe en los registros.");
    			comercioDB = listCom.get(0);    			    			
    			comercioDB.setEmailComercio(MotorPagosHelper.formateaString(comercioVO.getEmail(), 100));
    			logger.debug("Actualizando datos del comercio en la BD.");
    			em.persist(comercioDB);    

    			logger.debug("Se busca el convenio en la base de datos.");
    			Convenio convenio = utilServices.findConvenioByIdComercio(comercioDB.getIdComercio(), comercioVO.getNombreConvenio(), em);
    			
    			if(convenio==null){
    				logger.debug("El convenio [{}] no existe para el comercio [{}]. Será creado.", comercioVO.getNombreConvenio(), comercioDB.getIdComercio());
    				String idConvenio = MotorPagosHelper.getCadenaAlfanumAleatoria(ConstantesMotorPagos.LARGO_ID_CONVENIO);
            		convenioDB = MapperDB.getConvenioDB(idConvenio, comercioVO.getNombreConvenio(), comercioDB, comercioVO.getPin());
        			em.persist(convenioDB);
    			}else{
    				logger.debug("El convenio [{}] no existe para el comercio [{}]. Será reutilizado.", comercioVO.getNombreConvenio(), comercioDB.getIdComercio());
    				convenioDB = convenio;
    			}
    		}
    		
    		logger.debug("Obteniendo Correlativos desde la BD.");    		
			String idDispositivo = MotorPagosHelper.getCadenaAlfanumAleatoria(ConstantesMotorPagos.LARGO_ID_DISPOSITIVO);			
				
			logger.debug("Se valida la existencia de la cuenta en la BD.");
    		String keyCuenta = CryptData.desEncriptar(comercioVO.getLlaveCuenta());
        	
        	String nroCuenta = MotorPagosHelper.cleanCuenta(keyCuenta.substring(0, keyCuenta.length()-3));
        	String tipoCuenta = keyCuenta.substring(keyCuenta.length()-3);
        	
        	Cuenta cuentaDB = utilServices.findCuentaByNroTipo(nroCuenta, tipoCuenta, em);
        	CuentasConvenio cuentaConvenio;
        	
    		if(cuentaDB==null){
    			logger.debug("Cuenta no existe, se procede a registrar cuenta en la BD.");
    			String idCuenta = MotorPagosHelper.getCadenaAlfanumAleatoria(ConstantesMotorPagos.LARGO_ID_CUENTA);
    			cuentaDB = MapperDB.getCuentaDB(idCuenta, comercioVO.getLlaveCuenta(), ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.LIMITE_TEF));
    			cuentaConvenio = MapperDB.getCuentaConvenio(convenioDB.getIdConvenio(), cuentaDB.getIdCuenta());      			    			
    		}else{
    			cuentaConvenio = em.find(CuentasConvenio.class, MapperDB.getCuentaConvenioPK(cuentaDB.getIdCuenta(), convenioDB.getIdConvenio()));
    			if(cuentaConvenio==null){
    				logger.debug("Cuenta existe, se procede a asociar al convenio.");
    				cuentaConvenio = MapperDB.getCuentaConvenio(convenioDB.getIdConvenio(), cuentaDB.getIdCuenta());
    			}else{
    				logger.debug("Cuenta ya se encuentra asociada al convenio.");
    			}
    		}
  					
    		logger.debug("Almacenando datos de la cuenta en la BD.");
    		em.persist(cuentaDB);
    		
    		logger.debug("Almacenando datos de la relacion cuenta-convenio en la BD.");
			em.persist(cuentaConvenio);
			
			Dispositivo dispositivoDB = MapperDB.getDispositivoDB(idDispositivo, GenBCH.genClave(comercioVO.getPin()), comercioVO.getAliasDispositivo(), comercioVO.getModeloDispositivo(), ConstantesMotorPagos.TIPO_DISP_ADMIN);			
			DispositivosConvenio dispConvenioDB = MapperDB.getDispositivosConvenioDB(idDispositivo, convenioDB.getIdConvenio(), cuentaDB.getIdCuenta());
														
			logger.debug("Almacenando datos del dispositivo en la BD.");
			em.persist(dispositivoDB);
			
			logger.debug("Almacenando datos de la relacion convenio-dispositivo en la BD.");
			em.persist(dispConvenioDB);
						
			respuesta = MapperVO.getRespuestaEnrolamientoVO(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA), 
															idDispositivo, cuentaDB.getIdCuenta(), convenioDB.getIdConvenio(), null);
			em.getTransaction().commit();			
			
		}catch(Exception e){
			logger.error("Error, El Enrolamiento no pudo efectuarse.", e);
			respuesta = MapperVO.getRespuestaEnrolamientoVO(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO), null, null, null, null);			
		}finally{
			em.close();
		}
    	return respuesta;
    }
}
