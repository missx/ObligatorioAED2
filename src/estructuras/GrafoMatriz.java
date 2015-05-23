package estructuras;

public class GrafoMatriz {
	
	private int size;
	private int cantNodos;
	private Arco[][] matrizAdyacencia;
	private boolean[] nodosUsados;
	
	private int getSize() {
		return size;
	}
	
	private void setSize(int size) {
		this.size = size;
	}
	
	private int getCantNodos() {
		return cantNodos;
	}
	
	private void setCantNodos(int cantNodos) {
		this.cantNodos = cantNodos;
	}
	
	private Arco[][] getMatrizAdyacencia() {
		return matrizAdyacencia;
	}
	
	private void setMatrizAdyacencia(Arco[][] matrizAdyacencia) {
		this.matrizAdyacencia = matrizAdyacencia;
	}
	
	private boolean[] getNodosUsados() {
		return nodosUsados;
	}
	
	private void setNodosUsados(boolean[] nodosUsados) {
		this.nodosUsados = nodosUsados;
	}
	
	public void crearGrafo(int cantMax){
	    this.size = 0;
	    this.cantNodos = cantMax;
	    this.matrizAdyacencia = new Arco[cantMax + 1][cantMax + 1];
	    for(int i = 0; i < cantMax; i++){
	        for(int j = 0; j < cantMax; j++){
	            this.matrizAdyacencia[i][j] = new Arco();      
	        }
	    }
	    this.nodosUsados = new boolean[cantMax + 1];
	}

	void agregarVertice(int nro){
	    this.size++;
	    this.nodosUsados[nro] = true;
	}

	void agregarArista(int vertOri, int vertDest){
	    
	}

	//PRE: El grafo es no dirigido
	public boolean esConexo(){
	    //con el dfs
	    boolean[] marcados = new boolean[this.cantNodos];
	    //agarro el primero de nodosUsados que este en true y tiro el bfs sobre ese
	    int i = 0;
	    //busco el primer nodo true de nodosUsados
	    while(i < cantNodos && !nodosUsados[i]){
	        i++;
	    }
	    DFS(i, marcados); //aca llamo al DFS
	    for(int j = 0; j < cantNodos; j++){
	        if(!marcados[j] && nodosUsados[j]){
	            return false;    
	        }
	    }
	    return true; //no encontré ningun vertice definido que no estuviese marcado
	}

	public void DFS(int v, boolean[] visitados){
	    visitados[v] = true;
	    for(int i = 0; i < cantNodos; i++){
	        if(matrizAdyacencia[v][i].isExiste() && !visitados[i]){
	            DFS(i, visitados);
	        }
	    }
	}
	
	
}
