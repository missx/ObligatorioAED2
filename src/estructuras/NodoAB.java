package estructuras;

import dominio.Vendedor;

public class NodoAB {
	
	private Vendedor dato;
    private NodoAB izq;
    private NodoAB der;
    
    public NodoAB(Vendedor d){
        this.setDato(d);
    }

    public NodoAB getIzq() {
		return izq;
	}

    public void setIzq(NodoAB izq) {
		this.izq = izq;
	}

	public NodoAB getDer() {
		return der;
	}

	public void setDer(NodoAB der) {
		this.der = der;
	}

	public Vendedor getDato() {
		return dato;
	}

	public void setDato(Vendedor dato) {
		this.dato = dato;
	}
    
}
