package estructuras;

import sistema.Utils;
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
	
	public int cantNodos(){
		if(this.raiz == null){
			return 0;
		}else{
			return cantNodos(this.raiz);
		}
	}
	
	public int cantNodos(NodoAB nodo) {
		int cont = 0;
		if(nodo != null){
			cont += cantNodos(nodo.getIzq()); 	
			cont++; 							
			cont += cantNodos(nodo.getDer());
		}
		return cont;
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
		//tratar cedulas como int
		int cedulaDeNodoInt = Utils.convertirCedulaAInt(nodo.getDato());
		int cedulaVendedorInt = Utils.convertirCedulaAInt(n);
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
        else{ 
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
        //tratar cedulas como int
  		String cedNodoStr = n.getDato().getCedula().replace(".", "");
  		cedNodoStr = cedNodoStr.replace("-", "");
  		int cedulaDeNodoInt = Integer.parseInt(cedNodoStr);
  		String cedVendedorStr = x.getCedula().replace(".", "");
  		cedVendedorStr = cedVendedorStr.replace("-", "");
  		int cedulaVendedorInt = Integer.parseInt(cedVendedorStr);
  		  		
        if(cedulaVendedorInt < cedulaDeNodoInt){
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
	
	/*
	 * Muestra contenido de los nodos en orden
	 */
	public String mostrarInOrder(){
		String retorno = "";
    	return mostrarInOrder(this.raiz, retorno);
    }
	
	/*
	 * Muestra contenido de los nodos en orden
	 * @param NodoAB a
	 */
    public String mostrarInOrder(NodoAB a, String retorno){
        if (a != null){
        	retorno = mostrarInOrder(a.getIzq(), retorno);
            retorno += a.getDato().getCedula() + ";" + a.getDato().getNombre() + ";" + a.getDato().getCelular() + "|";
            System.out.println(retorno);
            retorno = mostrarInOrder(a.getDer(), retorno);
            return retorno;
        }
        return retorno;
    }
    
    /*
     * Eliminar un elemento del arbol
     * @param int value
     * @return boolean
     */
    public boolean eliminar(Vendedor v) {
        if (this.raiz == null)
              return false;
        else {
              if (this.raiz.getDato().equals(v)) {
                    NodoAB auxRoot = new NodoAB(new Vendedor("4.486.255-3"));
                    auxRoot.setIzq(this.raiz);
                    boolean result = this.raiz.eliminar(v, auxRoot);
                    this.raiz = auxRoot.getIzq();
                    return result;
              } else {
                    return this.raiz.eliminar(v, null);
              }
        }
    }

}
