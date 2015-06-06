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
		this.tamañoTabla = tam;
		this.tabla = new Punto[tam];
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
		int key = Math.abs(p.getCoordX().intValue()) + Math.abs(p.getCoordY().intValue());
		int pos = h(key);
		boolean flag = true;
		while(flag){
			if(tabla[pos] != null){
				if(!(tabla[pos].getCoordX().equals(0.0) && tabla[pos].getCoordY().equals(0.0))){
					pos += 1;
					if(pos == tabla.length){
						pos = 0;
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
		tabla[pos] = p;
		return pos;
	}
	
	/*
	 * Chequea si el obj está en la tabla
	 */
	public boolean pertenece(double coordX, double coordY){
		int key = Math.abs((int)coordX) + Math.abs((int)coordY);
		int pos = h(key);boolean flag = true;
		int count = 0;
		while(flag){
			if(count < tabla.length){
				count += 1;
				if(tabla[pos] != null){
					if(!(tabla[pos].getCoordX().equals(0.0) && tabla[pos].getCoordY().equals(0.0))){
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
				if(tabla[pos] != null || (tabla[pos].getCoordX() == 0.0 && tabla[pos].getCoordY() == 0.0)){
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
	
	public void eliminarPunto(int pos){
		tabla[pos] = new Punto(0.0,0.0);
	}
	

	public void imprimirLista(){
		for(int i = 0; i < tabla.length; i++){
			if(tabla[i] != null){
				System.out.println(i + " --> " + tabla[i].getCoordX() + " - " + tabla[i].getCoordY());
			}
		}
	}
	
}
