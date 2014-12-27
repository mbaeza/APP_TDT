/**
 * 
 */
package cl.bch.motorpagos.srmclient;

import java.util.ArrayList;

/**
 * @author boyanedel
 *
 */
public class FilialesRsp {
	private int numeroFiliales;
	private ArrayList<FilialSRM> filiales;
	/**
	 * @return the numeroFiliales
	 */
	public int getNumeroFiliales() {
		return numeroFiliales;
	}

	/**
	 * @return the filiales
	 */
	public ArrayList<FilialSRM> getFiliales() {
		return filiales;
	}

	
	/**
	 * 
	 * @param respuesta
	 * @return
	 */
	public static FilialesRsp parsear(String dataOut){
		FilialesRsp resp = new FilialesRsp();
		String codigoRetorno = dataOut.substring(0, 2);
		
		if("00".equals(codigoRetorno)){
			int largoData = 45;
			int posInicial = 5;
			int iteraciones = Integer.parseInt(dataOut.substring(2,5));
			
			resp.filiales = new ArrayList<FilialSRM>();
			
			for(int i=1;i<=iteraciones;i++){
				FilialSRM filial = new FilialSRM();
				filial.setRutFilial(dataOut.substring(posInicial, posInicial+largoData).substring(0, 10));
				filial.setNombreFilial(dataOut.substring(posInicial, posInicial+largoData).substring(10));
				
				posInicial = posInicial+largoData;
				
				resp.filiales.add(filial);
			}
			resp.numeroFiliales = resp.filiales.size();
		}else{
			resp.numeroFiliales = 0;
		}
		return resp;
	}
}
