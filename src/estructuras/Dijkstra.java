package estructuras;

public class Dijkstra {

	static int[] dist;
	static int[] prec;
	static boolean[] visited;
	static int DESTINO;
	static int ORIGEN;
	static GrafoMatriz grafo;

	
	public void dijkstra(GrafoMatriz gr, int origen, int fin) {
		grafo = gr;
		dist = new int[gr.getSize()]; // distancia mas corta desde origen
		prec = new int[gr.getSize()]; // nodo precedente en el camino
		visited = new boolean[gr.getSize()]; // inicialmente todos false
		ORIGEN = origen;
		DESTINO = fin;
		
		//ponemos todos con el max valor y los visitados en false
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}
		
		dist[origen] = 0; //distancia desde origen a origen es 0
		visited[origen] = true; //el origen como true ya que fue visitado
		
		dijkstra(origen);
		
	}
	
	public void dijkstra(int punto){
		boolean flag = true;
		//Busco vertices adyacentes del origen
		Lista l = obtenerAdyacentes(punto);
		
		//Obtengo el vertice de menor distancia
		int vmd = obtenerVerticeMenorDistancia(l, punto);
		
		int peso = grafo.devolverDistancia(vmd, punto);
		
		visited[vmd] = true;
		prec[vmd] = punto;
		dist[vmd] = dist[punto] + peso;
		
		if(vmd == DESTINO){
			flag = false;
		}
		if(flag){
			System.out.println(vmd);
			dijkstra(vmd);
		}
	}

	private Lista obtenerAdyacentes(int punto){
		return grafo.obtenerVerticesAdyacentes(punto);
	}
	
	private int obtenerVerticeMenorDistancia(Lista l, int actual){
		int verticeMenor = 0;
		int vertice = 0;
		int pesoAux = 0;
		int menorPeso = Integer.MAX_VALUE;
		NodoLista aux = l.getInicio();
		
		while(aux != null){
			vertice = (Integer)aux.getDato();
			if(vertice != DESTINO){
				if(!visited[vertice]){
					pesoAux = grafo.devolverDistancia(vertice, actual);
					if(pesoAux < menorPeso){
						menorPeso = pesoAux;
						verticeMenor = vertice;
					}
					aux = aux.getSig();
				}
				else{
					aux = aux.getSig();
				}
			}
			else{
				verticeMenor = vertice;
				aux = null;
			}
		}
		return verticeMenor;
	}	
	
	public void imprimirCamino(GrafoMatriz Grafo) {
		int i = 0;
		while (i < dist.length) {
			System.out.println(dist[i] + " - " + prec[i]);
			i += 1;
		}
	}
}
