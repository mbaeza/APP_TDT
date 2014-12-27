/**
 * 
 */
package cl.bch.motorpagos.wsclient;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.ejb.ClienteBean;
import cl.bch.motorpagos.security.CryptData;
import cl.bch.motorpagos.security.GenBCH;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.CuentaVO;
import cl.bch.motorpagos.vo.CuentasClienteVO;
import cl.bch.motorpagos.vo.LoginClienteVO;
import cl.bch.motorpagos.vo.RespuestaLoginClienteVO;

/**
 * @author boyanedel
 *
 */
public class ClienteCS000152 extends GeneralClient {
	private static final Logger logger = LoggerFactory.getLogger(ClienteCS000176_getDispositivos.class);
	private static final String END_POINT = "http://"+ GeneralClient.host +":"+ GeneralClient.port +"/RenovacionInternet/CS000152_ObtenerDatosValidarClave";
	private static final String RESPONSE_XML = "http://osb.bancochile.cl/ESB/RenovacionInternet/ObtenerDatosValidarClave/OpObtenerResponse";
	private static final String REQUEST_HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:obt=\"http://osb.bancochile.cl/RenovacionInternet/ObtenerDatosValidarClave/\" xmlns:head=\"http://osb.bancochile.cl/common/HeaderRequest\" xmlns:opob=\"http://osb.bancochile.cl/ESB/RenovacionInternet/ObtenerDatosValidarClave/OpObtenerRequest\">";

	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public RespuestaLoginClienteVO callCS000152(LoginClienteVO loginVO){
		RespuestaLoginClienteVO respuesta = new RespuestaLoginClienteVO();
		
		String request = this.generaRequest(loginVO);
		if(request==null){
			logger.error("Error, al intentar armar el request para el servicio CS000152.");
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
		}else{
			logger.debug("*******REQUEST*******");
			logger.debug(request);
			logger.debug("*******REQUEST*******");
			
			try {
				SOAPMessage response = this.callWS(request, END_POINT);
				
				logger.debug("*******RESPONSE*******");
				logger.debug(getSOAPMessageAsString(response));
			    logger.debug("*******RESPONSE*******");
			    
			    respuesta = (RespuestaLoginClienteVO)this.parseResponse(response);
				
			} catch (Exception e) {
				logger.error("Error, No fue posible invocar el servicio CS000152.", e);	
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
			}
		}
		return respuesta;
	}

	@Override
	protected String generaRequest(Object requestVO) {
		StringBuffer bf = new StringBuffer(700);
		LoginClienteVO loginVO = (LoginClienteVO)requestVO;
		
		bf.append(GeneralClient.ENCODE_HEADER + REQUEST_HEADER);
		bf.append("<soapenv:Header><obt:headerRequest><head:consumidor><head:idApp>");
		bf.append(ConstantesMotorPagos.ID_APP);
		bf.append("</head:idApp><head:usuario>");
		bf.append(MotorPagosHelper.cleanRut(loginVO.getRut()));
		bf.append("</head:usuario></head:consumidor><head:transaccion><head:internalCode>");
		bf.append(ConstantesMotorPagos.INTERNAL_CODE);
		bf.append("</head:internalCode><head:idTransaccionNegocio>");
		bf.append(ConstantesMotorPagos.ID_USUARIO);
		bf.append(MotorPagosHelper.cleanRut(loginVO.getRut()));
		bf.append("</head:idTransaccionNegocio><head:fechaHora>");
		bf.append(MotorPagosHelper.getFechaActual());
		bf.append("</head:fechaHora><head:canal>");
		bf.append(ConstantesMotorPagos.ID_CANAL);
		bf.append("</head:canal><head:sucursal>");
		bf.append(ConstantesMotorPagos.ID_SUCURSAL);
		bf.append("</head:sucursal></head:transaccion></obt:headerRequest></soapenv:Header><soapenv:Body><obt:ObtenerRequest><reqObtener><opob:Cuerpo><opob:rut>");
		bf.append(loginVO.getRut());
		bf.append("</opob:rut><opob:clave>");
		try {
			bf.append(GenBCH.genClave(loginVO.getClave()));
		} catch (GeneralSecurityException e) {
			logger.error("Se produjo un error al intentar encriptar la clave del cliente.", e);
			return null;
		}
		bf.append("</opob:clave><opob:listaProductos>");
		
		for(int i=0; i < ConstantesMotorPagos.PRODUCTOS_PERMITIDOS.length; i++){
			bf.append("<opob:productos><opob:codigoProducto>");
			bf.append(ConstantesMotorPagos.PRODUCTOS_PERMITIDOS[i]);
			bf.append("</opob:codigoProducto></opob:productos>");
		}		
	            
	    bf.append("</opob:listaProductos></opob:Cuerpo></reqObtener></obt:ObtenerRequest></soapenv:Body></soapenv:Envelope>");
	    
		return bf.toString();
	}
	
