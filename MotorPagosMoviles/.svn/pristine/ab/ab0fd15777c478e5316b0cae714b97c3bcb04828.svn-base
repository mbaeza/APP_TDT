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
import cl.bch.motorpagos.vo.RespuestaVO;
import cl.bch.motorpagos.vo.ValidarDispositivoSegVO;

public class ClienteCS000176_validaDispositivo extends GeneralClient {
	private static final Logger logger = LoggerFactory.getLogger(ClienteCS000176_validaDispositivo.class);
	private static final String END_POINT = "http://"+ GeneralClient.host +":"+ GeneralClient.port +"/RenovacionInternet/CS000176_ValidarDispositivoSeguridad";
	private static final String RESPONSE_XML = "http://osb.bancochile.cl/ESB/RenovacionInternet/ValidarDispositivoSeguridad/OpValidarDispositivoResponse";
	private static final String REQUEST_HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:val=\"http://osb.bancochile.cl/RenovacionInternet/ValidarDispositivoSeguridad/\" xmlns:head=\"http://osb.bancochile.cl/common/HeaderRequest\" xmlns:opv=\"http://osb.bancochile.cl/ESB/RenovacionInternet/ValidarDispositivoSeguridad/OpValidarDispositivoRequest\">";
	
	/**
	 * 
	 * @param validarVO
	 * @return
	 */
	public RespuestaVO callCS000176(ValidarDispositivoSegVO validarVO){
		RespuestaVO respuesta = null;
		
		String request = this.generaRequest(validarVO);
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
			logger.error("Error, No fue posible invocar el servicio CS000176.", e);	
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}
		
