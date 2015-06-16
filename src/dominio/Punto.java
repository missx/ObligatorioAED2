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
	
	public String getCoordXConCerosAlFinal(){
		String coordXStr = String.valueOf(this.coordX);
		if(coordXStr.contains(",")){
			String[] arrayDivididoXComa = coordXStr.split("\\,");
			if(arrayDivididoXComa[1].length() < 6){
				for(int i = 0; i <=6 - arrayDivididoXComa[1].length(); i++){
					coordXStr += "0";
				}
			}
		}
		if(coordXStr.contains(".")){
			String[] arrayDivididoXPunto = coordXStr.split("\\.");
			if(arrayDivididoXPunto[1].length() < 6){
				for(int i = 0; i < 6 - arrayDivididoXPunto[1].length(); i++){
					coordXStr += "0";
				}
			}
		}
		return coordXStr;
	}
	
	public String getCoordYConCerosAlFinal(){
		String coordYStr = String.valueOf(this.coordY);
		if(coordYStr.contains(",")){
			String[] arrayDivididoXComa = coordYStr.split("\\,");
			if(arrayDivididoXComa[1].length() < 6){
				for(int i = 0; i < 6 - arrayDivididoXComa[1].length(); i++){
					coordYStr += "0";
				}
			}
		}
		if(coordYStr.contains(".")){
			String[] arrayDivididoXPunto = coordYStr.split("\\.");
			if(arrayDivididoXPunto[1].length() < 6){
				for(int i = 0; i < 6 - arrayDivididoXPunto[1].length(); i++){
					coordYStr += "0";
				}
			}
		}
		return coordYStr;
	}
}
