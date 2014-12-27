/**
 * 
 */
package cl.bch.motorpagos.command;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.UsuariosAdm;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.vo.LoginAdmWebVO;
import cl.bch.motorpagos.vo.RespuestaVO;

/**
 * @author boyanedel
 *
 */
public class LoginAdmWebCommand extends IServiceCommand {
	private static final Logger logger = LoggerFactory.getLogger(LoginAdmWebCommand.class);
	
	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public RespuestaVO loginAdmMesa(LoginAdmWebVO loginVO){
		EntityManager em = this.factory.createEntityManager();
		RespuestaVO respuesta = null;
		
		try{
			em.getTransaction().begin();
			
			UsuariosAdm usuarioAdm = em.find(UsuariosAdm.class, loginVO.getIdUsuario());
			if(usuarioAdm!=null){
				
				if(usuarioAdm.getPassword().equalsIgnoreCase(loginVO.getClaveUsuario())){
					respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Validación Exitosass.");
				}else{
					respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Clave inválida, intenta nuevamente.");
				}
				
			}else{
				logger.error("Usuario [{}] no existe en los registros.", loginVO.getIdUsuario());
				respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
			}
			
		}catch(Exception e){
			logger.error("Error, No fue posible realizar el login de la Mesa de Ayuda.", e);
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);			
		}finally{
			em.close();
		}
		
		return respuesta;
	}
}
