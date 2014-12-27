/**
 * 
 */
package cl.bch.motorpagos.wsclient;

import java.util.Iterator;

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
import cl.bch.motorpagos.vo.LoginComercioVO;
import cl.bch.motorpagos.vo.RespuestaLoginComercioVO;

/**
 * @author boyanedel
 *
 */
public class ClienteCS000029 extends GeneralClient {
	private static final Logger logger = LoggerFactory.getLogger(ClienteCS000029.class);
	private static final String END_POINT = "http://"+ GeneralClient.host +":"+ GeneralClient.port +"/contactabilidad/CS000029_ConsultaInformacionBasicaEmpresas";
	private static final String RESPONSE_XML = "http://osb.bancochile.cl/ESB/ConsultaInformacionBasicaEmpresas/OpConsultarInfoBasicaResponse";
	private static final String RESPONSE_XML_DIR = "http://osb.bancochile.cl/entities/contactabilidad/Direccion";
	private static final String REQUEST_HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:con=\"http://osb.bancochile.cl/ConsultaInformacionBasicaEmpresas/\" xmlns:head=\"http://osb.bancochile.cl/common/HeaderRequest\" xmlns:opc=\"http://osb.bancochile.cl/ESB/ConsultaInformacionBasicaEmpresas/OpConsultarInfoBasicaRequest\">";

	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public RespuestaLoginComercioVO callCS000029(LoginComercioVO loginVO){
		RespuestaLoginComercioVO respuesta = new RespuestaLoginComercioVO();
		
		String request = this.generaRequest(loginVO);
		logger.debug("*******REQUEST*******");
		logger.debug(request);
		logger.debug("*******REQUEST*******");
		
		try {
			SOAPMessage response = this.callWS(request, END_POINT);
			
			logger.debug("*******RESPONSE*******");
			logger.debug(getSOAPMessageAsString(response));
		    logger.debug("*******RESPONSE*******");
		    
		    respuesta = (RespuestaLoginComercioVO)this.parseResponse(response);
			
		} catch (Exception e) {
			logger.error("Error, No fue posible invocar el servicio CS000029.", e);	
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
		}
		
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#generaRequest(java.lang.Object)
	 */
	@Override
	protected String generaRequest(Object requestVO) {
		StringBuffer bf = new StringBuffer(500);
		LoginComercioVO loginVO = (LoginComercioVO)requestVO;
		
		bf.append(REQUEST_HEADER);
		bf.append("<soapenv:Header><con:headerRequest><head:consumidor><head:idApp>");
		bf.append(ConstantesMotorPagos.ID_APP);
		bf.append("</head:idApp><head:usuario>");
		bf.append(MotorPagosHelper.cleanRut(loginVO.getRutPersona()));
		bf.append("</head:usuario></head:consumidor><head:transaccion><head:idTransaccionNegocio>");
		bf.append(ConstantesMotorPagos.ID_USUARIO);
		bf.append(MotorPagosHelper.cleanRut(loginVO.getRutPersona()));		
		bf.append("</head:idTransaccionNegocio><head:fechaHora>");
		bf.append(MotorPagosHelper.getFechaActual());
		bf.append("</head:fechaHora><head:canal>");
		bf.append(ConstantesMotorPagos.ID_CANAL);
		bf.append("</head:canal></head:transaccion></con:headerRequest></soapenv:Header><soapenv:Body><con:ConsultarInfoBasica><datosEntrada><opc:rutEmpresa>");
		bf.append(loginVO.getRutEmpresa());
		bf.append("</opc:rutEmpresa></datosEntrada></con:ConsultarInfoBasica></soapenv:Body></soapenv:Envelope>");		
		
		return bf.toString();
	}

	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#parseResponse(javax.xml.soap.SOAPMessage)
	 */
	@Override
	protected Object parseResponse(SOAPMessage response) {
		RespuestaLoginComercioVO respuesta = new RespuestaLoginComercioVO();

		SOAPEnvelope env;
		try {
			env = response.getSOAPPart().getEnvelope();
			SOAPBody sb = env.getBody();
			
			//Datos que se obtienen desde el cuerpo	
			QName razonSocial = new QName(RESPONSE_XML, "razonSocial");
			
			//TODO identificar campo del mail			
			QName listaDirecciones = new QName(RESPONSE_XML, "listaDirecciones");
			QName direcciones = new QName(RESPONSE_XML, "direcciones");
			
			QName usoPostal = new QName(RESPONSE_XML_DIR, "usoPostal");	//Se toma la direccion uso postal Y
			QName direccion = new QName(RESPONSE_XML_DIR, "direccion");	
			QName numero = new QName(RESPONSE_XML_DIR, "numero");	
			QName departamento = new QName(RESPONSE_XML_DIR, "departamento");	
			QName ciudad = new QName(RESPONSE_XML_DIR, "ciudad");	
			QName comuna = new QName(RESPONSE_XML_DIR, "comuna");		
						
			Iterator it = sb.getChildElements(); //ConsultarInfoBasicaResponse	*****VER RESPUESTA EN SOAPUI******	    
			SOAPBodyElement sbe = (SOAPBodyElement) it.next();

			it=sbe.getChildElements(); //datosSalida
			sbe=(SOAPBodyElement) it.next();
			
			it=sbe.getChildElements(razonSocial);
			SOAPBodyElement rSocial = (SOAPBodyElement) it.next();
			
			it=sbe.getChildElements(listaDirecciones);//listaDirecciones
			sbe=(SOAPBodyElement) it.next();
						
			String dir = null;
			it=sbe.getChildElements(direcciones); //direcciones
			while(it.hasNext()){			
				
				SOAPBodyElement item =(SOAPBodyElement) it.next();
				
				Iterator i=item.getChildElements(usoPostal);
				SOAPBodyElement postal = (SOAPBodyElement) i.next();
				
				if (postal.getValue().equalsIgnoreCase("Y")){
					i=item.getChildElements(direccion);
					SOAPBodyElement dir1 = (SOAPBodyElement) i.next();
								
					i=item.getChildElements(numero);
					SOAPBodyElement dir2 = (SOAPBodyElement) i.next();
					
					i=item.getChildElements(departamento);
					SOAPBodyElement dir3 = (SOAPBodyElement) i.next();
					
					i=item.getChildElements(ciudad);
					SOAPBodyElement dir4 = (SOAPBodyElement) i.next();
					
					i=item.getChildElements(comuna);
					SOAPBodyElement dir5 = (SOAPBodyElement) i.next();
					
					if(dir1.getValue()!=null){
						dir = dir1.getValue();
					}
					if(dir2.getValue()!=null){
						dir = dir + " " + dir2.getValue();
					}
					if(dir3.getValue()!=null){
						dir = dir + " " + dir3.getValue();
					}
					if(dir4.getValue()!=null){
						dir = dir + " " + dir4.getValue();
					}
					if(dir5.getValue()!=null){
						dir = dir + " " + dir5.getValue(); 
					}
					
					break;
				}
			}
			
			respuesta.setNombre(rSocial.getValue());
			respuesta.setMail("");
			respuesta.setDireccion(dir);
			
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Operación Exitosa"));
			
		} catch (SOAPException e) {
			logger.error("Error, No fue posible parsear la respuesta del servicio CS000029.", e);
			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
		}
		return respuesta;
	}

}
