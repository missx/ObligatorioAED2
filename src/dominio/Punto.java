package dominio;

public class Punto {
	
	private Double coordX;
	private Double coordY;
	
	public Double getCoordX() {
		return coordX;
	}
	public void setCoordX(Double coordX) {
		this.coordX = coordX;
	}
	public Double getCoordY() {
		return coordY;
	}
	public void setCoordY(Double coordY) {
		this.coordY = coordY;
	}
	
	public Punto(Double x, Double y){
		this.coordX = x;
		this.coordY = y;
	}
}
