package cl.bch.motorpagos.vo;

import java.io.Serializable;

public class RespuestaLoginClienteVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3738546001605754410L;
	
	private String nombre;
	private String codigoMarca;
	private String mail;
	private String direccion;
	private RespuestaVO retorno;
	private CuentasClienteVO cuentas;
	private DispositivosClienteVO dispositivos;
	
	
	/**
	 * @return the cuentas
	 */
	public CuentasClienteVO getCuentas() {
		return cuentas;
	}
	/**
	 * @param cuentas the cuentas to set
	 */
	public void setCuentas(CuentasClienteVO cuentas) {
		this.cuentas = cuentas;
	}
	/**
	 * @return the dispositivos
	 */
	public DispositivosClienteVO getDispositivos() {
		return dispositivos;
	}
	/**
	 * @param dispositivos the dispositivos to set
	 */
	public void setDispositivos(DispositivosClienteVO dispositivos) {
		this.dispositivos = dispositivos;
	}
	/**
	 * @return the retorno
	 */
	public RespuestaVO getRetorno() {
		return retorno;
	}
	/**
	 * @param retorno the retorno to set
	 */
	public void setRetorno(RespuestaVO retorno) {
		this.retorno = retorno;
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}
	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(180);
		
		bf.append(this.retorno.toString());
		bf.append(" Nombre : ");
		bf.append(this.nombre);
		bf.append(", CodigoMarca : ");
		bf.append(this.codigoMarca);
		bf.append(", Mail :");
		bf.append(this.mail);
		bf.append(", Direccion :");
		bf.append(this.direccion);			
		
		if(this.cuentas!=null){
			bf.append(this.cuentas.toString());
		}
		if(this.dispositivos!=null){
			bf.append(this.dispositivos.toString());
		}
		return bf.toString();
	}
	
}
