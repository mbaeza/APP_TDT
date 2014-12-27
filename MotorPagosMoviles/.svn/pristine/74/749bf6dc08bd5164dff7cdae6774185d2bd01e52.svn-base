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
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.RespuestaFichaChicaVO;
import cl.bch.motorpagos.vo.ObtenerDatosFichaChicaVO;

/**
 * @author fahumadaa
 * 
 */
public class ClienteCS000191_ObtenerDatosFichaChica extends GeneralClient {
	private static final Logger logger = LoggerFactory
			.getLogger(ClienteCS000191_ObtenerDatosFichaChica.class);
	private static final String END_POINT = "http://" + GeneralClient.host
			+ ":" + GeneralClient.port
			+ "/RenovacionInternet/CS000191_ObtenerDatosFichaChica";
	private static final String RESPONSE_XML = "http://osb.bancochile.cl/ESB/RenovacionInternet/ObtenerDatosFichaChica/OpObtenerResponse";
	private static final String REQUEST_HEADER = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:obt=\"http://osb.bancochile.cl/RenovacionInternet/ObtenerDatosFichaChica/\" xmlns:head=\"http://osb.bancochile.cl/common/HeaderRequest\" xmlns:opob=\"http://osb.bancochile.cl/ESB/RenovacionInternet/ObtenerDatosFichaChica/OpObtenerRequest\">";

	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public RespuestaFichaChicaVO callCS000191(
			ObtenerDatosFichaChicaVO obtenerDatosFichaChicaVO) {
		RespuestaFichaChicaVO fichaChicaCliente = null;

		String request = this.generaRequest(obtenerDatosFichaChicaVO);
		logger.debug("*******REQUEST*******");
		logger.debug(request);
		logger.debug("*******REQUEST*******");

		try {
			SOAPMessage response = this.callWS(request, END_POINT);

			logger.debug("*******RESPONSE*******");
			logger.debug(getSOAPMessageAsString(response));
			logger.debug("*******RESPONSE*******");

			fichaChicaCliente = (RespuestaFichaChicaVO) this
					.parseResponse(response);

		} catch (Exception e) {
			logger.error(
					"Error, No fue posible invocar el servicio CS000191_ObtenerDatosFichaChica.",
					e);
		}

		return fichaChicaCliente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cl.bch.motorpagos.wsclient.GeneralClient#generaRequest(java.lang.Object)
	 */
	@Override
	protected String generaRequest(Object requestVO) {
		StringBuffer bf = new StringBuffer(800);
		ObtenerDatosFichaChicaVO obtenerDatosFichaChicaVO = (ObtenerDatosFichaChicaVO) requestVO;

		bf.append(REQUEST_HEADER);
		bf.append("<soapenv:Header><obt:headerRequest><head:consumidor><head:idApp>");
		bf.append(ConstantesMotorPagos.ID_APP);
		bf.append("</head:idApp><head:usuario>");
		bf.append(MotorPagosHelper.cleanRut(obtenerDatosFichaChicaVO.getRut()));
		bf.append("</head:usuario></head:consumidor><head:transaccion><head:idTransaccionNegocio>");
		bf.append(ConstantesMotorPagos.ID_USUARIO);
		bf.append(MotorPagosHelper.cleanRut(obtenerDatosFichaChicaVO.getRut()));
		bf.append("</head:idTransaccionNegocio><head:fechaHora>");
		bf.append(MotorPagosHelper.getFechaActual());
		bf.append("</head:fechaHora><head:canal>");
		bf.append(ConstantesMotorPagos.ID_CANAL);
		bf.append("</head:canal><head:sucursal>");
		bf.append(ConstantesMotorPagos.ID_SUCURSAL);
		bf.append("</head:sucursal></head:transaccion></obt:headerRequest></soapenv:Header><soapenv:Body><obt:ObtenerRequest><reqObtener><opob:Cuerpo>");

		bf.append("<opob:rutCliente>");
		bf.append(obtenerDatosFichaChicaVO.getRut());
		bf.append("</opob:rutCliente>");
		bf.append("</opob:Cuerpo></reqObtener></obt:ObtenerRequest></soapenv:Body></soapenv:Envelope>");

		return bf.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cl.bch.motorpagos.wsclient.GeneralClient#parseResponse(javax.xml.soap
	 * .SOAPMessage)
	 */
	@Override
	protected Object parseResponse(SOAPMessage response) {
		RespuestaFichaChicaVO respuesta = new RespuestaFichaChicaVO();

		SOAPEnvelope env;
		try {
			env = response.getSOAPPart().getEnvelope();
			SOAPBody sb = env.getBody();

			// Datos que se obtienen desde el cuerpo
			QName retorno = new QName(RESPONSE_XML, "codigoRetorno");

			QName rutCliente = new QName(RESPONSE_XML, "rutCliente");
			QName nombre = new QName(RESPONSE_XML, "nombre");
			QName apellidoPaterno = new QName(RESPONSE_XML, "apellidoPaterno");
			QName nombreEjecutivo = new QName(RESPONSE_XML, "nombreEjecutivo");
			QName apellidoPaternoEjecutivo = new QName(RESPONSE_XML,
					"apellidoPaternoEjecutivo");
			QName emailEjecutivo = new QName(RESPONSE_XML, "emailEjecutivo");
			QName codigoPaisEjecutivo = new QName(RESPONSE_XML,
					"codigoPaisEjecutivo");
			QName codigoCiudadEjecutivo = new QName(RESPONSE_XML,
					"codigoCiudadEjecutivo");
			QName telefono = new QName(RESPONSE_XML, "telefono");
			QName segmento = new QName(RESPONSE_XML, "segmento");
			QName marca = new QName(RESPONSE_XML, "marca");
			QName banca = new QName(RESPONSE_XML, "banca");
			QName categoria = new QName(RESPONSE_XML, "categoria");
			QName codigoSegmento = new QName(RESPONSE_XML, "codigoSegmento");
			QName codigoMarca = new QName(RESPONSE_XML, "codigoMarca");
			QName codigoBanca = new QName(RESPONSE_XML, "codigoBanca");
			QName tipoCliente = new QName(RESPONSE_XML, "tipoCliente");
			QName sexo = new QName(RESPONSE_XML, "sexo");
			QName direccion = new QName(RESPONSE_XML, "direccion");
			QName numeroDireccion = new QName(RESPONSE_XML, "numeroDireccion");
			QName complementoDireccion = new QName(RESPONSE_XML,
					"complementoDireccion");

			QName comuna = new QName(RESPONSE_XML, "comuna");
			QName ciudad = new QName(RESPONSE_XML, "ciudad");
			QName region = new QName(RESPONSE_XML, "region");
			QName pais = new QName(RESPONSE_XML, "pais");

			QName EmailParticularCliente = new QName(RESPONSE_XML, "EmailParticularCliente");
			
			QName codigoCategoria = new QName(RESPONSE_XML, "codigoCategoria");

			Iterator it = sb.getChildElements(); // ObtenerResponse *****VER
													// RESPUESTA EN SOAPUI******
			SOAPBodyElement sbe = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(); // respObtener
			sbe = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(); // Cuerpo
			sbe = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(retorno);
			SOAPBodyElement itemRetorno = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(rutCliente);
			SOAPBodyElement beRutCliente = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(nombre);
			SOAPBodyElement beNombre = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(apellidoPaterno);
			SOAPBodyElement beApellidoPaterno = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(nombreEjecutivo);
			SOAPBodyElement beNombreEjecutivo = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(apellidoPaternoEjecutivo);
			SOAPBodyElement beApellidoPaternoEjecutivo = (SOAPBodyElement) it
					.next();

			it = sbe.getChildElements(emailEjecutivo);
			SOAPBodyElement beEmailEjecutivo = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(codigoPaisEjecutivo);
			SOAPBodyElement beCodigoPaisEjecutivo = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(codigoCiudadEjecutivo);
			SOAPBodyElement beCodigoCiudadEjecutivo = (SOAPBodyElement) it
					.next();

			it = sbe.getChildElements(telefono);
			SOAPBodyElement beTelefono = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(segmento);
			SOAPBodyElement beSegmento = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(marca);
			SOAPBodyElement beMarca = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(banca);
			SOAPBodyElement beBanca = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(categoria);
			SOAPBodyElement beCategoria = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(codigoSegmento);
			SOAPBodyElement beCodigoSegmento = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(codigoMarca);
			SOAPBodyElement beCodigoMarca = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(codigoBanca);
			SOAPBodyElement beCodigoBanca = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(tipoCliente);
			SOAPBodyElement beTipoCliente = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(sexo);
			SOAPBodyElement beSexo = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(direccion);
			SOAPBodyElement beDireccion = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(numeroDireccion);
			SOAPBodyElement beNumeroDireccion = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(complementoDireccion);
			SOAPBodyElement beComplementoDireccion = (SOAPBodyElement) it
					.next();

			it = sbe.getChildElements(comuna);
			SOAPBodyElement beComuna = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(ciudad);
			SOAPBodyElement beCiudad = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(region);
			SOAPBodyElement beRegion = (SOAPBodyElement) it.next();

			it = sbe.getChildElements(pais);
			SOAPBodyElement bePais = (SOAPBodyElement) it.next();
			
			it=sbe.getChildElements(EmailParticularCliente);
			SOAPBodyElement beEmail = (SOAPBodyElement) it.next();
			
			it = sbe.getChildElements(codigoCategoria);
			SOAPBodyElement beCodigoCategoria = (SOAPBodyElement) it.next();

			respuesta.setRetorno(MapperVO.getRespuestaVO(
					ConstantesMotorPagos.CODIGO_RETORNO_OK,
					"Operacion Exitosa."));

			respuesta.setRutCliente(beRutCliente.getValue());

			// Se extrae sólo el primer nombre del cliente y su primer apellido
			String nombreCompleto = beNombre.getValue();
			String[] nombres = nombreCompleto.split(" ");
			if (nombres.length > 0) {
				respuesta.setNombre(nombres[0] + " "
						+ beApellidoPaterno.getValue());
			} else {
				respuesta.setNombre(beApellidoPaterno.getValue());
			}

			// Marca: 2=Banco Edwards
			respuesta.setMarca(beMarca.getValue());
			respuesta.setCodigoMarca(beCodigoMarca.getValue());

			// Se concatena la direccion
			if (beDireccion.getValue() != null) {
				respuesta.setDireccion(beDireccion.getValue());
			}
			if (beNumeroDireccion.getValue() != null) {
				respuesta.setDireccion(respuesta.getDireccion() + " "
						+ beNumeroDireccion.getValue());
			}
			if (beComplementoDireccion.getValue() != null) {
				respuesta.setDireccion(respuesta.getDireccion() + " "
						+ beComplementoDireccion.getValue());
			}
			if (beComuna.getValue() != null) {
				respuesta.setComuna(beComuna.getValue());
			}
			if (beCiudad.getValue() != null) {
				respuesta.setCiudad(beCiudad.getValue());
			}
			if (beRegion.getValue() != null) {
				respuesta.setRegion(beRegion.getValue());
			}
			if (bePais.getValue() != null) {
				respuesta.setPais(bePais.getValue());
			}
			if (beEmail.getValue() != null) {
				respuesta.setMail(beEmail.getValue());
			}
			
			// Se extrae sólo el primer nombre del ejecutivo y su primer
			// apellido
			if (beNombreEjecutivo.getValue() != null && beApellidoPaternoEjecutivo.getValue() != null) {
				String nombreCompletoEjecutivo = beNombreEjecutivo.getValue();
				String[] nombresEjecutivo = nombreCompletoEjecutivo.split(" ");
				if (nombresEjecutivo.length > 0) {
					respuesta.setNombreEjecutivo(nombresEjecutivo[0] + " "
							+ beApellidoPaternoEjecutivo.getValue());
				} else {
					respuesta.setNombreEjecutivo(beApellidoPaternoEjecutivo
							.getValue());
				}
			} else {
				respuesta.setNombreEjecutivo("");
			}

			respuesta.setEmailEjecutivo(beEmailEjecutivo.getValue());

			// Telefono del ejecutivo
			respuesta.setCodigoPaisEjecutivo(beCodigoPaisEjecutivo.getValue());
			respuesta.setCodigoCiudadEjecutivo(beCodigoCiudadEjecutivo
					.getValue());
			respuesta.setTelefono(beTelefono.getValue());

			// Email del ejecutivo
			respuesta.setEmailEjecutivo(beEmailEjecutivo.getValue());

			// Categorias: 3=Persona Natural
			respuesta.setCategoria(beCategoria.getValue());
			respuesta.setCodigoCategoria(beCodigoCategoria.getValue());

			// Banca: 1=Personas
			respuesta.setBanca(beBanca.getValue());
			respuesta.setCodigoBanca(beCodigoBanca.getValue());

			respuesta.setTipoCliente(beTipoCliente.getValue()); // P
			respuesta.setSexo(beSexo.getValue()); // 2=Femenino

			// Segmento: 17=Personal
			respuesta.setSegmento(beSegmento.getValue());
			respuesta.setCodigoSegmento(beCodigoSegmento.getValue());

		} catch (Exception e) {
			logger.error(
					"Error, No fue posible parsear la respuesta del servicio CS000152.",
					e);
			respuesta.setRetorno(MapperVO.getRespuestaVO(
					ConstantesMotorPagos.CODIGO_RETORNO_NOOK,
					MensajesError.ERROR_GENERICO));
		}
		return respuesta;
	}

}
