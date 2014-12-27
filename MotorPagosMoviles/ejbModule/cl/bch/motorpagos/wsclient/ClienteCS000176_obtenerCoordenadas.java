/**
 * 
 */
package cl.bch.motorpagos.wsclient;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.CoordenadasDispositivoVO;
import cl.bch.motorpagos.vo.LoginClienteVO;

/**
 * @author boyanedel
 *
 */
public class ClienteCS000176_obtenerCoordenadas extends GeneralClient {
	private static final Logger logger = LoggerFactory.getLogger(ClienteCS000176_obtenerCoordenadas.class);
	private static final String END_POINT = "http://"+ GeneralClient.host +":"+ GeneralClient.port +"/RenovacionInternet/CS000176_ValidarDispositivoSeguridad";
	private static final String REQUEST_HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:val=\"http://osb.bancochile.cl/RenovacionInternet/ValidarDispositivoSeguridad/\" xmlns:head=\"http://osb.bancochile.cl/common/HeaderRequest\" xmlns:opob=\"http://osb.bancochile.cl/ESB/RenovacionInternet/ValidarDispositivoSeguridad/OpObtenerCoordenadasRequest\">";

	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public CoordenadasDispositivoVO callCS000176(LoginClienteVO loginVO){
		CoordenadasDispositivoVO coordenadas = null;
		
		String request = this.generaRequest(loginVO);
		logger.debug("*******REQUEST*******");
		logger.debug(request);
		logger.debug("*******REQUEST*******");
		
		try {
			SOAPMessage response = this.callWS(request, END_POINT);
			
			logger.debug("*******RESPONSE*******");
			logger.debug(getSOAPMessageAsString(response));
		    logger.debug("*******RESPONSE*******");
		    
		    coordenadas = (CoordenadasDispositivoVO)this.parseResponse(response);
			
		} catch (Exception e) {
			logger.error("Error, No fue posible invocar el servicio CS000176_obtenerCoordenadas.", e);	
		}
		
		return coordenadas;
	}
	
	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#generaRequest(java.lang.Object)
	 */
	@Override
	protected String generaRequest(Object requestVO) {
		StringBuffer bf = new StringBuffer(800);
		LoginClienteVO clienteVO = (LoginClienteVO)requestVO;
		
		bf.append(REQUEST_HEADER);
		bf.append("<soapenv:Header><val:headerRequest><head:consumidor><head:idApp>");
		bf.append(ConstantesMotorPagos.ID_APP);
		bf.append("</head:idApp><head:usuario>");
		bf.append(MotorPagosHelper.cleanRut(clienteVO.getRut()));
		bf.append("</head:usuario></head:consumidor><head:transaccion><head:idTransaccionNegocio>");		
		bf.append(ConstantesMotorPagos.ID_USUARIO);
		bf.append(MotorPagosHelper.cleanRut(clienteVO.getRut()));
		bf.append("</head:idTransaccionNegocio><head:fechaHora>");
		bf.append(MotorPagosHelper.getFechaActual());
		bf.append("</head:fechaHora><head:canal>");
		bf.append(ConstantesMotorPagos.ID_CANAL);
		bf.append("</head:canal><head:sucursal>");
		bf.append(ConstantesMotorPagos.ID_SUCURSAL);
		bf.append("</head:sucursal></head:transaccion></val:headerRequest></soapenv:Header><soapenv:Body><val:ObtenerCoordenadasRequest><reqObtenerCoordenadas><opob:Cuerpo><opob:canalConsulta>");
		
		if(MotorPagosHelper.isEmpresa(clienteVO.getIdApp())){
			bf.append(ConstantesMotorPagos.ID_CANAL_BODY_EMPRESAS);
		}else{
			bf.append(ConstantesMotorPagos.ID_CANAL_BODY);
		}
		
		bf.append("</opob:canalConsulta><opob:rutCliente>");
		bf.append(MotorPagosHelper.cleanRut(clienteVO.getRut()));
		bf.append("</opob:rutCliente><opob:grupoId>");
		bf.append(ConstantesMotorPagos.ID_GRUPO_BCH);
		bf.append("</opob:grupoId><opob:tipoDispositivo>");
		bf.append(ConstantesMotorPagos.ID_DISP_DIGICARD);
		bf.append("</opob:tipoDispositivo><opob:cantidadCoordenadas>3</opob:cantidadCoordenadas></opob:Cuerpo></reqObtenerCoordenadas></val:ObtenerCoordenadasRequest></soapenv:Body></soapenv:Envelope>");		
		
		return bf.toString();
	}

	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#parseResponse(javax.xml.soap.SOAPMessage)
	 */
	@Override
	protected Object parseResponse(SOAPMessage response) {
		CoordenadasDispositivoVO respuesta = null;
		ArrayList<SOAPBodyElement> listaCoordenadas = new ArrayList<SOAPBodyElement>();

		SOAPEnvelope env;
		try {
			env = response.getSOAPPart().getEnvelope();
			SOAPBody sb = env.getBody();
				
			SOAPBodyElement sbe = (SOAPBodyElement) sb.getChildElements().next(); //ObtenerCoordenadasResponse	*****VER RESPUESTA EN SOAPUI******	
			sbe=(SOAPBodyElement) sbe.getChildElements().next(); //respObtenerCoordenadas
			sbe=(SOAPBodyElement) sbe.getChildElements().next(); //Cuerpo
			
			Iterator it=sbe.getChildElements();
			while(it.hasNext()){
				SOAPBodyElement hijo = (SOAPBodyElement) it.next();
								
				if(hijo.getLocalName().equalsIgnoreCase("codigoRetorno")){
					if(!hijo.getValue().equalsIgnoreCase("0")){
						break;
					}
				}else if(hijo.getLocalName().equalsIgnoreCase("CoordenadasTarjeta")){					
					listaCoordenadas.add(hijo);
				}					
			}
			
			if(listaCoordenadas.isEmpty()){
				logger.debug("No fue posible obtener las coordenadas.");
			}else{
				respuesta = new CoordenadasDispositivoVO();
				respuesta.setCoordenada1(((SOAPBodyElement)((SOAPBodyElement)listaCoordenadas.get(0)).getChildElements().next()).getValue());
				respuesta.setCoordenada2(((SOAPBodyElement)((SOAPBodyElement)listaCoordenadas.get(1)).getChildElements().next()).getValue());
				respuesta.setCoordenada3(((SOAPBodyElement)((SOAPBodyElement)listaCoordenadas.get(2)).getChildElements().next()).getValue());
			}
						
		} catch (SOAPException e) {
			logger.error("Error, No fue posible parsear la respuesta del servicio CS000176_validaDispositivo.", e);	
		}
		return respuesta;
	}
	
//	public static void main(String args[]){
//		ClienteCS000176_obtenerCoordenadas cliente = new ClienteCS000176_obtenerCoordenadas();
//		ClienteVO cli = new ClienteVO();
//		cli.setRut("7743915-3");
//		
//		String respuesta[] = cliente.callCS000176(cli);
//		
//		System.out.println(respuesta);
//	}

}
