/**
 * 
 */
package cl.bch.motorpagos.wsclient;

import java.util.Iterator;

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
import cl.bch.motorpagos.vo.TallerDeProductoVO;
import cl.bch.motorpagos.vo.TransferenciaVO;

/**
 * @author boyanedel
 *
 */
public class ClienteCS003006 extends GeneralClient {
	private static final Logger logger = LoggerFactory.getLogger(ClienteCS003006.class);
	private static final String END_POINT = "http://"+ GeneralClient.host +":"+ GeneralClient.port +"/TransferenciaElectronicaFondos/CS003006_OrquestadorTEF";
	private static final String REQUEST_HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:head=\"http://osb.bancochile.cl/ESB/nuevatef/common/HeaderRequest\" xmlns:nuev=\"http://osb.bancochile.cl/ESB/nuevatef\" xmlns:tip=\"http://osb.bancochile.cl/ESB/nuevatef/common/tipos\">";
	
	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public RespuestaVO callCS003006(TransferenciaVO transferencia){
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
		    
		    if(respuesta==null || respuesta.getCodigoRetorno()==null){
		    	 respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		    }		    
		} catch (Exception e) {
			logger.error("Error, No fue posible invocar el servicio CS003006.", e);	
			 respuesta = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
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

		//Formatea valores de campos
		String rutComercio = MotorPagosHelper.cleanRut(transferenciaVO.getRutComercio());
		rutComercio = MotorPagosHelper.formateaString(rutComercio, "0", 9);

		String rutClienteOrigen = MotorPagosHelper.cleanRut(transferenciaVO.getRutClienteOrigen());
		rutClienteOrigen = MotorPagosHelper.formateaString(rutClienteOrigen, "0", 9);
		
		String cuentaComercio = MotorPagosHelper.formateaString(transferenciaVO.getCtaDestino(), "0", 12);
		
		String cuentaClienteOrigen = MotorPagosHelper.formateaString(transferenciaVO.getCtaOrigen(), "0", 12);
		
		String nombreClienteOrigen = MotorPagosHelper.formateaString(transferenciaVO.getNombreCliente(), 22);
		
		String nombreComercio  = MotorPagosHelper.formateaString(transferenciaVO.getNombreComercio(), 22);
		
		String canal = ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_BANCO);
		
		String trxId = transferenciaVO.getClaveOperacion();
		
		String asunto = transferenciaVO.getGlosaTrx();
		
		//TODO Parametrizar Taller de Productos
		String data1;
		String data2;
		String data3;
		String data4;
		String data5;
		String data6;
		String data7;
		String data8;
		String data9;
		
		if (rutClienteOrigen == rutComercio){
			data1 = cuentaComercio; //cuenta abono
			data2 = "";
			data3 = "";
			data4 = rutClienteOrigen;
			data5 = "";
			data6 = canal;
			data7 = trxId;
			data8 = cuentaComercio;
			data9 = rutComercio;
		} else {
			data1 = nombreComercio;
			data2 = cuentaComercio;
			data3 = canal;
			data4 = rutClienteOrigen;
			data5 = cuentaClienteOrigen;
			data6 = nombreClienteOrigen;
			data7 = trxId;
			data8 = asunto;
			data9 = rutComercio;
		}
		
		//Construccion del request
		bf.append(REQUEST_HEADER);		
		
		bf.append("<soapenv:Header><head:cabeceraBus><head:user>");
		
