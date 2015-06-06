package estructuras;

import dominio.Propiedad;
import dominio.Punto;

public class HashPropiedad {
	
	private NodoHashPropiedad[] tabla;
	private int tama�oTabla;
	
	public NodoHashPropiedad[] getTabla() {
		return tabla;
	}
	public void setTabla(NodoHashPropiedad[] tabla) {
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
	public HashPropiedad(int tam){
		this.tama�oTabla = tam;
		this.tabla = new NodoHashPropiedad[tam];
		for(int i = 0; i < tabla.length; i++){
			this.tabla[i] = new NodoHashPropiedad();
		}
	}
	
	/*
	 * Funci�n de hash
	 */
	public int h(String direccion){
		int nr = 0;
		for(int i = 0; i < direccion.length(); i++){
			nr += (int)direccion.charAt(i);
		}
		return nr % tama�oTabla;
	}
	
	/*
	 * Inserta un obj en la tabla
	 */
	public boolean insertar(Propiedad p){
		int key = h(p.getDireccion());
		int recorridos = 0;
		boolean flag = true;
		while(flag && recorridos < this.tama�oTabla){
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
		if(recorridos == this.tama�oTabla)
			return false;
		else
			return true;
	}
	
	/*
	 * Chequea si el obj est� en la tabla
	 */
	public boolean pertenece(String direccion){
		int key = h(direccion);
		boolean flag = true;
		int recorridos = 0;
		while(flag && recorridos < this.tama�oTabla){
			if(this.tabla[key].getDato().getDireccion().equals(direccion)){
				flag = false;
			}else{
				key++;
				recorridos++;
			}
		}
		if(recorridos == this.tama�oTabla){
			return false;
		}else{
			return true;
		}
	}
	/*
	public int devolverPosActual(double coordX, double coordY){
		int pos = h(coordX, coordY);
		boolean flag = true;
		int count = 0;
		while(flag){
			if(count < tabla.length){
				count += 1;
				if(tabla[pos] != null){
					if(tabla[pos].getCoordX() == coordX && tabla[pos].getCoordY() == coordY){
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
	*/
		
	public boolean esVacio(){
		boolean vacio = true;
		for(int i = 0; i < this.tabla.length; i++){
			if(this.tabla[i] != null){
				vacio = false;
			}
		}		
		return vacio;
	}
	
	
}
