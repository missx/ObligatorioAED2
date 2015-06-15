package estructuras;

import dominio.Punto;
import dominio.PuntoDeInteres;
import sistema.Enumerados.Rubro;

public class DijkstraRubroCercano {

	public static int[] dist;
	public static int[] prec;
	static boolean[] visited;
	static int DESTINO;
	static int ORIGEN;
	static GrafoMatriz grafo;
	static Hash h;
	static Rubro r;
	static Punto p;
	
	public void dijkstra(GrafoMatriz gr, Hash ha, Rubro rubro, int origen) {
		grafo = gr;
		dist = new int[ha.getTamañoTabla()]; // distancia mas corta desde origen
		prec = new int[ha.getTamañoTabla()]; // nodo precedente en el camino
		visited = new boolean[ha.getTamañoTabla()]; //Nodo que ya fue visitado
		ORIGEN = origen;
		h = ha;
		r = rubro;
		
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
		String resultado = "";
		//Busco vertices adyacentes del origen
		Lista l = obtenerAdyacentes(punto);
		
		//Obtengo el vertice de menor distancia
		int vmd = obtenerVerticeMenorDistancia(l, punto);
		
		int peso = grafo.devolverDistancia(vmd, punto);
		
		//Modificar los array con el nuevo punto seleccionado
		visited[vmd] = true;
		prec[vmd] = punto;
		dist[vmd] = dist[punto] + peso;
		
		p = h.devolverPuntoPorPosicion(vmd);
		
		if(p instanceof PuntoDeInteres){
			PuntoDeInteres pi = (PuntoDeInteres)p;
			if(pi.getRubro() == r){
				flag = false;
				resultado = "Coordenadas X: " + p.getCoordX().toString() + ", Coordenadas Y: " + p.getCoordY().toString();
				imprimirCoordenadas(p);
			}
		}
		if(flag){
			System.out.println(vmd);
			dijkstra(vmd);
		}
		else{
			devolverResultado(resultado);
		}
	}
	
	private String devolverResultado(String r){
		return r;
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
			//Verificar si el punto ya fue visitado
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
	
	public void imprimirCoordenadas(Punto punto) {
		System.out.println("Destino - Coordeanas X: " + punto.getCoordX() + "Coordenadas Y: " + punto.getCoordY());
	}
}