	@Override
	protected RespuestaLoginClienteVO parseResponse(SOAPMessage response) {
		RespuestaLoginClienteVO respuesta = new RespuestaLoginClienteVO();

		SOAPEnvelope env;
		try {
			env = response.getSOAPPart().getEnvelope();
			SOAPBody sb = env.getBody();
			
			//Datos que se obtienen desde el cuerpo
			QName retorno = new QName(RESPONSE_XML, "codigoRetorno");
			
			QName nombre = new QName(RESPONSE_XML, "nombre");
			QName apellidoPaterno = new QName(RESPONSE_XML, "apellidoPaterno");
			QName codigoMarca = new QName(RESPONSE_XML, "codigoMarca");
			
			QName direccion = new QName(RESPONSE_XML, "direccion");
			QName numeroDireccion = new QName(RESPONSE_XML, "numeroDireccion");
			QName complementoDireccion = new QName(RESPONSE_XML, "complementoDireccion");
			QName comuna = new QName(RESPONSE_XML, "comuna");
			QName ciudad = new QName(RESPONSE_XML, "ciudad");
			
			QName EmailParticularCliente = new QName(RESPONSE_XML, "EmailParticularCliente");
			
			QName listaProductos = new QName(RESPONSE_XML, "listaProductos");
			
			QName numProducto = new QName(RESPONSE_XML, "numProducto");
			QName codigoProducto = new QName(RESPONSE_XML, "codigoProducto");
			QName claseCuenta = new QName(RESPONSE_XML, "claseCuenta");
			QName estado = new QName(RESPONSE_XML, "estado");
						
			Iterator it = sb.getChildElements(); //ObtenerResponse	*****VER RESPUESTA EN SOAPUI******	    
			SOAPBodyElement sbe = (SOAPBodyElement) it.next();

			it=sbe.getChildElements(); //respObtener
			sbe=(SOAPBodyElement) it.next();

			it=sbe.getChildElements(); //Cuerpo
			sbe=(SOAPBodyElement) it.next();

			it=sbe.getChildElements(retorno);
			SOAPBodyElement itemRetorno = (SOAPBodyElement) it.next();
			
			if("00".equals(itemRetorno.getValue())){
				it=sbe.getChildElements(nombre);
				SOAPBodyElement nom = (SOAPBodyElement) it.next();
				
				it=sbe.getChildElements(apellidoPaterno);
				SOAPBodyElement apePat = (SOAPBodyElement) it.next();
				
				it=sbe.getChildElements(codigoMarca);
				SOAPBodyElement beCodigoMarca = (SOAPBodyElement) it.next();

				it=sbe.getChildElements(direccion);
				SOAPBodyElement dir1 = (SOAPBodyElement) it.next();
				
				it=sbe.getChildElements(numeroDireccion);
				SOAPBodyElement dir2 = (SOAPBodyElement) it.next();
				
				it=sbe.getChildElements(complementoDireccion);
				SOAPBodyElement dir3 = (SOAPBodyElement) it.next();
				
				it=sbe.getChildElements(comuna);
				SOAPBodyElement dir4 = (SOAPBodyElement) it.next();
				
				it=sbe.getChildElements(ciudad);
				SOAPBodyElement dir5 = (SOAPBodyElement) it.next();
				
				it=sbe.getChildElements(EmailParticularCliente);
				SOAPBodyElement email = (SOAPBodyElement) it.next();
				
				it=sbe.getChildElements(listaProductos);
				sbe=(SOAPBodyElement) it.next();
				
				ArrayList<CuentaVO> cuentasCliente = new ArrayList<CuentaVO>();
				
				it=sbe.getChildElements(); //productos
				while(it.hasNext()){
					
					SOAPBodyElement item = (SOAPBodyElement) it.next();								
					SOAPBodyElement cod = (SOAPBodyElement)item.getChildElements(codigoProducto).next();
					SOAPBodyElement clsCuenta = (SOAPBodyElement)item.getChildElements(claseCuenta).next();
					SOAPBodyElement est = (SOAPBodyElement)item.getChildElements(estado).next();

					String tipoCuenta = cod.getValue();
					
					if(MotorPagosHelper.isTokenPermitido(tipoCuenta) && MotorPagosHelper.isActivaVigente(est.getValue())){
											
						SOAPBodyElement num = (SOAPBodyElement)item.getChildElements(numProducto).next();	
						CuentaVO cuenta = new CuentaVO();
						
						cuenta.setLlaveCuenta(CryptData.encriptar(num.getValue()+tipoCuenta));					
						cuenta.setMascaraCuenta(MotorPagosHelper.enmascaraCuenta(num.getValue(), clsCuenta.getValue(), tipoCuenta));
						cuentasCliente.add(cuenta);
					}						
				}
				
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Operacion Exitosa."));
				
				CuentasClienteVO cuentasVO = new CuentasClienteVO();
				cuentasVO.setCuentasCliente(cuentasCliente);
				respuesta.setCuentas(cuentasVO);
				
				//Se extrae sÃ³lo el primer nombre del cliente
				String nombreCompleto = nom.getValue();
				String[] nombres = nombreCompleto.split(" ");
				if (nombres.length > 0){
					respuesta.setNombre(nombres[0]+" "+apePat.getValue());
				}
				else {
					respuesta.setNombre(apePat.getValue());
				}
					
				respuesta.setMail(email.getValue());
				
				respuesta.setCodigoMarca(beCodigoMarca.getValue());
				
				//Se concatena la direccion
				if(dir1.getValue()!=null){
					respuesta.setDireccion(dir1.getValue());
				}
				if(dir2.getValue()!=null){
					respuesta.setDireccion(respuesta.getDireccion() +" "+dir2.getValue());
				}
				if(dir3.getValue()!=null){
					respuesta.setDireccion(respuesta.getDireccion() +" "+dir3.getValue());
				}
				if(dir4.getValue()!=null){
					respuesta.setDireccion(respuesta.getDireccion() +" "+dir4.getValue());
				}
				if(dir5.getValue()!=null){
					respuesta.setDireccion(respuesta.getDireccion() +" "+dir5.getValue());
				}
			}else if(ConstantesMotorPagos.CODIGO_RETORNO_CLAVE_INVALIDA.equals(itemRetorno.getValue())){
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_CLAVE_INVALIDA, "Los datos ingresados no son válidos, intenta nuevamente."));
			}else if(ConstantesMotorPagos.CODIGO_RETORNO_CLAVE_EXPIRADA.equals(itemRetorno.getValue())){
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_CLAVE_EXPIRADA, "Tu clave se encuentra expirada. Ingresa a Banco en Línea."));
			}else if(ConstantesMotorPagos.CODIGO_RETORNO_CLAVE_X_INICIAR.equals(itemRetorno.getValue())){
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_CLAVE_X_INICIAR, "Inicializa su clave en Banco en Línea."));
			}else if(ConstantesMotorPagos.CODIGO_RETORNO_CLAVE_BLOQUEADA.equals(itemRetorno.getValue())){
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_CLAVE_BLOQUEADA, "Tu clave se encuentra bloqueada, ingresa a Banco en Línea para desbloquearla."));
			}else if(ConstantesMotorPagos.CODIGO_RETORNO_CLAVE_NRO_BLOQUEO.equals(itemRetorno.getValue())){
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_CLAVE_NRO_BLOQUEO, "Tu clave se encuentra bloqueada, retira tu nueva clave en cualquier sucursal."));
			}else{
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, "Los datos ingresados no son válidos, intenta nuevamente."));
			}
		} catch (Exception e) {
			logger.error("Error, No fue posible parsear la respuesta del servicio CS000152.", e);
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
		} 
		return respuesta;
	}
	
//	/**
//	 * 
//	 * @param args
//	 */
//	public static void main(String args[]){
//		LoginClienteVO loginVO = new LoginClienteVO();
//		loginVO.setClave("530c0feebb1f9bb8");
//		loginVO.setRut("7743915-3");
//		
//		ClienteCS000152 cliente = new ClienteCS000152();
//		cliente.callCS000152(loginVO);
//	}
}
