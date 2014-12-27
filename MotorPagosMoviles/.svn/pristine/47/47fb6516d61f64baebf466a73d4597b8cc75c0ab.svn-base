package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USUARIOS_ADM database table.
 * 
 */
@Entity
@Table(name="USUARIOS_ADM")
@NamedQuery(name="UsuariosAdm.findAll", query="SELECT u FROM UsuariosAdm u")
public class UsuariosAdm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idusuario;

	private String password;

	public UsuariosAdm() {
	}

	public String getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}