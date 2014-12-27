/**
 * 
 */
package cl.bch.motorpagos.wsclient;

import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.FechaContableVO;

/**
 * @author boyanedel
 *
 */
public class ClienteCS002028_calculoFechaHabil extends GeneralClient {
	private static final Logger logger = LoggerFactory.getLogger(ClienteCS000508.class);
	private static final String END_POINT = "http://"+ GeneralClient.host +":"+ GeneralClient.port +"/Cutoff/CS002028_CalculoFechaHabil";
	private static final String RESPONSE_XML = "http://osb.bancochile.cl/ESB/CalculoFechaHabil/OpCalculoFechaHabilResponse";
	private static final String REQUEST_HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cal=\"http://osb.bancochile.cl/CalculoFechaHabil/\" xmlns:head=\"http://osb.bancochile.cl/common/HeaderRequest\" xmlns:opc=\"http://osb.bancochile.cl/ESB/CalculoFechaHabil/OpCalculoFechaHabilRequest\">";
	
	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public String callCS002028(FechaContableVO fechaContableVO){
		String respuesta = null;
		
		String request = this.generaRequest(fechaContableVO);
		logger.debug("*******REQUEST*******");
		logger.debug(request);
		logger.debug("*******REQUEST*******");
		
		try {
			SOAPMessage response = this.callWS(request, END_POINT);
			
			logger.debug("*******RESPONSE*******");
			logger.debug(getSOAPMessageAsString(response));
		    logger.debug("*******RESPONSE*******");
		    
		  respuesta = (String)this.parseResponse(response);
			
		} catch (Exception e) {
			logger.error("Error, No fue posible invocar el servicio CS002028.CalculoFechaHabil.", e);	
		}
		
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#generaRequest(java.lang.Object)
	 */
	@Override
	protected String generaRequest(Object requestVO) {
		StringBuffer bf = new StringBuffer(4000);
		FechaContableVO fechaContableVO = (FechaContableVO) requestVO;
				
		bf.append(REQUEST_HEADER);
		
		bf.append("<soapenv:Header><cal:headerRequest><head:consumidor><head:idApp>");
		bf.append(ConstantesMotorPagos.ID_APP);
		bf.append("</head:idApp><head:usuario>");
		String rutClienteOrigen = MotorPagosHelper.cleanRut(fechaContableVO.getRutCliente());
		rutClienteOrigen = MotorPagosHelper.formateaString(rutClienteOrigen, "0", 9);
		
		bf.append(rutClienteOrigen);		
		bf.append("</head:usuario></head:consumidor><head:transaccion><head:idTransaccionNegocio>");
		bf.append(ConstantesMotorPagos.ID_USUARIO);
		bf.append(rutClienteOrigen);
		bf.append("</head:idTransaccionNegocio><head:fechaHora>");
		bf.append(MotorPagosHelper.getFechaActual());
		bf.append("</head:fechaHora><head:canal>");
		bf.append(ConstantesMotorPagos.ID_CANAL);
		bf.append("</head:canal><head:sucursal>");
		bf.append(ConstantesMotorPagos.ID_SUCURSAL);
		bf.append("</head:sucursal></head:transaccion></cal:headerRequest></soapenv:Header><soapenv:Body><cal:CalculoFechaHabil><cuerpo><opc:fechaIngreso>");
		bf.append(fechaContableVO.getFechaActual());
		bf.append("</opc:fechaIngreso></cuerpo></cal:CalculoFechaHabil></soapenv:Body></soapenv:Envelope>");
		
		return bf.toString();
	}

	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#parseResponse(javax.xml.soap.SOAPMessage)
	 */
	@Override
	protected Object parseResponse(SOAPMessage response) {
		
		String resp = null;
		SOAPEnvelope env;
		try {
			env = response.getSOAPPart().getEnvelope();
			SOAPBody sb = env.getBody();
			
			QName fechaSalida = new QName(RESPONSE_XML, "fechaSalida");
			QName codError = new QName(RESPONSE_XML, "codError");
			
			SOAPBodyElement sbe=(SOAPBodyElement)sb.getChildElements().next(); //CalculoFechaHabilResponse			
			sbe=(SOAPBodyElement)sbe.getChildElements().next(); //cuerpo
			
			Iterator it = sbe.getChildElements(codError);
			SOAPBodyElement codErrorItem = (SOAPBodyElement) it.next();
			
			if("00".equals(codErrorItem.getValue())){
				it = sbe.getChildElements(fechaSalida);
				SOAPBodyElement fechaSalidaItem = (SOAPBodyElement) it.next();
				
				resp = fechaSalidaItem.getValue();
			}
			
		} catch (Exception e) {
			logger.error("Error, No fue posible parsear la respuesta del servicio CS002028.CalculoFechaHabil.", e);	
		}
		return resp;
	}
	
//	/**
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		ClienteCS002028_calculoFechaHabil test = new ClienteCS002028_calculoFechaHabil();
//		
//		String resp=test.callCS002028("20140230");
//		System.out.println(resp);
//		
//	}

}
