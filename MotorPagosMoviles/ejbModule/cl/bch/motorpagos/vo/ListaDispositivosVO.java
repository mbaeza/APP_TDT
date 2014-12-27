package cl.bch.motorpagos.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaDispositivosVO implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5668362953302883006L;
	
	private ArrayList<DispositivoRegistradoVO> dispositivos;
	
	/**
	 * @return the dispositivos
	 */
	public ArrayList<DispositivoRegistradoVO> getDispositivos() {
		return dispositivos;
	}
	/**
	 * @param dispositivos the dispositivos to set
	 */
	public void setDispositivos(
			ArrayList<DispositivoRegistradoVO> dispositivos) {
		this.dispositivos = dispositivos;
	}	
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(30);
		
		if(this.dispositivos!=null){
			
			for(int i=0; i<dispositivos.size();i++){
				DispositivoRegistradoVO dispositivo = this.dispositivos.get(i);
				
				bf.append('[');
				bf.append(dispositivo.toString());
				bf.append("], ");
			}
		}		
		return bf.toString();
	}
}
