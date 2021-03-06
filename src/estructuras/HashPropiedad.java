package estructuras;

import dominio.Propiedad;
import dominio.Punto;

public class HashPropiedad {
	
	private NodoHashPropiedad[] tabla;
	private int tamañoTabla;
	
	public NodoHashPropiedad[] getTabla() {
		return tabla;
	}
	public void setTabla(NodoHashPropiedad[] tabla) {
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
	public HashPropiedad(int tam){
		this.tamañoTabla = tam;
		this.tabla = new NodoHashPropiedad[tam];
		for(int i = 0; i < tabla.length; i++){
			this.tabla[i] = new NodoHashPropiedad();
		}
	}
	
	/*
	 * Función de hash
	 */
	public int h(String direccion){
		int nr = 0;
		for(int i = 0; i < direccion.length(); i++){
			nr += (int)direccion.charAt(i);
		}
		return nr % tamañoTabla;
	}
	
	/*
	 * Inserta un obj en la tabla
	 */
	public boolean insertar(Propiedad p){
		int key = h(p.getDireccion());
		int recorridos = 0;
		boolean flag = true;
		while(flag && recorridos < this.tamañoTabla){
			if(tabla[key].getDato() == null){ //usa esta pos
				tabla[key] = new NodoHashPropiedad(p);
				flag = false;
			}else{ //ya esta ocupado, sigue o pasa a 0
				recorridos++;
				key += 1;
				if(key == tabla.length){
					key = 0;
				}
			}
		}
		if(recorridos == this.tamañoTabla)
			return false;
		else
			return true;
	}
	
	/*
	 * Chequea si el obj está en la tabla
	 */
	public boolean pertenece(String direccion){
		
		int key = h(direccion);
		boolean flag = true;
		int recorridos = 1;
		while(flag && recorridos < this.tamañoTabla){			
			if(this.tabla[key].getDato() != null && this.tabla[key].getDato().getDireccion().equals(direccion)){
				flag = false;
			}else{
				key++;
				recorridos++;
				if(key == this.tamañoTabla){
					key = 0;
				}
			}
		}
		if(recorridos == this.tamañoTabla){
			return false;
		}else{
			return true;
		}
	}
	
	public int devolverPosActual(String direccion){
		int pos = h(direccion);
		boolean flag = true;
		int count = 0;
		while(flag){
			if(count < tabla.length){
				count += 1;
				if(tabla[pos] != null){
					if(tabla[pos].getDato().getDireccion() == direccion){
						return pos;
					}
					else{
						pos += 1;
						if(pos == tabla.length){
							pos = 0;
						}
					}
				}
				else{
					flag = false;
				}
			}
			else{
				flag = false;
			}
		}
		return -1;
	}
		
	public boolean esVacio(){
		boolean vacio = true;
		for(int i = 0; i < this.tabla.length; i++){
			if(this.tabla[i].getDato() != null){
				vacio = false;
			}
		}	
		System.out.println("es vacio " +vacio);
		return vacio;
	}
	
	public Propiedad devolverPropiedad(String direccion){
		int key = h(direccion);
		int recorridos = 1;
		while(recorridos <= this.tamañoTabla){			
			if(this.tabla[key].getDato() != null && this.tabla[key].getDato().getDireccion().equals(direccion)){
				return this.tabla[key].getDato();
			}else{
				key++;
				recorridos++;
				if(key == this.tamañoTabla){
					key = 0;
				}
			}
		}
		return null;
	}
	
	/*
	 * Elimina propiedad del hash. Setea a null el dato
	 * Usado queda como true
	 * @param String direccion
	 */
	public void eliminarPropiedad(String direccion){
		int key = this.h(direccion);
		this.tabla[key].setDato(null);
	}
	
}
