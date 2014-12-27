/**
 * 
 */
package cl.bch.motorpagos.srmclient;


/**
 * @author boyanedel
 *
 */
public class Dproducto {
	private String idProducto;
	private int numLlaves;
	private int largoLlave;
	private int largoData;
	
	public Dproducto parsear(String str, int ini) throws LoginException{
		try {
			Dproducto d=new Dproducto();
			d.idProducto=str.substring(ini,ini+3);
			d.numLlaves=Integer.parseInt(str.substring(ini+3,ini+7));
			d.largoLlave=Integer.parseInt(str.substring(ini+7, ini+9));
			d.largoData=Integer.parseInt(str.substring(ini+9,ini+11));
			return d;
		} catch (NumberFormatException e) {
			throw new LoginException(98, "Error al parsear el mensaje en descriptores", e);
		}
	}

	public String getIdProducto() {
		return this.idProducto;
	}

	public int getNumLlaves() {
		return this.numLlaves;
	}

	public int getLargoLlave() {
		return this.largoLlave;
	}
	
	public void setNumLlaves(int numLlaves){
		this.numLlaves = numLlaves;
	}

	public int getLargoData() {
		return this.largoData;
	}

	
	
}
