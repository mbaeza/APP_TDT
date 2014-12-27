package cl.bch.motorpagos;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bancochile.monitor.tx.ConectorCIO;
import cl.bch.motorpagos.command.*;
import cl.bch.motorpagos.exceptions.MotorPagosException;
import cl.bch.motorpagos.persistencia.ManagerFactoryInitializer;
import cl.bch.motorpagos.security.ClaveNoValidaException;
import cl.bch.motorpagos.security.Seguridad;
import cl.bch.motorpagos.util.*;
import cl.bch.motorpagos.vo.*;

/**
 * @author boyanedel
 *
 */
public class MotorPagosManager {
	private static final Logger logger = LoggerFactory.getLogger(MotorPagosManager.class);
	EntityManagerFactory factory = ManagerFactoryInitializer.getInstance().getEntityManagerFactory();
	
	/**
	 * 
	 * @param loginVO
	 * @return RespuestaLoginClienteVO
	 */
	public RespuestaLoginClienteVO loginCliente(LoginClienteVO loginVO){
		RespuestaLoginClienteVO respuesta = new RespuestaLoginClienteVO();
				
		if (this.isVersionValida(loginVO.getIdApp(), loginVO.getVersionApp())){
		
			logger.debug("Se procede a validar y hacer login del cliente [{}].", loginVO.getRut());
			MotorPagosServicesWS services = new MotorPagosServicesWS();
			respuesta = services.loginCliente(loginVO);
			
			if(respuesta.getRetorno().getCodigoRetorno().equalsIgnoreCase(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
				if(respuesta.getCuentas()!=null && respuesta.getCuentas().getCuentasCliente().size()>0){
					
					logger.debug("Se obtienen los dispositivos de seguridad del cliente [{}].", loginVO.getRut());
					RespuestaLoginClienteVO respuestaDisp = this.obtenerDispositivosSeguridad(loginVO, services);
					if(respuestaDisp.getRetorno().getCodigoRetorno().equalsIgnoreCase(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
						respuesta.setDispositivos(respuestaDisp.getDispositivos());
					}else{
						respuesta.setRetorno(respuestaDisp.getRetorno());
					}
								
				}else{
					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Ud. no tiene cuentas para este servicio."));
				}
			}
		}else{
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_VERSION_OBSOLETA));
		}
		return respuesta;
	}
	
