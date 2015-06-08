package sistema;

import java.util.ArrayList;

import estructuras.Arco;
import estructuras.GrafoMatriz;
import estructuras.Hash;
import estructuras.Lista;
import estructuras.Queue;

public class BreadthFirstSearch {

	private ArrayList usados;
	private Arco[] conectados;
	private Arco ORIGEN;
	private Hash tableHash;
	
	public BreadthFirstSearch(GrafoMatriz matriz, Arco origen, Hash tableHash){
		
		//inicializar arrays con el tamaño de la matriz
		usados = new ArrayList();
		conectados = new Arco[matriz.getSize()];
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
					
				}
			}
		}
		
	}
			
}
