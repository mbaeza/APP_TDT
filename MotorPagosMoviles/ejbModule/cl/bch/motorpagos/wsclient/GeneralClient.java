/**
 * 
 */
package cl.bch.motorpagos.wsclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.util.ConfigurationLoader;

/**
 * @author boyanedel
 *
 */
public abstract class GeneralClient {
	protected static String host = ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.ALIAS_SERVER);
	protected static String port = ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.PORT_SERVER);
	protected static String portTeller = ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.PORT_SERVER_TELLER);
	protected static String ENCODE_HEADER = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";	
	//PUEDES CONSTRUIR CON OBJETOS TAMBIEN
	//REF: http://docs.oracle.com/cd/E19340-01/820-6767/aeqex/index.html
	
	/**
	 * 
	 * @param msg
	 * @return
	 */
	protected static String getSOAPMessageAsString(SOAPMessage msg){
		Logger logger = LoggerFactory.getLogger(GeneralClient.class);
		ByteArrayOutputStream baos = null;
		String s = null;
		try {
			baos = new ByteArrayOutputStream();
			msg.writeTo(baos);
			s = baos.toString();
		} catch(SOAPException | IOException e) {
			logger.error("Error en getSOAPMessageAsString.", e);
		}
		return s;
	}

	/**
	 * 
	 * @param request
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected SOAPMessage callWS(String request, String url) throws SOAPException, MalformedURLException {
		
		SOAPMessage sm = MessageFactory.newInstance().createMessage();
		sm.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
		sm.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "ISO-8859-1");

		SOAPPart sp = sm.getSOAPPart();	

		sp.setContent((Source) new StreamSource(new StringReader(request)));

		SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
		// -----------------------------------
		// URL con TIMEOUT
		// -----------------------------------
		URL endpoint = new URL(null,
		                      url,
		                    new URLStreamHandler() {
								
								@Override
								protected URLConnection openConnection(URL url) throws IOException {
								      URL clone_url = new URL(url.toString());
					                    HttpURLConnection clone_urlconnection = (HttpURLConnection) clone_url.openConnection();
					                    // TimeOut settings
					                    int timeOut = Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.BUS_TIME_OUT));
					                    clone_urlconnection.setConnectTimeout(timeOut*1000);
					                    clone_urlconnection.setReadTimeout(timeOut*1000);
					                    return clone_urlconnection;
								}
							});


		
	    // -----------------
	    // Enviar SOAP messageS
	    // -----------------
	    SOAPMessage retour = connection.call(sm, endpoint);
	    
	    return retour;		    
	}
	
	/**
	 * 
	 * @param requestVO
	 * @return
	 */
	protected abstract String generaRequest(Object requestVO);
	
	/**
	 * 
	 * @param response
	 * @return
	 */
	protected abstract Object parseResponse(SOAPMessage response);

}
