/**
 * 
 */
package cl.bch.motorpagos.wsclient;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.security.CryptData;
import cl.bch.motorpagos.util.ConfigurationLoader;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.CuentaVO;
import cl.bch.motorpagos.vo.CuentasClienteVO;
import cl.bch.motorpagos.vo.LoginClienteVO;
import cl.bch.motorpagos.vo.RespuestaMediosDePagoVO;

/**
 * @author boyanedel
 *
 */
public class ClienteCS000026 extends GeneralClient {
	private static final Logger logger = LoggerFactory.getLogger(ClienteCS000026.class);
	private static final String END_POINT = "http://"+ GeneralClient.host +":"+ GeneralClient.port +"/contactabilidad/CS000026_ConsultaProductoCliente";
	private static final String RESPONSE_XML = "http://osb.bancochile.cl/ESB/ConsultaProductoCliente/OpConsultarProdClienteResponse";
	private static final String REQUEST_HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:con=\"http://osb.bancochile.cl/ConsultaProductoCliente/\" xmlns:head=\"http://osb.bancochile.cl/common/HeaderRequest\" xmlns:opc=\"http://osb.bancochile.cl/ESB/ConsultaProductoCliente/OpConsultarProdClienteRequest\">";

	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public RespuestaMediosDePagoVO callCS000026(LoginClienteVO loginVO){
		RespuestaMediosDePagoVO respuesta = new RespuestaMediosDePagoVO();
		
		String request = this.generaRequest(loginVO);
		if(request==null){
			logger.error("Se produjo un error al intentar parsear el request en CS000026.");
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
			    
			    respuesta = (RespuestaMediosDePagoVO)this.parseResponse(response);
				
			} catch (Exception e) {
				logger.error("Error, No fue posible invocar el servicio CS000026.", e);	
				respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
			}
		}
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#generaRequest(java.lang.Object)
	 */
	@Override
	protected String generaRequest(Object requestVO) {
		StringBuffer bf = new StringBuffer(600);
		LoginClienteVO loginVO = (LoginClienteVO)requestVO;
		
		bf.append(REQUEST_HEADER);
		bf.append("<soapenv:Header><con:headerRequest><head:consumidor><head:idApp>");
		bf.append(ConstantesMotorPagos.ID_APP);
		bf.append("</head:idApp><head:usuario>");
		bf.append(MotorPagosHelper.cleanRut(loginVO.getRut()));
		bf.append("</head:usuario></head:consumidor><head:transaccion><head:idTransaccionNegocio>");
		bf.append(ConstantesMotorPagos.ID_USUARIO);
		bf.append(MotorPagosHelper.cleanRut(loginVO.getRut()));		
		bf.append("</head:idTransaccionNegocio><head:fechaHora>");
		bf.append(MotorPagosHelper.getFechaActual());
		bf.append("</head:fechaHora><head:canal>");
		bf.append(ConstantesMotorPagos.ID_CANAL);
		bf.append("</head:canal><head:sucursal>");
		bf.append(ConstantesMotorPagos.ID_SUCURSAL);
		bf.append("</head:sucursal></head:transaccion></con:headerRequest></soapenv:Header><soapenv:Body><con:ConsultarProdCliente><datosEntrada><opc:rutCliente>");
		bf.append(loginVO.getRut());
		bf.append("</opc:rutCliente><opc:listaProductos>");
		
		for(int i=0; i<ConstantesMotorPagos.PRODUCTOS_PERMITIDOS.length; i++){
			bf.append("<opc:codigoProducto>");
			bf.append(ConstantesMotorPagos.PRODUCTOS_PERMITIDOS[i]);
			bf.append("</opc:codigoProducto>");
		}
		
		bf.append("</opc:listaProductos></datosEntrada></con:ConsultarProdCliente></soapenv:Body></soapenv:Envelope>");		
		
		return bf.toString();
	}

	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#parseResponse(javax.xml.soap.SOAPMessage)
	 */
	@Override
	protected Object parseResponse(SOAPMessage response) {
		RespuestaMediosDePagoVO respuesta = new RespuestaMediosDePagoVO();

		SOAPEnvelope env;
		try {
			env = response.getSOAPPart().getEnvelope();
			SOAPBody sb = env.getBody();
			
			//Datos que se obtienen desde el cuerpo
			
			QName listaProductos = new QName(RESPONSE_XML, "listaProductos");			
			QName numProducto = new QName(RESPONSE_XML, "numProducto");
			QName codigoProducto = new QName(RESPONSE_XML, "codigoProducto");
			QName claseCuenta = new QName(RESPONSE_XML, "claseCuenta");
			QName estado = new QName(RESPONSE_XML, "estado");
						
			SOAPBodyElement sbe = (SOAPBodyElement) sb.getChildElements().next();//ConsultarProdClienteResponse	*****VER RESPUESTA EN SOAPUI******	 
			sbe=(SOAPBodyElement) sbe.getChildElements().next();//datosSalida			
			sbe=(SOAPBodyElement) sbe.getChildElements(listaProductos).next();			
			ArrayList<CuentaVO> cuentasCliente = new ArrayList<CuentaVO>();
			
			Iterator it=sbe.getChildElements(); //productos
			int limiteCuenta = Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.LIMITE_TEF));
			while(it.hasNext()){
				
				SOAPBodyElement item = (SOAPBodyElement) it.next();								
				SOAPBodyElement cod = (SOAPBodyElement)item.getChildElements(codigoProducto).next();
				SOAPBodyElement clsCuenta = (SOAPBodyElement)item.getChildElements(claseCuenta).next();
				SOAPBodyElement est = (SOAPBodyElement)item.getChildElements(estado).next();

				String tipoCuenta = cod.getValue();
				
				if(MotorPagosHelper.isTokenPermitido(tipoCuenta) && MotorPagosHelper.isActivaVigente(est.getValue())){
										
					SOAPBodyElement num = (SOAPBodyElement)item.getChildElements(numProducto).next();	
					CuentaVO cuenta = new CuentaVO();
					
					cuenta.setLlaveCuenta(		CryptData.encriptar(num.getValue()+tipoCuenta));					
					cuenta.setMascaraCuenta(	MotorPagosHelper.enmascaraCuenta(num.getValue(), clsCuenta.getValue(), tipoCuenta));
					cuenta.setLimiteCuenta(		limiteCuenta);
					cuenta.setUtilizadoCuenta(	0);
					cuentasCliente.add(cuenta);
				}						
			}
			
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Operacion Exitosa."));
			
			CuentasClienteVO cuentasVO = new CuentasClienteVO();
			cuentasVO.setCuentasCliente(cuentasCliente);
			respuesta.setCuentas(cuentasVO);			
			
		} catch (Exception e) {
			logger.error("Error, No fue posible parsear la respuesta del servicio CS000026.", e);
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
//		loginVO.setRut("7743915-3");
//		
//		ClienteCS000026 cliente = new ClienteCS000026();
//		cliente.callCS000026(loginVO);
//	}	

}
