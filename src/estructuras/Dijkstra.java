package estructuras;

import java.util.ArrayList;

public class Dijkstra {

	static int[] dist;
	static int[] prec;
	static boolean[] visited;
	static int ORIGEN;
	
	/*
	 * 
	 */
	public static int[] dijkstra(GrafoMatriz Grafo, int origen) {
		dist = new int[Grafo.getSize()]; // distancia mas corta desde origen
		prec = new int[Grafo.getSize()]; // nodo precedente en el camino
		visited = new boolean[Grafo.getSize()]; // inicialmente todos false
		ORIGEN = origen;
		
		//ponemos todos con el max valor
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[origen] = 0; //distancia desde origen a origen es 0

		for (int i = 0; i < dist.length; i++) {
			int prox = minVertice(dist, visited); //se busca el de menor dist entre los dos
			visited[prox] = true;

			// El camino mas corto a prox es dist[prox] y via prec[prox]
			System.out.println("prox " + prox);
			ArrayList ady = Grafo.obtenerVerticesAdyacentes(prox).devolverTodosEnArrayList();
			for (int j = 0; j < ady.size(); j++) {
				int vert = (int)ady.get(j);
				int d = dist[prox] + Grafo.devolverDistancia(prox, vert);
				if (dist[vert] > d) {
					dist[vert] = d;
					prec[vert] = prox;
				}
			}
		}
		return prec; // (ignorar prec[s]==0!)
	}

	/*
	 * 
	 */
	private static int minVertice(int[] dist, boolean[] v) {
		int x = Integer.MAX_VALUE;
		int y = -1; // grafo no conexo, o no tiene vertices no visitados
		for (int i = 0; i < dist.length; i++) {
			if (!v[i] && dist[i] < x) {
				y = i;
				x = dist[i];
			}
		}
		return y;
	}

	/*
	 * 
	 */
	public static void imprimirCamino(GrafoMatriz Grafo) {
		ArrayList camino = new ArrayList();
		int x = prec.length;
		while (x != ORIGEN) {
			camino.add(0, prec[x]);
			x = prec[x];
		}
		camino.add(0, prec[ORIGEN]);
		System.out.println(camino);
	}
}
