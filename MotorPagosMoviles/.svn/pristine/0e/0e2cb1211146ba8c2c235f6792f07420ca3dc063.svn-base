/**
 * 
 */
package cl.bch.motorpagos.wsclient;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.RespuestaConsultaSaldoVO;

/**
 * @author boyanedel
 *
 */
public class ClienteCS000069 extends GeneralClient {
	private static final Logger logger = LoggerFactory.getLogger(ClienteCS000069.class);
	private static final String END_POINT = "http://"+ GeneralClient.host +":"+ GeneralClient.port +"/SaldosCartolas/CS000069_ConsultaSaldoCuenta";
	private static final String RESPONSE_XML = "http://osb.bancochile.cl/ESB/ConsultasSaldoCuenta/OpConsultasSaldoCuentaResponse";
	private static final String REQUEST_HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:con=\"http://osb.bancochile.cl/ConsultasSaldoCuenta/\" xmlns:head=\"http://osb.bancochile.cl/common/HeaderRequest\" xmlns:opc=\"http://osb.bancochile.cl/ESB/ConsultasSaldoCuenta/OpConsultasSaldoCuentaRequest\">";

	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public RespuestaConsultaSaldoVO callCS000069(String nroCuenta){
		RespuestaConsultaSaldoVO respuesta = new RespuestaConsultaSaldoVO();
		
		String request = this.generaRequest(nroCuenta);
		logger.debug("*******REQUEST*******");
		logger.debug(request);
		logger.debug("*******REQUEST*******");
		
		try {
			SOAPMessage response = this.callWS(request, END_POINT);
			
			logger.debug("*******RESPONSE*******");
			logger.debug(getSOAPMessageAsString(response));
		    logger.debug("*******RESPONSE*******");
		    
		    respuesta = (RespuestaConsultaSaldoVO)this.parseResponse(response);
			
		} catch (Exception e) {
			logger.error("Error, No fue posible invocar el servicio CS000069.", e);	
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
		}
		
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#generaRequest(java.lang.Object)
	 */
	@Override
	protected String generaRequest(Object requestVO) {
		StringBuffer bf = new StringBuffer(600);
		String nroCuenta = (String)requestVO;
		
		bf.append(REQUEST_HEADER);
		bf.append("<soapenv:Header><con:headerRequest><head:consumidor><head:idApp>");
		bf.append(ConstantesMotorPagos.ID_APP);
		bf.append("</head:idApp><head:usuario>");
		bf.append(nroCuenta);
		bf.append("</head:usuario></head:consumidor><head:transaccion><head:idTransaccionNegocio>");
		bf.append(ConstantesMotorPagos.ID_USUARIO);
		bf.append(nroCuenta);		
		bf.append("</head:idTransaccionNegocio><head:fechaHora>");
		bf.append(MotorPagosHelper.getFechaActual());
		bf.append("</head:fechaHora><head:canal>INTERNET</head:canal><head:sucursal>");
		bf.append(ConstantesMotorPagos.ID_SUCURSAL);
		bf.append("</head:sucursal></head:transaccion></con:headerRequest></soapenv:Header><soapenv:Body><con:ConsultasSaldoCuenta><cuerpo><opc:numeroCuenta>");
		bf.append(nroCuenta);
		bf.append("</opc:numeroCuenta></cuerpo></con:ConsultasSaldoCuenta></soapenv:Body></soapenv:Envelope>");		
		
		return bf.toString();
	}

	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#parseResponse(javax.xml.soap.SOAPMessage)
	 */
	@Override
	protected Object parseResponse(SOAPMessage response) {
		RespuestaConsultaSaldoVO respuesta = new RespuestaConsultaSaldoVO();

		SOAPEnvelope env;
		try {
			env = response.getSOAPPart().getEnvelope();
			SOAPBody sb = env.getBody();
			
			QName saldoDisponible = new QName(RESPONSE_XML, "SaldoDisponible");
			QName saldoContable = new QName(RESPONSE_XML, "SaldoContable");			
			QName retencion1Dia = new QName(RESPONSE_XML, "Retencion1Dia");	
			QName retencionNDia = new QName(RESPONSE_XML, "RetencionNDia");	
			QName moneda = new QName(RESPONSE_XML, "Moneda");		
						
			SOAPBodyElement sbe = (SOAPBodyElement) sb.getChildElements().next(); //ConsultasSaldoCuentaResponse	*****VER RESPUESTA EN SOAPUI******	  
			sbe=(SOAPBodyElement) sbe.getChildElements().next(); //cuerpo
			sbe=(SOAPBodyElement) sbe.getChildElements().next(); //Cuenta
			
			SOAPBodyElement itemSalDisp = (SOAPBodyElement)sbe.getChildElements(saldoDisponible).next();
			SOAPBodyElement itemSalCont = (SOAPBodyElement)sbe.getChildElements(saldoContable).next();
			SOAPBodyElement itemRet1Dia = (SOAPBodyElement)sbe.getChildElements(retencion1Dia).next();
			SOAPBodyElement itemRetNDia = (SOAPBodyElement)sbe.getChildElements(retencionNDia).next();
			SOAPBodyElement itemMoneda = (SOAPBodyElement)sbe.getChildElements(moneda).next();
						
			respuesta.setSaldoDisponible(	Integer.parseInt(itemSalDisp.getValue()));
			respuesta.setSaldoContable(		Integer.parseInt(itemSalCont.getValue()));
			respuesta.setRetencionesDia1(	Integer.parseInt(itemRet1Dia.getValue()));
			respuesta.setRetencionesDiaN(	Integer.parseInt(itemRetNDia.getValue()));
			respuesta.setMoneda(			itemMoneda.getValue());
			
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Operacion Exitosa."));
			
		} catch (SOAPException e) {
			logger.error("Error, No fue posible parsear la respuesta del servicio CS000069.", e);
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
		}
		return respuesta;
	}
	
//	/**
//	 * 
//	 * @param args
//	 */
//	public static void main(String args[]){
//		
//		ClienteCS000069 cliente = new ClienteCS000069();
//		cliente.callCS000069("108005017");
//	}	

}