		return respuesta;
	}
	
	@Override
	protected String generaRequest(Object requestVO) {
		StringBuffer bf = new StringBuffer(1300);
		ValidarDispositivoSegVO clienteVO = (ValidarDispositivoSegVO)requestVO;
		
		bf.append(REQUEST_HEADER);
		bf.append("<soapenv:Header><ns1:headerRequest soapenv:actor=\"http://schemas.xmlsoap.org/soap/actor/next\" soapenv:mustUnderstand=\"0\" xmlns:ns1=\"http://osb.bancochile.cl/RenovacionInternet/ValidarDispositivoSeguridad/\"><ns2:consumidor xmlns:ns2=\"http://osb.bancochile.cl/common/HeaderRequest\"><ns2:idApp>");
		bf.append(ConstantesMotorPagos.ID_APP);
		bf.append("</ns2:idApp><ns2:usuario>");
		bf.append(MotorPagosHelper.cleanRut(clienteVO.getRutCliente()));
		bf.append("</ns2:usuario></ns2:consumidor><ns3:transaccion xmlns:ns3=\"http://osb.bancochile.cl/common/HeaderRequest\"><ns3:internalCode>");		
		bf.append(ConstantesMotorPagos.INTERNAL_CODE);
		bf.append("</ns3:internalCode><ns3:idTransaccionNegocio>");
		bf.append(ConstantesMotorPagos.ID_USUARIO);
		bf.append(MotorPagosHelper.cleanRut(clienteVO.getRutCliente()));	
		bf.append("</ns3:idTransaccionNegocio><ns3:fechaHora>");
		bf.append(MotorPagosHelper.getFechaActual());
		bf.append("</ns3:fechaHora><ns3:canal>");
		bf.append(ConstantesMotorPagos.ID_CANAL);
		bf.append("</ns3:canal><ns3:sucursal>");
		bf.append(ConstantesMotorPagos.ID_SUCURSAL);
		bf.append("</ns3:sucursal></ns3:transaccion></ns1:headerRequest></soapenv:Header><soapenv:Body><ValidarDispositivoRequest xmlns=\"http://osb.bancochile.cl/RenovacionInternet/ValidarDispositivoSeguridad/\"><reqValidarDispositivo xmlns=\"\"><ns4:Cuerpo xmlns:ns4=\"http://osb.bancochile.cl/ESB/RenovacionInternet/ValidarDispositivoSeguridad/OpValidarDispositivoRequest\"><ns4:canalConsulta>");
		
		if(MotorPagosHelper.isEmpresa(clienteVO.getIdApp())){
			bf.append(ConstantesMotorPagos.ID_CANAL_BODY_EMPRESAS);
		}else{
			bf.append(ConstantesMotorPagos.ID_CANAL_BODY);
		}
		
		bf.append("</ns4:canalConsulta><ns4:rutCliente>");
		bf.append(MotorPagosHelper.cleanRut(clienteVO.getRutCliente()));
		bf.append("</ns4:rutCliente><ns4:grupo>");
		bf.append(ConstantesMotorPagos.ID_GRUPO_BCH);
		bf.append("</ns4:grupo><ns4:tipoDispositivo>");		
		bf.append(clienteVO.getTipoDispSeguridad());
		bf.append("</ns4:tipoDispositivo>");
		
		if(clienteVO.getTipoDispSeguridad().equalsIgnoreCase(ConstantesMotorPagos.ID_DISP_DIGIPASS)){
			bf.append("<ns4:token>");
			bf.append(clienteVO.getClaveSeguridad());
			bf.append("</ns4:token>");
			
			if(MotorPagosHelper.isEmpresa(clienteVO.getIdApp())){
				bf.append("<ns4:companyId>");
				bf.append(MotorPagosHelper.cleanRut(clienteVO.getRutEmpresa()));
				bf.append("</ns4:companyId><ns4:parentCompanyId>");
				bf.append(MotorPagosHelper.cleanRut(clienteVO.getRutEmpresa()));
				bf.append("</ns4:parentCompanyId>");
			}			
		}else if(clienteVO.getTipoDispSeguridad().equalsIgnoreCase(ConstantesMotorPagos.ID_DISP_DIGICARD)){
			bf.append("<ns4:CoordenadasTarjeta><ns4:valor>");
			bf.append(clienteVO.getCoordenada1());
			bf.append("</ns4:valor></ns4:CoordenadasTarjeta><ns4:CoordenadasTarjeta><ns4:valor>");
			bf.append(clienteVO.getCoordenada2());
			bf.append("</ns4:valor></ns4:CoordenadasTarjeta><ns4:CoordenadasTarjeta><ns4:valor>");
			bf.append(clienteVO.getCoordenada3());
			bf.append("</ns4:valor></ns4:CoordenadasTarjeta>");
		}		
						
		bf.append("</ns4:Cuerpo></reqValidarDispositivo></ValidarDispositivoRequest></soapenv:Body></soapenv:Envelope>");
		
		return bf.toString();
	}
	
	@Override
	protected Object parseResponse(SOAPMessage response) {
		RespuestaVO respuesta = new RespuestaVO();

		SOAPEnvelope env;
		try {
			env = response.getSOAPPart().getEnvelope();
			SOAPBody sb = env.getBody();
		
			QName retornoServicio = new QName(RESPONSE_XML, "retornoServicio");
			
			SOAPBodyElement sbe = (SOAPBodyElement) sb.getChildElements().next(); //ValidarDispositivoResponse	*****VER RESPUESTA EN SOAPUI******
			sbe=(SOAPBodyElement) sbe.getChildElements().next(); //respValidarDispositivo
			sbe=(SOAPBodyElement) sbe.getChildElements().next(); //Cuerpo
			SOAPBodyElement codigo=(SOAPBodyElement) sbe.getChildElements(retornoServicio).next();
			
			if(codigo.getValue().equalsIgnoreCase("0")){
				respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Validacion Exitosa.");
			}else{
				if (codigo.getValue() == "12"){  //Codigo 12, Dispositivo bloqueado
					respuesta = MapperVO.getRespuestaVO(codigo.getValue(), "Dispositivo de Seguridad bloqueado.");
				} else if (codigo.getValue() == "13"){  //Codigo 13, Dispositivo bloqueado
					respuesta = MapperVO.getRespuestaVO(codigo.getValue(), "Dispositivo de Seguridad bloqueado.");
				} else { //Codigo 11 clave inválida
					respuesta = MapperVO.getRespuestaVO(codigo.getValue(), "Clave Inválida, reintente.");
				}
			}			
			
		} catch (SOAPException e) {
			logger.error("Error, No fue posible parsear la respuesta del servicio CS000176_validaDispositivo.", e);	
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}
		return respuesta;
	}
	
//	public static void main(String[] args){
//		Logger logger = LoggerFactory.getLogger("");
//		RespuestaVO respuesta = null;
//		ClienteCS000176_validaDispositivo cliente = new ClienteCS000176_validaDispositivo();
//		
//		ClienteVO clienteVO = new ClienteVO();
//		clienteVO.setRut("7743915-3");
//		clienteVO.setClaveSeguridad("233135");
//		clienteVO.setTipoDispSeguridad("1");
//		clienteVO.setCoordenada1("77");
//		clienteVO.setCoordenada2("77");
//		clienteVO.setCoordenada3("77");
//		clienteVO.setIdApp("APP");
//		
//		String request = cliente.generaRequest(clienteVO);
//		logger.debug("*******REQUEST*******");
//		System.out.println(request);
//		logger.debug("*******REQUEST*******");
//		
//		try {
//			SOAPMessage response = cliente.callWS(request, END_POINT);
//			
//			logger.debug("*******RESPONSE*******");
//			 System.out.println(getSOAPMessageAsString(response));
//		    logger.debug("*******RESPONSE*******");
//		    
//		    respuesta = (RespuestaVO)cliente.parseResponse(response);
//		    System.out.println(respuesta.toString());
//			
//		} catch (Exception e) {
//			logger.error("Error, No fue posible invocar el servicio CS000176.", e);	
//		}
//	}
}
