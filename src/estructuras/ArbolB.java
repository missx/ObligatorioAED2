package estructuras;

import dominio.Vendedor;

public class ArbolB {

	NodoAB raiz;
	
	/*
	 * Verifica si es arbol vacio o no 
	 * @return boolean: True si es vacío, false si no
	 */
	public boolean esArbolVacio() {
		return (raiz == null) ;
	}
	
	/*
	 * Inserta un elemento al arbol
	 * @param Vendedor n: el vendedor que queremos insertar
	 */
	public void insertarElemento(Vendedor v){
		if (this.esArbolVacio()){
            this.raiz = new NodoAB(v);
		}else{
			this.insertarElemento(v, this.raiz);
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
        else{
            if(n.getDato().equals(x)){
                return n;
            }
            else{
                NodoAB aux = Buscar(x, n.getDer());
                if(aux != null){ //aca para mi (xime) es si aux == null y ahi retorna por el lado izq
                    return Buscar(x, n.getIzq());
                }
                else{
                    return aux;//return Buscar(x, n.der); No lo hago asi y quito el aux ya que recorro 2 veces el arbol
                }
            }
        }
    }
	
	/*
	 * Retorna true si el elemento existe, false
	 * de lo contrario
	 * @param Vendedor v: vendedor que queremos saber si existe
	 * @return boolean
	 */
	public boolean existeElemento(Vendedor v) {
		NodoAB nodo = Buscar(v, raiz);
		
		if(nodo != null) {
			return true;
		} else {
			return false;
		}
	}
	
}
