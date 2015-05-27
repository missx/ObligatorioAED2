package estructuras;

import dominio.Vendedor;

public class ArbolB {

	private NodoAB raiz;
	
	public NodoAB getRaiz() {
		return raiz;
	}

	public void setRaiz(NodoAB raiz) {
		this.raiz = raiz;
	}
	
	public ArbolB(){
		
	}
	
	/*
	 * Verifica si es arbol vacio o no 
	 * @return boolean: True si es vacío, false si no
	 */
	public boolean esArbolVacio() {
		return (getRaiz() == null) ;
	}
	
	/*
	 * Inserta un elemento al arbol
	 * @param Vendedor n: el vendedor que queremos insertar
	 */
	public void insertarElemento(Vendedor v){
		if (this.esArbolVacio()){
            this.setRaiz(new NodoAB(v));
		}else{
			this.insertarElemento(v, this.getRaiz());
		}
	}
	
	/*
	 * Inserta un elemento al arbol
	 * @param Vendedor n: el vendedor que queremos insertar
	 * @param NodoAB nodo: el nodo desde el cual vamos a insertar
	 */
	public void insertarElemento(Vendedor n, NodoAB nodo) {
		int cedulaDeNodoInt = Integer.parseInt(nodo.getDato().getCedula());
		int cedulaVendedorInt = Integer.parseInt(n.getCedula());
		NodoAB nuevo = null;

        if(cedulaVendedorInt < cedulaDeNodoInt){
        	// n < dato => insertaré en subárbol izq.
            if(nodo.getIzq() == null){
                nuevo = new NodoAB(n);
                nodo.setIzq(nuevo);
             }
             else
                 insertarElemento(n, nodo.getIzq());
        }
        else if(cedulaVendedorInt > cedulaDeNodoInt){   
        	// n > dato => insertaré en subárbol derecho
			if(nodo.getDer() == null){
				nuevo = new NodoAB(n);
				nodo.setDer(nuevo);
			}
            else
				insertarElemento(n, nodo.getDer());
		}
	}
	 
	/*
	 * Busca determinado elemento en el arbol 
	 * @param Vendedor x: el vendedor que buscamos
	 * @param NodoAB n: el nodo desde donde comienza a buscar
	 * @return NodoAB: el nodo que buscabamos
	 */
	public NodoAB Buscar(Vendedor x, NodoAB n){
        if(n == null){
            return null;
        }
        if(n.getDato().equals(x)){
            return n;
        }
        if(Integer.parseInt(x.getCedula()) < Integer.parseInt(n.getDato().getCedula())){
        	return Buscar(x, n.getIzq());
        }else{
            return  Buscar(x, n.getDer());
        }
    }

	/*
	 * Retorna true si el elemento existe, false
	 * de lo contrario
	 * @param Vendedor v: vendedor que queremos saber si existe
	 * @return boolean
	 */
	public boolean existeElemento(Vendedor v) {
		if(this.raiz == null){
			return false;
		}else{
			NodoAB nodo = Buscar(v, getRaiz());
			if(nodo != null) {
				return true;
			} else {
				return false;
			}
		}		
	}

	
	
}