		bf.append(rutClienteOrigen);
		bf.append("</head:user><head:ipOrigen>");
		//TODO Setear IP
		bf.append("152.139.28.142");
		bf.append("</head:ipOrigen><head:pathServices>");
		bf.append("cl.bch.motorpagos.wsclient.ClienteCS003006");
		bf.append("</head:pathServices><head:txId>");
		bf.append(transferenciaVO.getClaveOperacion());
		bf.append("</head:txId><head:startDate>");
		bf.append(MotorPagosHelper.getFechaActual());
		bf.append("</head:startDate><head:channelId>");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_HEADER_ID_CANAL));
		bf.append("</head:channelId><head:oficinaOrigen>");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_OFICINA_ORIGEN));
		bf.append("</head:oficinaOrigen></head:cabeceraBus></soapenv:Header><soapenv:Body><nuev:MensajeTEFRequest><nuev:DatosCabeceraNegocio><nuev:oficinaOrigen>");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_OFICINA_ORIGEN));
		bf.append("</nuev:oficinaOrigen><nuev:canalOrigen>");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_CANAL));
		bf.append("</nuev:canalOrigen><nuev:fechaContable>");
		bf.append(transferenciaVO.getFechaContable());
		bf.append("</nuev:fechaContable><nuev:fechaCorriente>");
		bf.append(MotorPagosHelper.getFechaActual());
		bf.append("</nuev:fechaCorriente><nuev:txId>");
		bf.append(transferenciaVO.getClaveOperacion());
		bf.append("</nuev:txId><nuev:referencia>");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_REFERENCIA));
		bf.append("</nuev:referencia></nuev:DatosCabeceraNegocio><nuev:DatosNegocio tipoTEF=\"");
		bf.append(transferenciaVO.getTipoTransferencia());
		bf.append("\"><nuev:monto moneda=\"CLP\">");
		bf.append(transferenciaVO.getMonto());
		bf.append("</nuev:monto><nuev:detalleCargo codProducto=\"");
		bf.append(transferenciaVO.getTaller().getCodProductoCargo());
		bf.append("\" codTransaccion=\"");
		bf.append(transferenciaVO.getTaller().getCodTrxCargo());
		bf.append("\" codExtendido=\"");
		bf.append(transferenciaVO.getTaller().getCodExtCargo());
		bf.append("\"><tip:numeroProducto codBanco=\"");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_CODIGO_BANCO));
		bf.append("\" tipoProducto=\"");
		bf.append(transferenciaVO.getTokenCtaOrigen());
		bf.append("\">");
		bf.append(cuentaClienteOrigen); //MotorPagosHelper.formateaString(transferenciaVO.getCtaOrigen(), "0", 12)
		bf.append("</tip:numeroProducto><tip:cliente><tip:rutCliente>");
		bf.append(rutClienteOrigen);
		bf.append("</tip:rutCliente><tip:nombreCliente>");
		bf.append(nombreClienteOrigen); //transferenciaVO.getNombreCliente()
		bf.append("</tip:nombreCliente></tip:cliente><tip:segmento>");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_SEGMENTO));
		bf.append("</tip:segmento><tip:bancaCliente>");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_BANCA));	
		bf.append("</tip:bancaCliente></nuev:detalleCargo><nuev:detalleAbono codProducto=\"");
		bf.append(transferenciaVO.getTaller().getCodProductoAbono());
		bf.append("\" codTransaccion=\"");
		bf.append(transferenciaVO.getTaller().getCodTrxAbono());
		bf.append("\" codExtendido=\"");
		bf.append(transferenciaVO.getTaller().getCodExtAbono());
		bf.append("\"><tip:numeroProducto codBanco=\"");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_CODIGO_BANCO));
		bf.append("\" tipoProducto=\"");
		bf.append(transferenciaVO.getTokenCtaDestino());
		bf.append("\">");
		bf.append(cuentaComercio); //MotorPagosHelper.formateaString(transferenciaVO.getCtaDestino(), "0", 12)
		bf.append("</tip:numeroProducto><tip:cliente><tip:rutCliente>");

		bf.append(rutComercio);

		bf.append("</tip:rutCliente><tip:nombreCliente>");
		bf.append(nombreComercio); // transferenciaVO.getNombreComercio()
		bf.append("</tip:nombreCliente></tip:cliente></nuev:detalleAbono><nuev:campo><nuev:valorCampo nombreCampo=\"glosaBancoOrigen\">");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_BANCO));
		bf.append("</nuev:valorCampo><nuev:valorCampo nombreCampo=\"glosaBancoDestino\">");
		bf.append(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.TEF_BANCO));

		//Data Variable
		bf.append("</nuev:valorCampo><nuev:valorCampo nombreCampo=\"DATA1\">");
		bf.append(data1);
		bf.append("</nuev:valorCampo><nuev:valorCampo nombreCampo=\"DATA2\">");
		bf.append(data2);
		bf.append("</nuev:valorCampo><nuev:valorCampo nombreCampo=\"DATA3\">");
		bf.append(data3);
		bf.append("</nuev:valorCampo><nuev:valorCampo nombreCampo=\"DATA4\">");
		bf.append(data4);
		bf.append("</nuev:valorCampo><nuev:valorCampo nombreCampo=\"DATA5\">");
		bf.append(data5);
		bf.append("</nuev:valorCampo><nuev:valorCampo nombreCampo=\"DATA6\">");

		bf.append(data6); 
		bf.append("</nuev:valorCampo><nuev:valorCampo nombreCampo=\"DATA7\">");
		bf.append(data7); 
		bf.append("</nuev:valorCampo><nuev:valorCampo nombreCampo=\"DATA8\">");
		bf.append(data8); 
		bf.append("</nuev:valorCampo><nuev:valorCampo nombreCampo=\"DATA9\">");
		bf.append(data9); 
		bf.append("</nuev:valorCampo></nuev:campo></nuev:DatosNegocio></nuev:MensajeTEFRequest></soapenv:Body></soapenv:Envelope>");
				
		return bf.toString();
	}

	/* (non-Javadoc)
	 * @see cl.bch.motorpagos.wsclient.GeneralClient#parseResponse(javax.xml.soap.SOAPMessage)
	 */
	@Override
	protected Object parseResponse(SOAPMessage response) {
		RespuestaVO resp=new RespuestaVO();
		SOAPEnvelope env;
		try {
			env = response.getSOAPPart().getEnvelope();
			SOAPBody sb = env.getBody();
			SOAPBodyElement sbe = (SOAPBodyElement)sb.getChildElements().next(); //MensajeTEFResponse
			
			Iterator it = sbe.getChildElements();
			
			while(it.hasNext()){
				SOAPBodyElement item = (SOAPBodyElement)it.next();
				
				if("nuev:codigoRespuesta".equals(item.getNodeName())){
					resp.setCodigoRetorno(item.getValue());
				}else if("nuev:descripcionRetorno".equals(item.getNodeName())){
					resp.setGlosaRetorno(item.getValue());
				}
			}
							
		} catch (Exception e) {
			logger.error("Error, No fue posible parsear la respuesta del servicio CS003006.", e);	
			resp = MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO);
		}
		return resp;
	}
	
