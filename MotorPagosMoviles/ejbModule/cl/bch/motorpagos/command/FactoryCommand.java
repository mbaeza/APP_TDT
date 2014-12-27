/**
 * 
 */
package cl.bch.motorpagos.command;

import javax.persistence.EntityManagerFactory;

/**
 * @author boyanedel
 *
 */
public class FactoryCommand {
	public static final String COMMAND_ENROLAMIENTO_PERSONA = "EnrolarPersona";
	public static final String COMMAND_OBTENER_CUENTAS = "CuentasCliente";
	public static final String COMMAND_OBTENER_RUT_CLIENTE = "GetRutCliente";
	public static final String COMMAND_OBTENER_NRO_CUENTA = "GetNroCuenta";
	public static final String COMMAND_VALIDAR_LIMITE = "ValidarLimite";
	public static final String COMMAND_ACTUALIZAR_LIMITE = "ActualizarLimite";
	public static final String COMMAND_UPD_ESTADO_DIP_CLIENTE = "UpdEstadoDispCliente";
	public static final String COMMAND_UPD_MEDIO_PAGO = "UpdMedioPago";
	public static final String COMMAND_OBTENER_MOV_CLI = "GetMovsCliente";
	public static final String COMMAND_ACTUALIZAR_PERSONA = "ActualizarPersona";
	
	public static final String COMMAND_ENROLAMIENTO_COMERCIO = "EnrolarComercio";
	public static final String COMMAND_ENROLAMIENTO_VENDEDOR = "EnrolarVendedor";
	public static final String COMMAND_PRE_COBRO = "PreCobro";
	public static final String COMMAND_UPD_ESTADO_DIP_CONVENIO = "UpdEstadoDispConvenio";
	public static final String COMMAND_DELETE_DISP_CONVENIO = "DeleteDispConvenio";
	public static final String COMMAND_OBTENER_CONVENIOS = "GetConvenios";
	public static final String COMMAND_OBTENER_MOV_CONV = "GetMovsConvenio";
	public static final String COMMAND_OBTENER_MOV_COM = "GetMovsComercio";
	public static final String COMMAND_CIERRE_DISPOSITIVO = "CierreDispositivo";
	
	public static final String COMMAND_VALIDAR_PIN = "ValidarPin";
	public static final String COMMAND_RESET_PIN = "ResetPin";
	public static final String COMMAND_CAMBIAR_PIN = "UpdatePin";
	public static final String COMMAND_OBTENER_RUT_DISP = "GetRutDispositivo";
	public static final String COMMAND_OBTENER_DATOS_PAGO = "GetDatosPago";
	public static final String COMMAND_REGISTRA_PAGO = "RegistraPago";
	public static final String COMMAND_VALIDAR_PAGO = "ValidarPago";
	public static final String COMMAND_OBTENER_PRE_COBRO = "GetPreCobro";
	public static final String COMMAND_MOVIMIENTOS_RECIENTES = "GetMovsRecientes";
	public static final String COMMAND_CREATE_AVATAR = "CreateAvatar";
	public static final String COMMAND_OBTENER_AVATAR = "ObtenerAvatar";
	public static final String COMMAND_CANCELAR_COBRO = "CancelarCobro";
	public static final String COMMAND_OBTENER_VERSIONES = "ObtenerVersiones";
	
	public static final String COMMAND_LISTA_DISP_CLIENTE = "ListaDispositivosCliente";
	public static final String COMMAND_LISTA_DISP_CONVENIO = "ListaDispositivosConvenio";
	
	public static final String COMMAND_LOGIN_ADM_MESA = "LoginAdministracionMesa";
	
