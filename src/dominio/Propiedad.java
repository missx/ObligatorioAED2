package dominio;

import sistema.Enumerados.TipoPropiedad;

public class Propiedad extends Punto{
	
	private TipoPropiedad tipo;
	private String direccion;
	
	
	private TipoPropiedad getTipo() {
		return tipo;
	}
	private void setTipo(TipoPropiedad tipo) {
		this.tipo = tipo;
	}
	private String getDireccion() {
		return direccion;
	}
	private void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Propiedad(Double coordX, Double coordY, String dir, TipoPropiedad tipo){
		super(coordX, coordY);
		this.direccion = dir;
		this.tipo = tipo;
	}
	
	
	
	
}