//	/**
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		ClienteCS003006 tef=new ClienteCS003006();
//		TransferenciaVO vo=new TransferenciaVO();
//		
//		vo.setClaveOperacion("INTERNET_P2014081200018136330");
//		vo.setRutClienteOrigen("086650010");
//		vo.setRutComercio("086650010");
//		vo.setCtaOrigen("070090009103");
//		vo.setCtaDestino("072310000307");
//		vo.setMonto("1");
//		vo.setTokenCtaDestino("JUV");
//		vo.setTokenCtaOrigen("JUV");
//		vo.setFechaContable("2014-08-13");
//		vo.setNombreCliente("Cliente TEF");
//		vo.setNombreComercio("Comercio TEF");
//		vo.setTipoTransferencia("TMC");
//		
//		TallerDeProductoVO taller = new TallerDeProductoVO();
//		taller.setCodProductoCargo("BMNT");
//		taller.setCodTrxCargo("10D");
//		taller.setCodExtCargo("00690");
//		taller.setCodProductoAbono("BMNT");
//		taller.setCodTrxAbono("56B");
//		taller.setCodExtAbono("00690");
//		
//		vo.setTaller(taller);
//		
//		RespuestaVO resp = tef.callCS003006(vo);
//		
//		System.out.println(resp.getCodigoRetorno());
//		System.out.println(resp.getGlosaRetorno());
//	}

}
