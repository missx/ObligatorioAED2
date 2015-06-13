package estructuras;

import dominio.Vendedor;
import interfaces.IQueue;

public class Queue implements IQueue{
	
	private NodoLista front;
	private NodoLista back;
	
	public Queue(){
		this.front = null;
		this.back = null;
	}
	
	@Override
	public void enqueue(Object elem) {
		NodoLista nuevo = new NodoLista(elem);
		if(this.front == null){
			this.back = nuevo;
			this.front = this.back;
		}else{
			this.back.setSig(nuevo);
			this.back = this.back.getSig();
		}
	}

	@Override
	public Object dequeue() {
		NodoLista aux = this.front;
		this.front = this.front.getSig();
		aux.setSig(null);
		if(this.front == null){
			this.back = null;
		}
		return aux;
	}

	@Override
	public Object front() {
		return this.front.getDato();
	}

	@Override
	public boolean isEmpty() {
		return this.front == null;
	}

	@Override
	public boolean isFull() {
		if(this.front == null){
			return false;
		}
		return true;
	}

	public void borrarElemento(Vendedor v) {
		if(!this.isEmpty()){
			if(this.front.getDato().equals(v))
				this.dequeue();
			else{
				NodoLista aux = this.front;
				while(aux.getSig() != null && !aux.getSig().getDato().equals(v))
					aux = aux.getSig();
				if(aux.getSig() != null){
					System.out.println("aux getsig");
					NodoLista aBorrar = aux.getSig();
					aux.setSig(aBorrar.getSig());
					aBorrar.setSig(null);
				}
				
			}
		}
	}
	
	/*
	 * Devuelve la cantidad de nodos de la queue
	 */
	public int cantNodos(){
		int cont = 0;
		
		if(!this.isEmpty()){
			NodoLista aux = this.front;
			while(aux!= null){
				cont++;
				aux = aux.getSig();
			}
		}
		return cont;
	}
}
