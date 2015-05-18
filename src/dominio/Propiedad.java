package dominio;

import sistema.Enumerados.TipoPropiedad;

public class Propiedad {
	
	private Double coordX;
	private Double coordY;
	private TipoPropiedad tipo;
	private String direccion;
	
	private Double getCoordX() {
		return coordX;
	}
	private void setCoordX(Double coordX) {
		this.coordX = coordX;
	}
	private Double getCoordY() {
		return coordY;
	}
	private void setCoordY(Double coordY) {
		this.coordY = coordY;
	}
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
		this.coordX = coordX;
		this.coordY = coordY;
		this.direccion = dir;
		this.tipo = tipo;
	}
	
	
	
	
}
