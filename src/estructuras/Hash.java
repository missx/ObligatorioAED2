package estructuras;

import sistema.Enumerados.Rubro;
import dominio.Punto;
import dominio.PuntoDeInteres;
import dominio.Vendedor;

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
	public int h(double coordX, double coordY){
		String str = String.valueOf(coordX) + String.valueOf(coordX);
		int nr = 0;
		for(int i = 0; i < str.length(); i++){
			nr += (int)str.charAt(i);
		}
		System.out.println("key " +nr%tamañoTabla);
		return nr%tamañoTabla;
	}
	
	/*
	 * Inserta un obj en la tabla
	 */
	public int insertar(Punto p){
		int pos = h(p.getCoordX(), p.getCoordY());
		boolean flag = true;
		while(flag){
			if(tabla[pos] != null){
				System.out.println("acuya");
				if(!(tabla[pos].getCoordX().equals(0.0) && tabla[pos].getCoordY().equals(0.0))){
					pos += 1;
					System.out.println("posicion ocupada " +pos);
					if(pos == tabla.length){
						pos = 0;
					}
				}
				else{
					flag = false;
					System.out.println("aqui");
				}
			}
			else{
				System.out.println("aca");
				flag = false;
			}
		}
		tabla[pos] = p;
		System.out.println("posicion real " + pos);
		return pos;
	}
	
	/*
	 * Chequea si el obj está en la tabla
	 */
	public boolean pertenece(double coordX, double coordY){
		int pos = h(coordX, coordY);
		boolean flag = true;
		int count = 0;
		while(flag){
			if(count < tabla.length){
				count += 1;
				if(tabla[pos] != null){
					if(!(tabla[pos].getCoordX().equals(0.0) && tabla[pos].getCoordY().equals(0.0))){
						if(tabla[pos].getCoordX() == coordX && tabla[pos].getCoordY() == coordY){
							return true;
						}else{
							pos += 1;
							if(pos == tabla.length){
								pos = 0;
							}
						}
					}else{
						pos += 1;
						if(pos == tabla.length){
							pos = 0;
						}
					}
				}else{
					flag = false;
				}
			}
		}
		return false;
	}
	
	public int devolverPosActual(double coordX, double coordY){
		int pos = h(coordX, coordY);
		boolean flag = true;
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
	
	/*
	 * Retorna true si al menos hay un objeto del rubro rubro
	 * en la tabla
	 * @param Rubro rubro
	 * @return boolean
	 */
	public boolean existePuntoDeEseRubro(Rubro rubro){
		for(Punto p : this.tabla){
			if(p instanceof PuntoDeInteres){
				if(((PuntoDeInteres) p).getRubro().equals(rubro)){
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * Devuelve las keys de los puntos que son rubro
	 * @param Rubro rubro
	 * @return Lista
	 */
	public Lista devolverKeysDeRubro(Rubro rubro){
		Lista keys = new Lista();
		int recorridos = 1;
		int key = 0;
		while(recorridos <= tamañoTabla){			
			if(this.tabla[key] != null && this.tabla[key] instanceof PuntoDeInteres &&
					((PuntoDeInteres)this.tabla[key]).getRubro().equals(rubro)){
				//agregamos al vector
				keys.insertarInicio(key);
				key++;
				recorridos++;
			}else{
				key++;
				recorridos++;
				if(key == this.tamañoTabla){
					key = 0;
				}
			}
		}	
		return keys;
	}
	
	/*
	 * Devuelve en un string las coordenadas de las keys que se 
	 * pasan en el vector
	 * @param int[] vector
	 * @return String
	 */
	public String mostrarCoordsDeKeysEnVector(int[] vector){
		String resultado = "";
		for(int i = 0; i < vector.length; i++){
			System.out.println("key a buscar" +vector[i]);
			resultado += this.tabla[vector[i]].getCoordX() + ";" + this.tabla[vector[i]].getCoordY() + "|";
			if(vector[i + 1] == 0){
				break;
			}
		}
		return resultado;
	}
	
}
