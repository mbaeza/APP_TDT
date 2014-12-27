/**
 * 
 */
package cl.bch.motorpagos.wsclient;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class ClienteCS000177 extends GeneralClient {
	private static final Logger logger = LoggerFactory.getLogger(ClienteCS000384.class);
	private static final String END_POINT = "http://"+ GeneralClient.host +":"+ GeneralClient.port +"/Correo/CS000177_EnvioCorreoInternoExterno";
	private static final String REQUEST_HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:env=\"http://osb.bancochile.cl/RenovacionInternet/EnvioCorreoInternoExterno/\" xmlns:head=\"http://osb.bancochile.cl/common/HeaderRequest\" xmlns:open=\"http://osb.bancochile.cl/ESB/RenovacionInternet/EnvioCorreoInternoExterno/OpEnvioCorreoInternoExternoRequest\">";

	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public RespuestaVO callCS000177(TransferenciaVO tefVO){
		RespuestaVO respuesta = new RespuestaVO();
		
		String request = this.generaRequest(tefVO);
		if(request==null){
			logger.error("Error, al intentar parsear el request en CS000177.");
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}else{
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
				logger.error("Error, No fue posible invocar el servicio CS000177.", e);	
				respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
			}
		}
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#generaRequest(java.lang.Object)
	 */
	@Override
	protected String generaRequest(Object requestVO) {
		StringBuffer bf = new StringBuffer(16000);
		TransferenciaVO tefVO = (TransferenciaVO)requestVO;
		
		bf.append(REQUEST_HEADER);
		bf.append("<soapenv:Header><env:headerRequest><head:consumidor><head:idApp>");
		bf.append(ConstantesMotorPagos.ID_APP);
		bf.append("</head:idApp><head:usuario>");
		bf.append(MotorPagosHelper.cleanRut(tefVO.getRutClienteOrigen()));
		bf.append("</head:usuario></head:consumidor><head:transaccion><head:internalCode>");
		bf.append(ConstantesMotorPagos.INTERNAL_CODE);
		bf.append("</head:internalCode><head:idTransaccionNegocio>");
		bf.append(ConstantesMotorPagos.ID_USUARIO);
		bf.append(MotorPagosHelper.cleanRut(tefVO.getRutClienteOrigen()));		
		bf.append("</head:idTransaccionNegocio><head:fechaHora>");
		bf.append(MotorPagosHelper.getFechaActual());
		bf.append("</head:fechaHora><head:canal>");
		bf.append(ConstantesMotorPagos.ID_CANAL);
		bf.append("</head:canal><head:sucursal>");
		bf.append(ConstantesMotorPagos.ID_SUCURSAL);
		bf.append("</head:sucursal></head:transaccion></env:headerRequest></soapenv:Header><soapenv:Body><env:EnvioCorreoInternoExterno><reqEnvioCorreoInternoExterno><open:Cuerpo><open:to>");
		bf.append(tefVO.getMailCliente());
		bf.append("</open:to><open:from>serviciodetransferencias@bancochile.cl</open:from><open:date>");
		bf.append(MotorPagosHelper.getFechaActual());
			
		bf.append("</open:date><open:subject>Comprobante de Pago.</open:subject><open:contentType>text/html</open:contentType><open:replyTo>serviciodetransferencias@bancochile.cl</open:replyTo><open:replyAddress>serviciodetransferencias@bancochile.cl</open:replyAddress><open:body>"
				+"<![CDATA[<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"
				+"<html xmlns=\"http://www.w3.org/1999/xhtml\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">"
				+"<head><meta name=\"viewport\" content=\"width=device-width\" /><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
				+"<title>Banco de Chile | Mi Pago</title><style type=\"text/css\">img { max-width: 100% !important; }body { -webkit-font-smoothing: antialiased !important; -webkit-text-size-adjust: none !important; width: 100% !important; height: 100% !important; }</style></head>"		
				+"<body bgcolor=\"#FFFFFF\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; -webkit-font-smoothing: antialiased !important; -webkit-text-size-adjust: none !important; width: 100% !important; height: 100% !important; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">"
				+"<table class=\"head-wrap\" bgcolor=\"#f6f7f8\" style=\"border-bottom:1px solid #e7e8e8;font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; width: 100%; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">"
				+"<tr><td>&nbsp;</td><td class=\"header container\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; display: block !important; max-width: 600px !important; clear: both !important; margin-top: 0; margin-right: auto; margin-bottom: 0; margin-left: auto; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">"
				+"<div class=\"content\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; max-width: 600px; display: block; margin-top: 0; margin-right: auto; margin-bottom: 0; margin-left: auto; padding-top: 5px; padding-right: 5px; padding-bottom: 5px; padding-left: 5px;\">"
				+"<table bgcolor=\"#f6f7f8\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; width: 100%; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">"
				+"<tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\"><img src=\"https://pagosmoviles.bancochile.cl/bchmovil/images/mi_pago.png\" alt=\"Banco de Chile\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; max-width: 100% !important; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\" /></td>"
				+"</tr></table></div></td><td>&nbsp;</td></tr></table><table class=\"body-wrap\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; width: 100%; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\"><tr><td>&nbsp;</td>"
				+"<td class=\"container\" bgcolor=\"#FFFFFF\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; display: block !important; max-width: 600px !important; clear: both !important; margin-top: 0; margin-right: auto; margin-bottom: 0; margin-left: auto; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">"
				+"<div class=\"content\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; max-width: 600px; display: block; margin-top: 0; margin-right: auto; margin-bottom: 0; margin-left: auto; padding-top: 15px; padding-right: 15px; padding-bottom: 15px; padding-left: 15px;\">"
				+"<table style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; width: 100%; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">"
				+"<tr><td><h3 style=\"font-family: 'HelveticaNeue-Light', 'Helvetica Neue Light', 'Helvetica Neue', Helvetica, Arial, 'Lucida Grande', sans-serif; line-height: 1.1; color: #000; font-weight: 500; font-size: 24px; margin-top: 0; margin-right: 0; margin-bottom: 15px; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">Comprobante de transferencia electr&oacute;nica de fondos</h3>"
				+"<p class=\"lead\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; font-weight: normal; font-size: 15px; line-height: 1.6; color: #666; margin-top: 0; margin-right: 0; margin-bottom: 10px; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">Estimado(a) <strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">");
				
		bf.append(tefVO.getNombreCliente());
		bf.append("</strong>,<br /> Le informamos que su transferencia de fondos a nuestro(a) cliente <strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">");
		bf.append(tefVO.getNombreComercio());
		
		if(tefVO.getTipoComercio().equals(ConstantesMotorPagos.COMERCIO_PERSONA)){
		
			bf.append("</strong> se ha efectuado exitosamente.</p><br /><table class=\"callout\" width=\"100%\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; font-size: 14px; width: 100%; margin-top: 0; margin-right: 0; margin-bottom: 10px; margin-left: 0; padding-top: 15px; padding-right: 5px; padding-bottom: 15px; padding-left: 5px; border-top-color: #8DCFFF; border-right-color: #8DCFFF; border-bottom-color: #8DCFFF; border-left-color: #8DCFFF; border-top-style: solid; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; background-color: #D9EFFF;\" bgcolor=\"#D9EFFF\">");
			bf.append("<tr><td width=\"10px\" style=\"padding: 0; margin: 10px 0px 0px;\">&nbsp;</td><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\"><strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">Beneficiario:</strong></td>");
			bf.append("<td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">");
			bf.append(tefVO.getNombreComercio());
			bf.append("</td><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td></tr><tr><td width=\"10px\" style=\"padding: 0; margin: 10px 0px 0px;\">&nbsp;</td><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\"><strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">Rut:</strong></td>");
			bf.append("<td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">");
			bf.append(MotorPagosHelper.formatearRutPuntosGuion(tefVO.getRutComercio()));
			bf.append("</td><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td></tr><tr><td width=\"10px\" style=\"padding: 0; margin: 10px 0px 0px;\">&nbsp;</td><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\"><strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">Email:</strong></td>");
			bf.append("<td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">");
			bf.append(tefVO.getMailComercio());
			bf.append("</td><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td></tr><tr><td width=\"10px\" style=\"padding: 0; margin: 10px 0px 0px;\">&nbsp;</td>");
			bf.append("<td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\"><strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">N&ordm; de cuenta:</strong></td>");
			bf.append("<td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">");
			//TODO FORMATEAR CUENTA COMERCIO
			bf.append(tefVO.getCtaDestino());
			bf.append("</td><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td></tr><tr><td width=\"10px\" style=\"padding: 0; margin: 10px 0px 0px;\">&nbsp;</td>");
			bf.append("<td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td></tr><tr><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td></tr>");
			bf.append("<tr><td width=\"10px\">&nbsp;</td><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; font-size: 18px; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; border-top-style: dotted; border-top-color: #8DCFFF; border-top-width: 1px; padding-top: 15px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px;\"><strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 10px; margin-right: 0; margin-bottom: 0px; margin-left: 0;\">Monto:</strong></td>");
			bf.append("<td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; font-size: 18px; border-top-style: dotted; border-top-color: #8DCFFF; border-top-width: 1px; padding-left: 0px; padding-bottom: 0px; padding-right: 0px; padding-top: 15px; margin-top: 10px; margin-right: 0; margin-bottom: 0px; margin-left: 0;\">$"); 
			bf.append(MotorPagosHelper.formatearMonto(tefVO.getMonto()));
			bf.append("</td><td width=\"10px\">&nbsp;</td></tr></table><table width=\"100%\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; font-size: 12px; width: 100%; margin-top: 0px; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 15px; padding-right: 0; padding-bottom: 0; padding-left: 0;\">");
			bf.append("<tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\"><strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">Comprobante: </strong><br />");
			bf.append(MotorPagosHelper.formatearIdTrx(tefVO.getClaveOperacion()));
			bf.append("<br /><br /><strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">Fecha y Hora: </strong><br />");
			bf.append(tefVO.getFechaHoraTrx());
			bf.append("</td><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; text-align: right; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\" align=\"right\"><img src=\"https://pagosmoviles.bancochile.cl/bchmovil/images/comprobante.jpg\" style=\"max-width: 100% !important; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\" /></td>");
			bf.append("</tr></table></td></tr></table></div></td><td>&nbsp;</td></tr></table>");
					
		}else if(tefVO.getTipoComercio().equals(ConstantesMotorPagos.COMERCIO_EMPRESA)){
			
			bf.append("</strong> se ha efectuado exitosamente.</p><br /><table class=\"callout\" width=\"100%\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; font-size: 14px; width: 100%; margin-top: 0; margin-right: 0; margin-bottom: 10px; margin-left: 0; padding-top: 15px; padding-right: 5px; padding-bottom: 15px; padding-left: 5px; border-top-color: #8DCFFF; border-right-color: #8DCFFF; border-bottom-color: #8DCFFF; border-left-color: #8DCFFF; border-top-style: solid; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; background-color: #D9EFFF;\" bgcolor=\"#D9EFFF\">");
			bf.append("<tr><td width=\"10px\" style=\"padding: 0; margin: 10px 0px 0px;\">&nbsp;</td><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\"><strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">Beneficiario:</strong></td>");
			bf.append("<td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">");
			bf.append(tefVO.getNombreComercio());
			bf.append("</td><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td></tr><tr><td width=\"10px\" style=\"padding: 0; margin: 10px 0px 0px;\">&nbsp;</td><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\"><strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">Rut:</strong></td>");
			bf.append("<td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">");
			bf.append(MotorPagosHelper.formatearRutPuntosGuion(tefVO.getRutComercio()));
			bf.append("</td><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td></tr><tr><td width=\"10px\" style=\"padding: 0; margin: 10px 0px 0px;\">&nbsp;</td><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\"><strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">Email:</strong></td>");
			bf.append("<td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">");
			bf.append(tefVO.getMailComercio());
			bf.append("</td><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td></tr><tr><td width=\"10px\" style=\"padding: 0; margin: 10px 0px 0px;\">&nbsp;</td>");
			bf.append("<td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\"><strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">N&ordm; de cuenta:</strong></td>");
			bf.append("<td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">");
			//TODO FORMATEAR CUENTA COMERCIO
			bf.append(tefVO.getCtaDestino());
			bf.append("</td><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td></tr><tr><td width=\"10px\" style=\"padding: 0; margin: 10px 0px 0px;\">&nbsp;</td>");
			bf.append("<td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td></tr><tr><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td><td width=\"10px\" style=\"padding: 0; margin:0px;\">&nbsp;</td></tr>");
			bf.append("<tr><td width=\"10px\">&nbsp;</td><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; font-size: 18px; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; border-top-style: dotted; border-top-color: #8DCFFF; border-top-width: 1px; padding-top: 15px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px;\"><strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 10px; margin-right: 0; margin-bottom: 0px; margin-left: 0;\">Monto:</strong></td>");
			bf.append("<td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; font-size: 18px; border-top-style: dotted; border-top-color: #8DCFFF; border-top-width: 1px; padding-left: 0px; padding-bottom: 0px; padding-right: 0px; padding-top: 15px; margin-top: 10px; margin-right: 0; margin-bottom: 0px; margin-left: 0;\">$"); 
			bf.append(MotorPagosHelper.formatearMonto(tefVO.getMonto()));
			bf.append("</td><td width=\"10px\">&nbsp;</td></tr></table><table width=\"100%\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; font-size: 12px; width: 100%; margin-top: 0px; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 15px; padding-right: 0; padding-bottom: 0; padding-left: 0;\">");
			bf.append("<tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\"><strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">Comprobante: </strong><br />");
			bf.append(MotorPagosHelper.formatearIdTrx(tefVO.getClaveOperacion()));
			bf.append("<br /><br /><strong style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">Fecha y Hora: </strong><br />");
			bf.append(tefVO.getFechaHoraTrx());
			bf.append("</td><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; text-align: right; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\" align=\"right\"><img src=\"comprobante.jpg\" style=\"max-width: 100% !important; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\" /></td>");
			bf.append("</tr></table></td></tr></table></div></td><td>&nbsp;</td></tr></table>");
			
		}		
		
		bf.append("<table class=\"footer-wrap\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; width: 100%; clear: both !important; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">"
				+"<tr><td>&nbsp;</td><td class=\"container\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; display: block !important; max-width: 600px !important; clear: both !important; margin-top: 0; margin-right: auto; margin-bottom: 0; margin-left: auto; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">"
				+"<div class=\"content\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; max-width: 600px; display: block; margin-top: 0; margin-right: auto; margin-bottom: 0; margin-left: auto; padding-top: 0px; padding-right: 15px; padding-bottom: 15px; padding-left: 15px;\">"
				+"<table style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; width: 100%; margin-top: 0; margin-right: 0; margin-bottom: 0; margin-left: 0; padding-top: 0; padding-right: 0; padding-bottom: 0; padding-left: 0;\">"
				+"<tr><td align=\"left\"><p class=\"bordertop\" style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; font-weight: normal; font-size: 11px; line-height: 1.6; color: #999; border-top-style: solid; border-top-width: 1px; border-top-color: #dbdbdb; margin-top: 0; margin-right: 0; margin-bottom: 10px; margin-left: 0; padding-top: 15px; padding-right: 0; padding-bottom: 0; padding-left: 0;\">Inf&oacute;rmese sobre la garant&iacute;a estatal de los dep&oacute;sitos en su banco o en www.sbif.cl<br />"
				+"&copy; 2014, Banco de Chile. Todos los Derechos Reservados.</p></td></tr></table></div></td><td>&nbsp;</td></tr></table></body></html>"	 
				+"]]></open:body></open:Cuerpo></reqEnvioCorreoInternoExterno></env:EnvioCorreoInternoExterno></soapenv:Body></soapenv:Envelope>");		
		
		return bf.toString();	
		
	}

	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#parseResponse(javax.xml.soap.SOAPMessage)
	 */
	@Override
	protected Object parseResponse(SOAPMessage response) {
		RespuestaVO respuesta = new RespuestaVO();

		SOAPEnvelope env;
		try {
			env = response.getSOAPPart().getEnvelope();
			SOAPBody sb = env.getBody();
			
			//Datos que se obtienen desde el cuerpo
			
			SOAPBodyElement sbe = (SOAPBodyElement) sb.getChildElements().next();//EnvioCorreoInternoExternoResponse	*****VER RESPUESTA EN SOAPUI******	 
			sbe=(SOAPBodyElement) sbe.getChildElements().next();//respEnvioCorreoInternoExterno			
			sbe=(SOAPBodyElement) sbe.getChildElements().next();//Cuerpo
			
			SOAPBodyElement codRetorno = (SOAPBodyElement)sbe.getChildElements().next();
						
			if(codRetorno.getValue().equals("00")){
				respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "Operacion Exitosa.");
			}else{
				respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
			}							
		} catch (Exception e) {
			logger.error("Error, No fue posible parsear la respuesta del servicio CS000177.", e);
			respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		} 
		return respuesta;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]){
		ClienteCS000177 cliente = new ClienteCS000177();
		TransferenciaVO tefVO = new TransferenciaVO();
		tefVO.setRutClienteOrigen("12345-K");
		tefVO.setNombreCliente("Nombre Cliente");
		tefVO.setNombreComercio("Nombre Comercio");
		tefVO.setRutComercio("1-9");
		tefVO.setNombreConvenio("Nombre Suc");
		tefVO.setIdVendedor("idVendedor");
		tefVO.setGlosaTrx("glosa trx");
		tefVO.setFechaHoraTrx("30-04-2014 11:11:11");
		tefVO.setMonto("10000");
		tefVO.setClaveOperacion("IDTRX0001");
		tefVO.setTipoComercio(ConstantesMotorPagos.COMERCIO_PERSONA);
		tefVO.setMailCliente("prueba@bancochile.cl");
		tefVO.setMailComercio("comercio@email.com");
		tefVO.setCtaDestino("123445676");
		
		System.out.println(cliente.callCS000177(tefVO).toString());
	}

}