	/**
	 * 
	 * @param idCommand
	 * @param factory
	 * @return
	 */
	public static IServiceCommand getCommand(String idCommand, EntityManagerFactory factory){
		IServiceCommand comando;
		
		switch (idCommand) {
		case FactoryCommand.COMMAND_ENROLAMIENTO_PERSONA:
			comando = new EnrolarPersonaCommand();
			break;
		case FactoryCommand.COMMAND_OBTENER_CUENTAS:
			comando = new CuentasClienteCommand();
			break;
		case FactoryCommand.COMMAND_OBTENER_RUT_CLIENTE:
			comando = new RutClienteCommand();
			break;
		case FactoryCommand.COMMAND_OBTENER_NRO_CUENTA:
			comando = new NumeroCuentaCommand();
			break;
		case FactoryCommand.COMMAND_VALIDAR_LIMITE:
			comando = new ValidarLimiteCommand();
			break;
		case FactoryCommand.COMMAND_ACTUALIZAR_LIMITE:
			comando = new ActualizarLimiteCommand();
			break;
		case FactoryCommand.COMMAND_UPD_ESTADO_DIP_CLIENTE:
			comando = new ActualizarEstDispClienteCommand();
			break;
		case FactoryCommand.COMMAND_UPD_MEDIO_PAGO:
			comando = new ActualizarMedioPagoCommand();
			break;
		case FactoryCommand.COMMAND_OBTENER_MOV_CLI:
			comando = new ObtenerMovsClienteCommand();
			break;
		case FactoryCommand.COMMAND_ENROLAMIENTO_COMERCIO:
			comando = new EnrolarComercioCommand();
			break;
		case FactoryCommand.COMMAND_ENROLAMIENTO_VENDEDOR:
			comando = new EnrolarVendedorCommand();
			break;
		case FactoryCommand.COMMAND_PRE_COBRO:
			comando = new PreCobroCommand();
			break;
		case FactoryCommand.COMMAND_UPD_ESTADO_DIP_CONVENIO:
			comando = new ActualizarEstDispConvenioCommand();
			break;
		case FactoryCommand.COMMAND_DELETE_DISP_CONVENIO:
			comando = new DeleteDispConvenioCommand();
			break;
		case FactoryCommand.COMMAND_OBTENER_CONVENIOS:
			comando = new ObtenerConveniosCommand();
			break;
		case FactoryCommand.COMMAND_OBTENER_MOV_CONV:
			comando = new ObtenerMovsConvenioCommand();
			break;
		case FactoryCommand.COMMAND_OBTENER_MOV_COM:
			comando = new ObtenerMovsComercioCommand();
			break;
		case FactoryCommand.COMMAND_CIERRE_DISPOSITIVO:
			comando = new CierreDispCommand();
			break;
		case FactoryCommand.COMMAND_VALIDAR_PIN:
			comando = new ValidarPinCommand();
			break;
		case FactoryCommand.COMMAND_RESET_PIN:
			comando = new ResetearPinCommand();
			break;
		case FactoryCommand.COMMAND_CAMBIAR_PIN:
			comando = new CambiarPinCommand();
			break;
		case FactoryCommand.COMMAND_OBTENER_RUT_DISP:
			comando = new ObtenerRutDispositivoCommand();
			break;
		case FactoryCommand.COMMAND_OBTENER_DATOS_PAGO:
			comando = new ObtenerDatosPagoCommand();
			break;
		case FactoryCommand.COMMAND_REGISTRA_PAGO:
			comando = new RegistrarPagoCommand();
			break;
		case FactoryCommand.COMMAND_VALIDAR_PAGO:
			comando = new ValidarPagoCommand();
			break;
		case FactoryCommand.COMMAND_OBTENER_PRE_COBRO:
			comando = new ObtienePreCobroCommand();
			break;
		case FactoryCommand.COMMAND_MOVIMIENTOS_RECIENTES:
			comando = new ObtieneMovsRecientesCommand();
			break;
		case FactoryCommand.COMMAND_CREATE_AVATAR:
			comando = new CreateAvatarCommand();
			break;
		case FactoryCommand.COMMAND_OBTENER_AVATAR:
			comando = new ObtenerAvatarCommand();
			break;
		case FactoryCommand.COMMAND_CANCELAR_COBRO:
			comando = new CancelarCobroCommand();
			break;
		case FactoryCommand.COMMAND_OBTENER_VERSIONES:
			comando = new ObtenerVersionesCommand();
			break;
		case FactoryCommand.COMMAND_LISTA_DISP_CLIENTE:
			comando = new ListaDispClienteCommand();
			break;
		case FactoryCommand.COMMAND_LISTA_DISP_CONVENIO:
			comando = new ListaDispConvenioCommand();
			break;
		case FactoryCommand.COMMAND_LOGIN_ADM_MESA:
			comando = new LoginAdmWebCommand();
			break;
		case FactoryCommand.COMMAND_ACTUALIZAR_PERSONA:
			comando = new ActualizarPersonaCommand();
			break;
		default:
			comando = null;
			break;
		}
		if(comando!=null)
			comando.setEntityManagerFactory(factory);
		
		return comando;
	}

}
