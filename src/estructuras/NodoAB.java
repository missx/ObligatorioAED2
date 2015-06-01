package estructuras;

import sistema.Utils;
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
	
	public boolean eliminar(Vendedor v, NodoAB padre) {
		int cedV = Utils.convertirCedulaAInt(v);
		int cedThis = Utils.convertirCedulaAInt(this.dato);
        if (cedV < cedThis) {
              if (izq != null)
                    return izq.eliminar(v, this);
              else
                    return false;
        } else if (cedV > cedThis) {
              if (der != null)
                    return der.eliminar(v, this);
              else
                    return false;
        } else {
              if (izq != null && der != null) {
                  this.dato = der.minValor();
                  der.eliminar(this.dato, this);
              } else if (padre.izq == this) {
            	  padre.izq = (izq != null) ? izq : der;
              } else if (padre.der == this) {
            	  padre.der = (izq != null) ? izq : der;
              }
              return true;
        }
	}

	public Vendedor minValor() {
		if (izq == null)
	        return dato;
		else
	        return izq.minValor();
	}
    
}
