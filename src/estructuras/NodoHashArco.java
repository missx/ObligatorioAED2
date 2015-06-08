package estructuras;

import dominio.Propiedad;

public class NodoHashArco {

	private Arco dato;
	private boolean usado;
	private Arco arcoPadre;
	
	public Arco getDato() {
		return dato;
	}
	public void setDato(Arco dato) {
		this.dato = dato;
	}
	public boolean isUsado() {
		return usado;
	}
	public void setUsado(boolean usado) {
		this.usado = usado;
	}
	
	public NodoHashArco(Arco arco){
		this.dato = arco;
		this.usado = true;
	}
	
	public NodoHashArco(Arco arco, Arco arcoPadre){
		this.dato = arco;
		this.usado = true;
		this.arcoPadre = arcoPadre;
	}
	
	public NodoHashArco(){
	}
	public Arco getArcoPadre() {
		return arcoPadre;
	}
	public void setArcoPadre(Arco arcoPadre) {
		this.arcoPadre = arcoPadre;
	}
}
