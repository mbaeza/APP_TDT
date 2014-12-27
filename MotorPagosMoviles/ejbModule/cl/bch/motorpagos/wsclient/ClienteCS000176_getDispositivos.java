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
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.DispositivoSeguridadVO;
import cl.bch.motorpagos.vo.LoginClienteVO;

/**
 * @author boyanedel
 *
 */
public class ClienteCS000176_getDispositivos extends GeneralClient {
	private static final Logger logger = LoggerFactory.getLogger(ClienteCS000176_getDispositivos.class);
	private static final String END_POINT = "http://"+ GeneralClient.host +":"+ GeneralClient.port +"/RenovacionInternet/CS000176_ValidarDispositivoSeguridad";
	private static final String RESPONSE_XML = "http://osb.bancochile.cl/ESB/RenovacionInternet/ValidarDispositivoSeguridad/OpObtenerTipoDispositivoHabilitadoResponse";
	private static final String REQUEST_HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:val=\"http://osb.bancochile.cl/RenovacionInternet/ValidarDispositivoSeguridad/\" xmlns:head=\"http://osb.bancochile.cl/common/HeaderRequest\" xmlns:opob=\"http://osb.bancochile.cl/ESB/RenovacionInternet/ValidarDispositivoSeguridad/OpObtenerTipoDispositivoHabilitadoRequest\">";
	
	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public ArrayList<DispositivoSeguridadVO> callCS000176(LoginClienteVO loginVO){
		ArrayList<DispositivoSeguridadVO> dispositivos = null;
		
		String request = this.generaRequest(loginVO);
		logger.debug("*******REQUEST*******");
		logger.debug(request);
		logger.debug("*******REQUEST*******");
		
		try {
			SOAPMessage response = this.callWS(request, END_POINT);
			
			logger.debug("*******RESPONSE*******");
			logger.debug(getSOAPMessageAsString(response));
		    logger.debug("*******RESPONSE*******");
		    
		    dispositivos = (ArrayList<DispositivoSeguridadVO>)this.parseResponse(response);
			
		} catch (Exception e) {
			logger.error("Error, No fue posible invocar el servicio CS000176_getDispositivos.", e);	
		}
		
		return dispositivos;
	}
	
	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#generaRequest(java.lang.Object)
	 */
	@Override
	protected String generaRequest(Object requestVO) {
		StringBuffer bf = new StringBuffer(800);
		LoginClienteVO loginVO = (LoginClienteVO)requestVO;
		
		bf.append(REQUEST_HEADER);
		bf.append("<soapenv:Header><val:headerRequest><head:consumidor><head:idApp>");
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
		bf.append("</head:sucursal></head:transaccion></val:headerRequest></soapenv:Header><soapenv:Body><val:ObtenerTipoDispositivoHabilitadoRequest><reqObtenerTipoDispositivoHabilitado><opob:Cuerpo><opob:canalConsulta>");
		
		if(MotorPagosHelper.isEmpresa(loginVO.getIdApp())){
			bf.append(ConstantesMotorPagos.ID_CANAL_BODY_EMPRESAS);
		}else{
			bf.append(ConstantesMotorPagos.ID_CANAL_BODY);
		}
				
		bf.append("</opob:canalConsulta><opob:rutCliente>");
		bf.append(MotorPagosHelper.cleanRut(loginVO.getRut()));
		bf.append("</opob:rutCliente><opob:grupo>");
		bf.append(ConstantesMotorPagos.ID_GRUPO_BCH);
		bf.append("</opob:grupo><opob:transaccionBancaria><opob:monto>");
		bf.append(ConstantesMotorPagos.ID_MONTO);
		bf.append("</opob:monto><opob:tipo>");
		bf.append(ConstantesMotorPagos.ID_TIPO);
		bf.append("</opob:tipo></opob:transaccionBancaria></opob:Cuerpo></reqObtenerTipoDispositivoHabilitado></val:ObtenerTipoDispositivoHabilitadoRequest></soapenv:Body></soapenv:Envelope>");		
		
		return bf.toString();
	}

	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#parseResponse(javax.xml.soap.SOAPMessage)
	 */
	@Override
	protected Object parseResponse(SOAPMessage response) {
		ArrayList<DispositivoSeguridadVO> listaDisp = null;

		SOAPEnvelope env;
		try {
			env = response.getSOAPPart().getEnvelope();
			SOAPBody sb = env.getBody();
			
			//Datos que se obtienen desde el cuerpo	
			QName codigoRetorno = new QName(RESPONSE_XML, "codigoRetorno");
			
			QName listaDispositivos = new QName(RESPONSE_XML, "ListaDispositivos");
			QName dispositivos = new QName(RESPONSE_XML, "Dispositivo");
			QName numeroSerie = new QName(RESPONSE_XML, "numeroSerie");
			QName tipo = new QName(RESPONSE_XML, "tipo");
			
			Iterator it = sb.getChildElements(); //ObtenerTipoDispositivoHabilitadoResponse	*****VER RESPUESTA EN SOAPUI******	    
			SOAPBodyElement sbe = (SOAPBodyElement) it.next();

			it=sbe.getChildElements(); //respObtenerTipoDispositivoHabilitadoResponse
			sbe=(SOAPBodyElement) it.next();

			it=sbe.getChildElements(); //Cuerpo
			sbe=(SOAPBodyElement) it.next();
			
			SOAPBodyElement codigo = (SOAPBodyElement)sbe.getChildElements(codigoRetorno).next();
			
			if(codigo==null){
				logger.error("Se produjo un error al invocar al servicio de obtencion de dispositivos de Seguridad.");
				return listaDisp;
			}else if (!codigo.getValue().equalsIgnoreCase("0")){
				logger.debug("CodigoRetorno : {}.", codigo.getValue());
				return listaDisp;
			}
			
			it=sbe.getChildElements(listaDispositivos);//Lista Dispositivos
			sbe=(SOAPBodyElement) it.next();
						
			it=sbe.getChildElements(dispositivos); //Dispositivo
			listaDisp = new ArrayList<DispositivoSeguridadVO>();
			
			while(it.hasNext()){			
				
				DispositivoSeguridadVO dispVO = new DispositivoSeguridadVO();
				
				SOAPBodyElement dispositivo =(SOAPBodyElement) it.next();
				
				Iterator i=dispositivo.getChildElements(numeroSerie);
				SOAPBodyElement item=(SOAPBodyElement) i.next();
				dispVO.setSerie(item.getValue());
								
				i=dispositivo.getChildElements(tipo);
				item=(SOAPBodyElement) i.next();
				dispVO.setTipo(item.getValue());
								
				if(dispVO.getSerie()==null || dispVO.getTipo()==null){
					logger.warn("Dispositivo de seguridad con problemas.");
				}
				listaDisp.add(dispVO);
				
			}									
		} catch (SOAPException e) {
			logger.error("Error, No fue posible parsear la respuesta del servicio CS000176_getDispositivos.", e);	
		}
		return listaDisp;
	}
	
//	public static void main(String[] args){
//		Logger logger = LoggerFactory.getLogger("");
//		ArrayList<DispositivoSeguridadVO> dispositivos = null;
//		ClienteCS000176_getDispositivos cliente = new ClienteCS000176_getDispositivos();
//		
//		LoginClienteVO loginVO = new LoginClienteVO();
//		//loginVO.setRut("10851080-3");
//		loginVO.setRut("14062716-k");
//		
//		String request = cliente.generaRequest(loginVO);
//		logger.debug("*******REQUEST*******");
//		logger.debug(request);
//		logger.debug("*******REQUEST*******");
//		
//		try {
//			SOAPMessage response = cliente.callWS(request, END_POINT);
//			
//			logger.debug("*******RESPONSE*******");
//			logger.debug(getSOAPMessageAsString(response));
//		    logger.debug("*******RESPONSE*******");
//		    
//		    dispositivos = (ArrayList<DispositivoSeguridadVO>)cliente.parseResponse(response);
//		    System.out.println(dispositivos.toString());
//			
//		} catch (Exception e) {
//			logger.error("Error, No fue posible invocar el servicio CS000176.", e);	
//		}
//		
//	}

}
