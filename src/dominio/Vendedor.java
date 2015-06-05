package dominio;

import estructuras.HashPropiedad;


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
		this.setHashPropiedades(new HashPropiedad(50));
	}
	
	public Vendedor(String ced){
		this.cedula = ced;
		this.setHashPropiedades(new HashPropiedad(50));
	}
	
	@Override
	public boolean equals(Object o){
	    if(o == null)
	    	return false;
	    if(!(o instanceof Vendedor))
	    	return false;

	    Vendedor otro = (Vendedor) o;
	    if(this.getCedula() != otro.getCedula())     
	    	return false;
	    
	    return true;
	 }

	public HashPropiedad getHashPropiedades() {
		return hashPropiedades;
	}

	public void setHashPropiedades(HashPropiedad hashPropiedades) {
		this.hashPropiedades = hashPropiedades;
	}
	
	public String listarPropiedades(){
		String listado = "";
		for(int i = 0; i < this.hashPropiedades.getTamañoTabla(); i++){
			listado += this.hashPropiedades.getTabla()[i].getCoordX() + ";" + 
					this.hashPropiedades.getTabla()[i].getCoordY() + "|";
		}
		return listado;
	}

}
