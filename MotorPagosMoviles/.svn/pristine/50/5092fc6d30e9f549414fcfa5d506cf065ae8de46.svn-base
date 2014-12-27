package cl.bch.motorpagos.srmclient;

import java.util.ArrayList;
import java.util.List;

import cl.bch.motorpagos.util.MotorPagosHelper;

public class LoginRsp {
	private String retorno;
	private String color;
	private String rut;
	private String rutGrupo;
	private String nombre;
	private String tipoCliente;
	private String tipo;
	private String categoria;
	private String bancaCliente;
	private String numPerfiles;
	private String perfil;
	private String perfilLng;		
	
	public List<Producto> productosCTDoJUV=new ArrayList<Producto>();
	
	public static LoginRsp parsear(String s) throws LoginException{
		LoginRsp rsp=new LoginRsp();
		rsp.retorno=s.substring(0, 2);
		if ("00".equals(rsp.retorno)){
			try {
				rsp.color=s.substring(2,3);
				rsp.rut=s.substring(3,13);
				rsp.rutGrupo=s.substring(13,23);
				rsp.nombre=s.substring(23,58);
				rsp.tipoCliente=s.substring(77,78);
				rsp.tipo=s.substring(78,79);
				rsp.categoria=s.substring(79,80);
				rsp.bancaCliente=s.substring(80,81);
				rsp.numPerfiles=s.substring(81,83);
				rsp.perfil=s.substring(83,91);
				rsp.perfilLng=s.substring(91,113);
				int ini=114;  //val vector a la data, ini vector a la especificacion de la data
				int val=s.substring(ini).indexOf("$")+ini+1;
				while (s.charAt(ini)!='$') {
					Dproducto d = new Dproducto(); 
					d = d.parsear(s, ini);
					int llaves = d.getNumLlaves();
					//FILTRA LA DATA QUE BUSCA
					if (MotorPagosHelper.isTokenPermitido(d.getIdProducto())){
						int i=val; //i puntero para leer la data
						
						while(d.getNumLlaves()>0) {
							Producto p=new Producto();
							p.setIdProducto(d.getIdProducto());
							p.setLlave(s.substring(i,i+d.getLargoLlave()));
							i+=d.getLargoLlave();
							p.setData(s.substring(i,i+d.getLargoData()));
							i+=d.getLargoData();
							rsp.productosCTDoJUV.add(p);
							d.setNumLlaves(d.getNumLlaves()-1);
							
						}
					}
					val+=llaves*(d.getLargoData()+d.getLargoLlave()); //actualiza la posicion en la data
					ini+=11; //sgte registro de especificacion de data
				}
			} catch (LoginException e) {
				throw new LoginException(98, "Error de parseo del mensaje de respuesta", e);
			}
							
		} 
		
		return rsp;
	}	
	

	public String getRetorno() {
		return retorno;
	}

	public String getColor() {
		return color;
	}

	public String getRut() {
		return rut;
	}

	public String getRutGrupo() {
		return rutGrupo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public String getTipo() {
		return tipo;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getBancaCliente() {
		return bancaCliente;
	}

	public String getNumPerfiles() {
		return numPerfiles;
	}

	public String getPerfil() {
		return perfil;
	}

	public String getPerfilLng() {
		return perfilLng;
	}

	public List<Producto> getProductosCTDoJUV() {
		return productosCTDoJUV;
	}
	
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(200);
		
		bf.append("retorno : ");
		bf.append(this.retorno);
		bf.append(", color : ");
		bf.append(this.color);
		bf.append(", rut : ");
		bf.append(this.rut);
		bf.append(", rutGrupo : ");
		bf.append(this.rutGrupo);
		bf.append(", nombre : ");
		bf.append(this.nombre);
		bf.append(", tipoCliente : ");
		bf.append(this.tipoCliente);
		bf.append(", tipo : ");
		bf.append(this.tipo);
		bf.append(", categoria : ");
		bf.append(this.categoria);
		bf.append(", bancaCliente : ");
		bf.append(this.bancaCliente);
		bf.append(", numPerfiles : ");
		bf.append(this.numPerfiles);
		bf.append(", perfil : ");
		bf.append(this.perfil);
		bf.append(", perfilLng : ");
		bf.append(this.perfilLng);	
		
		if(this.productosCTDoJUV != null && !this.productosCTDoJUV.isEmpty()){
			int i=0;
			for(Producto producto: productosCTDoJUV){
				bf.append(", ["+i+"] IdProducto : ");
				bf.append(producto.getIdProducto());
				bf.append(", Llave : ");
				bf.append(producto.getLlave());
				bf.append(", Data : ");
				bf.append(producto.getData());
				i++;
			}
		}
				
		return bf.toString();
	}
	
	
}
