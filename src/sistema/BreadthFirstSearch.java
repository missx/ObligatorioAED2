package sistema;

import java.util.ArrayList;
import java.util.List;

import estructuras.Arco;
import estructuras.GrafoMatriz;
import estructuras.Hash;
import estructuras.HashArco;
import estructuras.Lista;
import estructuras.Queue;

public class BreadthFirstSearch {

	private boolean[] usados;
	private int[] conectados;
	private int ORIGEN;
	private Hash tableHash;
	
	public BreadthFirstSearch(GrafoMatriz matriz, int origen, Hash tableHash){
		
		//inicializar arrays con el tamaño de la matriz
		usados = new boolean[matriz.getSize()];
		conectados = new int[matriz.getSize()];//conectados = new HashArco(matriz.getSize());
		ORIGEN = origen;
		tableHash = tableHash;
		
		bfs(matriz, origen);
	}
	
	public void bfs(GrafoMatriz matriz, int origen){
		
		Queue queueDePadres = new Queue(); //creamos la queue
		usados[origen] = true;//usados.add(origen);
		queueDePadres.enqueue(origen); //agregamos el origen a la queue
		
		while(!queueDePadres.isEmpty()){
			
			int verticePadre = (int)queueDePadres.dequeue();
			//int arcoPadreKey = tableHash.devolverPosActual(arcoPadre.getCoordXi(), arcoPadre.getCoordYi());
			Lista adyacentes = matriz.obtenerVerticesAdyacentes(verticePadre);
			ArrayList arrayAdyacentes = adyacentes.devolverTodosEnArrayList();
			for(Object hijo : arrayAdyacentes){ 
				int verticeHijo = (int)hijo;
				if(!usados[verticeHijo]){ 
					conectados[verticeHijo] = verticePadre; //conectados.insertar(arcoHijo, arcoPadre);
					usados[verticeHijo] = true; //usados.add(arcoHijo);
					queueDePadres.enqueue(verticeHijo);
				}
			}
		}
	}
	
	/*
	 * Nos permite saber si un vertice está conectado
	 * al origen
	 * @param int vertice
	 * @return boolean
	 */
	public boolean estaConectado(int vertice){
		return usados[vertice];
	}
	
	/*
	 * Retorna el camino desde el origen a el Arco dado
	 * @param Arco a
	 * @return ArrayList
	 */
	public ArrayList caminoA(int vertice){
		if(!estaConectado(vertice)){
			return null;
		}
		ArrayList camino = new ArrayList();
		//primero agregamos el vertice a
		camino.add(vertice);
		//Arco padre = conectados.retornarArcoPadre(vertice);
		while(conectados[vertice] != ORIGEN){
			vertice = conectados[vertice];
			camino.add(vertice);
		}
		camino.add(ORIGEN);
		return camino;
	}
			
}
