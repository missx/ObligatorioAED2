package estructuras;

import dominio.Propiedad;

public class NodoHashPropiedad {
	
	private Propiedad dato;
	private boolean usado;
	
	public Propiedad getDato() {
		return dato;
	}
	public void setDato(Propiedad dato) {
		this.dato = dato;
	}
	public boolean isUsado() {
		return usado;
	}
	public void setUsado(boolean usado) {
		this.usado = usado;
	}
	
	public NodoHashPropiedad(Propiedad dato){
		this.dato = dato;
		this.usado = true;
	}
	
	public NodoHashPropiedad(){
	}
}
