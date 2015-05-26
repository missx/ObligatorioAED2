package estructuras;

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
	public void dequeue() {
		NodoLista aux = this.front;
		this.front = this.front.getSig();
		aux.setSig(null);
		aux = null; //ver si esto esta bien
		if(this.front == null){
			this.back = null;
		}
		
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
}
