package estructuras;

import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra2 {

	static final int SIN_CONEXION = -1;
	
	class CaminoDijkstra{
		public int[] vertices;
		public int nodoOrigen;
		public int nodoFin;
		public int distanciaTotal;
		
		public CaminoDijkstra(int tamVertices, int origen, int dest, int distTot){
			vertices = new int[tamVertices];
			nodoOrigen =origen;
			nodoFin =dest;
			distanciaTotal=distTot;
		}
		
		public CaminoDijkstra(int tamVertices){
			vertices = new int[tamVertices];
		}
	}
	
	
	public Dijkstra2(){
		
		
	}
	
	class DatosDeVisita{
		int distanciaHastaAhora;
		int verticeActual;
		int vertAnterior;
	}
	
	public ArrayList caminoMasCorto(GrafoMatriz matGrafo, int orig, int destino){
		
		DatosDeVisita[] arrayDatosVisita = new DatosDeVisita[matGrafo.getSize()]; //bookeeping
		int[] todosLosVertices = new int[matGrafo.getSize()];
		
		for(int i : todosLosVertices){
			DatosDeVisita datosVisita = new DatosDeVisita();
			datosVisita.distanciaHastaAhora = 0;
			datosVisita.verticeActual = i;
			datosVisita.vertAnterior = SIN_CONEXION;
			arrayDatosVisita[i] = datosVisita;
		}
		
		//inicializar queue con vertice orig
		Queue queueVert = new Queue();
		queueVert.enqueue(orig);
		
		while(!queueVert.isEmpty()){
			
			int vertActual = (int)queueVert.dequeue();
			DatosDeVisita visitaActual = arrayDatosVisita[vertActual];
			ArrayList adyacentes = matGrafo.obtenerVerticesAdyacentes(vertActual).devolverTodosEnArrayList();
			
			for(Object proxNodo : adyacentes.toArray()){
				int distancia = matGrafo.devolverDistancia(vertActual, (int)proxNodo);
				DatosDeVisita proxDatos = arrayDatosVisita[(int)proxNodo];
				
				boolean visitado = (proxDatos.vertAnterior != SIN_CONEXION);
				boolean esCaminoMasCorto = (visitaActual.distanciaHastaAhora + distancia) < proxDatos.distanciaHastaAhora;
				
				if(!visitado || esCaminoMasCorto){
					//cambiar dist mas corta
					proxDatos.distanciaHastaAhora = visitaActual.distanciaHastaAhora + distancia;
					proxDatos.vertAnterior = vertActual;
					
					//poner en la queue el proxNodo
					queueVert.enqueue((int)proxNodo);
				}
			}
		}
		
		//queue vacia
		DatosDeVisita ultimo = arrayDatosVisita[destino];
		if(ultimo.vertAnterior == SIN_CONEXION){
			//implementar para que muestre un camino sin nada, no hay con
			return null;
		}else{
			CaminoDijkstra caminoFinal = new CaminoDijkstra(matGrafo.getSize());
			caminoFinal.distanciaTotal = ultimo.distanciaHastaAhora;
			caminoFinal.nodoFin = destino;
			caminoFinal.nodoOrigen = orig;
			caminoFinal.vertices[destino] = ultimo.verticeActual; //ver si esto esta bien
			
			ArrayList al = new ArrayList();
			al.add(ultimo.verticeActual);
			
			while(ultimo != null){
				al.add(ultimo.vertAnterior);
				if(ultimo.vertAnterior == orig){
					al.add(ultimo.vertAnterior);
					ultimo = null;
				}else{
					ultimo = arrayDatosVisita[ultimo.vertAnterior];
				}
			}
			Collections.reverse(al);
			return al;
		}
		
		
	}
	
	
}
