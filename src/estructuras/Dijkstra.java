package estructuras;

import java.util.ArrayList;

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
			prec[i] = Integer.MAX_VALUE;
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
			
			//Busco vertices adyacentes del origen
			Lista l = obtenerAdyacentes(puntoActual);
			
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
		
		while(aux != null){
			vertice = (Integer)aux.getDato();
			pesoAux = grafo.devolverDistancia(vertice, actual);
			//Verificar si el punto ya fue visitado
			if(!visited[vertice] || (dist[actual] + pesoAux < dist[vertice] && dist[vertice] != Integer.MAX_VALUE)){
				
				if(dist[actual] + pesoAux > dist[vertice]){
					return aux;
				}
				if(pesoAux <= menorPeso){
					menorPeso = pesoAux;
					verticeMenor = aux;
				}
				
				aux = aux.getSig();
			}
			else{
				aux = aux.getSig();
			}
		}
		return verticeMenor;
	}	
	
		
	public String imprimirCamino(GrafoMatriz Grafo, Hash tableHash) {
		int i = 0;
		String[] arraysAlReves = new String[tableHash.getTabla().length];
		String coordenadas = "";
		int actual = DESTINO;
		while(actual != ORIGEN) {
			if(prec[actual] == Integer.MAX_VALUE){
				coordenadas = "NO_COORDS";
				break;
			}
			arraysAlReves[i] = tableHash.getTabla()[actual].getCoordXConCerosAlFinal() + ";" + tableHash.getTabla()[actual].getCoordYConCerosAlFinal() + "|";
			
			actual = prec[actual];
			i++;
		}
		arraysAlReves[i] = tableHash.getTabla()[ORIGEN].getCoordXConCerosAlFinal() + ";" + tableHash.getTabla()[ORIGEN].getCoordYConCerosAlFinal() + "|";
		
		if(!coordenadas.equals("NO_COORDS")){
			for(int j = arraysAlReves.length - 1; j >= 0; j--){
				if(arraysAlReves[j] != null){
					coordenadas += arraysAlReves[j];
				}
			}
		}
		
		return coordenadas;
	}

}
