package estructuras;

public class NodoAB {
	
	Object dato;
    private NodoAB izq;
    private NodoAB der;
    
    public NodoAB(int d){
        this.dato = d;
    }

	NodoAB getIzq() {
		return izq;
	}

	void setIzq(NodoAB izq) {
		this.izq = izq;
	}

	NodoAB getDer() {
		return der;
	}

	void setDer(NodoAB der) {
		this.der = der;
	}
    
}
