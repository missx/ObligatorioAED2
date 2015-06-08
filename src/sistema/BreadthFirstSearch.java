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

	private ArrayList usados;
	private HashArco conectados;
	private Arco ORIGEN;
	private Hash tableHash;
	
	public BreadthFirstSearch(GrafoMatriz matriz, Arco origen, Hash tableHash){
		
		//inicializar arrays con el tamaño de la matriz
		usados = new ArrayList();
		conectados = new HashArco(matriz.getSize());
		ORIGEN = origen;
		tableHash = tableHash;
		
		bfs(matriz, origen);
	}
	
	public void bfs(GrafoMatriz matriz, Arco origen){
		
		Queue queueDePadres = new Queue(); //creamos la queue
		usados.add(origen);
		queueDePadres.enqueue(origen); //agregamos el origen a la queue
		
		while(!queueDePadres.isEmpty()){
			
			Arco arcoPadre = (Arco)queueDePadres.dequeue();
			int arcoPadreKey = tableHash.devolverPosActual(arcoPadre.getCoordXi(), arcoPadre.getCoordYi());
			Lista adyacentes = matriz.obtenerVerticesAdyacentes(arcoPadreKey);
			ArrayList arrayAdyacentes = adyacentes.devolverTodosEnArrayList();
			for(Object hijo : arrayAdyacentes){ 
				Arco arcoHijo = (Arco)hijo;
				if(!usados.contains(arcoHijo)){ 
					conectados.insertar(arcoHijo, arcoPadre);
					usados.add(arcoHijo);
					queueDePadres.enqueue(arcoHijo);
				}
			}
		}
	}
	
	/*
	 * Nos permite saber si un Arco está conectado
	 * al origen
	 * @param Arco a
	 * @return boolean
	 */
	public boolean estaConectado(Arco a){
		return usados.contains(a);
	}
	
	/*
	 * Retorna el camino desde el origen a el Arco dado
	 * @param Arco a
	 * @return ArrayList
	 */
	public ArrayList caminoA(Arco a){
		if(!estaConectado(a)){
			return null;
		}
		ArrayList camino = new ArrayList();
		//primero agregamos el vertice a
		camino.add(a);
		Arco padre = conectados.retornarArcoPadre(a);
		while(padre != ORIGEN){
			padre = conectados.retornarArcoPadre(padre);
			camino.add(padre);
		}
		camino.add(ORIGEN);
		return camino;
	}
			
}