	/**
	 * 
	 * @param loginVO
	 * @return RespuestaLoginClienteVO
	 */
	public RespuestaLoginClienteVO loginClienteWeb(LoginClienteVO loginVO){
		RespuestaLoginClienteVO respuesta = new RespuestaLoginClienteVO();
				
		if (this.isVersionValida(loginVO.getIdApp(), loginVO.getVersionApp())){
		
			logger.debug("Se procede a validar y hacer login del cliente [{}].", loginVO.getRut());
			MotorPagosServicesWS services = new MotorPagosServicesWS();
			respuesta = services.loginCliente(loginVO);
						
		}else{
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_VERSION_OBSOLETA));
		}
		return respuesta;
	}

	/**
	 * 
	 * @param loginVO
	 * @return RespuestaVO
	 */
	public RespuestaVO loginAdministracionWeb(LoginAdmWebVO loginVO){
		RespuestaVO respuesta = null;
				
		if (this.isVersionWebValida(loginVO.getIdApp(), loginVO.getVersionApp())){
		
			logger.debug("Se procede a validar y hacer login del usuario [{}].", loginVO.getIdUsuario());
			LoginAdmWebCommand comando = (LoginAdmWebCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_LOGIN_ADM_MESA, this.factory);
			respuesta = comando.loginAdmMesa(loginVO);
						
		}else{
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_VERSION_OBSOLETA);
		}
		return respuesta;
	}
	

	
	/**
	 * 
	 * @param clienteVO
	 * @return RespuestaEnrolamientoVO
	 */
	public RespuestaEnrolamientoVO enrolarCliente(ClienteVO clienteVO){
		RespuestaVO respuestaVO = null;
		RespuestaEnrolamientoVO respuesta = null;

		MotorPagosServicesWS servicesWS = new MotorPagosServicesWS();

		logger.debug("Se procede a validar datos del cliente [{}].", clienteVO.getRut());
		
		respuestaVO = servicesWS.validarDatosCliente(clienteVO);
		if (respuestaVO.getCodigoRetorno().equalsIgnoreCase(ConstantesMotorPagos.CODIGO_RETORNO_NOOK)){
			respuesta = new RespuestaEnrolamientoVO();
			respuesta.setRetorno(respuestaVO);
			return respuesta;
		}
		
		logger.debug("Se procede a validar dispositivo de seguridad del cliente [{}].", clienteVO.getRut());
		
											
		respuestaVO = servicesWS.validarDispositivoSeguridad(MapperVO.getValidarDispositivoSegVO(clienteVO.getClaveSeguridad(), 
																											 clienteVO.getCoordenada1(),
																											 clienteVO.getCoordenada2(), 
																											 clienteVO.getCoordenada3(), 
																											 clienteVO.getRut(), 
																											 null,
																											 clienteVO.getSerieDispSeguridad(),
																											 clienteVO.getTipoDispSeguridad(), 
																											 clienteVO.getIdApp()));
				
		if (respuestaVO.getCodigoRetorno().equalsIgnoreCase(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			logger.debug("Se procede a enrolar al cliente [{}] en los registros del sistema.", clienteVO.getRut());
			EnrolarPersonaCommand comando = (EnrolarPersonaCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_ENROLAMIENTO_PERSONA, this.factory);
			respuesta = comando.enrolarCliente(clienteVO);
		}else{
			respuesta = new RespuestaEnrolamientoVO();
			respuesta.setRetorno(respuestaVO);
		}
				
		return respuesta;
	}
	
	/**
	 * 
	 * @param loginComercioVO
	 * @return RespuestaLoginComercioVO
	 */
	public RespuestaLoginComercioVO loginComercio(LoginComercioVO loginComercioVO){
		RespuestaLoginComercioVO respuesta = new RespuestaLoginComercioVO();
		
		if (this.isVersionValida(loginComercioVO.getIdApp(), loginComercioVO.getVersionApp())){
		
			MotorPagosServicesWS services = new MotorPagosServicesWS();
			MotorPagosServicesSRM servicesSRM = new MotorPagosServicesSRM();	
		
			if (loginComercioVO.getRutEmpresa()==null || loginComercioVO.getRutEmpresa().length()==0){
				logger.debug("El cliente [{}] sera logeado como Persona.", loginComercioVO.getRutPersona());
				
				LoginClienteVO loginClienteVO = new LoginClienteVO();
				loginClienteVO.setClave(		loginComercioVO.getClave());
				loginClienteVO.setIdApp(		ConstantesMotorPagos.ID_APP_PAGOS);
				loginClienteVO.setVersionApp(	loginComercioVO.getVersionApp());
				loginClienteVO.setRut(			loginComercioVO.getRutPersona());
				
				RespuestaLoginClienteVO respuestaCliente = this.loginCliente(loginClienteVO);			
				
				respuesta = new RespuestaLoginComercioVO();
				respuesta.setDireccion(		respuestaCliente.getDireccion());
				respuesta.setMail(			respuestaCliente.getMail());
				respuesta.setNombre(		respuestaCliente.getNombre());
				respuesta.setRetorno(		respuestaCliente.getRetorno());
				respuesta.setCuentas(		respuestaCliente.getCuentas());
				respuesta.setDispositivos( 	respuestaCliente.getDispositivos());
	
			}else{
				logger.debug("El cliente [{}] apoderado [{}] será logeado como Empresa.", loginComercioVO.getRutEmpresa(), loginComercioVO.getRutPersona());
				
				respuesta = servicesSRM.callSDAF(loginComercioVO);
				
				if(respuesta.getRetorno().getCodigoRetorno().equalsIgnoreCase(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
					RespuestaLoginComercioVO datosEmpresa = services.getInfoEmpresa(loginComercioVO);
					
					if(datosEmpresa.getRetorno().getCodigoRetorno().equalsIgnoreCase(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
						
						respuesta.setDireccion(	datosEmpresa.getDireccion());
						respuesta.setMail(		datosEmpresa.getMail());
						respuesta.setNombre(	datosEmpresa.getNombre());

						logger.debug("Se obtienen las filiales de la empresa [{}].", loginComercioVO.getRutEmpresa());
						respuesta.setFiliales(servicesSRM.callSLGN(loginComercioVO.getRutEmpresa()));
												
						LoginClienteVO loginVO = new LoginClienteVO();
						loginVO.setRut(		loginComercioVO.getRutPersona());
						loginVO.setIdApp(	ConstantesMotorPagos.ID_APP_COBROS);
						
						try{
							respuesta.setDispositivos(services.obtenerDispositivosSeguridad(loginVO));
							
							if(respuesta.getDispositivos()==null || respuesta.getDispositivos().getDispSeguridad() == null || respuesta.getDispositivos().getDispSeguridad().size()==0){
								respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Solicite su dispositivo de seguridad en sucursales."));
							}
						}catch(MotorPagosException e){
							respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
						}
											
					}else{
						respuesta.setRetorno(datosEmpresa.getRetorno());
					}				
				}			
			}		
		}else{
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_VERSION_OBSOLETA));
		}
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param clienteVO
	 * @return RespuestaEnrolamientoVO
	 */
	public RespuestaEnrolamientoVO enrolarComercio(ComercioVO comercioVO){
		
		RespuestaEnrolamientoVO respuesta = null;
		MotorPagosServicesWS servicesWS = new MotorPagosServicesWS();
		
		logger.debug("Se procede a validar el dispositivo de seguridad del apoderado [{}].", comercioVO.getRutPersonas());
		
		if (comercioVO.getRutEmpresa()==null || comercioVO.getRutEmpresa().length()==0){
			comercioVO.setIdApp(ConstantesMotorPagos.ID_APP_PAGOS);
		}else{
			comercioVO.setIdApp(ConstantesMotorPagos.ID_APP_COBROS);
		}
		
		RespuestaVO respuestaVO = servicesWS.validarDispositivoSeguridad(MapperVO.getValidarDispositivoSegVO(comercioVO.getClaveSeguridad(), 
																											 comercioVO.getCoordenada1(),
																											 comercioVO.getCoordenada2(), 
																											 comercioVO.getCoordenada3(), 
																											 comercioVO.getRutPersonas(), 
																											 comercioVO.getRutEmpresa(),
																											 comercioVO.getSerieDispSeguridad(),
																											 comercioVO.getTipoDispSeguridad(), 
																											 comercioVO.getIdApp()));
				
		if (respuestaVO.getCodigoRetorno().equalsIgnoreCase(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			EnrolarComercioCommand comando = (EnrolarComercioCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_ENROLAMIENTO_COMERCIO, this.factory);
			respuesta = comando.enrolarComercio(comercioVO);
		}else{
			respuesta = new RespuestaEnrolamientoVO();
			respuesta.setRetorno(respuestaVO);
		}
				
		return respuesta;
	}
	
	/**
	 * 
	 * @param dispositivoVO
	 * @return
	 */
	public RespuestaDispVentaVO enrolarDispositivoVenta(DispositivoVentaVO dispositivoVO){
		
		logger.debug("Se procede a enrolar el dispositivo de venta del comercio [{}].", dispositivoVO.getIdConvenio());
		EnrolarVendedorCommand comando = (EnrolarVendedorCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_ENROLAMIENTO_VENDEDOR, this.factory);
		RespuestaDispVentaVO respuesta = comando.enrolarDispositivoVenta(dispositivoVO);
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param pinVO
	 * @return
	 */
	private RespuestaValidaPinVO validarPin(ValidarPinVO pinVO){
		return this.validarPin(pinVO, null);
	}
	
	/**
	 * 
	 * @param pinVO
	 * @return
	 */
	public RespuestaValidaPinVO validarPin(ValidarPinVO pinVO, String tipoCall){
		RespuestaValidaPinVO respuesta = new RespuestaValidaPinVO();
		
		boolean versionValida = false;
		
		if(tipoCall != null){
			versionValida = this.isVersionValida(pinVO.getIdApp(), pinVO.getVersionApp());
		}
		
		if(tipoCall==null || versionValida){				
			logger.debug("Se procede a validar el Pin del dispositivo [{}].", pinVO.getIdDispositivo());
			ValidarPinCommand comando = (ValidarPinCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_VALIDAR_PIN, this.factory);
			respuesta = comando.validarPin(pinVO);
		}else{
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_VERSION_OBSOLETA));
		}
	
		return respuesta;
	}
	
	/**
	 * 
	 * @param atribucionesVO
	 * @return
	 */
	public RespuestaVO validarPoderesApoderado(AtribucionesComercioVO atribucionesVO){
		RespuestaVO respuesta;
		logger.debug("Se procede a validar las atribuciones del Apoderado [{}].", atribucionesVO.getRutPersona());
		
		if(MotorPagosHelper.getValorNumericoRut(atribucionesVO.getRutPersona())<=50000000){
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA);
		}else{
			MotorPagosServicesWS services = new MotorPagosServicesWS();			
			respuesta = services.validarPoderesApoderado(atribucionesVO);
		}
		return respuesta;
	}
	
	/**
	 * 
	 * @param preCobroVO
	 * @return
	 */
	public RespuestaValidaPagoVO registrarPreCobro(PreCobroVO preCobroVO){
		RespuestaValidaPagoVO respuesta = new RespuestaValidaPagoVO();
		
		ValidarPinVO pinVO = MapperVO.getValidarPinVO(preCobroVO.getIdDispositivoComercio(), preCobroVO.getPin());
		RespuestaValidaPinVO respValPin = this.validarPin(pinVO);
		
		if(respValPin.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			logger.debug("Se procede a almacenar el pre-cobro [{}] en los registros del sistema.", preCobroVO.getIdTrxPago());
			PreCobroCommand comando = (PreCobroCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_PRE_COBRO, this.factory);
			respuesta.setRetorno(comando.registrarPreCobro(preCobroVO));
			
			if(respuesta.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
				
				ValidarPagoVO validarVO = new ValidarPagoVO();
				validarVO.setIdConvenio(	preCobroVO.getIdConvenio());
				validarVO.setIdTrxPago(		preCobroVO.getIdTrxPago());
				validarVO.setIdDispositivo(	preCobroVO.getIdDispositivoComercio());
				validarVO.setPin(			preCobroVO.getPin());
				
				respuesta = this.validarPago(validarVO);
			}
		}else{
			respuesta.setRetorno(respValPin.getRetorno());
		}		
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param pagoVO
	 * @return RespuestaPagoVO
	 */
	public RespuestaPagoVO efectuarPago(PagoVO pagoVO, TransferenciaVO transferencia){
		logger.debug("Se procede a realizar el pago de servicio [{}].", pagoVO.getIdTrxPago());
		RespuestaPagoVO respuestaPago = new RespuestaPagoVO();
		
		ValidarPinVO pinVO = MapperVO.getValidarPinVO(pagoVO.getIdDispositivoCliente(), pagoVO.getPinCliente());
		RespuestaValidaPinVO respValPin = this.validarPin(pinVO);
		
		if(respValPin.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			MotorPagosServicesWS servicesWS = new MotorPagosServicesWS();
			
			logger.debug("Se obtienen los datos de la transaccion [{}], para realizar la transferencia.", pagoVO.getIdTrxPago());
			ObtenerDatosPagoCommand comandoDatos = (ObtenerDatosPagoCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_DATOS_PAGO, this.factory);
			transferencia = comandoDatos.getDatosTrxPago(pagoVO, transferencia);
			
			
			logger.debug(transferencia.toString());
					
			if(transferencia.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
				
				logger.debug("Se obtiene la fecha contable.");
				String fechaContable = servicesWS.obtieneFechaContable(transferencia.getIdCliente());
				transferencia.setFechaContable(fechaContable);
				
				if(fechaContable!=null){
					
					try {
						logger.debug("Se realiza validacion de clave dinamica.");
						Seguridad.validarClaveDinamica(pagoVO.getIdTrxPago().substring(pagoVO.getIdTrxPago().length()-11), 
													   pagoVO.getPinCliente(), 
													   transferencia.getHoraCobro(), 
													   pagoVO.getIdDispositivoCliente());
					
						RegistrarPagoCommand comandoPago = (RegistrarPagoCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_REGISTRA_PAGO, this.factory);
						
						if(pagoVO.getEstadoPago().equals(ConstantesMotorPagos.PAGO_RECHAZO)){
							
							logger.debug("El cliente rechazo el pago de la transaccion [{}], se procede a registrar el cambio de estado.", pagoVO.getIdTrxPago());
							respuestaPago = comandoPago.registrarPago(transferencia, pagoVO.getIdTrxPago(), ConstantesMotorPagos.TRX_CANCELADA, pagoVO.getIdTrxPago());
							
						}else if(pagoVO.getEstadoPago().equals(ConstantesMotorPagos.PAGO_ACEPTO)){
							
							logger.debug("Se procede a validar limite de la cuenta del cliente [{}].", transferencia.getIdCliente());
							ValidarLimiteCommand comandoLimite = (ValidarLimiteCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_VALIDAR_LIMITE, this.factory);
							respuestaPago.setRetorno(comandoLimite.validarLimiteCliente(transferencia));
							//String montoTrx = transferencia.getMonto();
							
							if(respuestaPago.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){							
								
								logger.debug("Se realiza transferencia de fondos.");				
								//TODO invocar CIO para inicio de Trx
								ConectorCIO cio = new ConectorCIO();
								
								String idTrxCio = null;
								try{
									logger.debug("Se inicia Trx de CIO.");
									idTrxCio = cio.iniciaTrx(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TIPO_TRX_CIO), 
															 ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.ID_CANAL_CIO), null);
									
									//TODO corregir post liberacion nueva TEF, para setear idCio con clave operacion.					
									String trxApp = transferencia.getClaveOperacion();
									transferencia.setClaveOperacion(idTrxCio);
									
									RespuestaVO respuesta = servicesWS.transferirMonto(transferencia);
									
									if(respuesta != null && respuesta.getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
										
										try{
											cio.avisoCierre(idTrxCio, "ACEPTADA");
											
											//TODO borrar una vez se use la claveOperacion para ir al CIO
											transferencia.setClaveOperacion(trxApp);
											
											logger.debug("Se realiza registro del pago de la trx [{}] en la BD.", pagoVO.getIdTrxPago());
											respuestaPago = comandoPago.registrarPago(transferencia, pagoVO.getIdTrxPago(), ConstantesMotorPagos.TRX_PAGADA, idTrxCio);
											
											if(respuestaPago.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
												logger.debug("Se procede a realizar actualizacion del limite de la cuenta del cliente [{}].", transferencia.getIdCliente());
												ActualizarLimiteCommand comando = (ActualizarLimiteCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_ACTUALIZAR_LIMITE, this.factory);
												RespuestaVO respActualizacion = comando.actualizarLimiteCliente(transferencia);
												
												if(respActualizacion.getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_NOOK)){
													logger.warn("Se produjo un error al intentar actualizar el monto acumulado dia del cliente [{}].", transferencia.getIdCliente());
												}
												
												if(transferencia.getMailCliente()!=null && transferencia.getMailCliente().length()>0){
													logger.debug("Se prodece a enviar el mail de notificacion al cliente [{}].", transferencia.getIdCliente());
													RespuestaVO resMail = servicesWS.sendMailCliente(transferencia);
													if(resMail.getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_NOOK)){
														logger.error("Se produjo un error al enviar el correo de notificacion al cliente [{}].", transferencia.getIdCliente());
													}		
												}else{
													logger.error("El cliente [{}] no tiene asociado un mail, no se envío mail de notificación.", transferencia.getIdCliente());
												}											
											}else{
												try{
													cio.avisoCierre(idTrxCio, "RECHAZO");
												}catch(Exception e){
													logger.error("Error, no fue posible cerrar la trx [{}] en CIO.", idTrxCio);									
												}
											}									
										}catch(Exception e){
											logger.error("Se produjo un error al cerrar trx con estado ACEPTADA, No fue posible registrar estado de la transaccion.");
											respuestaPago.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
										}
									}else{
										logger.debug("Se cierra Trx de CIO con rechazo.");
										try{
											cio.avisoCierre(idTrxCio, "RECHAZO");
										}catch(Exception e){
											logger.error("Error, al cerrar la trx [{}] en Cio con estado RECHAZO.", idTrxCio , e);
										}
										
										logger.debug("Se realiza registro del rechazo de la trx [{}] en la BD.", pagoVO.getIdTrxPago());
										respuestaPago = comandoPago.registrarPago(transferencia, pagoVO.getIdTrxPago(), ConstantesMotorPagos.TRX_RECHAZO, idTrxCio);
										respuestaPago.getRetorno().setCodigoRetorno(ConstantesMotorPagos.CODIGO_RETORNO_NOOK);
									}												
									
								}catch(Exception e){
									logger.error("Error, No fue posible iniciar transaccion.", e);
									respuestaPago.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
									logger.debug("Se realiza registro del rechazo de la trx [{}] en la BD.", pagoVO.getIdTrxPago());
									
									respuestaPago = comandoPago.registrarPago(transferencia, pagoVO.getIdTrxPago(), ConstantesMotorPagos.TRX_RECHAZO, idTrxCio);
									respuestaPago.getRetorno().setCodigoRetorno(ConstantesMotorPagos.CODIGO_RETORNO_NOOK);								
								}				
							}else{
								logger.debug("Se realiza registro del rechazo de la trx [{}] en la BD.", pagoVO.getIdTrxPago());
								comandoPago.registrarPago(transferencia, pagoVO.getIdTrxPago(), ConstantesMotorPagos.TRX_RECHAZO, pagoVO.getIdTrxPago());							
							}
						}else{
							logger.error("Error, El estado enviado [{}] no está permitido.", pagoVO.getEstadoPago());
							respuestaPago.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
						}				
					} catch (ClaveNoValidaException e) {
						logger.error("La validacion de Clave Dinamica no fue exitosa para la trx [{}].", pagoVO.getIdTrxPago());
						respuestaPago.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
					}
				}else{
					logger.error("No fue posible obtener la fecha contable.");
					respuestaPago.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
				}							
				
			}else{			
				respuestaPago.setRetorno(transferencia.getRetorno());
			}
		
					
		}else{
			respuestaPago.setRetorno(respValPin.getRetorno());
		}
				
		return respuestaPago;
	}
	
	/**
	 * 
	 * @param validarPagoVO
	 * @return
	 */
	public RespuestaValidaPagoVO validarPago(ValidarPagoVO validarPagoVO){
		RespuestaValidaPagoVO respuesta = new RespuestaValidaPagoVO();
		//Se valida el pin
		ValidarPinVO pinVO = MapperVO.getValidarPinVO(validarPagoVO.getIdDispositivo(), validarPagoVO.getPin());
		RespuestaValidaPinVO respValPin = this.validarPin(pinVO);
		
		if(respValPin.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			int reintentos=1;
			int maximoIntentos=Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.NRO_REINTENTOS_VALIDACION));
			while(reintentos<=maximoIntentos){
				respuesta = this.validarPago(validarPagoVO, reintentos);
				
				if(respuesta.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK) 
						 || respuesta.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_NOOK)
						 || respuesta.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_CANC)
						 || respuesta.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_RECH)
						 || respuesta.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_ABORT)){
						
					break;
				}else{
					try {
						Thread.sleep(Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.DELAY_REINTENTO_VALIDACION)));
					} catch (InterruptedException e) {
						logger.error("Se produjo un error al intentar pausar la consulta de estado transacción [{}].", validarPagoVO.getIdTrxPago());
					}					
				}
				reintentos++;
			}			
		}else{
			respuesta.setRetorno(respValPin.getRetorno());
		}
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param validarPagoVO
	 * @return
	 */
	public RespuestaValidaPagoVO validarPago(ValidarPagoVO validarPagoVO, int invocacionNro){
		RespuestaValidaPagoVO respuesta=null;
		
		logger.debug("Se procede a validar el pago de la transaccion [{}]. intento Nro[{}]", validarPagoVO.getIdTrxPago(), invocacionNro);
		ValidarPagoCommand comando = (ValidarPagoCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_VALIDAR_PAGO, this.factory);
		respuesta = comando.validarPago(validarPagoVO);
				
		return respuesta;
	}
	
	/**
	 * 
	 * @param rutCliente
	 * @return
	 */
	public RespuestaDispRegistradosVO listaDispositivosCliente(String rutCliente) {
		ListaDispClienteCommand comando = (ListaDispClienteCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_LISTA_DISP_CLIENTE, this.factory);
		logger.debug("Se procede a obtener el listado de dispositivos del cliente [{}].", rutCliente);
		return comando.listaDispositivosCliente(rutCliente);
	}
	
	/**
	 * 
	 * @param idConvenio
	 * @return
	 */
	public RespuestaDispRegistradosVO listaDispositivosConvenio(String idConvenio){
		ListaDispConvenioCommand comando = (ListaDispConvenioCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_LISTA_DISP_CONVENIO, this.factory);
		logger.debug("Se procede a obtener el listado de dispositivos del convenio [{}].", idConvenio);
		return comando.listaDispositivosConvenio(idConvenio);	
	}
	
	/**
	 * 
	 * @param rutCliente
	 * @return
	 */
	public RespuestaDispositivosSeguridadVO listaDispositivosSeguridad(ObtenerDispositivosSeguridadVO dispositivosSeguridadVO) {
		MotorPagosServicesWS services = new MotorPagosServicesWS();
		RespuestaDispositivosSeguridadVO respuesta = new RespuestaDispositivosSeguridadVO();
		
		logger.debug("Se procede a consultar los dispositivos de seguridad del cliente [{}].", dispositivosSeguridadVO.getRutCliente());
		
		if (this.isVersionWebValida(dispositivosSeguridadVO.getIdApp(), dispositivosSeguridadVO.getVersionApp())){
			
			LoginClienteVO loginVO = new LoginClienteVO();
			loginVO.setRut(dispositivosSeguridadVO.getRutCliente().toUpperCase());		
			
			try{
				respuesta.setDispositivos(services.obtenerDispositivosSeguridad(loginVO));
				
				if(respuesta.getDispositivos()==null || respuesta.getDispositivos().getDispSeguridad() == null || respuesta.getDispositivos().getDispSeguridad().size()==0){
					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Solicite su dispositivo de seguridad en sucursales."));
				}else{
					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Obtencion de dispositivos exitosa."));
				}
			}catch(MotorPagosException e){
				logger.error("Error, se produjo un error al obtener las coordenadas del dispositivo de seguridad del cliente [{}].", dispositivosSeguridadVO.getRutCliente());
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
			}						
		}else{
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_VERSION_OBSOLETA));
		}	
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param dispositivo
	 * @return
	 */
	public RespuestaVO cambiarEstadoDispositivo(CambioEstadoVO dispositivo) {
		RespuestaVO respuesta;
		
		if (this.isVersionWebValida(dispositivo.getIdApp(), dispositivo.getVersionApp())){
			
			MotorPagosServicesWS servicesWS = new MotorPagosServicesWS();
			ActualizarEstDispClienteCommand comandoDispCli = (ActualizarEstDispClienteCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_UPD_ESTADO_DIP_CLIENTE, this.factory);
			ActualizarEstDispConvenioCommand comandoDispConv = (ActualizarEstDispConvenioCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_UPD_ESTADO_DIP_CONVENIO, this.factory);
			
			if(dispositivo.getEstado().equals(ConstantesMotorPagos.ESTADO_ACTIVO_DISP) 
					|| dispositivo.getEstado().equals(ConstantesMotorPagos.ESTADO_DISP_ELIMINADO)){
				
				if(dispositivo.getTipoDispSeguridad()==null){
					logger.error("Error, No se enviaron dispositivos de seguridad.");
					respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
				}else{
					logger.debug("Se procede a validar el dispositivo de seguridad.");
					ClienteVO clienteVO = new ClienteVO();
					
					clienteVO.setTipoDispSeguridad(	dispositivo.getTipoDispSeguridad());
					
					if(dispositivo.getRutCliente()!=null && dispositivo.getRutCliente().length()>0){
						clienteVO.setRut(				dispositivo.getRutCliente());
					}else{
						clienteVO.setRut(				dispositivo.getRutApoderado());
					}
									
					clienteVO.setClaveSeguridad(	dispositivo.getClaveSeguridad());
					clienteVO.setCoordenada1(		dispositivo.getCoordenada1());
					clienteVO.setCoordenada2(		dispositivo.getCoordenada2());
					clienteVO.setCoordenada3(		dispositivo.getCoordenada3());
					
					respuesta = servicesWS.validarDispositivoSeguridad(MapperVO.getValidarDispositivoSegVO(clienteVO.getClaveSeguridad(), 
																											 clienteVO.getCoordenada1(),
																											 clienteVO.getCoordenada2(), 
																											 clienteVO.getCoordenada3(), 
																											 clienteVO.getRut(), 
																											 dispositivo.getRutEmpresa(),
																											 dispositivo.getSerieDispSeguridad(),
																											 clienteVO.getTipoDispSeguridad(), 
																											 clienteVO.getIdApp()));
					
					if(respuesta.getCodigoRetorno().equalsIgnoreCase(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
						logger.debug("Validacion exitosa, Se procede a cambiar el estado del dispositivo [{}].", dispositivo.getId());
						
						if(dispositivo.getRutCliente()!=null && dispositivo.getRutCliente().length()>0){
							respuesta = comandoDispCli.cambiarEstadoDispositivo(dispositivo);
						}else{
							respuesta = comandoDispConv.cambiarEstadoDispositivoConvenio(dispositivo);
						}
					}
				}
			}else{		
				logger.debug("Se procede a cambiar el estado del dispositivo [{}].", dispositivo.getId());
				
				if(dispositivo.getRutCliente()!=null && dispositivo.getRutCliente().length()>0){
					respuesta = comandoDispCli.cambiarEstadoDispositivo(dispositivo);
				}else{
					respuesta = comandoDispConv.cambiarEstadoDispositivoConvenio(dispositivo);
				}
			}
		}else{
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_VERSION_OBSOLETA);
		}
		return respuesta;
	}	
	
	/**
	 * 
	 * @param dispositivoVO
	 * @return
	 */
	public RespuestaVO desvincularDispositivoConvenio(DesvincularDispositivoVO dispositivoVO){
		RespuestaVO respuesta;
		
		ValidarPinVO pinVO = MapperVO.getValidarPinVO(dispositivoVO.getIdDispositivoAdmin(), dispositivoVO.getPinAdmin());
		RespuestaValidaPinVO respPin = this.validarPin(pinVO);
		
		if(respPin.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){	
			logger.debug("Se procede a desvincular el dispositivo [{}] del convenio [{}].", dispositivoVO.getIdDispositivo(), dispositivoVO.getIdConvenio());
			
			if(MotorPagosHelper.esDispAdmin(respPin)){
				DeleteDispConvenioCommand comando = (DeleteDispConvenioCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_DELETE_DISP_CONVENIO, this.factory);
				respuesta = comando.desvincularDispositivoConvenio(dispositivoVO);
			}else{
				logger.error("Error, El dispositivo [{}] no es de tipo Administrador.", dispositivoVO.getIdDispositivoAdmin());
				respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Dispositivo no es Administrador.");
			}
		}else{
			respuesta = respPin.getRetorno();
		}		
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param dispositivo
	 * @return
	 */
	public RespuestaVO validarDispositivoSeguridadCliente(ValidarDispositivoSegVO dispositivo) {
		MotorPagosServicesWS servicesWS = new MotorPagosServicesWS();
		logger.debug("Se procede a validar el dispositivo de seguridad del cliente [{}].", dispositivo.getRutCliente());
				
		RespuestaVO respuesta = servicesWS.validarDispositivoSeguridad(dispositivo);
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param idCliente
	 * @return
	 */
	public RespuestaMediosDePagoVO obtenerMediosDePagoCliente(ObtenerMediosDePagoVO obtenerVO) {
		
		MotorPagosServicesWS servicesWS = new MotorPagosServicesWS();
		RespuestaMediosDePagoVO respuesta = new RespuestaMediosDePagoVO();
		
		ValidarPinVO pinVO = MapperVO.getValidarPinVO(obtenerVO.getIdDispositivo(), obtenerVO.getPin());
		RespuestaValidaPinVO respPin = this.validarPin(pinVO);
				
		if(respPin.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			logger.debug("Se obtienen los limites, pre-definidos por el cliente [{}].", obtenerVO.getIdCliente());			
			CuentasClienteCommand ctaComando = (CuentasClienteCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_CUENTAS, this.factory);
			ArrayList<CuentaVO>	cuentasLimites = ctaComando.obtenerCuentasCliente(obtenerVO.getIdCliente());
			logger.debug("Se obtuvieron [{}] limites pre-establecidos por el cliente [{}].", cuentasLimites.size(), obtenerVO.getIdCliente());
			
			logger.debug("Se obtiene el rut del cliente [{}].", obtenerVO.getIdCliente());	
			RutClienteCommand rutComando = (RutClienteCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_RUT_CLIENTE, this.factory);
			String rutCliente = rutComando.obtenerRutCliente(obtenerVO.getIdCliente());		
								
			if(rutCliente==null){				
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
			}else{
				logger.debug("Se procede a obtener los productos del cliente [{}].", obtenerVO.getIdCliente());
				respuesta = servicesWS.obtenerMediosDePagoCliente(rutCliente);
				if(respuesta.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
					
					for(CuentaVO cuentaResp: respuesta.getCuentas().getCuentasCliente()){
						for(CuentaVO cuentaLim: cuentasLimites){
							if(cuentaResp.getLlaveCuenta().equals(cuentaLim.getLlaveCuenta())){
								cuentaResp.setLimiteCuenta(cuentaLim.getLimiteCuenta());
								cuentaResp.setUtilizadoCuenta(cuentaLim.getUtilizadoCuenta());
							}
						}
					}
					
					LoginClienteVO loginVO = new LoginClienteVO();
					loginVO.setRut(rutCliente);
					
					logger.debug("Se obtienen los dispositivos de seguridad del cliente [{}].", obtenerVO.getIdCliente());
					RespuestaLoginClienteVO respuestaDisp = this.obtenerDispositivosSeguridad(loginVO, servicesWS);
					if(respuestaDisp.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
						respuesta.setDispositivos(respuestaDisp.getDispositivos());
					}else{
						respuesta.setRetorno(respuestaDisp.getRetorno());
					}						
				}		
			}	
		}else{
			respuesta.setRetorno(respPin.getRetorno());
		}
			
		return respuesta;
	}
	
	/**
	 * 
	 * @param medioDePagoVO
	 * @return
	 */
	public RespuestaEnrolamientoVO actualizarMedioDePagoCliente(ActualizarMedioDePagoVO medioDePagoVO){
		RespuestaEnrolamientoVO respuesta = new RespuestaEnrolamientoVO();
		
		ValidarPinVO pinVO = MapperVO.getValidarPinVO(medioDePagoVO.getIdDispositivo(), medioDePagoVO.getPin());
		RespuestaValidaPinVO respPin = this.validarPin(pinVO);
		
		if(respPin.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			
			int limiteMaximoPermitido = Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.LIMITE_MAXIMO_TEF));
			if(medioDePagoVO.getLimiteCuenta() > limiteMaximoPermitido){
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "El máximo definido no puede superar el tope de $"+MotorPagosHelper.formatearMonto(String.valueOf(limiteMaximoPermitido))));
			}else{
				logger.debug("Se obtiene el rut del cliente [{}].", medioDePagoVO.getIdCliente());
				RutClienteCommand rutComando = (RutClienteCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_RUT_CLIENTE, this.factory);
				String rutCliente = rutComando.obtenerRutCliente(medioDePagoVO.getIdCliente());
				
				if(rutCliente==null){
					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));					
				}else{
					logger.debug("Se valida el dispositivo de seguridad del cliente [{}].", medioDePagoVO.getIdCliente());
					ValidarDispositivoSegVO dispositivo = MapperVO.getValidarDispositivoSegVO(medioDePagoVO.getClaveSeguridad(), 
																							  medioDePagoVO.getCoordenada1(), 
																							  medioDePagoVO.getCoordenada2(), 
																							  medioDePagoVO.getCoordenada3(), 
																							  rutCliente,
																							  null,
																							  medioDePagoVO.getSerieDispSeguridad(), 
																							  medioDePagoVO.getTipoDispSeguridad(),
																							  ConstantesMotorPagos.ID_APP_PAGOS);
					
					
					RespuestaVO respuestaDisp = this.validarDispositivoSeguridadCliente(dispositivo);
					
					if(respuestaDisp.getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
						logger.debug("Se procede a actualizar el medio de pago del cliente [{}].", medioDePagoVO.getIdCliente());
						ActualizarMedioPagoCommand comando = (ActualizarMedioPagoCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_UPD_MEDIO_PAGO, this.factory);
						respuesta = comando.actualizarMedioDePagoCliente(medioDePagoVO);
					}else{
						respuesta.setRetorno(respuestaDisp);
					}
				}
			}			
		}else{			
			respuesta.setRetorno(respPin.getRetorno());
		}
		return respuesta;
	}
	
	/**
	 * 
	 * @param reseteoVO
	 * @return
	 */
	public RespuestaVO resetearPin(ResearPinVO reseteoVO, ClienteVO cliente) {
		RespuestaVO respuesta;
		
		logger.debug("Se procede a obtener el rut asociado al dispositivo [{}] a resetear.", reseteoVO.getIdDispositivo());
		ObtenerRutDispositivoCommand comandoRut = (ObtenerRutDispositivoCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_RUT_DISP, this.factory);
		String datos[] = comandoRut.obtenerRutAsociadoDispositivo(reseteoVO.getIdDispositivo());
		
		
		if(datos==null){
			logger.error("Error, no se encontro al cliente en los registros.");
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}else{
			cliente.setRut(datos[0]);
			logger.debug("Se procede a resetear el pin del dispositivo [{}].", reseteoVO.getIdDispositivo());
			
			ValidarDispositivoSegVO dispositivo = MapperVO.getValidarDispositivoSegVO(reseteoVO.getClaveSeguridad(),
																					  reseteoVO.getCoordenada1(),
																					  reseteoVO.getCoordenada2(),
																					  reseteoVO.getCoordenada3(),
																					  datos[0], 
																					  datos[1],
																					  reseteoVO.getSerieDispSeguridad(),
																					  reseteoVO.getTipoDispSeguridad(),
																					  datos[2]);
			
			respuesta = this.validarDispositivoSeguridadCliente(dispositivo);
			
			if(respuesta.getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
				ResetearPinCommand comandoReset = (ResetearPinCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_RESET_PIN, this.factory);
				respuesta = comandoReset.reseteoPinDispositivoCliente(reseteoVO);
			}			
		}
		return respuesta;
	}
	
	/**
	 * 
	 * @param preCobroVO
	 * @return
	 */
	public RespuestaPreCobroVO validarPreCobro(ObtenerPreCobroVO preCobroVO){
		RespuestaPreCobroVO respuesta = new RespuestaPreCobroVO();
		ValidarPinVO pinVO = MapperVO.getValidarPinVO(preCobroVO.getIdDispositivo(), preCobroVO.getPin());
		RespuestaValidaPinVO respPin = this.validarPin(pinVO);
		
		if(respPin.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			int reintentos=1;
			int maximoIntentos=Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.NRO_REINTENTOS_VALIDACION));
			while(reintentos<=maximoIntentos){
				respuesta = this.validarPreCobro(preCobroVO.getIdTrxPago(), reintentos);
				
				if(respuesta.getRetorno().getCodigoRetorno().equalsIgnoreCase(ConstantesMotorPagos.CODIGO_RETORNO_OK) || 
						respuesta.getRetorno().getCodigoRetorno().equalsIgnoreCase(ConstantesMotorPagos.CODIGO_RETORNO_NOOK)){
					break;
				}else{
					try {
						Thread.sleep(Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.DELAY_REINTENTO_VALIDACION)));
					} catch (InterruptedException e) {
						logger.error("Se produjo un error al intentar pausar la consulta el registro del precobro de la trx [{}].", preCobroVO.getIdTrxPago());
					}					
				}
				reintentos++;
			}
			
		}else{
			respuesta.setRetorno(respPin.getRetorno());
		}
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param idTrxPago
	 * @param invocacionNro
	 * @return
	 */
	public RespuestaPreCobroVO validarPreCobro(String idTrxPago, int invocacionNro) {
		RespuestaPreCobroVO respuesta=null;
		
		logger.debug("Se procede a validar el registro del prepago de la trx[{}]. intento Nro[{}].", idTrxPago, invocacionNro);
		ObtienePreCobroCommand comando = (ObtienePreCobroCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_PRE_COBRO, this.factory);
		respuesta = comando.validarPreCobro(idTrxPago);
				
		return respuesta;
	}
	
	/**
	 * 
	 * @param rutEmpresa
	 * @param rutApoderado
	 * @return
	 */
	public RespuestaConveniosVO obtieneConveniosComercio(ObtenerConveniosComercioVO conveniosComercioVO) {
		RespuestaConveniosVO respuesta;
		
		if (this.isVersionWebValida(conveniosComercioVO.getIdApp(), conveniosComercioVO.getVersionApp())){
			
			logger.debug("Se procede a obtener los convenios de la empresa [{}] apoderado [{}].", conveniosComercioVO.getRutEmpresa(), conveniosComercioVO.getRutApoderado());
			ObtenerConveniosCommand comando = (ObtenerConveniosCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_CONVENIOS, this.factory);
		
			respuesta = comando.obtieneConveniosComercio(conveniosComercioVO.getRutEmpresa().toUpperCase(), conveniosComercioVO.getRutApoderado().toUpperCase());
		}else{
			respuesta = new RespuestaConveniosVO();
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_VERSION_OBSOLETA));
		}
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param consultaVO
	 * @return
	 */
	public RespuestaListadoMovimientosVO obtieneMovimientosConvenioComercio(ConsultaListadoMovVO consultaVO){
		logger.debug("Se procede a obtener los movimientos asociados al convenio [{}].", consultaVO.getIdConvenio());
		
		RespuestaListadoMovimientosVO respuesta;
		
		if (this.isVersionWebValida(consultaVO.getIdApp(), consultaVO.getVersionApp())){
			if(consultaVO.getIdConvenio()!=null && consultaVO.getIdConvenio().length() > 0){
				ObtenerMovsConvenioCommand comando = (ObtenerMovsConvenioCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_MOV_CONV, this.factory);
				respuesta = comando.obtieneMovimientosConvenioComercio(consultaVO);
			}else{
				ObtenerMovsComercioCommand comando = (ObtenerMovsComercioCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_MOV_COM, this.factory);
				respuesta = comando.obtieneMovimientosComercio(consultaVO);
			}
		}else{
			respuesta = new RespuestaListadoMovimientosVO();
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_VERSION_OBSOLETA));
		}	
		return respuesta;
	}
	
	/**
	 * 
	 * @param consultaVO
	 * @return
	 */
	public RespuestaListadoMovimientosVO obtieneMovimientosCliente(ConsultaListadoMovClienteVO consultaVO){
		RespuestaListadoMovimientosVO respuesta;
		if (this.isVersionWebValida(consultaVO.getIdApp(), consultaVO.getVersionApp())){
			logger.debug("Se procede a obtener los movimientos asociados al cliente [{}].", consultaVO.getRutCliente());
			ObtenerMovsClienteCommand comando = (ObtenerMovsClienteCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_MOV_CLI, this.factory);
			
			respuesta = comando.obtieneMovimientosCliente(consultaVO);
		}else{
			respuesta = new RespuestaListadoMovimientosVO();
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_VERSION_OBSOLETA));
		}
		return respuesta;
	}
	
	/**
	 * 
	 * @param consultaVO
	 * @return
	 */
	public RespuestaConsultaSaldoVO obtenerSaldoCuentaCliente(ConsultaSaldoVO consultaVO, RespuestaConsultaCuentaVO respCuenta){
		RespuestaConsultaSaldoVO respuesta = new RespuestaConsultaSaldoVO();
		ValidarPinVO pinVO = MapperVO.getValidarPinVO(consultaVO.getIdDispositivo(), consultaVO.getPin());
		RespuestaValidaPinVO respPin = this.validarPin(pinVO);
				
		if(respPin.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			logger.debug("Se procede a obtener el numero de cuenta desde los registros [{}].", consultaVO.getIdCuenta());
			MotorPagosServicesWS servicesWS = new MotorPagosServicesWS();
			
			String idCuenta = consultaVO.getIdCuenta();
			NumeroCuentaCommand comando = (NumeroCuentaCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_NRO_CUENTA, this.factory);
			respCuenta = comando.obtenerNumeroCuenta(consultaVO, respCuenta);
			
			if(respCuenta.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
				respuesta = servicesWS.consultaSaldoCta(respCuenta.getNroCuenta());			
			}else{
				respuesta.setRetorno(respCuenta.getRetorno());
			}
			
			respuesta.setIdCuenta(idCuenta);
		}else{
			respuesta.setRetorno(respPin.getRetorno());
		}
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param consultaVO
	 * @return
	 */
	public RespuestaMarcaClienteVO obtenerMarcaCliente(ConsultaMarcaClienteVO consultaVO){
		RespuestaMarcaClienteVO respuesta = new RespuestaMarcaClienteVO();
		ValidarPinVO pinVO = MapperVO.getValidarPinVO(consultaVO.getIdDispositivo(), consultaVO.getPin());
		RespuestaValidaPinVO respPin = this.validarPin(pinVO);
				
		if(respPin.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			logger.debug("Se procede a obtener el rut asociado al dispositivo [{}] para obtener marca cliente.", consultaVO.getIdDispositivo());
			ObtenerRutDispositivoCommand comandoRut = (ObtenerRutDispositivoCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_RUT_DISP, this.factory);
			String datos[] = comandoRut.obtenerRutAsociadoDispositivo(consultaVO.getIdDispositivo());
			if(datos==null){
				logger.error("Error, no se encontro al cliente en los registros.");
				RespuestaVO respRut = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
				respuesta.setRetorno(respRut);
				return respuesta;
			}
			
			String rutCliente = datos[0];
			logger.debug("Se procede a obtener la marca del cliente [{}].", rutCliente);
			MotorPagosServicesWS servicesWS = new MotorPagosServicesWS();
			
			RespuestaFichaChicaVO respMarca = servicesWS.consultaFichaChicaCliente(rutCliente);
			
			ActualizarPersonaCommand comandoActualizar = (ActualizarPersonaCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_ACTUALIZAR_PERSONA, this.factory);
			RespuestaVO respuestaActualizar = comandoActualizar.actializarMarcaCliente(consultaVO.getIdDispositivo(),respMarca.getCodigoMarca());
			
			if (respuestaActualizar.getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
				respuesta.setRetorno(respMarca.getRetorno());
			
				if(respMarca.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
					respuesta.setCodigoMarca(respMarca.getCodigoMarca());
				}
			} else {
				respuesta.setRetorno(respuestaActualizar);
			}
			
		}else{
			respuesta.setRetorno(respPin.getRetorno());
		}
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param idDispositivo
	 * @param tipoConsulta 
	 * @return
	 */
	public RespuestaListadoMovimientosVO obtieneMovimientosRecientes(ObtenerMovRecientesVO obtenerVO, String tipoConsulta){
		RespuestaListadoMovimientosVO respuesta = new RespuestaListadoMovimientosVO();
		ValidarPinVO pinVO = MapperVO.getValidarPinVO(obtenerVO.getIdDispositivo(), obtenerVO.getPin());
		RespuestaValidaPinVO respPin = this.validarPin(pinVO);
				
		if(respPin.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			logger.debug("Se procede a obtener los movimientos recientes del dispositivo [{}].", obtenerVO.getIdDispositivo());
			ObtieneMovsRecientesCommand comando = (ObtieneMovsRecientesCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_MOVIMIENTOS_RECIENTES, this.factory);
			
			respuesta = comando.obtieneMovimientosRecientes(obtenerVO.getIdDispositivo(), tipoConsulta);
		}else{
			respuesta.setRetorno(respPin.getRetorno());
		}
		
		return respuesta;	
	}
	
	/**
	 * 
	 * @param cambioVO
	 * @return
	 */
	public RespuestaVO cambiarPin(CambioPinVO cambioVO, ClienteVO clienteVO){
		RespuestaVO respuesta = null;		
		
		ValidarPinVO pinVO = MapperVO.getValidarPinVO(cambioVO.getIdDispositivo(), cambioVO.getPinActual());
		RespuestaValidaPinVO resp = this.validarPin(pinVO);
		
		if(resp.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			
			logger.debug("Se procede a obtener el rut asociado al dispositivo [{}].", cambioVO.getIdDispositivo());
			ObtenerRutDispositivoCommand comandoRut = (ObtenerRutDispositivoCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_RUT_DISP, this.factory);
			String datos[] = comandoRut.obtenerRutAsociadoDispositivo(cambioVO.getIdDispositivo());
			
			if(datos!=null)
			clienteVO.setRut(datos[0]);
			
			logger.debug("Se procede a realizar el cambio de pin del dispositivo [{}].", cambioVO.getIdDispositivo());
			CambiarPinCommand comando = (CambiarPinCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_CAMBIAR_PIN, this.factory);
			respuesta = comando.cambiarPinDispositivo(cambioVO);
		}else{
			respuesta = resp.getRetorno();
		}
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	private RespuestaLoginClienteVO obtenerDispositivosSeguridad(LoginClienteVO loginVO, MotorPagosServicesWS services){
		RespuestaLoginClienteVO respuesta = new RespuestaLoginClienteVO();
		
		logger.debug("Se procede a obtener los dispositivos de seguridad del cliente [{}].", loginVO.getRut());
		try{
			respuesta.setDispositivos(services.obtenerDispositivosSeguridad(loginVO));
			
			if(respuesta.getDispositivos()==null || respuesta.getDispositivos().getDispSeguridad() == null || respuesta.getDispositivos().getDispSeguridad().size()==0){
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Solicite su dispositivo de seguridad en sucursales."));
			}else{
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Obtencion Exitosa."));
			}
		}catch(MotorPagosException e){
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
		}
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param cierreDiario
	 * @return
	 */
	public RespuestaCierreDiarioVO cierreDiarioDispositivo(CierreDiarioVO cierreDiario){
		RespuestaCierreDiarioVO respuesta = new RespuestaCierreDiarioVO();
		
		ValidarPinVO pinVO = MapperVO.getValidarPinVO(cierreDiario.getIdDispositivo(), cierreDiario.getPin());
		RespuestaValidaPinVO respPin = this.validarPin(pinVO);
		
		if(respPin.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			logger.debug("Se genera el totalizado para cierre de dispositivo de venta [{}].", cierreDiario.getIdDispositivo());	
			CierreDispCommand comando = (CierreDispCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_CIERRE_DISPOSITIVO, this.factory);
			respuesta = comando.cierreDiarioDispositivo(cierreDiario);
		}else{
			respuesta.setRetorno(respPin.getRetorno());
		}
		return respuesta;		
	}
	
	/**
	 * 
	 * @param idCliente
	 * @param imagenBase64
	 * @return
	 */
	public RespuestaVO createAvatar(AvatarComercioVO avatarVO) {
		RespuestaVO respuesta = new RespuestaVO();
		
		ValidarPinVO pinVO = MapperVO.getValidarPinVO(avatarVO.getIdDispositivo(), avatarVO.getPin());
		RespuestaValidaPinVO respPin = this.validarPin(pinVO);
		
		if(respPin.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			if(MotorPagosHelper.esDispAdmin(respPin) || MotorPagosHelper.esDispCli(respPin)){
				logger.debug("Se almacenará el avatar del convenio [{}].", avatarVO.getIdConvenio());
				
				CreateAvatarCommand comando = (CreateAvatarCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_CREATE_AVATAR, this.factory);
				respuesta =  comando.saveAvatar(avatarVO);
				
			}else{
				logger.error("Error, El dispositivo [{}] no es de tipo Administrador.", avatarVO.getIdDispositivo());
				respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Dispositivo no es Administrador.");
			}
		}else{
			respuesta = respPin.getRetorno();
		}

		return respuesta;
	}
	
	/**
	 * 
	 * @param idConvenio
	 * @param imagenBase64
	 * @return
	 */
	public String getAvatar(String idAvatar) {
		String respuesta = null;
		logger.debug("Se obtiene el avatar [{}].", idAvatar);
			
		ObtenerAvatarCommand comando = (ObtenerAvatarCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_AVATAR, this.factory);
		respuesta = comando.obtenerAvatar(idAvatar);
		
		return respuesta;
	}
	
	/**
	 * 
	 * @param cancelaVO
	 * @return
	 */
	public RespuestaVO cancelarCobro(CancelaCobroVO cancelaVO){
		RespuestaVO respuesta = new RespuestaVO();
		
		ValidarPinVO pinVO = MapperVO.getValidarPinVO(cancelaVO.getIdDispositivo(), cancelaVO.getPin());
		RespuestaValidaPinVO respPin = this.validarPin(pinVO);
		
		if(respPin.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			logger.debug("Se procede a cancelar el cobro de la transaccion [{}].", cancelaVO.getIdTrx());	
			CancelarCobroCommand comando = (CancelarCobroCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_CANCELAR_COBRO, this.factory);
			respuesta = comando.cancelarCobro(cancelaVO);
		}else{
			respuesta = respPin.getRetorno();
		}
		return respuesta;		
	}	
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<VersionVO> getVersionesApps(){
		ObtenerVersionesCommand comando = (ObtenerVersionesCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_VERSIONES, this.factory);
		
		return comando.getVersionesApps();
	}
	
	/**
	    * 
	    * @param idApp
	    * @param version
	    * @param tipo
	    * @return
	    */
		public boolean isVersionWebValida(String idApp, String version){
			if("WEBMOVIL".equals(idApp)){
				return true;
			}else{
				return false;
			}
		}	
	/**
    * 
    * @param idApp
    * @param version
    * @param tipo
    * @return
    */
	public boolean isVersionValida(String idApp, String version){
		VersionsLoader versions = VersionsLoader.getInstance();
		return versions.validaVersion(idApp, version);
	}	
	
	/**
	 * 
	 * @param idCliente
	 * @return
	 */
	public String obtenerRutCliente(String idCliente){
		RutClienteCommand rutComando = (RutClienteCommand)FactoryCommand.getCommand(FactoryCommand.COMMAND_OBTENER_RUT_CLIENTE, this.factory);
		
		return rutComando.obtenerRutCliente(idCliente);
	}
	
	/**
	 * 
	 * @param idCliente
	 * @return
	 */
	public RespuestaMediosDePagoVO obtenerCuentasFilial(ObtenerCuentasFilialVO obtenerVO) {
		
		MotorPagosServicesWS servicesWS = new MotorPagosServicesWS();
		MotorPagosServicesSRM servicesSRM = new MotorPagosServicesSRM();
		
		RespuestaMediosDePagoVO respuesta = new RespuestaMediosDePagoVO();
		
		//Se crea objeto para validar datos.
		LoginComercioVO loginComercioVO = new LoginComercioVO();
		loginComercioVO.setClave(		obtenerVO.getClave());
		loginComercioVO.setIdApp(		obtenerVO.getIdApp());
		loginComercioVO.setRutEmpresa(	obtenerVO.getRutEmpresa());
		loginComercioVO.setRutPersona(	obtenerVO.getRutPersona());
		loginComercioVO.setVersionApp(	obtenerVO.getVersionApp());
		
		RespuestaLoginComercioVO respuestaLogin = servicesSRM.callSDAF(loginComercioVO);
		
		if(respuestaLogin.getRetorno().getCodigoRetorno().equalsIgnoreCase(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
			logger.debug("Se procede a obtener los productos de la empresa [{}].", obtenerVO.getRut());
			respuesta = servicesWS.obtenerMediosDePagoCliente(obtenerVO.getRut());
			
			if(respuesta.getRetorno().getCodigoRetorno().equals(ConstantesMotorPagos.CODIGO_RETORNO_OK)){
				if(respuesta.getCuentas()==null || respuesta.getCuentas().getCuentasCliente()==null || respuesta.getCuentas().getCuentasCliente().size()==0){
					respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Debe solicitar cuenta para operar con sistema."));
				}			
			}
		}else{
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
		}		
					
		return respuesta;
	}
}
