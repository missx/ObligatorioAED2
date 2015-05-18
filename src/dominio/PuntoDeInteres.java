package dominio;
import sistema.Enumerados.Rubro;

public class PuntoDeInteres {

	private Double coordX;
	private Double coordY;
	private Rubro rubro;
	private String nombre;
	
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
	private Rubro getRubro() {
		return rubro;
	}
	private void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}
	private String getNombre() {
		return nombre;
	}
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public PuntoDeInteres(Double x, Double y, Rubro r, String nom){
		this.coordX = x;
		this.coordY = y;
		this.rubro = r;
		this.nombre = nom;
	}
	
	
}
