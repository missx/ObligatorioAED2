package estructuras;

import dominio.PuntoDeInteres;

public class Dijkstra {

	public static int[] dist;
	public static int[] prec;
	static boolean[] visited;
	static int DESTINO;
	static int ORIGEN;
	static GrafoMatriz grafo;
	
	public void dijkstra(GrafoMatriz gr, int origen, int fin) {
		grafo = gr;
		dist = new int[gr.getCantNodos()]; // distancia mas corta desde origen
		prec = new int[gr.getCantNodos()]; // nodo precedente en el camino
		visited = new boolean[gr.getCantNodos()]; //Nodo que ya fue visitado
		ORIGEN = origen;
		System.out.println(origen +  " " + fin);
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
		//creo queue y agrego el primer punto
		Queue queue = new Queue();
		queue.enqueue(punto);
		
		while(!queue.isEmpty()){
			//saco el del frente
			int puntoActual = (int)((NodoLista)queue.dequeue()).getDato();
			System.out.println("padre " + puntoActual);
			//boolean flag = true;
			//Busco vertices adyacentes del origen
			Lista l = obtenerAdyacentes(puntoActual);
			System.out.println("es vacia " + l.esVacia());
			
			//NodoLista nodo = l.getInicio();
			NodoLista nodo = obtenerNodoMenorDistancia(l, puntoActual);
			while(nodo != null){
				int otroPunto = (int)nodo.getDato();
				l.borrarUnElem(otroPunto);
				System.out.println("mostrar hijo " + otroPunto);
				int peso = grafo.devolverDistancia(otroPunto, puntoActual);
				
				if(!visited[otroPunto] || dist[puntoActual] + peso < dist[otroPunto]){
					visited[otroPunto] = true;
					prec[otroPunto] = puntoActual;
					dist[otroPunto] = dist[puntoActual] + peso;
					
					//enqueue punto
					queue.enqueue(otroPunto);
				}
				nodo = obtenerNodoMenorDistancia(l, puntoActual);
			}
			
			//Obtengo el vertice de menor distancia
			/*int vmd = obtenerVerticeMenorDistancia(l, punto);
			
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
		}*/
		}
	}

	private Lista obtenerAdyacentes(int punto){
		return grafo.obtenerVerticesAdyacentes(punto);
	}
	
	private NodoLista obtenerNodoMenorDistancia(Lista l, int actual){
		NodoLista verticeMenor = null;
		int vertice = 0;
		int pesoAux = 0;
		int menorPeso = Integer.MAX_VALUE;
		NodoLista aux = l.getInicio();
		int x = Integer.MAX_VALUE;
		int y = -1;   // graph not connected, or no unvisited vertices
		while(aux != null){
			vertice = (Integer)aux.getDato();
			//Verificar si el punto ya fue visitado
			if(!visited[vertice]){
				prec[vertice] = actual;
				pesoAux = grafo.devolverDistancia(vertice, actual);
				if(dist[actual] + pesoAux > dist[vertice]){
					return aux;
				}
				dist[vertice] = dist[actual] + pesoAux;
				if(pesoAux < menorPeso){
					menorPeso = pesoAux;
					verticeMenor = aux;
				}
				if(pesoAux == menorPeso){
					//algo
				}
				aux = aux.getSig();
			}
			else{
				aux = aux.getSig();
			}
		}
		return verticeMenor;
	}	
	
	private int obtenerVerticeMenorDistancia(Lista l, int actual){
		int verticeMenor = 0;
		int vertice = 0;
		int pesoAux = 0;
		int menorPeso = Integer.MAX_VALUE;
		NodoLista aux = l.getInicio();
		
		while(aux != null){
			vertice = (Integer)aux.getDato();
			if(!visited[vertice]){
				prec[vertice] = actual;
				pesoAux = grafo.devolverDistancia(vertice, actual);
				if(dist[actual] + pesoAux > dist[vertice]){
					return vertice;
				}
				dist[vertice] = dist[actual] + pesoAux;
				if(pesoAux < menorPeso){
					menorPeso = pesoAux;
					verticeMenor = vertice;
				}
				if(pesoAux == menorPeso){
					//algo
				}
				aux = aux.getSig();
			}
			else{
				aux = aux.getSig();
			}
		}
		return verticeMenor; 
	}	
	

	public int[] imprimirCamino(GrafoMatriz Grafo) {
		int i = 0;
		int[] vectorFinal = new int[prec.length];
		int actual = DESTINO;
		while(actual != ORIGEN) {
			System.out.println("Distancia:" + dist[actual] + " - Presente:" + actual + "- Anterior:" + prec[actual]);
			vectorFinal[i] = actual;
			actual = prec[actual];
			i++;
		}
		vectorFinal[i] = ORIGEN;
		//dar vuelta vector
		int[] vFinalFinal = new int[vectorFinal.length];
		int count = 0;
		for(int j = vectorFinal.length - 1; j >= 0; j--){
			vFinalFinal[count] = vectorFinal[j];
			count++;
		}
		System.out.println("Distancia:" + dist[ORIGEN] + " - Presente:" + actual + " - Anterior:" + prec[ORIGEN]);
		return vFinalFinal;
	}

}
