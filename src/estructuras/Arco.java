package estructuras;

public class Arco {

	private boolean existe;
	
	boolean isExiste() {
		return existe;
	}


	private void setExiste(boolean existe) {
		this.existe = existe;
	}
	
	public Arco(boolean existe){
		this.setExiste(existe);
	}
	
	public Arco(){
		
	}
}
