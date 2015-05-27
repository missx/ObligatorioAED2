package dominio;


public class Vendedor {

	private String cedula;
	private String nombre;
	private String email;
	private String celular;
	
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
	
	public Vendedor(String nom, String ced, String cel, String email){
		this.cedula = ced;
		this.celular = cel;
		this.email = email;
		this.nombre = nom;
	}
	
	public Vendedor(String ced){
		this.cedula = ced;
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

}
