package estructuras;

import java.awt.List;

public class Hash {

	private Lista[] tabla;
	private int tamañoTabla;
	
	public Lista[] getTabla() {
		return tabla;
	}
	public void setTabla(Lista[] tabla) {
		this.tabla = tabla;
	}
	public int getTamañoTabla() {
		return tamañoTabla;
	}
	public void setTamañoTabla(int tamañoTabla) {
		this.tamañoTabla = tamañoTabla;
	}
	
	/*
	 * Constructor
	 */
	public Hash(int tam){
		this.tamañoTabla = tam;
		this.tabla = new Lista[tam];
		for (int i = 0; i < tabla.length; i++) {
			tabla[i] = new Lista();
		}
	}
	
	/*
	 * Función de hash
	 */
	public int h(int nr){
		//TODO crear la función del hash
		return 0;
	}
	
	/*
	 * Inserta un obj en la tabla
	 */
	public void insertar(Object obj){
		//TODO crear la función insertar
	}
	
	/*
	 * Chequea si el obj está en la tabla
	 */
	public boolean pertenece(int nr){
		//TODO crear la función pertenece
		return false;
	}
}
