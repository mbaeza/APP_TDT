/**
 * 
 */
package cl.bch.motorpagos.command;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.Cliente;
import cl.bch.motorpagos.persistencia.Comercio;
import cl.bch.motorpagos.persistencia.Convenio;
import cl.bch.motorpagos.persistencia.Cuenta;
import cl.bch.motorpagos.persistencia.CuentasCliente;
import cl.bch.motorpagos.persistencia.CuentasConvenio;
import cl.bch.motorpagos.persistencia.Dispositivo;
import cl.bch.motorpagos.persistencia.DispositivosCliente;
import cl.bch.motorpagos.persistencia.DispositivosConvenio;
import cl.bch.motorpagos.security.CryptData;
import cl.bch.motorpagos.security.GenBCH;
import cl.bch.motorpagos.util.ConfigurationLoader;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperDB;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.ClienteVO;
import cl.bch.motorpagos.vo.RespuestaEnrolamientoVO;


/**
 * @author boyanedel
 *
 */
public class EnrolarPersonaCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(EnrolarPersonaCommand.class);
	
	 /**
     * 
     * @param clienteVO
     * @return RespuestaEnrolamientoVO
     */
    public RespuestaEnrolamientoVO enrolarCliente(ClienteVO clienteVO){
    	EntityManager em = this.factory.createEntityManager();
    	MotorPagosServicesUtils utilServices = new MotorPagosServicesUtils();
    	RespuestaEnrolamientoVO respuesta = null;
    	
    	try{
			em.getTransaction().begin();	

			logger.debug("Se busca el cliente [{}] en los registros del sistema.", clienteVO.getRut());
    		List<Cliente> resultadoQuery = utilServices.findClienteByRut(clienteVO.getRut(), em);
    		
    		Cliente clienteDB = null;
    		String idCliente = null;
    		
    		if(resultadoQuery==null || resultadoQuery.isEmpty()){
    			logger.debug("El cliente [{}] no existe en los registros, se procede a crear en la BD.", clienteVO.getRut());
    			idCliente = MotorPagosHelper.getCadenaAlfanumAleatoria(ConstantesMotorPagos.LARGO_ID_CLIENTE);
    			clienteDB = MapperDB.getClienteDB(clienteVO, idCliente);
    			logger.debug("Almacenando datos del cliente [{}] en la BD.", clienteVO.getRut());
    			em.persist(clienteDB);
    		}else{
    			logger.debug("El cliente [{}] ya existe en los registros del sistema, se obtiene el id ya registrado.", clienteVO.getRut());
    			clienteDB = (Cliente)resultadoQuery.get(0);
    			
    			clienteDB.setNombreCliente(clienteVO.getNombre());
    			clienteDB.setCodigoMarca(clienteVO.getMarca());
    			clienteDB.setMailcliente(MotorPagosHelper.formateaString(clienteVO.getMail(), 100));
    			idCliente = clienteDB.getIdCliente();    
    			em.persist(clienteDB);
    		}
    		
    		logger.debug("Se busca el comercio[{}] en los registros del sistema.", clienteVO.getRut());
    		Comercio comercioDB;
    		List<Comercio> listCom = utilServices.findIdComercioByRutEmpresaApoderado(clienteVO.getRut(), clienteVO.getRut(), em);    			
    		
    		Convenio convenioDB;
    		String idConvenio = null;
    		
    		if(listCom==null || listCom.isEmpty()){
    			logger.debug("El comercio [{}] no existe en los registros, será creado.", clienteVO.getRut());
    			String idComercio = MotorPagosHelper.getCadenaAlfanumAleatoria(ConstantesMotorPagos.LARGO_ID_COMERCIO);
    			comercioDB = MapperDB.getComercioDB(clienteVO, idComercio);
    			em.persist(comercioDB);   
    			    			
    			logger.debug("Almacenando datos del convenio en la BD.");
    			idConvenio = MotorPagosHelper.getCadenaAlfanumAleatoria(ConstantesMotorPagos.LARGO_ID_CONVENIO);
        		convenioDB = MapperDB.getConvenioDB(idConvenio, ConstantesMotorPagos.CONVENIO_PERSONAS, comercioDB, clienteVO.getPin());
    			em.persist(convenioDB);
    			
    		}else{
    			logger.debug("El comercio [{}] existe en los registros.", clienteVO.getRut());
    			comercioDB = listCom.get(0);    			    			
    			comercioDB.setEmailComercio(MotorPagosHelper.formateaString(clienteVO.getMail(), 100));
    			logger.debug("Actualizando datos del comercio [{}] en la BD.", clienteVO.getRut());
    			em.persist(comercioDB);    

    			logger.debug("Se busca el convenio en la base de datos.");
    			Convenio convenio = utilServices.findConvenioByIdComercio(comercioDB.getIdComercio(), ConstantesMotorPagos.CONVENIO_PERSONAS, em);
    			
    			if(convenio==null){
    				logger.debug("El convenio [{}] no existe para el comercio [{}]. Será creado.", ConstantesMotorPagos.CONVENIO_PERSONAS, comercioDB.getIdComercio());
    				idConvenio = MotorPagosHelper.getCadenaAlfanumAleatoria(ConstantesMotorPagos.LARGO_ID_CONVENIO);
            		convenioDB = MapperDB.getConvenioDB(idConvenio, ConstantesMotorPagos.CONVENIO_PERSONAS, comercioDB, clienteVO.getPin());
        			em.persist(convenioDB);
    			}else{
    				logger.debug("El convenio [{}] existe para el comercio [{}]. Será reutilizado.", convenio.getNombreConveniio(), comercioDB.getIdComercio());
    				idConvenio = convenio.getIdConvenio();
    				convenioDB = convenio;
    			}
    		}	
    		
    		logger.debug("Se valida la existencia de la cuenta en la BD.");
    		String keyCuenta = CryptData.desEncriptar(clienteVO.getLlaveCuenta());
        	
        	String nroCuenta = MotorPagosHelper.cleanCuenta(keyCuenta.substring(0, keyCuenta.length()-3));
        	String tipoCuenta = keyCuenta.substring(keyCuenta.length()-3);
        	
        	Cuenta cuentaDB = utilServices.findCuentaByNroTipo(nroCuenta, tipoCuenta, em);
        	CuentasCliente cuentaCliente;
        	CuentasConvenio cuentaConvenio;
        	
    		if(cuentaDB==null){
    			logger.debug("Cuenta no existe, se procede a registrar cuenta en la BD.");
    			String idCuenta = MotorPagosHelper.getCadenaAlfanumAleatoria(ConstantesMotorPagos.LARGO_ID_CUENTA);
    			cuentaDB = MapperDB.getCuentaDB(idCuenta, clienteVO.getLlaveCuenta(), ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.LIMITE_TEF));
    			cuentaCliente = MapperDB.getCuentaCliente(clienteDB.getIdCliente(), cuentaDB.getIdCuenta());   
    			cuentaConvenio = MapperDB.getCuentaConvenio(convenioDB.getIdConvenio(), cuentaDB.getIdCuenta());
    		}else{
    			cuentaCliente = em.find(CuentasCliente.class, MapperDB.getCuentaCliente(clienteDB.getIdCliente(), cuentaDB.getIdCuenta()).getId());
    			if(cuentaCliente==null){
    				logger.debug("Cuenta existe, se procede a asociar al cliente [{}].", clienteVO.getRut());
        			cuentaCliente = MapperDB.getCuentaCliente(clienteDB.getIdCliente(), cuentaDB.getIdCuenta());
    			}else{
    				logger.debug("Cuenta ya se encuentra asociada al cliente [{}].", clienteVO.getRut());
    			}
    			
    			cuentaConvenio = em.find(CuentasConvenio.class, MapperDB.getCuentaConvenioPK(cuentaDB.getIdCuenta(), convenioDB.getIdConvenio()));
    			if(cuentaConvenio==null){
    				logger.debug("Cuenta existe, se procede a asociar al convenio [{}].", convenioDB.getIdConvenio());
    				cuentaConvenio = MapperDB.getCuentaConvenio(convenioDB.getIdConvenio(), cuentaDB.getIdCuenta());
    			}else{
    				logger.debug("Cuenta ya se encuentra asociada al convenio [{}].", convenioDB.getIdConvenio());
    			}
    			
    		}
  		
    		logger.debug("Almacenando datos de la cuenta en la BD.");
			em.persist(cuentaDB);    			
			em.persist(cuentaCliente);
			em.persist(cuentaConvenio);
			
    		logger.debug("Obteniendo Correlativos desde la BD.");
    		String idDispositivo = MotorPagosHelper.getCadenaAlfanumAleatoria(ConstantesMotorPagos.LARGO_ID_DISPOSITIVO);
						
			Dispositivo dispositivoDB = MapperDB.getDispositivoDB(idDispositivo, GenBCH.genClave(clienteVO.getPin()), clienteVO.getAliasDispositivo(), clienteVO.getModeloDispositivo(), ConstantesMotorPagos.TIPO_DISP_CLIENTE);	
			DispositivosCliente dispClienteDB = MapperDB.getDispositivosClienteDB(idDispositivo, idCliente, cuentaDB.getIdCuenta());
			DispositivosConvenio dispConvenioDB = MapperDB.getDispositivosConvenioDB(idDispositivo, convenioDB.getIdConvenio(), cuentaDB.getIdCuenta());
			
			logger.debug("Almacenando dispositivo en la BD.");
			em.persist(dispositivoDB);
			
			logger.debug("Almacenando la relacion cliente-dispositivo en la BD.");
			em.persist(dispClienteDB);
			
			logger.debug("Almacenando datos de la relacion convenio-dispositivo en la BD.");
			em.persist(dispConvenioDB);
						
			respuesta = MapperVO.getRespuestaEnrolamientoVO(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA), 
															idDispositivo, 
															cuentaDB.getIdCuenta(), 
															idConvenio,
															idCliente);
			em.getTransaction().commit();			
			
		}catch(Exception e){		
			logger.error("Error, El Enrolamiento no pudo efectuarse.", e);
			
			respuesta = MapperVO.getRespuestaEnrolamientoVO(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO), 
															null, null, null, null);
		}finally{
			em.close();
		}
    	return respuesta;
    }

}
