package estructuras;

import dominio.Punto;

public class Hash {

	private Punto[] tabla;
	private int tamañoTabla;
	
	public Punto[] getTabla() {
		return tabla;
	}
	public void setTabla(Punto[] tabla) {
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
		this.tamañoTabla = tam + 1;
		this.tabla = new Punto[tam + 1];
	}
	
	/*
	 * Función de hash
	 */
	public int h(int nr){
		return nr%tamañoTabla;
	}
	
	/*
	 * Inserta un obj en la tabla
	 */
	public int insertar(Punto p){
		int key = (p.getCoordX().intValue() + p.getCoordY().intValue()) * -1;
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
	 * Chequea si el obj está en la tabla
	 */
	public boolean pertenece(double coordX, double coordY){
		int key = ((int)coordX + (int)coordY) * -1;
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
		int key = ((int)coordX + (int)coordY) * -1;
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
	
}
