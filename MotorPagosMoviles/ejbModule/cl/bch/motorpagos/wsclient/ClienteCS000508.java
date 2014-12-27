/**
 * 
 */
package cl.bch.motorpagos.wsclient;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.util.ConfigurationLoader;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.RespuestaVO;
import cl.bch.motorpagos.vo.TransferenciaVO;

/**
 * @author boyanedel
 *
 */
public class ClienteCS000508 extends GeneralClient {
	private static final Logger logger = LoggerFactory.getLogger(ClienteCS000508.class);
	private static final String END_POINT = "http://"+ GeneralClient.host +":"+ GeneralClient.port +"/PX_CuentaVista/CS000508_TransferenciaTerceros";
	private static final String RESPONSE_XML = "http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosResponse";
	private static final String REQUEST_HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";
	
	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public RespuestaVO callCS000508(TransferenciaVO transferencia){
		RespuestaVO respuesta = null;
		
		String request = this.generaRequest(transferencia);
		logger.debug("*******REQUEST*******");
		logger.debug(request);
		logger.debug("*******REQUEST*******");
		
		try {
			SOAPMessage response = this.callWS(request, END_POINT);
			
			logger.debug("*******RESPONSE*******");
			logger.debug(getSOAPMessageAsString(response));
		    logger.debug("*******RESPONSE*******");
		    
		  respuesta = (RespuestaVO)this.parseResponse(response);
			
		} catch (Exception e) {
			logger.error("Error, No fue posible invocar el servicio CS000508.", e);	
			respuesta=MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}
		
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#generaRequest(java.lang.Object)
	 */
	@Override
	protected String generaRequest(Object requestVO) {
		StringBuffer bf = new StringBuffer(4000);
		TransferenciaVO transferenciaVO = (TransferenciaVO)requestVO;
				
		bf.append(REQUEST_HEADER);
		bf.append("<soapenv:Header><ns1:headerRequest soapenv:actor=\"http://schemas.xmlsoap.org/soap/actor/next\" soapenv:mustUnderstand=\"0\" xmlns:ns1=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTerceros/\"><ns2:consumidor xmlns:ns2=\"http://osb.bancochile.cl/common/HeaderRequest\"><ns2:idApp>"); 
		bf.append(ConstantesMotorPagos.ID_APP);
		bf.append("</ns2:idApp><ns2:usuario>");
		String rutClienteOrigen = MotorPagosHelper.cleanRut(transferenciaVO.getRutClienteOrigen());
		rutClienteOrigen = MotorPagosHelper.formateaString(rutClienteOrigen, "0", 10);
		bf.append(rutClienteOrigen);
		bf.append("</ns2:usuario></ns2:consumidor><ns3:transaccion xmlns:ns3=\"http://osb.bancochile.cl/common/HeaderRequest\"><ns3:internalCode>");
		bf.append(ConstantesMotorPagos.INTERNAL_CODE);
		bf.append("</ns3:internalCode><ns3:idTransaccionNegocio>");
		bf.append(transferenciaVO.getClaveOperacion());
		bf.append("</ns3:idTransaccionNegocio><ns3:fechaHora>"); 
		bf.append(MotorPagosHelper.getFechaActual());
		bf.append("</ns3:fechaHora><ns3:canal>");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_HEADER_ID_CANAL));
		bf.append("</ns3:canal><ns3:sucursal>");
		bf.append(ConstantesMotorPagos.ID_SUCURSAL);
		bf.append("</ns3:sucursal></ns3:transaccion></ns1:headerRequest></soapenv:Header><soapenv:Body><TEFMismoBanco xmlns=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTerceros/\"><reqTEFMismoBanco xmlns=\"\"><ns4:rutOperador xmlns:ns4=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">"); 
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_RUT_OPERADOR));
		bf.append("</ns4:rutOperador><ns5:estacion xmlns:ns5=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_ESTACION));
		bf.append("</ns5:estacion><ns6:servicio xmlns:ns6=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">"); 
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_SERVICIO));
		bf.append("</ns6:servicio><ns7:log xmlns:ns7=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_LOG));
		bf.append("</ns7:log><ns8:canal xmlns:ns8=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">"); 
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_CANAL));		
		bf.append("</ns8:canal><ns9:token xmlns:ns9=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TOKEN));
		bf.append("</ns9:token><ns10:rutCliente xmlns:ns10=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">"); 
		bf.append(rutClienteOrigen);
		bf.append("</ns10:rutCliente><ns11:tokenTipoCliente xmlns:ns11=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">"); 
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_TOKEN_TIPO_CLIENTE));
		bf.append("</ns11:tokenTipoCliente><ns12:migracionCtaOrigen xmlns:ns12=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">"); 
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_MIG_CTA_ORIGEN));
		bf.append("</ns12:migracionCtaOrigen><ns13:codRelacionOrigen xmlns:ns13=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">"); 
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_COD_REL_ORIGEN));
		bf.append("</ns13:codRelacionOrigen><ns14:tokenCtaOrigen xmlns:ns14=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">"); 
		bf.append(transferenciaVO.getTokenCtaOrigen());
		bf.append("</ns14:tokenCtaOrigen><ns15:migracionCtaDestino xmlns:ns15=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">"); 
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_MIG_CTA_DESTINO));
		bf.append("</ns15:migracionCtaDestino><ns16:codRelacionDestino xmlns:ns16=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_COD_REL_DESTINO));
		bf.append("</ns16:codRelacionDestino><ns17:tokenCtaDestino xmlns:ns17=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">");
		bf.append(transferenciaVO.getTokenCtaDestino());
		bf.append("</ns17:tokenCtaDestino><ns18:oficinaOrigen xmlns:ns18=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_OFICINA_ORIGEN));		   
		bf.append("</ns18:oficinaOrigen><ns19:oficinaDestino xmlns:ns19=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">"); 
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_OFICINA_DESTINO));
		bf.append("</ns19:oficinaDestino><ns20:autorizacion xmlns:ns20=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">"); 
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_AUTORIZACION));
		bf.append("</ns20:autorizacion><ns21:cajero xmlns:ns21=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">");  
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_CAJERO));
		bf.append("</ns21:cajero><ns22:ctaOrigen xmlns:ns22=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">"); 
		bf.append(MotorPagosHelper.formateaString(transferenciaVO.getCtaOrigen(), "0", 12));
		bf.append("</ns22:ctaOrigen><ns23:ctaDestino xmlns:ns23=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">"); 
		bf.append(MotorPagosHelper.formateaString(transferenciaVO.getCtaDestino(), "0", 12));
		bf.append("</ns23:ctaDestino><ns24:montoTrx xmlns:ns24=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">"); 
		bf.append(MotorPagosHelper.formateaString(transferenciaVO.getMonto()+"00", "0", 13));
		bf.append("</ns24:montoTrx><ns25:indiceJournal xmlns:ns25=\"http://osb.bancochile.cl/CuentaVista/TransferenciaTercerosRequest\">"); 
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_INDICE_JOURNAL));
		bf.append("</ns25:indiceJournal></reqTEFMismoBanco></TEFMismoBanco></soapenv:Body></soapenv:Envelope>");

		return bf.toString();
	}

	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#parseResponse(javax.xml.soap.SOAPMessage)
	 */
	@Override
	protected Object parseResponse(SOAPMessage response) {
		QName cr = new QName(RESPONSE_XML, "codigoRetorno");
		RespuestaVO resp=new RespuestaVO();
		SOAPEnvelope env;
		try {
			env = response.getSOAPPart().getEnvelope();
			SOAPBody sb = env.getBody();
			SOAPBodyElement sbe=(SOAPBodyElement)sb.getChildElements().next(); //TEF Mismo Banco Response
			sbe=(SOAPBodyElement)sbe.getChildElements().next(); //RespTEFMismoBanco
			sbe=(SOAPBodyElement)sbe.getChildElements(cr).next();
			resp.setCodigoRetorno(sbe.getValue());
		} catch (Exception e) {
			logger.error("Error, No fue posible parsear la respuesta del servicio CS000508.", e);	
			resp = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}
		return resp;
	}
	
//	/**
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		ClienteCS000508 tef=new ClienteCS000508();
//		TransferenciaVO vo=new TransferenciaVO();
//		vo.setClaveOperacion("BCAMOV20121231017088909554124");
//		vo.setRutClienteOrigen("0170889932");
//		vo.setCtaOrigen("070000004300");
//		vo.setCtaDestino("001011008009");
//		vo.setMonto("1");
//		vo.setTokenCtaDestino("CTD");
//		vo.setTokenCtaOrigen("JUV");
//		RespuestaVO resp=tef.callCS000508(vo);
//		System.out.println(resp.getCodigoRetorno());
//		System.out.println(resp.getGlosaRetorno());
//		
//	}

}
