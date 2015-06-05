package estructuras;

import dominio.Propiedad;
import dominio.Punto;

public class HashPropiedad {
	
	private Propiedad[] tabla;
	private int tama�oTabla;
	
	public Propiedad[] getTabla() {
		return tabla;
	}
	public void setTabla(Propiedad[] tabla) {
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
		this.tabla = new Propiedad[tam];
	}
	
	/*
	 * Funci�n de hash
	 */
	public int h(int nr){
		return nr % tama�oTabla;
	}
	
	/*
	 * Inserta un obj en la tabla
	 */
	public int insertar(Propiedad p){
		int key = Math.abs(p.getCoordX().intValue()) + Math.abs(p.getCoordY().intValue());
		int pos = h(key);
		boolean flag = true;
		while(flag){
			if(tabla[pos] != null){
				pos += 1;
				if(pos == tabla.length){
					pos = 0;
				}
			}
			else{
				flag = false;
			}
		}
		tabla[pos] = p;
		System.out.println(pos);
		System.out.println(tabla[pos].getCoordX());
		return pos;
	}
	
	/*
	 * Chequea si el obj est� en la tabla
	 */
	public boolean pertenece(double coordX, double coordY){
		int key = Math.abs((int)coordX) + Math.abs((int)coordY);
		int pos = h(key);boolean flag = true;
		int count = 0;
		while(flag){
			if(count < tabla.length){
				count += 1;
				if(tabla[pos] != null){
					if(tabla[pos].getCoordX() == coordX && tabla[pos].getCoordY() == coordY){
						return true;
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
		return false;
	}
	
	public int devolverPosActual(double coordX, double coordY){
		int key = Math.abs((int)coordX) + Math.abs((int)coordY);
		int pos = h(key);boolean flag = true;
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
	
	public boolean existeDireccion(String dir){
		for(int i = 0; i < tabla.length; i++){
			if(tabla[i] != null){
				if(tabla[i].getDireccion() == dir){
					return true;
				}
			}
		}
		return false;
	}
	
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
