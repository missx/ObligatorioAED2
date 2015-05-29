package dominio;

public class Punto {
	
	private Double coordX;
	private Double coordY;
	
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
	
	public Punto(Double x, Double y){
		this.coordX = x;
		this.coordY = y;
	}
}
