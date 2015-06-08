package estructuras;

import dominio.Punto;
import interfaces.IGrafo;

public class GrafoMatriz implements IGrafo{
	
	private int size;
	private int cantNodos;
	private Arco[][] matrizAdyacencia;
	private boolean[] nodosUsados;
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getCantNodos() {
		return cantNodos;
	}
	
	public void setCantNodos(int cantNodos) {
		this.cantNodos = cantNodos;
	}
	
	public Arco[][] getMatrizAdyacencia() {
		return matrizAdyacencia;
	}
	
	public void setMatrizAdyacencia(Arco[][] matrizAdyacencia) {
		this.matrizAdyacencia = matrizAdyacencia;
	}
	
	public boolean[] getNodosUsados() {
		return nodosUsados;
	}
	
	public void setNodosUsados(boolean[] nodosUsados) {
		this.nodosUsados = nodosUsados;
	}
	
	@Override
	public void crearGrafo(int cantMax){
	    this.size = 0;
	    this.cantNodos = cantMax;
	    this.matrizAdyacencia = new Arco[cantMax + 1][cantMax + 1];
	    for(int i = 0; i < cantMax; i++){
	        for(int j = 0; j < cantMax; j++){
	            this.matrizAdyacencia[i][j] = new Arco();      
	        }
	    }
	    this.nodosUsados = new boolean[cantMax];
	}

	@Override
	public void agregarVertice(int nro){
	    this.size++;
	    this.nodosUsados[nro] = true;
	}

	@Override
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

	@Override
	public void agregarArista(int origen, int destino, int peso, Double coordXi, Double coordYi, Double coordXf, Double coordYf){
		Arco nuevo = new Arco(peso, coordXi, coordYi, coordXf, coordYf);
		this.matrizAdyacencia[origen][destino] = nuevo;
	}

	@Override
	public void eliminarVertice(int v) {
		this.nodosUsados[v]=false;
		this.size--;
		
		//Elimino las aristas donde v es miembro
		for(int i=1;i<=this.cantNodos;i++){
			this.matrizAdyacencia[i][v] = new Arco();
			this.matrizAdyacencia[v][i] = new Arco();
		}
	}

	@Override
	public void eliminarArista(int origen, int destino) {
		Arco nuevo = new Arco();
		this.matrizAdyacencia[origen][destino] = nuevo;
	}

	@Override
	public Lista obtenerVerticesAdyacentes(int v) {
		Lista l = new Lista();
		for(int i=1; i<=this.cantNodos; i++){
			if(this.sonAdyacentes(v, i)){
				l.insertarInicio(i);
			}
		}
		return l;
	}

	@Override
	public boolean sonAdyacentes(int a, int b) {
		return this.matrizAdyacencia[a][b].isExiste();
	}

	@Override
	public boolean estaVertice(int v) {
		return this.nodosUsados[v];
	}

	@Override
	public boolean esVacio() {
		return this.size==0;
	}
	
	public boolean hayLugar(){
        for(int i = 0; i < cantNodos; i++){
            if(!this.nodosUsados[i])
                return true;
        }
        return false;
    }  
	
	public Punto puntoInteresMasCercano(){
		return null;
	}
	
}
