/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class RespuestaLoginComercioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8251928918706217679L;
	private RespuestaVO retorno;
	private String nombre;
	private String direccion;
	private String mail;
	private CuentasClienteVO cuentas;
	private DispositivosClienteVO dispositivos;
	private FilialesEmpresaVO filiales;
//	private ConveniosComercioVO convenios;
	
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
	 * @return the cuentasComercio
	 */
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
	 * @return the filiales
	 */
	public FilialesEmpresaVO getFiliales() {
		return filiales;
	}
	/**
	 * @param filiales the filiales to set
	 */
	public void setFiliales(FilialesEmpresaVO filiales) {
		this.filiales = filiales;
	}
	//	/**
//	 * @return the convenios
//	 */
//	public ConveniosComercioVO getConvenios() {
//		return convenios;
//	}
//	/**
//	 * @param convenios the convenios to set
//	 */
//	public void setConvenios(ConveniosComercioVO convenios) {
//		this.convenios = convenios;
//	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(180);
		
		bf.append(this.retorno.toString());
		bf.append("Nombre : ");
		bf.append(this.nombre);
		bf.append(", Direccion : ");
		bf.append(this.direccion);
		bf.append(", Mail : ");
		bf.append(this.mail);
		
		if (this.cuentas != null){
			bf.append(this.cuentas.toString());
		}
		if(this.dispositivos!=null){
			bf.append(this.dispositivos.toString());
		}	
		if(this.filiales!=null){
			bf.append(this.filiales.toString());
		}
//		if(this.convenios!=null){
//			bf.append(this.convenios.toString());
//		}
		return bf.toString();
	}

}
