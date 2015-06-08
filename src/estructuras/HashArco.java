package estructuras;

import dominio.Propiedad;

public class HashArco {
	
	private NodoHashArco[] tabla;
	private int tama�oTabla;
	
	public NodoHashArco[] getTabla() {
		return tabla;
	}
	public void setTabla(NodoHashArco[] tabla) {
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
	public HashArco(int tam){
		this.tama�oTabla = tam;
		this.tabla = new NodoHashArco[tam];
		for(int i = 0; i < tabla.length; i++){
			this.tabla[i] = new NodoHashArco();
		}
	}
	
	/*
	 * Funci�n de hash
	 */
	public int h(Arco arco){
		int nr = (int) (arco.getCoordXi() + arco.getCoordYi() + arco.getCoordXf() + arco.getCoordYf());
		System.out.println("key elem " + nr % tama�oTabla);
		return nr % tama�oTabla;
	}
	
	/*
	 * Inserta un obj en la tabla
	 */
	public boolean insertar(Arco arcoHijo, Arco arcoPadre){
		int key = h(arcoHijo);
		int recorridos = 0;
		boolean flag = true;
		while(flag && recorridos < this.tama�oTabla){
			if(tabla[key].getDato() == null){ //usa esta pos
				tabla[key] = new NodoHashArco(arcoHijo, arcoPadre);
				System.out.println("inserto la propiedad ");
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
	public boolean pertenece(Arco arco){
		
		int key = h(arco);
		boolean flag = true;
		int recorridos = 1;
		while(flag && recorridos < this.tama�oTabla){			
			if(this.tabla[key].getDato() != null && this.tabla[key].getDato().equals(arco)){
				flag = false;
			}else{
				key++;
				recorridos++;
				if(key == this.tama�oTabla){
					key = 0;
				}
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
			if(this.tabla[i].getDato() != null){
				vacio = false;
			}
		}	
		System.out.println("es vacio " +vacio);
		return vacio;
	}
}
