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

import cl.bch.motorpagos.exceptions.MotorPagosException;
import cl.bch.motorpagos.security.CryptData;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.AtribucionesComercioVO;
import cl.bch.motorpagos.vo.RespuestaVO;

/**
 * @author boyanedel
 *
 */
public class ClienteCS000384 extends GeneralClient {
	private String idPoder;
	private static final Logger logger = LoggerFactory.getLogger(ClienteCS000384.class);	
	private static final String END_POINT = "http://"+ GeneralClient.host +":"+ GeneralClient.portTeller +"/PX_Apoderados/CS000384_ValPoderApoderado";
	private static final String RESPONSE_XML = "http://osb.bancochile.cl/ESB/ValPoderApoderado/OpValPoderApoderadoResponse";
	private static final String REQUEST_HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:val=\"http://osb.bancochile.cl/ValPoderApoderado/\" xmlns:head=\"http://osb.bancochile.cl/common/HeaderRequest\" xmlns:opv=\"http://osb.bancochile.cl/ESB/ValPoderApoderado/OpValPoderApoderadoRequest\">";
	
	/**
	 * @return the idPoder
	 */
	public String getIdPoder() {
		return idPoder;
	}

	/**
	 * @param idPoder the idPoder to set
	 */
	public void setIdPoder(String idPoder) {
		this.idPoder = idPoder;
	}
	
	/**
	 * 
	 * @param atribucionesVO
	 * @return
	 */
	public RespuestaVO callCS000384(AtribucionesComercioVO atribucionesVO){
		RespuestaVO respuesta = null;
		
		String request = this.generaRequest(atribucionesVO);
		if(request==null){
			logger.error("Se produjo un error al parsear el request CS000384.");
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}
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
			logger.error("Error, No fue posible invocar el servicio CS000384.", e);	
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}
		
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#generaRequest(java.lang.Object)
	 */
	@Override
	protected String generaRequest(Object requestVO) {
		StringBuffer bf = new StringBuffer(900);
		AtribucionesComercioVO comercioVO = (AtribucionesComercioVO)requestVO;
		
		bf.append(REQUEST_HEADER);
		bf.append("<soapenv:Header><val:headerRequest><head:consumidor><head:idApp>");
		bf.append(ConstantesMotorPagos.ID_APP);
		bf.append("</head:idApp><head:usuario>");
		bf.append(MotorPagosHelper.cleanRut(comercioVO.getRutEmpresa()));
		bf.append("</head:usuario></head:consumidor><head:transaccion><head:idTransaccionNegocio>");
		bf.append(ConstantesMotorPagos.ID_USUARIO);
		bf.append(MotorPagosHelper.cleanRut(comercioVO.getRutEmpresa()));		
		bf.append("</head:idTransaccionNegocio><head:fechaHora>");
		bf.append(MotorPagosHelper.getFechaActual());
		bf.append("</head:fechaHora><head:canal>");
		bf.append(ConstantesMotorPagos.ID_CANAL);
		bf.append("</head:canal></head:transaccion></val:headerRequest></soapenv:Header><soapenv:Body><val:ValPoderApoderado><cuerpo><opv:rutUsuario>12345678-9</opv:rutUsuario><opv:oficinaOrigen>000</opv:oficinaOrigen><opv:rutCliente>");
		bf.append(comercioVO.getRutEmpresa());
		bf.append("</opv:rutCliente><opv:cuentaCorriente>");
		try{
			String llaveCuenta = CryptData.desEncriptar(comercioVO.getLlaveCuenta());
			bf.append(MotorPagosHelper.cleanCuenta(llaveCuenta.substring(0, llaveCuenta.length()-3)));			
		}catch(MotorPagosException e){
			logger.error("Error, se produjo un error al obtener la cuenta del cliente.", e);
			return null;
		}
		bf.append("</opv:cuentaCorriente><opv:tipoMoneda>1</opv:tipoMoneda><opv:monto>1.00</opv:monto><opv:tipoSolicitud>");
		bf.append(this.getIdPoder());
		bf.append("</opv:tipoSolicitud><opv:numeroApoderados>1</opv:numeroApoderados><opv:apoderados><opv:apoderado><opv:rutApoderado>");
		bf.append(comercioVO.getRutPersona());
		bf.append("</opv:rutApoderado></opv:apoderado></opv:apoderados></cuerpo></val:ValPoderApoderado></soapenv:Body></soapenv:Envelope>");		
		
		return bf.toString();
	}

	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#parseResponse(javax.xml.soap.SOAPMessage)
	 */
	@Override
	protected Object parseResponse(SOAPMessage response) {
		RespuestaVO respuestaVO = new RespuestaVO();

		SOAPEnvelope env;
		try {
			env = response.getSOAPPart().getEnvelope();
			SOAPBody sb = env.getBody();
			
			//Datos que se obtienen desde el cuerpo	
			QName respuesta = new QName(RESPONSE_XML, "respuesta");
			QName mensajeRetorno = new QName(RESPONSE_XML, "mensajeRetorno");
			QName estado = new QName(RESPONSE_XML, "estado");
			
			SOAPBodyElement sbe = (SOAPBodyElement) sb.getChildElements().next();//ValPoderApoderadoResponse	*****VER RESPUESTA EN SOAPUI******	
			sbe=(SOAPBodyElement) sbe.getChildElements().next();//cuerpo	
			
			SOAPBodyElement resItem = (SOAPBodyElement) sbe.getChildElements(respuesta).next();
			SOAPBodyElement retItem = (SOAPBodyElement) sbe.getChildElements(mensajeRetorno).next();
			
			String retorno;
			if(resItem.getValue()==null){
				retorno = retItem.getValue();
			}else{
				retorno = resItem.getValue();
			}
			
			resItem = (SOAPBodyElement) sbe.getChildElements(estado).next();
			String resp = resItem.getValue();			
			
			if (resp!=null && resp.equals("true")){
				respuestaVO = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, retorno);
			}else{
				respuestaVO = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, retorno);
			}			
		} catch (SOAPException e) {
			logger.error("Error, No fue posible parsear la respuesta del servicio CS000384.", e);
			respuestaVO = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}
		return respuestaVO;
	}
	
//	/**
//	 * 
//	 * @param args
//	 */
//	public static void main(String args[]){
//		ClienteCS000348 cliente = new ClienteCS000348();
//		
//		AtribucionesComercioVO atribucionesVO = new AtribucionesComercioVO();
//		atribucionesVO.setRutEmpresa("99289000-2");
//		atribucionesVO.setRutPersona("10851080-3");
//		//comercioVO.setRutPersonas("14062716-K");
//		atribucionesVO.setLlaveCuenta("f75c4d7bb583cf266fea3ed7a30588bdc568c93c2c180c41");
//		cliente.setIdPoder("09");
//		
//		System.out.println(cliente.callCS000348(atribucionesVO));
//	}

}
