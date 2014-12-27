package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AVATAR_CONVENIO database table.
 * 
 */
@Entity
@Table(name="AVATAR_CONVENIO")
@NamedQuery(name="AvatarConvenio.findAll", query="SELECT a FROM AvatarConvenio a")
public class AvatarConvenio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idconvenio;

	@Lob
	private String avatar;

	public AvatarConvenio() {
	}

	public String getIdconvenio() {
		return this.idconvenio;
	}

	public void setIdconvenio(String idconvenio) {
		this.idconvenio = idconvenio;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}