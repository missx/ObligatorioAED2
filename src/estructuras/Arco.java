package estructuras;

public class Arco {

	private Integer distancia;
	private boolean existe;
	
	public Integer getDistancia() {
		return this.distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}
	
	public boolean isExiste() {
		return existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}
	
	public Arco(){
		
	}
	
	public Arco(Integer dist){
		this.distancia = dist;
	}
}
