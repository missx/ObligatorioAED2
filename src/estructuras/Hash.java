package estructuras;

import java.awt.List;

public class Hash {

	private Lista[] tabla;
	private int tama�oTabla;
	
	public Lista[] getTabla() {
		return tabla;
	}
	public void setTabla(Lista[] tabla) {
		this.tabla = tabla;
	}
	public int getTama�oTabla() {
		return tama�oTabla;
	}
	public void setTama�oTabla(int tama�oTabla) {
		this.tama�oTabla = tama�oTabla;
	}
	
	/*
	 * Constructor
	 */
	public Hash(int tam){
		this.tama�oTabla = tam;
		this.tabla = new Lista[tam];
		for (int i = 0; i < tabla.length; i++) {
			tabla[i] = new Lista();
		}
	}
	
	/*
	 * Funci�n de hash
	 */
	public int h(int nr){
		//TODO crear la funci�n del hash
		return 0;
	}
	
	/*
	 * Inserta un obj en la tabla
	 */
	public void insertar(Object obj){
		//TODO crear la funci�n insertar
	}
	
	/*
	 * Chequea si el obj est� en la tabla
	 */
	public boolean pertenece(int nr){
		//TODO crear la funci�n pertenece
		return false;
	}
}
