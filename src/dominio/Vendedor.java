package dominio;

import estructuras.HashPropiedad;
import estructuras.NodoHashPropiedad;


public class Vendedor {

	private String cedula;
	private String nombre;
	private String email;
	private String celular;
	private HashPropiedad hashPropiedades;
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public Vendedor(String ced, String nom, String email, String cel){
		this.cedula = ced;
		this.celular = cel;
		this.email = email;
		this.nombre = nom;
		this.setHashPropiedades(new HashPropiedad(23));
	}
	
	public Vendedor(String ced){
		this.cedula = ced;
		//this.setHashPropiedades(new HashPropiedad(23));
	}
	
	public HashPropiedad getHashPropiedades() {
		return hashPropiedades;
	}

	public void setHashPropiedades(HashPropiedad hashPropiedades) {
		this.hashPropiedades = hashPropiedades;
	}
	
	public String listarPropiedadesDelVendedor(){
		String listado = "";
		System.out.println("im here");
		for(int i = 0; i < this.hashPropiedades.getTamañoTabla(); i++){
			NodoHashPropiedad[] tabla = this.hashPropiedades.getTabla();
			if(tabla[i].getDato() != null){
				listado += tabla[i].getDato().listadoDeCoordenadas();
			}
		}
		System.out.println("listado " + listado);
		return listado;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedor other = (Vendedor) obj;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		return true;
	}

}
